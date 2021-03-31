package com.brixip.controller.loader.jobscheduler.systemjobs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.io.FilenameUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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

/*************
 * 
 * @author Pratiksha.Datir
 *
 */
public class FileCleanup implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("*****************File Cleanup Job Started*****************");
		try {
			updateNextFireTime(arg0);
			getFileCleanupParameters(arg0.getJobDetail().getKey().toString());
		} catch (UserAuthenticationException | LdapException e) {
			ServiceLogger.error(e);
		}
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
	
	
	/*********
	 * This method is used to get file cleanup parameters such as path and no of days to delete files.
	 * @return {@link Boolean}
	 * @throws UserAuthenticationException
	 * @throws LdapException
	 */
	private Boolean getFileCleanupParameters(String jobkey) throws UserAuthenticationException, LdapException {
		Boolean result = null;
		try{
			LoginSession loginSession = getAdminSession();
			if(loginSession!=null){
				result = loginSession.execute(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						Boolean result = null;
						String path = null;
						String noofdays = null;
						String extension = null;
						JobInformationManager jobInformationManager = null;
						try{
							jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
							if(jobInformationManager!=null){
								FilterGroup filterGroup = new FilterGroup().addFilter("jobkey",jobkey, EnumOperator.EQ);
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
												NameValueUnit pathNameValue = 
														nameValuelist.stream().filter(x->x.getName().equalsIgnoreCase("path")).findFirst().orElse(null);
												NameValueUnit noofdaysNameValue = 
														nameValuelist.stream().filter(x->x.getName().equalsIgnoreCase("noofdays")).findFirst().orElse(null);
												NameValueUnit fileExtensionNameValue = 
														nameValuelist.stream().filter(x->x.getName().equalsIgnoreCase("fileExtension")).findFirst().orElse(null);
												if(pathNameValue!=null && noofdaysNameValue!=null && fileExtensionNameValue!=null){
													path = (String)pathNameValue.getValue();
													noofdays = (String) noofdaysNameValue.getValue();
													extension = (String) fileExtensionNameValue.getValue();
													result = getFilesListAndDoCleanup(path, noofdays, extension);
												}
											}
										}
									}
								}
							}
						}catch(Exception e){
							ServiceLogger.error(e);
						}
						return result;
					}
				});
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
		return result;
	}

	/************
	 * This method is used to list and delete files for file cleanup purpose.
	 * @param path
	 * @param noofdays
	 * @return {@link Boolean}
	 */
	protected Boolean getFilesListAndDoCleanup(String path, String noofdays, String extension) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Boolean result = false;
		try{
			path = path.replace("''", "/");
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			int beforeDays = Integer.parseInt(noofdays);

			cal.add(Calendar.DAY_OF_MONTH, -beforeDays);
			Date beforeDate = cal.getTime();

			File folder = new File(path);
			if(folder.listFiles() != null){
				for(File fileEntry : folder.listFiles()){
					String fileDate = dateFormat.format(fileEntry.lastModified());
					Date date = dateFormat.parse(fileDate);
					if((date.compareTo(today)<=0) && (date.compareTo(beforeDate)>=0)){
						String fileextension = FilenameUtils.getExtension(fileEntry.getName());
						if(fileextension.equalsIgnoreCase(extension)){
							fileEntry.getAbsoluteFile().delete();
							result = true;
						}
					}
				}
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
		return result;
	}

	/************
	 * This method is used to get admin session.
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