package com.brix.testbed.monitor.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestBedMonitorAnalysisController {

	private static final Logger logger = LogManager.getLogger(TestBedMonitorAnalysisController.class);
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value = "/testbed", method = RequestMethod.GET)
	public ModelAndView testbed(@RequestParam("hostname") String hostname,
			@RequestParam("testbedname") String testbedname, HttpSession session) {
		session.setAttribute("host", hostname);
		session.setAttribute("testbed", testbedname);
		ModelAndView model = new ModelAndView("testbed");
		return model;
	}
	
	@RequestMapping(value = "/usermanagement", method = RequestMethod.GET)
	public ModelAndView usermanagement() {
		ModelAndView model = new ModelAndView("usermanagement");
		return model;
	}
}
