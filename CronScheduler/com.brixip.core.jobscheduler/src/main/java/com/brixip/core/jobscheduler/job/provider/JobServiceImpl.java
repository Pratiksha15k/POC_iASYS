package com.brixip.core.jobscheduler.job.provider;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.ConfigurationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import com.brixip.core.jobhistorymanager.JobHistoryManager;
import com.brixip.core.jobinformation.JobInformation;
import com.brixip.core.jobinformationmanager.JobInformationManager;
import com.brixip.core.jobscheduler.job.enums.JobStatus;
import com.brixip.core.jobscheduler.job.jobscheduler.RescheduleJobs;
import com.brixip.core.jobscheduler.job.service.JobService;
import com.brixip.core.jobscheduler.job.service.JobServiceException;
import com.brixip.core.jobscheduler.mapper.Customjob;
import com.brixip.core.jobscheduler.mapper.Customjoblist;
import com.brixip.core.jobscheduler.mapper.Jobclass;
import com.brixip.core.library.NameValueUnit;
import com.brixip.core.util.BrixCoreUtil;
import com.pvmsys.brix.buss.moduleconfig.reader.service.ModuleConfigReaderService;
import com.pvmsys.brix.core.common.library.ServiceLogger;

public class JobServiceImpl implements JobService {

	private String mFilename = "quartz.properties";
	private String fileName = "CustomJob.xml";
	private static Scheduler scheduler=null;
	private static Customjob customJob = null;
	private static ModuleConfigReaderService moduleConfigReaderService = null;
	String customJobXmlPath=null;
	public JobServiceImpl() {

	}

	BundleContext bContext = null;
	File quartzfile = null;
	SchedulerFactory factory = null;

	/**
	 * Method is used to activate this class
	 * @author Sagar.Mali
	 * @param bundleContext
	 */
	public void activate(BundleContext bundleContext) {
		System.out.println("########## Job service is activated #############");
		if (bContext == null) {
			bContext = bundleContext;
		}
		try {
			quartzfile = getFilePath(mFilename);
			if (quartzfile == null) {
				// Set Default Quartz Configuration
				factory = new StdSchedulerFactory();
			} else {
				// set Custom Quartz Configuration from reminder.properties
				factory = new StdSchedulerFactory(quartzfile.getPath());
			}
			if(scheduler==null){
				scheduler = factory.getScheduler();
			}
			
			RescheduleJobs.rescheduleJobs();
		} catch (Exception e) {
			ServiceLogger.error("Reminder scheduler Propertie file Not Found");
			e.printStackTrace();
		}
	}

	/**
	 * Method is used to deactivate
	 * @author sagar.mali
	 * @param
	 */
	public void deActivate() {
		System.out.println("########## Job service is deActivated #############");
		if (bContext != null) {
			bContext = null;
		}
	}


	/***********
	 * This function is used to create job and by getting scheduler instance start job according to its details.
	 * @param JobDetails
	 * @param flag
	 * @throws JobServiceException
	 * @return JobKey
	 * @author Pratiksha.Datir
	 */
	@Override
	public JobKey createJob(List<NameValueUnit> JobDetails, boolean flag) throws JobServiceException {
		JobKey jobKey1=null;
		Class<?> jobClass;
		try {
			// Creating scheduler factory and scheduler
			if(JobDetails !=null && !JobDetails.isEmpty()){
				String jobclassname="",jarname="",jobname="",jobdescription="",cronexpression="",groupname="";
				NameValueUnit jobClassName = JobDetails.stream().filter(x->x.getName().equalsIgnoreCase("jobClassName")).findFirst().orElse(null);
				if(jobClassName!=null)
					jobclassname = jobClassName.getValue().toString(); 
				 
				NameValueUnit jarName = JobDetails.stream().filter(x->x.getName().equalsIgnoreCase("jobJarName")).findFirst().orElse(null);
				if(jarName!=null)
					jarname = jarName.getValue().toString(); 

				NameValueUnit jobName = JobDetails.stream().filter(x->x.getName().equalsIgnoreCase("name")).findFirst().orElse(null);
				if(jobName!=null)
					jobname = jobName.getValue().toString(); 

				NameValueUnit jobDescription = JobDetails.stream().filter(x->x.getName().equalsIgnoreCase("description")).findFirst().orElse(null);
				if(jobDescription!=null)
					jobdescription = jobDescription.getValue().toString(); 

				NameValueUnit cronExpression = JobDetails.stream().filter(x->x.getName().equalsIgnoreCase("cronExpression")).findFirst().orElse(null);
				if(cronExpression!=null)
					cronexpression = cronExpression.getValue().toString(); 

				NameValueUnit groupName = JobDetails.stream().filter(x->x.getName().equalsIgnoreCase("jobGroupName")).findFirst().orElse(null);
				if(groupName!=null)
					groupname = groupName.getValue().toString(); 
				jobClass=getjobclass(jobclassname);
				JobDetail jobDetail = SetJob(jobClass,jobname,jobdescription,groupname);
				jobKey1=jobDetail.getKey();
				JobDetails.add(new NameValueUnit("jobkey", jobKey1.toString()));
				Trigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(jobKey1.getName(), jobname)
						.withSchedule(CronScheduleBuilder.cronSchedule(cronexpression))     
						.build();
				scheduler.start();
				scheduler.scheduleJob(jobDetail, trigger);
				if(flag)
					createJobInformationAndHistory(JobDetails,scheduler,trigger);
			}

		} catch (SchedulerException e) {
			e.printStackTrace();
			ServiceLogger.error(e);
		}
		return jobKey1;
	}


