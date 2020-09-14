package com.parkingspot.demo.rest;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspot.demo.dto.AddressResponse;
import com.parkingspot.demo.service.LocationService;

@RestController()
public class LocationController {

	
	@Autowired
	private LocationService service;
	
	@GetMapping("/locations")
	public ResponseEntity<AddressResponse> getAddress(@RequestParam("query") String query) throws IOException, URISyntaxException{
		AddressResponse response = service.getResponse(query);
		return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
		
		
	}
}
