package com.brixip.controller.loader.jobscheduler.servlet.activator;

import javax.servlet.ServletException;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import com.brixip.controller.loader.jobscheduler.servlet.JobSchedulerServlet;
import com.brixip.controller.loader.jobscheduler.servlet.SystemJobSchedulerServlet;

public class JobSchedulerServletActivator {
	private HttpService httpService;
	
	public JobSchedulerServletActivator(){
		System.out.println("############### Quartz Servlet Activator Constructed ###############");
	}
	 
	protected void activate(ComponentContext componentContext)throws NamespaceException, ServletException{
		System.out.println("############### Quartz Servlet Activator Started ###############");
		
		httpService = (HttpService)componentContext.locateService(HttpService.class.getSimpleName());
		
		final HttpContext httpContext = (HttpContext)httpService.createDefaultHttpContext();
		
		httpService.registerServlet("/createJob", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		
		httpService.registerServlet("/updateJob", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/deleteJob", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/pauseJob", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/resumeJob", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/getJobsManagementList", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/getJobsHistoryList", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/getJobsMonitorList", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/getJobDetails", 
				new JobSchedulerServlet(),
				null, 
				httpContext);
		httpService.registerServlet("/checkCronExpression",
				new JobSchedulerServlet(), 
				null, 
				httpContext);
		httpService.registerServlet("/getJobsList",
				new JobSchedulerServlet(), 
				null, 
				httpContext);
		httpService.registerServlet("/getEmailIdList", 
				new JobSchedulerServlet(), 
				null, 
				httpContext);
		httpService.registerServlet("/scheduleFileCleanupJob", 
				new SystemJobSchedulerServlet(), 
				null, 
				httpContext);
		
		httpService.registerServlet("/scheduleHealthCheckJob", 
				new SystemJobSchedulerServlet(), 
				null, 
				httpContext);
		
		httpService.registerServlet("/scheduleRestConnectionJob", 
				new SystemJobSchedulerServlet(), 
				null, 
				httpContext);
		
		httpService.registerServlet("/scheduleLdapConnectionJob", 
				new SystemJobSchedulerServlet(), 
				null, 
				httpContext);
		
		httpService.registerServlet("/rescheduleSystemJobs", 
				new SystemJobSchedulerServlet(), 
				null, 
				httpContext);
		
		httpService.registerServlet("/getJobParameters", 
				new SystemJobSchedulerServlet(), 
				null, 
				httpContext);
		
		httpService.registerServlet("/generateCronExpression", 
				new JobSchedulerServlet(), 
				null, 
				httpContext);
		httpService.registerServlet("/checkJobResumeOrPause", 
				new JobSchedulerServlet(), 
				null, 
				httpContext);
	}
	protected void deactivate(){
		System.out.println("########### Quartz Servlet Activator Deactivated ###########");
	}
	
}
