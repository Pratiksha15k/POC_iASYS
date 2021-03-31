package com.brixip.core.jobscheduler.job.jobscheduler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DynamicJob implements Job{
	private static int count;
	JobScheduler job;
	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		//count++;
		//Demo demo=new Demo();

		System.out.println("############################## Custom Job Running ##############################");
		
		
	}

}