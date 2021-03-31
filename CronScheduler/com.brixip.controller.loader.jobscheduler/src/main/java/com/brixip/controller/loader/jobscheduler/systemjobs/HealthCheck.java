package com.brixip.controller.loader.jobscheduler.systemjobs;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.brixip.buss.loader.systeminformation.service.SystemInfo;
import com.brixip.buss.loader.systeminformation.service.SystemInfoException;
import com.brixip.buss.loader.systeminformation.service.bean.HardDiskMemoryBean;
import com.brixip.buss.loader.systeminformation.service.bean.JavaInfoBean;
import com.brixip.core.email.service.EmailService;
import com.brixip.core.email.service.Mail;
import com.brixip.core.jobhistory.JobHistory;
import com.brixip.core.jobhistorymanager.JobHistoryManager;
import com.brixip.core.jobinformationmanager.JobInformationManager;
import com.brixip.core.library.FilterGroup;
import com.brixip.core.library.NameValueUnit;
import com.brixip.core.library.enums.EnumOperator;
import com.brixip.core.util.BrixCoreUtil;
import com.brixip.core.util.NameValueConvertUtility;
import com.pvmsys.brix.buss.moduleconfig.reader.service.ModuleConfigReaderService;
import com.pvmsys.brix.buss.moduleconfig.reader.service.PropertyNotFoundException;
import com.pvmsys.brix.buss.user.authentication.service.EnumErrorCode;
import com.pvmsys.brix.buss.user.authentication.service.EnumUserAuthenticationType;
import com.pvmsys.brix.buss.user.authentication.service.LdapException;
import com.pvmsys.brix.buss.user.authentication.service.RiseException;
import com.pvmsys.brix.buss.user.authentication.service.UserAuthentication;
import com.pvmsys.brix.buss.user.authentication.service.UserAuthenticationException;
import com.pvmsys.brix.core.common.library.ServiceLogger;
import com.pvmsys.brix.core.session.service.LoginSession;
import com.sun.management.OperatingSystemMXBean;

/********
 * Health Check Job Implementation
 * @author Pratiksha.Datir
 *
 */
