package com.brix.testbed.monitor.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
@Component
public class ApplicationInfo {
	
	@Value("${product.version}")
	private String versionNumber;
	
	@Value("${product.builder}")
	private String buildBy;
	
	@Value("${product.name}")
	private String applicationName;
	
	@Value("${product.builddate}")
	private String buildDate;

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getBuildBy() {
		return buildBy;
	}

	public void setBuildBy(String buildBy) {
		this.buildBy = buildBy;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	
	
}
