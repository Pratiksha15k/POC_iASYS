package com.logclenaup.deletefiles;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LogCleanup {
	public static void main(String[] args) {
		try{
			cleanupLog(10);
		}catch(Exception e){

		}
	}
	public static void cleanupLog(int days){
		try{
			String extension = "log";
			String startsWith = "brixlog";
			String path = "C:/brixlog/";
			List<Path> paths = Files.list(Paths.get(path)).collect(Collectors.toList());
			LocalDateTime now = LocalDateTime.now();  
			int day = new Date().getDate();
			day = day - days;
			String date = now.getYear() + "-" + ((new Date().getMonth())+1) + "-" + day;
			//LocalDate beforeDate = LocalDate.parse(date);
			for (Path entry : paths) {
				BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);
				Instant instant = Instant.parse(attributes.lastModifiedTime().toString());
				LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
				if (entry.getFileName().toString().startsWith(startsWith) && entry.getFileName().toString().endsWith(extension) && ldt.toLocalDate().isBefore(LocalDate.parse((CharSequence) new Date()))) {
					System.out.println("\t Marked for deletion: Yes"); 
					File file = new File(entry.toString());
					if(file.exists()){
						boolean result = file.delete();
						System.out.println("result = "+result);
					}
				}else {
					System.out.println("\t Marked for deletion: No");
				}
				System.out.println("\n");
			}
		}catch(Exception e){
			System.out.println("Message :"+e.getMessage());
		}
	}
}