public class HealthCheck implements Job{
	private String MESSAGE;
	private String SUBJECT;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("*****************Health Check Job Started*****************");
		updateNextFireTime(arg0);
		garbageCollector(arg0.getJobDetail().getKey().toString());
	}

	/***
	 * This method is used to update next fire time of job after its execution.
	 * @param context
	 */
	private void updateNextFireTime(JobExecutionContext context) {
		try{
			LoginSession loginSession = getAdminSession();
			if(loginSession!=null){
				loginSession.execute(new Callable<List<String>>() {
					JobInformationManager jobInformationManager = null;
					JobHistoryManager jobHistoryManager = null;
					@Override
					public List<String> call() throws Exception {
						jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
						jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
						if(jobHistoryManager!=null && jobInformationManager!=null){
							FilterGroup filterGroup = new FilterGroup().addFilter("jobInformation.jobkey", context.getJobDetail().getKey().toString(), EnumOperator.EQ);
							filterGroup.orderBy("-id");
							List<JobHistory> ls = jobHistoryManager.filterJobHistory(filterGroup);
							List<NameValueUnit> list = new ArrayList<>();
							list.add(new NameValueUnit("nextExecutionDate",context.getNextFireTime()));
							jobHistoryManager.updateJobHistory(ls.get(0).getId(), list);
						}
						return null;
					}
				});
			}
		}catch(Exception e){
			ServiceLogger.error("Problem while updating job history instance after job execution:"+e.getMessage()+":"+e.getCause());
		}
	}

	/********
	 * This method is used to run garbage collector.
	 */
	private void garbageCollector(String jobKey){
		SystemInfo systemInfo = null; 
		try {
			systemInfo = BrixCoreUtil.getService(SystemInfo.class);
			if(systemInfo!=null)
				systemInfo.runGC();
			sendAnEmail(jobKey);
		} catch (SystemInfoException e) {
			ServiceLogger.error(e);
		}
	}

	/****
	 * This method is used to send an email to the users having administrator system role.
	 */
	private void sendAnEmail(String jobKey) {
		SystemInfo systemInfo = null; 
		EmailService emailService = null;
		List<String> mailList = new ArrayList<>();
		String hardDiskInfoString = " ";
		try{
			systemInfo = BrixCoreUtil.getService(SystemInfo.class);
			emailService = BrixCoreUtil.getService(EmailService.class);
			if(systemInfo!=null){
				JavaInfoBean bean = systemInfo.getJavaMemoryStatistics();
				bean.getFreeMemory();
				bean.getNonHeapMemory();
				bean.getTotalMemory();
				bean.getUsedMemory();
				mailList = getEmailList(jobKey);
			//String cpuMemoryStatistic = getCPUMemoryStatistic();
				List<HardDiskMemoryBean> hardDiskInfoBean = systemInfo.getHardDiskDetails();
				for(HardDiskMemoryBean hardDiskBean :hardDiskInfoBean){
					hardDiskInfoString = hardDiskInfoString +
							"<table style='width: 100%; height: 38px; border-collapse: collapse;' border='1'>"
							+"<tr style='height: 19px;'>"
							+ "<th style='width: 33.33%; height: 19px; text-align: center;'>File System Root</th> "
							+ "<th style='width: 33.33%; height: 19px; text-align: center;'>"+hardDiskBean.getFileSystemRoot()+"</th> </tr>"+
							"<tr style='height: 19px;'>"
							+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Total Memory</td>"
							+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+hardDiskBean.getTotalSpace()+"</td></tr>"+
							"<tr style='height: 19px;'>"
							+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Used Memory</td>"
							+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+hardDiskBean.getUsableSpace()+"</td></tr>"+
							"<tr style='height: 19px;'>"
							+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Free Memory</td>"
							+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+hardDiskBean.getFreeSpace()+"</td></tr>"+
							"<tr style='height: 19px;'></table>";
				}
				
				MESSAGE = "<p>Dear Admin,<br>Your System Information is as follows:<br><b>JVM Memory Statistics :</b><br>"+
						"<table style='width: 100%; height: 38px; border-collapse: collapse;' border='1'>"+
						"<tr style='height: 19px;'>"
						+ "<th style='width: 33.33%; height: 19px; text-align: center;'>Title</th> "
						+ "<th style='width: 33.33%; height: 19px; text-align: center;'>Memory</th> </tr>"+
						"<tr style='height: 19px;'>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Total Memory</td>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+bean.getTotalMemory()+"</td></tr>"+
						"<tr style='height: 19px;'>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Used Memory</td>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+bean.getUsedMemory()+"</td></tr>"+
						"<tr style='height: 19px;'>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Free Memory</td>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+bean.getFreeMemory()+"</td></tr>"+
						"<tr style='height: 19px;'>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Non Heap Memory</td>"
						+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+bean.getNonHeapMemory()+"</td></tr></table>";
				
				MESSAGE = MESSAGE + "<br><b>Disk Memory Statistics :</b><br><br>"
						+ hardDiskInfoString 
						+"</p>";
				
				//MESSAGE = MESSAGE + "<br><b>CPU Memory Statistics :</b><br><br>" + cpuMemoryStatistic + "</p>";
				
				SUBJECT = "Regarding System Information.";
			}
			if(emailService!=null){
				Mail mail = new Mail();
				mail.setMsg(MESSAGE);
				mail.setSubject(SUBJECT);
				mail.setToUser(mailList);
				emailService.send(mail);
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
	}

	/*************
	 * This method is used to get CPU memory statistic.
	 * @return CPU memory statistic in HTML format.
	 * @author Pratiksha.Datir
	 */
	private String getCPUMemoryStatistic(){
		String cpuMemoryInfo = "";
		OperatingSystemMXBean osBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		cpuMemoryInfo = "<table style='width: 100%; height: 38px; border-collapse: collapse;' border='1'>"
				+"<tr style='height: 19px;'>"
				+ "<th style='width: 33.33%; height: 19px; text-align: center;'>File System Root</th> "
				+ "<th style='width: 33.33%; height: 19px; text-align: center;'>"+osBean.getTotalPhysicalMemorySize()+"</th> </tr>"+
				"<tr style='height: 19px;'>"
				+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Total Memory</td>"
				+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+osBean.getFreePhysicalMemorySize()+"</td></tr>"+
				"<tr style='height: 19px;'>"
				+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Used Memory</td>"
				+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+osBean.getProcessCpuLoad()+"</td></tr>"+
				"<tr style='height: 19px;'>"
				+ "<td style='width: 33.33%; height: 19px; text-align: center;'>Used Memory</td>"
				+ "<td style='width: 33.33%; height: 19px; text-align: center;'>"+""+"</td></tr>"+
				"</table>";
		return cpuMemoryInfo;
	}
	
	/**************
	 * This method is used to get email list of users having administrator system role.
	 * @return {@link List}
	 */
	private List<String> getEmailList(String jobKey) {
		List<String> emailList = new ArrayList<>();
		try{
			LoginSession loginSession = getAdminSession();
			if(loginSession!=null){
				emailList = loginSession.execute(new Callable<List<String>>() {
					@Override
					public List<String> call() throws Exception {
						String[] array = null;
						List<String> list = new ArrayList<>();
						JobInformationManager jobInformationManager = null;
						try{
							jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
							if(jobInformationManager!=null){
								FilterGroup filterGroup = new FilterGroup().addFilter("jobkey", jobKey, EnumOperator.EQ);
								List<List<NameValueUnit>> ls = jobInformationManager.filterJobInformation(filterGroup,
										new String[] { "jobParameters" });
								if(ls!=null && !ls.isEmpty()){
									for (List<NameValueUnit> nameValueUnits : ls) {
										NameValueUnit nameValueUnit = 
												nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("jobParameters")).findFirst().orElse(null);
										if(nameValueUnit!=null){
											List<NameValueUnit> nameValuelist = 
													NameValueConvertUtility.convertStringToListNameValue((String)nameValueUnit.getValue());
											if(nameValuelist!=null && !nameValuelist.isEmpty()){
												NameValueUnit nameValue = 
														nameValuelist.stream().filter(x->x.getName().equalsIgnoreCase("email")).findFirst().orElse(null);
												if(nameValue!=null){
													array = ((String)nameValue.getValue()).split(",");
												}
											}
											list = Arrays.asList(array);
										}
									}
								}
							}
						}catch(Exception e){
							ServiceLogger.error(e);
						}
						return list;
					}

				});
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
		return emailList;
	}

	/************
	 * This method is used to get email id list.
	 * @return {@link LoginSession}
	 * @throws UserAuthenticationException
	 * @throws LdapException
	 */
	private LoginSession getAdminSession() throws UserAuthenticationException, LdapException {
		LoginSession obj_Session = null;
		ModuleConfigReaderService moduleConfiguration = BrixCoreUtil.getService(ModuleConfigReaderService.class);
		UserAuthentication userAuthentication = BrixCoreUtil.getService(UserAuthentication.class);
		try {
			if (userAuthentication != null) {
				String username;
				username = (String) moduleConfiguration.getPropertyValue(String.class, "UserName");
				String defaultPwd = (String) moduleConfiguration.getPropertyValue(String.class, "Password");
				obj_Session = userAuthentication.authenticateUser(username, defaultPwd,
						EnumUserAuthenticationType.INTERNAL_CLIENT);
			}
		} catch (PropertyNotFoundException e) {
			throw new UserAuthenticationException(e.getMessage(), EnumErrorCode.GENERAL);
		} catch (RiseException e) {
			throw new UserAuthenticationException(e.getMessage(), EnumErrorCode.GENERAL);
		}
		return obj_Session;
	}
}