	/***********
	 * This function is used to find class from osgi bundle.
	 * @param symbolicName
	 * @param clazzName
	 * @return  Class<?>
	 * @author Sagar.Mali
	 */
	private Class<?> getjobclass(String fullyJobclassname){
		Class<?> jobclass =null;
		Bundle[] bundles = this.bContext.getBundles();
		for (int i = 0; i < bundles.length; i++) {
		Bundle bundle = bundles[i];
		try {
		 jobclass = bundle.loadClass(fullyJobclassname);
		 if(jobclass!=null)
		 return jobclass;
		//System.out.println(jobclass.getName());
		}catch (Exception e) {
		// TODO: handle exception
		}
		}
		return jobclass;
		}

	/***********
	 * This function is used to set job in scheduler.
	 * @param jobClassName
	 * @param jobName
	 * @param jobDescription
	 * @param jobGroupName
	 * @return  JobDetail
	 * @author Sagar.Mali
	 */
	@SuppressWarnings("unchecked")
	private JobDetail SetJob(Class<?> jobClassName,String jobName,String jobDescription,String jobGroupName){
		// Set Job with JobClass Name
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setName(jobName);
		//jobDetail.setDescription(jobDescription);
		//jobDetail.setGroup(jobGroupName);
		jobDetail.setJobClass((Class<? extends Job>) jobClassName);
		return jobDetail;
	}

	/***********
	 * This function is used to delete job from scheduler.
	 * @param jobClassName
	 * @param jobName
	 * @param jobDescription
	 * @param jobGroupName
	 * @return  JobDetail
	 * @throws JobServiceException
	 * @author Pratiksha.Datir
	 */
	@Override
	public boolean deleteJob(String jobKey) throws JobServiceException {
		boolean result = false;
		JobKey key = null;
		try {
			key = JobKey.jobKey(jobKey);
			result = scheduler.deleteJob(key);
		}catch (Exception e) {
			ServiceLogger.error(e);
		}	
		return result;
	}


	/***********
	 * This function is used to update job in scheduler.
	 * @param nameValueUnit
	 * @param jobId
	 * @param jobKey
	 * @return  JobKey
	 * @throws JobServiceException
	 * @author Pratiksha.Datir
	 */
	@Override
	public JobKey updateJob(List<NameValueUnit> nameValueUnit, String jobId, String jobKey) throws JobServiceException {
		JobDetail updatedJobDetail =null;
		Class<?> jobClass;
		try {
			if(nameValueUnit !=null){
				//String jobclassname="com.brixip.controller.loader.activitylog.servlet.NotificationScheduler",jarname="com.brixip.controller.loader.activitylog",jobname="",jobdescription="",cronexpression="",groupname="";
				String jobclassname="",jarname="",jobname="",jobdescription="",cronexpression="",groupname="";
				NameValueUnit jobClassName = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("jobClassName")).findFirst().orElse(null);
				if(jobClassName!=null)
					jobclassname = jobClassName.getValue().toString(); 

				NameValueUnit jarName = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("jobJarName")).findFirst().orElse(null);
				if(jarName!=null)
					jarname = jarName.getValue().toString(); 

				NameValueUnit jobName = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("name")).findFirst().orElse(null);
				if(jobName!=null)
					jobname = jobName.getValue().toString(); 

