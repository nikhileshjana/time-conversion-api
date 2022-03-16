package com.njana.timeconversion.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;

import org.springframework.stereotype.Service;

@Service
public class TimeConversionService {

	public String convertTime(final String timeZone) throws ZoneRulesException {
		final ZoneId timeZoneId = ZoneId.of(timeZone);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
		return ZonedDateTime.now(timeZoneId).format(formatter);		
	}

	
}
