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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthentication implements EnumWebServiceIF{

	static UserDetails userDetails = new UserDetails();
	
	JWTTokenUtil jwtTokenUtil = new JWTTokenUtil();
	
	@GET
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAccessToken(@HeaderParam("authorization")String authString){
		int returnCode=0;
		String result = null;
		String responseHeader = null;
		String reponseHeaderValue = null;
		Map<String, Object> map = new HashMap<>();
		try{
			if(authString!=null){
				String[] authParts = authString.split("\\s+");
				String authInfo = authParts[1];
				// Decode the data back to original string
				byte[] bytes =Base64.getDecoder().decode(authInfo);
				String decodedAuth = new String(bytes);
				String[] credentials = decodedAuth.split(":");
				String username = credentials[0];
				String password = credentials[1];
				if(LoginUtility.getUserPresentOrNot(username)){
					if(!LoginUtility.isUserDisabled(username)){
						userDetails.setUsername(username);
						userDetails.setPassword(password);
						final String token = jwtTokenUtil.generateToken(userDetails);
						if(token!=null && token.length()!=0){
							returnCode = I_REQ_SUCCESS;
							map.put("token",token);
							result=new Gson().toJson(map);
						}else{
							responseHeader = "WWW-Authenticate";
							reponseHeaderValue = "JWT Authentication";
							returnCode = I_REQ_UNAUTHORIZED;
						}
					}else{
						responseHeader = "WWW-Authenticate";
						reponseHeaderValue = "JWT Authentication";
						returnCode = I_DISABLED;
					}
				}else{
					responseHeader = "WWW-Authenticate";
					reponseHeaderValue = "JWT Authentication";
					returnCode = I_NOT_FOUND;
				}
			}else {
				responseHeader = "WWW-Authenticate";
				reponseHeaderValue = "JWT Authentication";
				returnCode = I_REQ_UNAUTHORIZED;
			}
		}catch(Exception e){
			
		}
		return Response.ok().header(responseHeader, reponseHeaderValue).entity(result).status(returnCode).build();
	} 
	
	public String authenticate(String accessToken){
		String sessionkey = null;
		try{
			boolean isValid = jwtTokenUtil.validateToken(accessToken, 
					jwtTokenUtil.getClaimFromToken(accessToken, Claims::getSubject));//returns username 
			if(isValid){
				sessionkey = LoginUtility.validateUser(userDetails.getUsername(), userDetails.getPassword());
				return sessionkey;
			}
		}catch(Exception e){
			throw new ExpiredJwtException(null, null, "JWT Token has expired");
		}
		return sessionkey;
	}
}
