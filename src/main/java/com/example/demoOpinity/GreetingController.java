package com.example.demoOpinity;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@GetMapping(value = "/greeting", produces = MediaType.TEXT_PLAIN_VALUE)
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {
		return "greetings";
	}
	
	@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String json() {
		return "json";
	}
}