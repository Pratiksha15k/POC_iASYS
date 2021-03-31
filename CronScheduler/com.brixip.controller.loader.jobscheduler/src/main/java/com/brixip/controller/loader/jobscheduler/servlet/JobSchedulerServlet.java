package com.brixip.controller.loader.jobscheduler.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.quartz.JobKey;
import com.brixIP.brixloader.buss.brixloader.flow.library.common.ResultRO;
import com.brixip.core.jobhistory.JobHistory;
import com.brixip.core.jobhistorymanager.JobHistoryManager;
import com.brixip.core.jobinformation.JobInformation;
import com.brixip.core.jobinformationmanager.JobInformationManager;
import com.brixip.core.jobscheduler.job.enums.JobStatus;
import com.brixip.core.jobscheduler.job.jobscheduler.GenerateCronExpression;
import com.brixip.core.jobscheduler.job.service.JobService;
import com.brixip.core.jobscheduler.mapper.Jobclass;
import com.brixip.core.library.FilterGroup;
import com.brixip.core.library.NameValueUnit;
import com.brixip.core.library.enums.EnumOperator;
import com.brixip.core.usermanager.UserManager;
import com.brixip.core.util.BrixCoreUtil;
import com.brixip.core.util.NameValueConvertUtility;
import com.pvmsys.brix.core.common.library.ServiceLogger;
import com.pvmsys.brix.core.session.service.LoginSession;
import net.sf.json.JSONObject;

/************
 * Servlet used to write quartz scheduler implementation.
 * @author Pratiksha.Datir
 *
 */
