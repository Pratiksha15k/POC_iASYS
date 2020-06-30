package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
public class ControllerClass {

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getStaticData/{reqtime}/{recCount}")
	public String getStaticData(@PathVariable("reqtime") long reqtime,@PathVariable("recCount") int recCount ) {
		long processInitialTime = System.currentTimeMillis();
		long requestGetTime = System.currentTimeMillis() - (reqtime);

		Gson gson = new Gson();
		
		@SuppressWarnings("rawtypes")
		List data = new ArrayList();
		HashMap rowData = new HashMap();
		{
			for (int i = 1 ; i <= recCount ; i++)
			{		
					rowData.put("attribute1", "Iasys1"+i);
			rowData.put("attribute2", "Iasys2"+i);
			rowData.put("attribute3", "Iasys3"+i);
			rowData.put("attribute4", "Iasys4"+i);
			rowData.put("attribute5", "Iasys5"+i);
			rowData.put("attribute6", "Iasys6"+i);
			rowData.put("attribute7", "Iasys7"+i);
			rowData.put("attribute8", "Iasys8"+i);
			rowData.put("attribute9", "Iasys9"+i);
			rowData.put("attribute10", "Iasys10"+i);
			data.add(rowData);
			}
		}
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("rowData", data);
		
		
		long calculatedProecessedTimeSec = System.currentTimeMillis()- processInitialTime;
		dataMap.put("processedTime",""+calculatedProecessedTimeSec+" (ms)");
		dataMap.put("requestTime",""+requestGetTime+" (ms)");
		dataMap.put("responseTime", ""+((System.currentTimeMillis())));
		
		/*System.out.println("processedTime:"+calculatedProecessedTimeSec);
		System.out.println("requestTime:"+requestGetTime);
		System.out.println("responseTime:"+(((System.currentTimeMillis()))));*/
		String g = gson.toJson(dataMap);
		
		return g;
	}
	
	@GetMapping(value = "/getProject")
	public String getProjects() {
		return "Hello";
	}
	
	
}
