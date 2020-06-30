package com.spring.oauth.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.oauth.bean.UserProfile;

@RestController
public class RestResource {

	@RequestMapping(value="/api/users/me", method = RequestMethod.GET,produces="application/json;")
    @ResponseBody
	public ResponseEntity<Map<String, Object>> profile() 
    {
        //Build some dummy data to return for testing
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@iasys.co.in";
 
        UserProfile profile = new UserProfile();
        profile.setName(user.getUsername());
        profile.setEmail(email);
        
        Map<String, Object> map = new HashMap<>();
        map.put("name", profile.getName());
        map.put("email", profile.getEmail());
        
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
	
	@RequestMapping(value="/", method = RequestMethod.GET,produces="application/json;")
    @ResponseBody
	public ResponseEntity<String> get() 
    {
        
        return new ResponseEntity<String>("<h1>Hello...</h1>", HttpStatus.OK);
    }
}