public class JobSchedulerServlet extends BaseServlet implements EnumerationIF{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req ,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession httpSession = req.getSession();
		PrintWriter responseWriter = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try{
			String sessionKey = (String) req.getParameter("sessionKey");
			final LoginSession loginSession = getLoginSession(sessionKey, httpSession);
			if (loginSession == null) {
				resp.setStatus(403);
			} else {
				String requestPath = req.getServletPath();
				switch(requestPath){
				case "/createJob":{
					String formdata = req.getParameter("formData");
					final List<NameValueUnit> jobnamevalue = NameValueConvertUtility.convertStringToListNameValue(formdata);
					ResultRO<Map<String, Object>> resultRO = loginSession
							.execute(new Callable<ResultRO<Map<String, Object>>>() {
								@Override
								public ResultRO<Map<String, Object>> call() throws Exception {
									return doCreateJob(jobnamevalue);
								}
							});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/updateJob":{
					String formdata = req.getParameter("formData");
					final String jobKey = req.getParameter("jobKey");
					final String jobId = req.getParameter("jobId"); 
					final List<NameValueUnit> jobNameValue = NameValueConvertUtility.convertStringToListNameValue(formdata);
					ResultRO<Map<String,Object>>  resultRO = loginSession.execute(new Callable<ResultRO<Map<String,Object>>>() {
						@Override
						public ResultRO<Map<String, Object>> call() throws Exception {
							return doUpdateJob(jobNameValue, jobKey, jobId);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/deleteJob":{
					String jobKey = req.getParameter("jobKey");
					String jobid = req.getParameter("jobId");
					ResultRO<Boolean> resultRO = loginSession.execute(new Callable<ResultRO<Boolean>>() {
						@Override
						public ResultRO<Boolean> call() throws Exception {
							return doDeleteJob(jobKey,jobid);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/pauseJob":{
					String formData = req.getParameter("formData");
					List<NameValueUnit> nameValueUnit = NameValueConvertUtility.convertStringToListNameValue(formData);
					ResultRO<Boolean> resultRO = loginSession.execute(new Callable<ResultRO<Boolean>>() {
						@Override
						public ResultRO<Boolean> call() throws Exception {
							return doPauseJob(nameValueUnit);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/resumeJob":{
					String formData = req.getParameter("formData");
					List<NameValueUnit> nameValueUnit = NameValueConvertUtility.convertStringToListNameValue(formData);
					ResultRO<Boolean> resultRO = loginSession.execute(new Callable<ResultRO<Boolean>>() {
						@Override
						public ResultRO<Boolean> call() throws Exception {
							return doResumeJob(nameValueUnit);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/getJobsManagementList":{
					ResultRO<List<Map<String, Object>>> resultRO = loginSession.execute(new Callable<ResultRO<List<Map<String, Object>>>>() {
						@Override
						public ResultRO<List<Map<String, Object>>> call() throws Exception {
							return doGetJobManagementList();
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/getJobsHistoryList":{
					boolean flag =	Boolean.parseBoolean(req.getParameter("rangeFlag"));
					String startDate = req.getParameter("startDate");
					String endDate = req.getParameter("endDate");
					ResultRO<List<Map<String, Object>>> resultRO = loginSession.execute(new Callable<ResultRO<List<Map<String, Object>>>>() {
						@Override
						public ResultRO<List<Map<String, Object>>> call() throws Exception {
							return doGetJobHistoryList(flag, startDate, endDate);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/getJobsMonitorList":{
					ResultRO<List<Map<String, Object>>> resultRO = loginSession.execute(new Callable<ResultRO<List<Map<String, Object>>>>() {
						@Override
						public ResultRO<List<Map<String, Object>>> call() throws Exception {
							return doGetJobMonitorList();
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/checkCronExpression" : {
					String cronExpression = req.getParameter("cronExp");
					ResultRO<Boolean> resultRO = loginSession.execute(new Callable<ResultRO<Boolean>>() {
						@Override
						public ResultRO<Boolean> call() throws Exception {
							return doCheckCronExpression(cronExpression);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/getJobsList" : {
					int value = Integer.parseInt(req.getParameter("rangeFlag"));
					ResultRO<List<String>> resultRO = loginSession.execute(new Callable<ResultRO<List<String>>>() {
						@Override
						public ResultRO<List<String>> call() throws Exception {
							return doGetJobsList(value);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/getEmailIdList" : {
					ResultRO<List<String>> resultRO = loginSession.execute(new Callable<ResultRO<List<String>>>() {
						@Override
						public ResultRO<List<String>> call() throws Exception {
							return doGetEmailIdList();
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/generateCronExpression" : {
					String formData = req.getParameter("formData");
					List<NameValueUnit> nameValueUnit = NameValueConvertUtility.convertStringToListNameValue(formData);
					String selectedTab = req.getParameter("selectedTab");
					ResultRO<String> resultRO = loginSession.execute(new Callable<ResultRO<String>>() {
						@Override
						public ResultRO<String> call() throws Exception {
							return doGenerateCronExpression(nameValueUnit, selectedTab);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				case "/checkJobResumeOrPause" : {
					String jobKey = req.getParameter("jobKey");
					String jobid = req.getParameter("jobId");
					ResultRO<Map<String, Object>> resultRO = loginSession.execute(new Callable<ResultRO<Map<String, Object>>>() {
						@Override
						public ResultRO<Map<String, Object>> call() throws Exception {
							return doCheckJobResumeOrPause(jobKey,jobid);
						}
					});
					jsonObject = JSONObject.fromObject(resultRO);
					break;
				}
				}
			}
			responseWriter.println(jsonObject.toString());
			responseWriter.close();
		}catch(Exception e){

		}
	}

	/*************
	 * This method is used to check job status of jobs.
	 * @param jobKey
	 * @param jobid
	 * @return {@link ResultRO}
	 */
	protected ResultRO<Map<String,Object>> doCheckJobResumeOrPause(String jobKey, String jobid) {
		ResultRO<Map<String,Object>> resultRO = new ResultRO<>();
		Map<String,Object> map = new HashMap<>();
		FilterGroup filterGroup = new FilterGroup();
		String jobStatus = null;
		JobHistoryManager jobHistoryManager = null;
		try{
			jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
			if(jobHistoryManager!=null){
				filterGroup.addFilter("jobInformation.id", BrixCoreUtil.getId(jobid), EnumOperator.EQ).orderBy("-id");;
				List<List<NameValueUnit>> ls = jobHistoryManager.filterJobHistory(filterGroup, new String[]{"jobstatus"});
				if(ls!=null && !ls.isEmpty()){
					List<NameValueUnit> list = ls.get(0);
					NameValueUnit statusNamevalue = list.stream().filter(x->x.getName().equalsIgnoreCase("jobstatus")).findFirst().orElse(null);
					if(statusNamevalue!=null){
						jobStatus = (String)statusNamevalue.getValue();
						map.put("jobStatus", jobStatus);
						resultRO.setResult(map);
						resultRO.setErrorCode(I_SUCCESS);
						resultRO.setMessage("Set JobStatus");
					}else{
						map.put("jobStatus", "");
						resultRO.setResult(map);
						resultRO.setErrorCode(I_FAILURE);
						resultRO.setMessage("Failed to Set JobStatus");
					}
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);	
			resultRO.setMessage("Problem while getting job status :"+e.getMessage()+":"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/*********
	 * This method is used generate cron expression.
	 * @param nameValueUnits
	 * @param selectedTab
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<String> doGenerateCronExpression(List<NameValueUnit> nameValueUnits, String selectedTab) {
		ResultRO<String> resultRO = new ResultRO<>();
		try{
			String cronExpression = GenerateCronExpression.generateCronExpression(nameValueUnits, selectedTab);
			if(cronExpression!=null){
				resultRO.setErrorCode(I_SUCCESS);
				resultRO.setResult(cronExpression);
				resultRO.setMessage("Set cron successfully");
			}else{
				resultRO.setErrorCode(I_FAILURE);
				resultRO.setResult(cronExpression);
				resultRO.setMessage("Failed to set cron successfully");
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while setting cron"+e.getMessage()+":"+e.getCause());
			resultRO.setResult("");
		}
		return resultRO;
	}

	/**********
	 * This method is used to get users email id's whose system role is administrator.
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<List<String>> doGetEmailIdList() {
		ResultRO<List<String>> resultRO =  new ResultRO<>();
		Set<String> emailList = new HashSet<>();
		UserManager userManager = null;
		try{
			userManager = BrixCoreUtil.getService(UserManager.class);
			FilterGroup filtergroup = new FilterGroup().addFilter("role.systemRole.name", "administrator",
					EnumOperator.CI_EQ);
			List<List<NameValueUnit>> ls = userManager.filterUser(filtergroup,
					new String[] { "user.eMail", "user.disabled" });
			if (ls != null && !ls.isEmpty()) {
				for (List<NameValueUnit> list : ls) {
					NameValueUnit disableNameValue = list.stream()
							.filter(x -> x.getName().equalsIgnoreCase("userdisabled")).findFirst()
							.orElse(null);
					if (disableNameValue != null) {
						if ((Integer.parseInt(disableNameValue.getValue().toString())) == 0) {
							NameValueUnit nameValue = list.stream()
									.filter(x -> x.getName().equalsIgnoreCase("usereMail")).findFirst()
									.orElse(null);
							if (nameValue != null) {
								if (nameValue.getValue() != null && nameValue.getValue() != "") {
									emailList.add(nameValue.getValue().toString());
								}
							}
						}
					}
					ArrayList<String> mailList = new ArrayList<>();
					mailList.addAll(emailList);
					resultRO.setErrorCode(I_SUCCESS);
					resultRO.setResult(mailList);
					resultRO.setMessage("Email List of Admin Users");
				}
			}else{
				resultRO.setErrorCode(I_NOT_FOUND);
				resultRO.setResult(null);
				resultRO.setMessage("Email List of Admin Users Not Found");
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Failed to get email list of admin users"+e.getMessage()+":"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/**********
	 * This method is used to get system jobs available.
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<List<String>> doGetJobsList(int value) {
		ResultRO<List<String>> resultRO = new ResultRO<>();
		List<String> list = new ArrayList<>();
		try{
			if(value == 0){//returns system jobs
				list.add("File Cleanup");
				list.add("Health Check");
				list.add("Rest Connection Check");
				list.add("LDAP Connection Check");
			}else if(value == 1){//returns custom jobs
				list = getCustomJobList();
			}
			if(list!=null && !list.isEmpty()){
				resultRO.setErrorCode(I_SUCCESS);
				resultRO.setResult(list);
				resultRO.setMessage("Job list");
			}else{
				resultRO.setErrorCode(I_FAILURE);
				resultRO.setResult(list);
				resultRO.setMessage("Failed to get job list");
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while getting jobs list:"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/*********
	 * This method is used to get custom job list.
	 * @return job name list
	 */
	private List<String> getCustomJobList() {
		List<String> list = new ArrayList<>();
		JobService jobService = null;
		List<Jobclass> joblist = null;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			if(jobService != null){
				joblist = jobService.getCustomJobList();
				if(joblist != null){
					for (Jobclass jobclass : joblist) {
						list.add(jobclass.getName());	
					}
				}
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}
		return list;
	}

	/*****
	 * This method is used to check whether cron expression is valid or not.
	 * @param cronExpression
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<Boolean> doCheckCronExpression(String cronExpression) {
		JobService jobService = null;
		ResultRO<Boolean> resultRO = new ResultRO<>();
		boolean result = false;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			if(jobService!=null){
				result = jobService.checkCronExpression(cronExpression);
				if (result) {
					resultRO.setErrorCode(I_SUCCESS);
					resultRO.setResult(result);
					resultRO.setMessage("Cron Expression is valid");
				}else{
					resultRO.setErrorCode(I_FAILURE);
					resultRO.setResult(result);
					resultRO.setMessage("Cron Expression is invalid");
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while validation cron expression:"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/*******
	 * This method is used to get job monitor list where only jobs are listing which has pause and running status. 
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<List<Map<String, Object>>> doGetJobMonitorList() {
		ResultRO<List<Map<String,Object>>> resultRO = new ResultRO<>();
		FilterGroup filterGroup = new FilterGroup();
		JobHistoryManager jobHistoryManager = null;
		JobInformationManager jobInformationManager = null;
		List<Map<String, Object>> list = new ArrayList<>();
		String jobInfoId = null;
		try{
			jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
			jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
			if(jobHistoryManager != null && jobInformationManager!=null){
				List<List<NameValueUnit>> ls = jobInformationManager.filterJobInformation(filterGroup,new String[]{"name","description","jobClassName","cronExpression","jobkey"});
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
					filterGroup1.addFilter("jobInformation.id", BrixCoreUtil.getId(jobInfoId), EnumOperator.EQ).AND().OPERNING_BRACKET().
					addFilter("jobstatus", JobStatus.RUNNING, EnumOperator.EQ).OR().addFilter("jobstatus", JobStatus.PAUSE, EnumOperator.EQ).CLOSING_BRACKET().orderBy("-id");
					List<List<NameValueUnit>> ls2 = new ArrayList<>();
					ls2 = jobHistoryManager.filterJobHistory(filterGroup1, new String[]{"triggerTime","jobstatus"});
					if(ls2!=null && !ls2.isEmpty()){
						list2.addAll(ls2.get(0));
						list.add(NameValueConvertUtility.convertListNameValueToListHashMap(list2));
					}
				}
				if(list!=null && !list.isEmpty()){
					resultRO.setResult(list);
					resultRO.setErrorCode(I_SUCCESS);
					resultRO.setMessage("List of jobs");
				}else{
					resultRO.setErrorCode(I_NOT_FOUND);
					resultRO.setMessage("List Not found");
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while fetching job list:"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}


	/******
	 * This method is used to get job history. The list of jobs having running and finished status.
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	@SuppressWarnings("deprecation")
	protected ResultRO<List<Map<String, Object>>> doGetJobHistoryList(boolean flag, String startDate, String endDate) {
		ResultRO<List<Map<String,Object>>> resultRO = new ResultRO<>();
		DateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		FilterGroup filterGroup = new FilterGroup();
		JobHistoryManager jobHistoryManager = null;
		List<List<NameValueUnit>> ls = null;
		List<Map<String, Object>> list = new ArrayList<>();
		try{
			jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);

			if(jobHistoryManager != null){
				if(flag){
					//  Get List of job history according to selected dates.
					Date startdate=formatter.parse(startDate);
					Date enddate=formatter.parse(endDate);
					filterGroup.OPERNING_BRACKET().addFilter("jobstatus", JobStatus.RUNNING, EnumOperator.EQ).OR().addFilter("jobstatus", JobStatus.FINISHED, EnumOperator.EQ).CLOSING_BRACKET().AND();
					filterGroup.addFilter("createdDate", enddate, EnumOperator.LE).AND().addFilter("createdDate", startdate, EnumOperator.GE);
					ls = jobHistoryManager.filterJobHistory(filterGroup, new String[]{"name","jobInformationId","jobGroupName","triggerTime","jobstatus"});
				}else{
					Calendar cal = Calendar.getInstance();
					String afterdate = formatter.format(cal.getTime());
					Date afterDate = new Date(afterdate);

					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					String beforedate = formatter.format(cal.getTime());
					Date beforeDate = new Date(beforedate);

					//  Get List of Todays job history records only.
					filterGroup.OPERNING_BRACKET().addFilter("jobstatus", JobStatus.RUNNING, EnumOperator.EQ).OR().addFilter("jobstatus", JobStatus.FINISHED, EnumOperator.EQ).CLOSING_BRACKET().AND();
					filterGroup.addFilter("createdDate", afterDate, EnumOperator.LE).AND().addFilter("createdDate", beforeDate, EnumOperator.GE);
					ls = jobHistoryManager.filterJobHistory(filterGroup, new String[]{"name","jobInformationId","jobGroupName","triggerTime","jobstatus"});
				}
				if(ls!=null && !ls.isEmpty()){
					ls.stream().forEach(x->{ 
						Map<String, Object> jobDetails= NameValueConvertUtility.convertListNameValueToListHashMap(x);
						if(jobDetails.containsKey("jobkey")){
							String jobKey = (String) jobDetails.get("jobkey");
							String arr[];
							arr = jobKey.split("\\.");
							if(arr.length > 0)
								jobKey = arr[1];
							jobDetails.replace("jobkey", jobKey);
						}
						list.add(jobDetails);
					});
					resultRO.setResult(list);
					resultRO.setErrorCode(I_SUCCESS);
					resultRO.setMessage("List of jobs");
				}else{
					resultRO.setErrorCode(I_NOT_FOUND);
					resultRO.setMessage("List Not found");
					resultRO.setResult(null);
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while fetching job list:"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/********
	 * This method is used to get all jobs having each and every details of it.
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	@SuppressWarnings({ "unchecked"})
	protected ResultRO<List<Map<String, Object>>> doGetJobManagementList() {
		ResultRO<List<Map<String, Object>>> resultRO = new ResultRO<>();
		JobInformationManager jobInformationManager = null;
		JobHistoryManager jobHistoryManager = null;
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> jobMap = new LinkedHashMap<>();
		try{
			jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
			jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
			if(jobHistoryManager!=null){
				FilterGroup filterGroup = new FilterGroup();
				List<List<NameValueUnit>> ls = jobInformationManager.filterJobInformation(filterGroup,
						new String[]{"name","description","jobClassName","jobJarName","cronExpression","jobHistory.jobGroupName",
								"jobType","jobHistory.nextExecutionDate","jobkey","jobHistory.id","createdDate"});
				if(ls!=null && !ls.isEmpty()){
					ls.stream().forEach(x->{ 
						Map<String, Object> jobDetails = NameValueConvertUtility.convertListNameValueToListHashMap(x);
						if(jobDetails.containsKey("jobkey")){
							String jobKey = (String) jobDetails.get("jobkey");
							String arr[];
							arr = jobKey.split("\\.");
							if(arr.length > 0)
								jobKey = arr[1];
							jobDetails.replace("jobkey", jobKey);
						}
						if(jobMap.containsKey(jobDetails.get("jobInformationKey").toString())){
							HashMap<String,Object> dummy = (HashMap<String,Object> )jobMap.get(jobDetails.get("jobInformationKey").toString());
							int oldHistoryId = Integer.parseInt(dummy.get("jobHistoryid").toString());
							int newHistoryId = Integer.parseInt(jobDetails.get("jobHistoryid").toString());
							if(newHistoryId>oldHistoryId)
								jobMap.put(jobDetails.get("jobInformationKey").toString(), jobDetails);
						}else{
							jobMap.put(jobDetails.get("jobInformationKey").toString(), jobDetails);
						}
					});
					for (Map.Entry<String,Object> entry : jobMap.entrySet())  {
						list.add((Map<String, Object>) entry.getValue());
					}
					if(list!=null && !list.isEmpty()){
						resultRO.setResult(list);
						resultRO.setErrorCode(I_SUCCESS);
						resultRO.setMessage("List of jobs");
					}
				}else{
					resultRO.setErrorCode(I_NOT_FOUND);
					resultRO.setMessage("List Not found");
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while fetching list of jobs:"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}


	/***
	 * This code is used to resume currently running job.
	 * @param jobKey
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<Boolean> doResumeJob(List<NameValueUnit> nameValueUnits) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");   
		String time = dt.format( new Date());
		JobHistoryManager jobHistoryManager = null;
		JobInformationManager jobInformationManager=null;
		ResultRO<Boolean> resultRO = new ResultRO<>();
		NameValueUnit grpNameValue = null;
		JobService  jobService = null;
		String key = null;
		boolean flag = false;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			FilterGroup filterGroup =  new FilterGroup();
			if(jobService!=null){
				if(nameValueUnits!=null && !nameValueUnits.isEmpty()){
					NameValueUnit jobKeyNameValue = nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("jobkey")).findFirst().orElse(null);
					if (jobKeyNameValue!=null)
						key = jobKeyNameValue.getValue().toString();
					flag = jobService.resumeJob(key);
					if(flag){
						/****
						 * If job is already exist we need to resumed it directly. So here we change status of it as resumed.
						 */
						jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
						jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
						if(jobHistoryManager!=null){
							NameValueUnit jobStatusNameValue = nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("jobStatus")).findFirst().orElse(null);
							NameValueUnit jobInformationidNameValue = nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("jobInformationKey")).findFirst().orElse(null);
							if(jobInformationManager!=null){
								filterGroup = filterGroup.addFilter("jobInformation.id", BrixCoreUtil.getId(jobInformationidNameValue.getValue().toString()), EnumOperator.EQ);
								List<List<NameValueUnit>> ls=jobInformationManager.filterJobInformation(filterGroup, new String[]{"jobGroupName"});
								if(ls!=null && !ls.isEmpty()){
									for (List<NameValueUnit> list : ls) {
										grpNameValue = list.stream().filter(x->x.getName().equalsIgnoreCase("jobGroupName")).findFirst().orElse(null);
									}
								}
							}
							if(jobStatusNameValue!=null)
								nameValueUnits.remove(jobStatusNameValue);
							if(jobInformationidNameValue!=null){
								nameValueUnits.remove(jobInformationidNameValue);
								nameValueUnits.add(new NameValueUnit("jobInformation", jobInformationidNameValue.getValue().toString()));
								nameValueUnits.add(new NameValueUnit("jobInformationId", jobInformationidNameValue.getValue().toString()));
							}
							if(grpNameValue!=null)
								nameValueUnits.add(new NameValueUnit("jobGroupName", grpNameValue.getValue().toString()));
							nameValueUnits.add(new NameValueUnit("jobstatus", JobStatus.RUNNING));
							nameValueUnits.add(new NameValueUnit("description", "Job is resumed."));
							nameValueUnits.add(new NameValueUnit("triggerTime", time));
							jobHistoryManager.createJobHistory(nameValueUnits);
						}
					}else{
						/*******
						 * As all jobs saved in RAM by scheduler, if job is not exist in RAM, we need to create it again.
						 */
						jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
						jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
						if(jobHistoryManager!=null && jobInformationManager!=null){
							NameValueUnit jobInformationidNameValue = nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("jobInformationKey")).findFirst().orElse(null);
							if(jobInformationManager!=null){
								filterGroup = filterGroup.addFilter("id", BrixCoreUtil.getId(jobInformationidNameValue.getValue().toString()), EnumOperator.EQ);
								List<List<NameValueUnit>> ls=jobInformationManager.filterJobInformation(filterGroup, new String[]{"jobHistory.jobGroupName","jobJarName"});
								if(ls!=null && !ls.isEmpty()){
									String groupName = "";
									NameValueUnit grpnamevalue = ls.get(0).stream().filter(x->x.getName().equalsIgnoreCase("jobHistoryjobGroupName")).findFirst().orElse(null);
									if(grpnamevalue!=null){
										groupName = grpnamevalue.getValue().toString();
										ls.get(0).remove(grpnamevalue);
										ls.get(0).add(new NameValueUnit("jobGroupName", groupName));
									}
									nameValueUnits.addAll(ls.get(0));
									jobService.createJob(nameValueUnits,false);
								}
							}
						}
					}
				}
				resultRO.setErrorCode(I_SUCCESS);
				resultRO.setResult(true);
				resultRO.setMessage("Job resumed successfully.");
			}else{
				resultRO.setErrorCode(I_FAILURE);
				resultRO.setResult(false);
				resultRO.setMessage("Failed to resume job");
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while resuming job :"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/****
	 * This code is used to pause currently running job.
	 * @param jobKey
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<Boolean> doPauseJob(List<NameValueUnit> jobdetails) {
		JobInformationManager jobInformationManager = null;
		JobHistoryManager jobHistoryManager = null;
		ResultRO<Boolean> resultRO = new ResultRO<>();
		NameValueUnit grpNameValue = null;
		JobService jobService = null;
		String key = null;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			if(jobService!=null){
				if(jobdetails!=null && !jobdetails.isEmpty()){
					NameValueUnit jobKeyNameValue = jobdetails.stream().filter(x->x.getName().equalsIgnoreCase("jobkey")).findFirst().orElse(null);
					if(jobKeyNameValue!=null)
						key = jobKeyNameValue.getValue().toString();
					jobService.pauseJob(key);
					jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
					jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
					if(jobHistoryManager!=null){
						NameValueUnit jobStatusNameValue = jobdetails.stream().filter(x->x.getName().equalsIgnoreCase("jobStatus")).findFirst().orElse(null);
						NameValueUnit jobInformationidNameValue = jobdetails.stream().filter(x->x.getName().equalsIgnoreCase("jobInformationKey")).findFirst().orElse(null);
						if(jobInformationManager!=null){
							FilterGroup filterGroup = new FilterGroup();
							filterGroup = filterGroup.addFilter("jobInformation.id", BrixCoreUtil.getId(jobInformationidNameValue.getValue().toString()), EnumOperator.EQ);
							List<List<NameValueUnit>> ls=jobInformationManager.filterJobInformation(filterGroup, new String[]{"jobGroupName"});
							if(ls!=null && !ls.isEmpty()){
								for (List<NameValueUnit> list : ls) {
									grpNameValue = list.stream().filter(x->x.getName().equalsIgnoreCase("jobGroupName")).findFirst().orElse(null);
								}
							}
						}
						if(jobStatusNameValue!=null)
							jobdetails.remove(jobStatusNameValue);
						if(jobInformationidNameValue!=null){
							jobdetails.remove(jobInformationidNameValue);
							jobdetails.add(new NameValueUnit("jobInformation", jobInformationidNameValue.getValue().toString()));
							jobdetails.add(new NameValueUnit("jobInformationId",jobInformationidNameValue.getValue().toString()));
						}
						if(grpNameValue!=null)
							jobdetails.add(new NameValueUnit("jobGroupName", grpNameValue.getValue().toString()));
						jobdetails.add(new NameValueUnit("jobstatus", JobStatus.PAUSE));
						jobdetails.add(new NameValueUnit("description", "Job is paused."));
						jobHistoryManager.createJobHistory(jobdetails);
					}
					resultRO.setErrorCode(I_SUCCESS);
					resultRO.setMessage("Job paused successfully");
					resultRO.setResult(true);

				}
			}else{
				resultRO.setErrorCode(I_FAILURE);
				resultRO.setMessage("Failed to pause job");
				resultRO.setResult(false);
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Failed while pausing job :"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/**
	 * This code is used to delete job.
	 * @param jobKey
	 * @param jobId
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<Boolean> doDeleteJob(String jobKey, String jobId) {
		JobInformationManager jobInformationManager = null;
		JobHistoryManager jobHistoryManager = null;
		ResultRO<Boolean> resultRO = new ResultRO<>();
		boolean result = true;
		JobService jobService = null;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			if(jobService!=null){
				result = jobService.deleteJob(jobKey);
				if(result){
					jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
					jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
					if(jobInformationManager!=null){
						FilterGroup filterGroup= new FilterGroup();
						filterGroup.addFilter("id", BrixCoreUtil.getId(jobId), EnumOperator.EQ);
						List<List<NameValueUnit>> ls = jobInformationManager.filterJobInformation(filterGroup, new String[]{"jobHistory.id"});
						JobInformation jobInformation=jobInformationManager.getJobInformationById(jobId);
						for (List<NameValueUnit> list : ls) {
							NameValueUnit historyId = list.stream().filter(x->x.getName().equalsIgnoreCase("jobHistoryid")).findFirst().orElse(null);
							JobHistory jobHistory=jobHistoryManager.getJobHistoryById("JobHistory:"+historyId.getValue().toString());
							jobHistory.removeRelation(jobInformation, "jobInformation");
						}
						List<List<NameValueUnit>> listNameValue= jobInformationManager.filterJobInformation(filterGroup, new String[]{"jobGroupName"});
						if(listNameValue!=null){
							createJobHistory(jobInformation,listNameValue,jobHistoryManager,jobInformationManager);
						}
					}
					resultRO.setErrorCode(I_SUCCESS);
					resultRO.setMessage("Job Deleted Successfully");
					resultRO.setResult(true);
				}else{
					resultRO.setErrorCode(I_FAILURE);
					resultRO.setMessage("Failed to delete job");
					resultRO.setResult(false);
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while deleting job :"+e.getMessage()+"-"+e.getCause());
			ServiceLogger.error(e);
		}
		return resultRO;
	}

	/********
	 * This method is used to create job history
	 * @param jobInformation
	 * @param listNameValue
	 * @param jobHistoryManager
	 * @param jobInformationManager
	 * @author Pratiksha.Datir
	 */
	private void createJobHistory(JobInformation jobInformation,List<List<NameValueUnit>> listNameValue, JobHistoryManager jobHistoryManager
			, JobInformationManager jobInformationManager){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");   
		String time = dt.format( new Date());
		List<NameValueUnit> nameValue=new ArrayList<>();
		NameValueUnit grpName = null;
		try{
			for (List<NameValueUnit> nameValueUnit : listNameValue) {
				grpName = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("jobGroupName")).findFirst().orElse(null);
			}
			nameValue.add(new NameValueUnit("jobstatus", JobStatus.FINISHED));
			nameValue.add(new NameValueUnit("description", "Job is deleted."));
			nameValue.add(new NameValueUnit("name", jobInformation.getName()));
			nameValue.add(new NameValueUnit("jobGroupName", grpName.getValue().toString()));
			nameValue.add(new NameValueUnit("jobInformationId", jobInformation.getId()));
			/*nameValue.add(new NameValueUnit("jobInformation", jobInformation.getId()));*/
			nameValue.add(new NameValueUnit("triggerTime", time));
			nameValue.add(new NameValueUnit("executionStartDate", jobInformation.getCreatedDate()));
			if(jobHistoryManager!=null){
				jobHistoryManager.createJobHistory(nameValue);
				jobInformationManager.removeJobInformation(jobInformation);
			}
		}catch(Exception e){
			ServiceLogger.error(e);
		}		
	}

	/**
	 * This code is used to update job.
	 * @param jobNameValue
	 * @param jobKey
	 * @param jobId
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<Map<String, Object>> doUpdateJob(List<NameValueUnit> jobNameValue, String jobKey, String jobId) {
		ResultRO<Map<String, Object>> resultRO = new ResultRO<>();
		JobService jobService = null;
		JobInformationManager jobInformationManager = null;
		JobInformation jobInformation = null;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
			if(jobService!=null){
				if(jobNameValue != null){
					NameValueUnit nameValue1 = jobNameValue.stream().filter(x->x.getName().equalsIgnoreCase("name")).findFirst().orElse(null);
					String newJobName = nameValue1.getValue().toString();
					NameValueUnit nameValue2 = jobNameValue.stream().filter(x->x.getName().equalsIgnoreCase("oldname")).findFirst().orElse(null);
					String oldJobName = nameValue2.getValue().toString();
					if(!oldJobName.equals(newJobName)){
						jobInformation = jobInformationManager.getJobInformationByName(newJobName);
					}
					if(jobInformation == null){
						jobNameValue.remove(nameValue2);
						JobKey jobKey1 = jobService.updateJob(jobNameValue, jobId, jobKey);
						if(jobKey1!=null){
							resultRO.setErrorCode(I_SUCCESS);
							resultRO.setMessage("Job updated successfully.");
						}else{
							resultRO.setErrorCode(I_FAILURE);
							resultRO.setMessage("Failed to update job");
						}
					}else{
						resultRO.setErrorCode(I_DUPLICATE);
						resultRO.setMessage("Job is already exist");
					}
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage("Problem while updating job:"+e.getMessage()+"-"+e.getCause());
		}
		return resultRO;
	}

	/****
	 * This function is used to create job and schedule it according to cron expression.
	 * @param jobnamevalue
	 * @return {@link ResultRO}
	 * @author Pratiksha.Datir
	 */
	protected ResultRO<Map<String, Object>> doCreateJob(List<NameValueUnit> jobnamevalue) {
		ResultRO<Map<String, Object>> resultRO = new ResultRO<>();
		JobInformationManager jobInformationManager = null;
		JobService jobService = null;
		String jobName = null;
		try{
			jobService = BrixCoreUtil.getService(JobService.class);
			jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
			if(jobService!=null && jobInformationManager!=null){
				if(jobnamevalue!=null){
					NameValueUnit nameValue = jobnamevalue.stream().filter(x->x.getName().equalsIgnoreCase("name")).findFirst().orElse(null);
					if(nameValue!=null)
						jobName = (String)nameValue.getValue();
					JobInformation jobInformation = jobInformationManager.getJobInformationByName(jobName);
					if(jobInformation == null){
						JobKey jobKey = jobService.createJob(jobnamevalue,true);
						if(jobKey!=null){
							resultRO.setErrorCode(I_SUCCESS);
							resultRO.setMessage("Job is scheduled ");
							Map<String, Object> jobMap =  new HashMap<>();
							jobMap.put("jobGroup", jobKey.getGroup().toString());
							jobMap.put("jobName", jobKey.getName().toString());
							resultRO.setResult(jobMap);
						}else{
							resultRO.setErrorCode(I_FAILURE);
							resultRO.setMessage("Failed to schedule job");
						}
					}else{
						resultRO.setErrorCode(I_DUPLICATE);
						resultRO.setMessage("Job Already exist");
					}
				}
			}
		}catch(Exception e){
			resultRO.setErrorCode(I_EXCEPTION);
			resultRO.setMessage(e.getMessage());
			ServiceLogger.error(e);
		}
		return resultRO;
	}
}
