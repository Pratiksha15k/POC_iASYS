package com.user.details;

import java.io.File;
import java.nio.file.Files;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDetailsForSendEmail {
	File file = null;
	String path="classpath:user.json";
	String content = null;
	@RequestMapping(value="/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String getDetails() {
		try {
			file = ResourceUtils.getFile(path);
			//Read File Content
			content = new String(Files.readAllBytes(file.toPath()));
			//Get a Json String
			System.out.println(content);
			return content;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
