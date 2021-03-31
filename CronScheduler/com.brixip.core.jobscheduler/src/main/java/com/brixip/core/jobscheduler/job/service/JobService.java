package com.brixip.core.jobscheduler.job.service;

import java.util.List;
import org.quartz.JobDetail;
import org.quartz.JobKey;

import com.brixip.core.jobscheduler.mapper.Jobclass;
import com.brixip.core.library.NameValueUnit;

/*********
 * 
 * @author Sagar.Mali
 *
 */
public interface JobService{

	public JobKey createJob(List<NameValueUnit> JobNameValueUnit, boolean flag) throws JobServiceException;
	
	public JobKey updateJob(List<NameValueUnit> nameValueUnit, String jobId, String jobKey) throws JobServiceException;
	
	public boolean resumeJob(String jobKey) throws JobServiceException;
	
	public void pauseJob(String jobKey) throws JobServiceException;
	
	public JobDetail getJobDetails(JobKey jobKey) throws JobServiceException;

	public boolean deleteJob(String jobKey) throws JobServiceException;
	
	public boolean checkCronExpression(String cronExpression)throws JobServiceException;
	
	public List<Jobclass> getCustomJobList()throws JobServiceException;
}
