package com.brixip.core.jobscheduler.job.jobscheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.brixip.core.jobhistorymanager.JobHistoryManager;
import com.brixip.core.jobinformationmanager.JobInformationManager;
import com.brixip.core.jobscheduler.job.service.JobService;
import com.brixip.core.library.FilterGroup;
import com.brixip.core.library.NameValueUnit;
import com.brixip.core.library.enums.EnumOperator;
import com.brixip.core.util.BrixCoreUtil;
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


/*********************************************************************************************************************************************************************
 * As scheduler save all jobs in RAM, After restarting server all jobs details will get lost. So all are needs to be reschedule again so that,                       *
 * it cannot affect our system.                                                                                                                                      *
 * so, This class is used to schedule all jobs which are in running status. In it, all jobs having running status are filtered and call create method of scheduler.  *
 * @author Pratiksha.Datir                                                                                                                                           *
 *********************************************************************************************************************************************************************/
 
public class RescheduleJobs implements Runnable{

	public static void rescheduleJobs(){
		JobService jobService = null;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			if(jobService!=null){
				List<List<NameValueUnit>> list = getJobList();
				if(list!=null && !list.isEmpty()){
					for (List<NameValueUnit> ls: list) {
						NameValueUnit jobStatusNameValue = ls.stream().filter(x->x.getName().equalsIgnoreCase("jobstatus")).findFirst().orElse(null);
						if(jobStatusNameValue!=null){
							jobService.createJob(ls, false);
							if(jobStatusNameValue.getValue().toString().equalsIgnoreCase("PAUSE")){
								NameValueUnit jobkeyNameValue = ls.stream().filter(x->x.getName().equalsIgnoreCase("jobkey")).findFirst().orElse(null);
								jobService.pauseJob(jobkeyNameValue.getValue().toString());
							}
						}
					}
				}
			}else{
				Thread.currentThread();
				Thread.sleep(1000);
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
	}

	/**********
	 * This method is used to get all jobs having running status.
	 * @return {@link List}
	 * @author Pratiksha.Datir
	 */
	protected static List<List<NameValueUnit>> getJobList() {
		List<List<NameValueUnit>> list = new ArrayList<>();
		LoginSession loginSession = null;
		try{
			loginSession = getAdminSession();
			if(loginSession!=null){
				list = loginSession.execute(new Callable<List<List<NameValueUnit>>>() {
					@Override
					public List<List<NameValueUnit>> call() throws Exception {
						FilterGroup filterGroup = new FilterGroup();
						JobHistoryManager jobHistoryManager = null;
						JobInformationManager jobInformationManager = null;
						List<List<NameValueUnit>> list = new ArrayList<>();
						String jobInfoId = null;
						jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
						jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
						
						List<List<NameValueUnit>> ls = jobInformationManager.filterJobInformation(filterGroup,new String[]{"name","description","jobClassName","jobJarName","jobGroupName","cronExpression","jobkey"});
						for (List<NameValueUnit> list2 : ls) {
							NameValueUnit keyNameValue = list2.stream().filter(x->x.getName().equalsIgnoreCase("jobInformationKey")).findFirst().orElse(null);
							if(keyNameValue!=null)
								jobInfoId = keyNameValue.getValue().toString();
							NameValueUnit jobkeyNameValue = list2.stream().filter(x->x.getName().equalsIgnoreCase("jobkey")).findFirst().orElse(null);
							if(jobkeyNameValue!=null){
								String jobKey = (String) jobkeyNameValue.getValue();
								String arr[];
								arr = jobKey.split("\\.");
								if(arr.length > 0)
									jobKey = arr[1];
								list2.remove(jobkeyNameValue);
								list2.add(new NameValueUnit("jobkey", jobKey));
							}
							FilterGroup filterGroup1 = new FilterGroup();
							filterGroup1.addFilter("jobInformation", BrixCoreUtil.getId(jobInfoId), EnumOperator.EQ).orderBy("-id");;
							List<List<NameValueUnit>> ls2 = jobHistoryManager.filterJobHistory(filterGroup1, new String[]{"triggerTime","jobstatus"});
							list2.addAll(ls2.get(0));
							list.add(list2);
						}
						return list;
					}
				});
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
		return list;
	}
	
	/******
	 * Get admin session to get list.
	 * @return LoginSession
	 * @throws UserAuthenticationException
	 * @throws LdapException
	 * @author Pratiksha.Datir
	 */
	private static LoginSession getAdminSession() throws UserAuthenticationException ,LdapException{
		LoginSession obj_Session = null;
		ModuleConfigReaderService moduleConfiguration = BrixCoreUtil.getService(ModuleConfigReaderService.class);
		UserAuthentication userAuthentication = BrixCoreUtil.getService(UserAuthentication.class);
		try {
			if (userAuthentication != null) {
				String username;
				username = (String) moduleConfiguration.getPropertyValue(String.class, "UserName");
				String defaultPwd = (String) moduleConfiguration.getPropertyValue(String.class, "Password");
				obj_Session = userAuthentication.authenticateUser(username, defaultPwd,EnumUserAuthenticationType.INTERNAL_CLIENT);
			}
		} catch (PropertyNotFoundException e) {
			throw new UserAuthenticationException(e.getMessage(),EnumErrorCode.GENERAL);
		} catch (RiseException e) {
			throw new UserAuthenticationException(e.getMessage(),EnumErrorCode.GENERAL);
		} 
		return obj_Session;
	}

	@Override
	public void run() {
		rescheduleJobs();
	}
}