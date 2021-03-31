package com.brixip.controller.loader.jobscheduler.systemjobs;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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

/***************
 * This class is used to Check Rest Connection Job.
 * @author Pratiksha.Datir
 */
public class CheckRestConnection implements Job{
	private static String Message;
	private static String Subject = "Regarding Rest Connection Check Status.";
	private static String urlString;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("***************Check Rest Connection Job Started**************");
		updateNextFireTime(arg0);
		checkRestConnection(arg0.getJobDetail().getKey().toString());
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

	/**********
	 * This method is used to check rest connection.  
	 * @author Pratiksha.Datir
	 */
	private void checkRestConnection(String jobKey) {
		List<String> mailList = getEmailList(jobKey);
		EmailService emailService = null;
		try{
			emailService = BrixCoreUtil.getService(EmailService.class);
			if(emailService!=null){
				if(mailList!=null && !mailList.isEmpty()){
					getServerStatus();
					Mail mail = new Mail();
					mail.setMsg(Message);
					mail.setSubject(Subject);
					mail.setToUser(mailList);
					emailService.send(mail);
				}
			}
		}catch(Exception e){
			ServiceLogger.error("Problem while sending mail for check rest connection job :"+e.getMessage()+":"+e.getCause());
		}
	}

	/*********
	 * This method is used to form server status message.
	 * @author Pratiksha.Datir
	 */
	private void getServerStatus() {
		System.out.println();
		try {
			if(urlString!=null){
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type", "application/json;charset=SJIS");
				conn.setRequestProperty("Accept", "application/json");
				Message="<html><p>Dear Admin,</p>";
				if(conn.getResponseCode() == 200){
					Message = Message + "<p>The request has succeeded.<br>Server Status - OK."
							+ "</p></html>";
				}else if(conn.getResponseCode() == 400){
					Message = Message + "<p>The server cannot or will not process the request due to something "
							+"that is perceived to be a client error."
							+"<br>Server Status - The server did not understand the request.</p></html>";
				}else if(conn.getResponseCode() == 403){
					Message = Message + "<p>The client does not have access rights to the content; that is, "
							+ "it is unauthorized, so the server is refusing to give the requested resource."
							+ "<br>Server Status - Access is forbidden to the requested page."
							+ "</p></html>";
				}else if(conn.getResponseCode() == 404){
					Message = Message + "<p>The server can not find the requested resource. Either check your URL is correct or not,"
							+ "or Requested resource does not exists."
							+ "<br>Server Status - The server can not find the requested page."
							+ "</p></html>";
				}else if(conn.getResponseCode() == 414){
					Message = Message + "<p>The URI requested is longer than the server is willing to interpret."
							+ "<br>Server Status is The server will not accept the request, "
							+ "because the url is too long. "
							+ "Occurs when you convert 'post' request to 'get' request with a long query information ."
							+ "</p></html>";
				}else if(conn.getResponseCode() == 301){
					Message = Message + "<p>The URL of the requested resource has been changed permanently.<br>"
							+ "Server Status - The requested page has moved to a new url .."
							+ "</p></html>";
				}else if(conn.getResponseCode() == 302){
					Message = Message + "<p>The URI of requested resource has been changed temporarily. "
							+ "Further changes in the URI might be made in the future. "
							+ "Therefore, this same URI should be used in future requests."
							+ "<br>Server Status - The requested page has moved temporarily to a new url."
							+ "</p></html>";
				}else if(conn.getResponseCode() == 500){
					Message = Message + "<p>The server has encountered a situation it doesn't know how to handle."
							+ "<br>Server Status - The request was not completed. The server met an unexpected condition."
							+ "</p></html>";
				}
			}
		} catch (Exception e) {
			Message = Message + "<p>The server has stopped."
					+ "<br>Server Status - The request was not completed. Server is unavailable right now."
					+ "</p></html>";
			ServiceLogger.error("Problem while generating server status message :"+e.getMessage()+":"+e.getCause());
		}
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
								FilterGroup filterGroup = new FilterGroup().addFilter("jobkey",jobKey, EnumOperator.EQ);
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
												NameValueUnit urlNameValue = 
														nameValuelist.stream().filter(x->x.getName().equalsIgnoreCase("url")).findFirst().orElse(null);
												if(urlNameValue!=null){
													urlString = (String)urlNameValue.getValue();
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
