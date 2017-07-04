package com.microsoft.ostc.springcloud.simplesample;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SpringBootApplication
public class SampleController {
	public static final boolean MOVE = false;
	//public static final boolean MOVE = true;
	
	private static ObjectMapper mapper = new ObjectMapper();

	@ResponseBody
    @RequestMapping(value = "/move")
	InstanceResponseBody moveGet(@RequestParam("index") int index) {
        return new InstanceResponseBody("null", index, MOVE);
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleController.class, args);
	}
	
	private class InstanceResponseBody {
		public boolean move = false;
		public int index = 0;
		public String instance = "";
		
		public InstanceResponseBody(String instance, int index, boolean move) {
			this.instance = instance;
			this.index = index;
			this.move = move;
		}
	}
}