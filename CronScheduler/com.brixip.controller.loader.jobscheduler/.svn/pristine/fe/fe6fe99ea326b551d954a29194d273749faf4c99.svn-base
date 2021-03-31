/**
 * 
 */
package com.brixip.controller.loader.jobscheduler.servlet;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import com.pvmsys.brix.core.common.library.ServiceLogger;
import com.pvmsys.brix.core.session.service.LoginSession;

/**
 * @author vijaysangar
 *
 */
public abstract class BaseServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4087967896884394121L;
	protected static final int L_ERROR = 1;
	protected static final int L_INFO = 2;
	protected static final int L_PARA = 3;
	protected static final int L_E_QUERY = 4;
	protected static final int L_I_QUERY = 5;


	@SuppressWarnings("unchecked")
	protected LoginSession getLoginSession(String strCurrentSession,HttpSession httpSession) {

		LoginSession obj_LoginSession = null;
		Map<String, LoginSession> sessionMap = null;
		sessionMap =  (Map<String, LoginSession>) httpSession.getAttribute("LoginSession");
		if(sessionMap!= null && sessionMap.containsKey(strCurrentSession))
			obj_LoginSession = sessionMap.get(strCurrentSession);

		if(obj_LoginSession == null ){
			sessionMap =  (Map<String, LoginSession>) httpSession.getAttribute("LoginSession");
			if(sessionMap!=null && sessionMap.containsKey(strCurrentSession)){
				sessionMap.remove(strCurrentSession);
				httpSession.removeAttribute("sessionKey");
				httpSession.invalidate();
			}

			obj_LoginSession = null;
		}
		return obj_LoginSession;
	}

	protected String getTimeLog(long startTime,String method){
		long diff = System.currentTimeMillis() - startTime;
		String logTime="EXECUTION_TIME:"+diff+" METHOD_NAME:"+method;
		return logTime;
	}

	public void setLogs(int logLevel, Exception ex, String methodName, List<String> parameters) {
		StringBuilder param = new StringBuilder();
		try {
			switch (logLevel) {
			case L_ERROR: {
				ServiceLogger.error(ex.getMessage());
				break;
			}
			case L_INFO: {
				ServiceLogger.error(ex.getMessage());
				ServiceLogger.info("Method Name:" + methodName);
				break;
			}
			case L_PARA: {
				ServiceLogger.error("Exception:" + ex.getMessage());
				ServiceLogger.info("Method Name:" + methodName);
				for (String parameter : parameters) {
					param.append(parameter);
					param.append("\t");
				}
				ServiceLogger.info("Parameters:" + param.toString());
				break;
			}
			case L_E_QUERY: {
				ServiceLogger.error("Exception:" + ex.getMessage());
				ServiceLogger.info("Method Name:" + methodName);
				for (String parameter : parameters) {
					param.append(parameter);
					param.append("\t");
				}
				ServiceLogger.info("Parameters:" + param.toString());
				break;
			}
			case L_I_QUERY: {
				ServiceLogger.error("Exception:" + ex.getMessage());
				ServiceLogger.info("Method Name:" + methodName);
				for (String parameter : parameters) {
					param.append(parameter);
					param.append("\t");
				}
				ServiceLogger.info("Parameters:" + param.toString());
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
