package com.brixip.core.web.service.authentication;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.naming.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang.RandomStringUtils;
import com.brixip.core.web.util.EnumWebServiceIF;
import com.brixip.core.web.util.LoginUtility;
import com.google.gson.Gson;

/******
 * 
 * @author pratiksha.datir
 *
 */
public class Auth2Authentication implements EnumWebServiceIF{

	static String authCode = null;
	
	static String redirectURL = null;
	
	static String sessionKey="";
	
	static long loginTime = 0;
	
	static UserDetails userDetails = new UserDetails();
	
	public static final long expirein = 120000;//3 min after login
	
	/*********
	 * Method used to get authorization code for getting access token.
	 * @param authString
	 * @return auth code in response
	 */
	@GET
	@Path("/authorize")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAuthenticationCode(@HeaderParam("authorization")String authString){
		HashMap<String,Object> map = new HashMap<String, Object>();
		String reponseHeader="";
		String reponseHeaderValue="";
		int retrunCode=0;
		String result=null;
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
				if (LoginUtility.getUserPresentOrNot(userName)) {
					if(!LoginUtility.isUserDisabled(userName)){
						userDetails.setUsername(userName);
						userDetails.setPassword(pwd);
						loginTime = System.currentTimeMillis();
						reponseHeader = "code";
						String authcode = RandomStringUtils.randomAlphanumeric(5);
						reponseHeaderValue = authcode;
						authCode = authcode;
						retrunCode = I_REQ_SUCCESS;
						map.put("code",authcode);
						result=new Gson().toJson(map);
					}else{
						reponseHeader = "WWW-Authenticate";
						reponseHeaderValue = "Auth2.0";
						retrunCode = I_DISABLED;
					}
				} else {
					reponseHeader = "WWW-Authenticate";
					reponseHeaderValue = "Auth2.0";
					retrunCode = I_NOT_FOUND;
				}
			} else {
				reponseHeader = "WWW-Authenticate";
				reponseHeaderValue = "Auth2.0";
				retrunCode = I_REQ_UNAUTHORIZED;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			reponseHeader = "WWW-Authenticate";
			reponseHeaderValue = "Auth2.0";
			retrunCode = I_REQ_UNAUTHORIZED;
		}
		return Response.ok().header(reponseHeader, reponseHeaderValue).entity(result).status(retrunCode).build();

	}
	
	/**************
	 * Method used to get access token for accessing API.
	 * @param grantType
	 * @param code
	 * @param redirecturl
	 * @return access token and when it will get expires in
	 */
	@GET
	@Path("/token")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAccessToken(@HeaderParam("grantType")String grantType, @HeaderParam("code")String code,@HeaderParam("redirectURL")String redirecturl){
		redirectURL = redirecturl;
		String tokenType = "bearer";
		int returnCode=0;
		Date expireintime = null;
		String result = null;
		String responseHeader = null;
		String reponseHeaderValue = null;
		Map<String, Object> map = new HashMap<>();
		try{
			if(code.equalsIgnoreCase(authCode) && grantType.equalsIgnoreCase("authorization_code")){
				//refreshToken = UUID.randomUUID().toString();
				sessionKey = LoginUtility.validateUser(userDetails.getUsername(), userDetails.getPassword());
				expireintime = new Date(System.currentTimeMillis() + expirein);
				expireAccessToken();
				map.put("accessToken", sessionKey);
				//map.put("refreshToken", refreshToken);
				map.put("tokenType", tokenType);
				map.put("expiresIn", expireintime);
				responseHeader = "accessToken";
				reponseHeaderValue = sessionKey;
				returnCode = I_REQ_SUCCESS;
				result = new Gson().toJson(map);
			}else {
				responseHeader = "WWW-Authenticate";
				reponseHeaderValue = "Auth2.0";
				returnCode = I_REQ_UNAUTHORIZED;
			}
		}catch(Exception e){
			e.printStackTrace();
			responseHeader = "WWW-Authenticate";
			reponseHeaderValue = "Auth2.0";
			returnCode = I_REQ_UNAUTHORIZED;
		}
		return Response.ok().header(responseHeader, reponseHeaderValue).entity(result).status(returnCode).build();
	}

	/********
	 * Method used to expire session i.e. logout from system after some delay of logged in or getting access token.
	 * Here after 3 minutes access token will get expires and logout from system automatically.
	 */
	private void expireAccessToken() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					if(sessionKey!=null){
						LoginUtility.signOut(sessionKey);
						sessionKey=null;
					}
				} catch (AuthenticationException e) {
					e.printStackTrace();
				}
			}
		};
			Timer timer = new Timer();
			timer.schedule(task, 120000);
	}
}