				NameValueUnit jobDescription = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("description")).findFirst().orElse(null);
				if(jobDescription!=null)
					jobdescription = jobDescription.getValue().toString(); 

				NameValueUnit cronExpression = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("cronExpression")).findFirst().orElse(null);
				if(cronExpression!=null)
					cronexpression = cronExpression.getValue().toString(); 

				NameValueUnit groupName = nameValueUnit.stream().filter(x->x.getName().equalsIgnoreCase("jobGroupName")).findFirst().orElse(null);
				if(groupName!=null)
					groupname = groupName.getValue().toString(); 
			
				JobKey key=JobKey.jobKey(jobKey);
				jobClass=getjobclass(jobclassname);
				updatedJobDetail = SetJob(jobClass,jobname,jobdescription,groupname);
				List<? extends Trigger> oldTriggerKey=scheduler.getTriggersOfJob(key);
				Trigger newTrigger = TriggerBuilder.newTrigger()
						.withIdentity(scheduler.getSchedulerName(), key.getName())
						.withSchedule(CronScheduleBuilder.cronSchedule(cronexpression))     
						.build();
				scheduler.addJob(updatedJobDetail, true, true); // 2nd parameter true means updating the existing job with the updated one.
				if(oldTriggerKey.size()>0){
					for (Trigger oldtriggerkey : oldTriggerKey) {
						scheduler.rescheduleJob(oldtriggerkey.getKey(), newTrigger);
					}
				}
				updateJobInformationAndHistory(nameValueUnit,scheduler,newTrigger,jobId);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return updatedJobDetail.getKey();
	}

	/*****
	 * This method is used to update job information and create instance of job history against job information
	 * @param JobDetails
	 * @param scheduler
	 * @param trigger
	 * @param jobId
	 * @author Pratiksha.Datir
	 */
	private void updateJobInformationAndHistory(List<NameValueUnit> JobDetails, Scheduler scheduler, Trigger trigger,String jobId) {
		JobInformationManager jobInformationManager = null;
		JobInformation jobInformation = null;
		JobHistoryManager jobHistoryManager = null;
		String jobStatus = null;
		try{
			jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
			jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
			if(jobInformationManager!=null){
				jobInformation = jobInformationManager.updateJobInformation(jobId, JobDetails);
			}
			JobDetails.add(new NameValueUnit("jobInformationId", jobInformation.getId()));
			JobDetails.add(new NameValueUnit("jobInformation", jobInformation.getId()));
			JobDetails.add(new NameValueUnit("triggerTime", new Date().toString()));
			JobDetails.add(new NameValueUnit("jobstatus", jobStatus));
			JobDetails.add(new NameValueUnit("executionStartDate", trigger.getStartTime()));
			JobDetails.add(new NameValueUnit("nextExecutionDate", trigger.getNextFireTime()));
			if(jobHistoryManager!=null)
				jobHistoryManager.createJobHistory(JobDetails);
		}catch(Exception e){
			e.printStackTrace();
			ServiceLogger.error(e);
		}
	}
	
	/***********
	 * This method is used to create job information and create instance of job history against job information
	 * @param JobDetails
	 * @param scheduler
	 * @param trigger
	 * @author Pratiksha.Datir
	 */
	private void createJobInformationAndHistory(List<NameValueUnit> JobDetails, Scheduler scheduler, Trigger trigger) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");   
		String time = dt.format(new Date());
		JobInformationManager jobInformationManager = null;
		JobInformation jobInformation = null;
		JobHistoryManager jobHistoryManager = null;
		try{
			jobInformationManager = BrixCoreUtil.getService(JobInformationManager.class);
			jobHistoryManager = BrixCoreUtil.getService(JobHistoryManager.class);
			if(jobInformationManager!=null)
				jobInformation = jobInformationManager.createJobInformation(JobDetails);
			JobDetails.add(new NameValueUnit("jobInformationId", jobInformation.getId()));
			JobDetails.add(new NameValueUnit("jobInformation", jobInformation.getId()));
			JobDetails.add(new NameValueUnit("triggerTime", time));
			JobDetails.add(new NameValueUnit("jobstatus", JobStatus.RUNNING));
			JobDetails.add(new NameValueUnit("executionStartDate", trigger.getStartTime()));
			JobDetails.add(new NameValueUnit("nextExecutionDate", trigger.getNextFireTime()));
			if(jobHistoryManager!=null)
				jobHistoryManager.createJobHistory(JobDetails);
		}catch(Exception e){
			e.printStackTrace();
			ServiceLogger.error(e);
		}
	}

	/***********
	 * This method is used to resume job using job key. At the time of server restart job needs to create again.
	 * @param jobKey
	 * @return boolean
	 * @throws JobServiceException
	 * @author Pratiksha.Datir
	 */
	@Override
	public boolean resumeJob(String jobKey) throws JobServiceException {
		JobKey key=null;
		boolean flag = false;
		try {
			key = JobKey.jobKey(jobKey);
			if(scheduler.checkExists(key)){
				scheduler.resumeJob(key);
				flag =  true;
			}else{
				flag = false ;
			}
		} catch(Exception e){
			ServiceLogger.error(e);
		}
		return flag;
	}

	/***********
	 * This method is used to pause job using job key.
	 * @param jobKey
	 * @return boolean
	 * @throws JobServiceException
	 * @author Pratiksha.Datir
	 */
	@Override
	public void pauseJob(String jobKey) throws JobServiceException {
		JobKey key= null;
		try {
			key = JobKey.jobKey(jobKey);
			scheduler.pauseJob(key);
		} catch(Exception e){
			ServiceLogger.error(e);
		}
	}

	/*****
	 * This method is used to get job details against job key.
	 * @param jobKey
	 * @throws JobServiceException
	 * @author Pratiksha.Datir
	 */
	@Override
	public JobDetail getJobDetails(JobKey jobKey) throws JobServiceException {
		JobDetail jobDetail=null;
		if(jobKey!=null && scheduler!=null){
			try {
				jobDetail=scheduler.getJobDetail(jobKey);
			} catch (SchedulerException e) {
				throw new JobServiceException("Failed to get Job Details" + jobKey, e);
			}
		}
		return jobDetail;
	}

	/**
	 * This method is used to locate the BOM file, it takes the path of file
	 * from the bundleContext.
	 * 
	 * @param filename
	 * @return it will return the file instance which has the BOM file address.
	 * @author Sagar.Mali
	 */
	private File getFilePath(String filename) {
		String homeDir = bContext.getProperty("osgi.bundle.configuration.dir");
		File ret = null;
		if (homeDir == null) {
			try {
				URL url = new URL(bContext.getProperty("osgi.configuration.area"));
				ret = new File(url.getFile(), filename);
			} catch (Throwable t) {
			}
		} else {
			ret = new File(homeDir, filename);
		}
		return ret;
	}

	/******
	 * This method is used to check cron expression whether it is valid or not.
	 * @return boolean true if valid, false if invalid
	 * @param cronExpression
	 * @author Pratiksha.Datir
	 * 
	 */
	@Override
	public boolean checkCronExpression(String cronExpression) throws JobServiceException {
		boolean flag = false;
		String result="";
		ArrayList<String> timeList = null;
		try {
			if (CronExpression.isValidExpression(cronExpression)) {
				timeList = new ArrayList<>();
				CronExpression cronExp = new CronExpression(cronExpression);
				Date date = new Date();
				for (int i = 0; i < 5; i++) {
					Date nextDate = cronExp.getNextValidTimeAfter(date);
					System.out.println(nextDate);
					timeList.add(nextDate.toString());
					date = nextDate;
				} 
			} 
			if (timeList != null) {
				for (String timeList1 : timeList) {
					result = result + "\n" + timeList1;
				}
			} else {
				result = "invalid";
			} 
			if(!result.equalsIgnoreCase("invalid"))
				flag = true;
			else
				flag = false;

		} catch (ParseException ex) {
			ServiceLogger.error(ex);
		} catch(Exception e){
			ServiceLogger.error(e);
		}
		return flag;
	}

	private boolean readCustomJobFile() {
		boolean flag = false;
		File path = null;
		File file = null;
		customJob = null;
		try{
			moduleConfigReaderService = BrixCoreUtil.getService(ModuleConfigReaderService.class);
			if(moduleConfigReaderService!=null){
				customJobXmlPath = (String) moduleConfigReaderService.getPropertyValue(String.class, "customJob");
				if(customJobXmlPath!=null){
					path = new File(customJobXmlPath);
					if(!path.exists())
						path.mkdir();
					file = new File(customJobXmlPath+File.separator+fileName);
					if (!file.exists())
						throw new ConfigurationException(fileName + " file is not presnet at location:"+customJobXmlPath);
					JAXBContext jaxbContext = JAXBContext.newInstance(Customjob.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					customJob = (Customjob) jaxbUnmarshaller.unmarshal(file);
					if(customJob!=null)
						flag = true;
					else
						flag = false;
				}
			}
		}catch(Exception e){
			ServiceLogger.error("Message: Problem in readCustomJobFile");
			ServiceLogger.error("Method: " + Thread.currentThread().getStackTrace()[1].getClassName() + "."
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + ":"
					+ Thread.currentThread().getStackTrace()[1].getLineNumber());
			ServiceLogger.error(e);
		}
		return flag;
	}

	@Override
	public List<Jobclass> getCustomJobList() throws JobServiceException {
		List<Jobclass> jobClassList = null;
		boolean flag = false;
		try{
			flag = readCustomJobFile();
			if(flag){
				if(customJob!=null){
					Customjoblist customJobList = customJob.getCustomjoblist();
					jobClassList = customJobList.getJobclass();
				}
			}
		}catch(Exception e){
			ServiceLogger.error("Message: Problem in getCustomJobList");
			ServiceLogger.error("Method: " + Thread.currentThread().getStackTrace()[1].getClassName() + "."
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + ":"
					+ Thread.currentThread().getStackTrace()[1].getLineNumber());
			ServiceLogger.error(e);
		}
		return jobClassList;
	}
	
}
