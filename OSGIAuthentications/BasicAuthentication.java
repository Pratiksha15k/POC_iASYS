package com.brixip.core.web.service.authentication;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.brixip.core.web.util.EnumWebServiceIF;
import com.brixip.core.web.util.LoginUtility;
import com.google.gson.Gson;

public class BasicAuthentication implements EnumWebServiceIF{
	
	//private static final String AUTHORIZATION = "authorization";
	/**
     * This API is used to Authentication user credentials. 
     * <p>
     * If no user authenticate successfully it will return token.
     *
     * @param 
     * @return ResultRO of Project List
     * @since 1.0
     */
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response authentication(@HeaderParam("authorization")String authString){
	String reponseHeader="";
	String reponseHeaderValue="";
	int retrunCode=0;
	String sessionKey="";
	String result=null;
	Map<String,Object> map = new HashMap<String, Object>();
	try{
		if(authString!=null){
			String[] authParts = authString.split("\\s+");
			String authInfo = authParts[1];
			// Decode the data back to original string
			byte[] bytes =Base64.getDecoder().decode(authInfo);
			String decodedAuth = new String(bytes);
			String[] credentials = decodedAuth.split(":");
			String userName = credentials[0];
			String pwd = credentials[1];
			sessionKey = LoginUtility.validateUser(userName, pwd);
			if (sessionKey != null && !sessionKey.isEmpty()) {
				reponseHeader = "accessToken";
				reponseHeaderValue = sessionKey;
				retrunCode = I_REQ_SUCCESS;
				map.put("accessToken",sessionKey);
				result=new Gson().toJson(map);
			} else {
				reponseHeader = "WWW-Authenticate";
				reponseHeaderValue = "Basic";
				retrunCode = I_REQ_UNAUTHORIZED;
			}
		} else {
			reponseHeader = "WWW-Authenticate";
			reponseHeaderValue = "Basic";
			retrunCode = I_REQ_UNAUTHORIZED;
		}
	}catch(Exception ex){
		ex.printStackTrace();
		reponseHeader = "WWW-Authenticate";
		reponseHeaderValue = "Basic";
		retrunCode = I_REQ_UNAUTHORIZED;
	}
	return Response.ok().header(reponseHeader, reponseHeaderValue).entity(result).status(retrunCode).build();
	
	}

}
