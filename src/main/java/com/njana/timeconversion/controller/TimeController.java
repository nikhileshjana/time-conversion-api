package com.njana.timeconversion.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.zone.ZoneRulesException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.njana.timeconversion.service.TimeConversionService;

@Controller
public class TimeController {
	
	@Autowired
	TimeConversionService tcService;
	
	@GetMapping(path = "/convert-time/v1")
	public ResponseEntity<Object> getCovertedTimev1(@RequestParam(value = "timezone") String timeZone) {
		final Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			responseMap.put(timeZone, tcService.convertTime(URLDecoder.decode(timeZone, "UTF-8")));
		} catch (UnsupportedEncodingException | ZoneRulesException  e) {
			responseMap.put(timeZone, "ERROR Occured in Conversion");
		}
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping(path = "/convert-time/v2")
	public ResponseEntity<Object> getCovertedTimev2(@RequestParam(value = "timezonelist") String timeZoneList) {
		final Map<String, Object> responseMap = new HashMap<String, Object>();
		Arrays.asList(timeZoneList.split("~")).stream().forEach(timeZone -> {
			try {
				responseMap.put(timeZone, tcService.convertTime(URLDecoder.decode(timeZone, "UTF-8")));
			} catch (UnsupportedEncodingException | ZoneRulesException e) {
				responseMap.put(timeZone, "ERROR Occured in Conversion");
			}
		});
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}
}
