package com.brixip.controller.loader.jobscheduler.customjobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendNotification implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("************ Custom Job Started *************");
	}

}
