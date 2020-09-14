package com.parkingspot.demo.service;

import static com.parkingspot.demo.dto.ApplicationConstant.API_KEY;
import static com.parkingspot.demo.dto.ApplicationConstant.DISCOVER_ENDPOINT;
import static com.parkingspot.demo.dto.ApplicationConstant.SEARCH_API;
import static com.parkingspot.demo.dto.ApplicationConstant.TOKEN;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.parkingspot.demo.dto.AddressResponse;
import com.parkingspot.demo.dto.Items;

@Service
public class LocationService {

	private static Logger logger = LoggerFactory.getLogger(LocationService.class);
	private static final ObjectMapper o = new ObjectMapper();
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	private RequestEntity<?> buildRequest(String host, String query) throws URISyntaxException{
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			      .scheme("https").host(host)
			      .query("apiKey="+API_KEY).query("searchtext="+query).build();
		System.out.println(uriComponents);
		return RequestEntity.get(uriComponents.toUri()).build();	
	}
	
	private RequestEntity<?> buildRequest(String host, String query1, String query2) throws URISyntaxException{
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			      .scheme("https").host(host)
			      .query("at="+query1).query("q="+query2).build();
		return RequestEntity.get(uriComponents.toUri()).header("Authorization", "Bearer "+TOKEN).build();	
	}
	
	private ResponseEntity<String> getResponse(RequestEntity<?> req){
		ResponseEntity<String> response = restTemplate.exchange(req, String.class);
		logger.debug("response "+response);
		return response;
	}
	
	
	@Async
    public CompletableFuture<ResponseEntity<String>> getLocationByParking(RequestEntity<?> req) {
		return CompletableFuture.completedFuture(getResponse(req));
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getLocationByResturant(RequestEntity<?> req) {
        return CompletableFuture.completedFuture(getResponse(req));
    }
    
    @Async
    public CompletableFuture<ResponseEntity<String>> getLocationByChargingStation(RequestEntity<?> req) {
        return CompletableFuture.completedFuture(getResponse(req));
    }
	
	
	
	public AddressResponse getResponse(String query) throws IOException, URISyntaxException {
		AddressResponse response = new AddressResponse();
		RequestEntity<?> buildRequest = buildRequest(SEARCH_API,query);
		ResponseEntity<?>  res = getResponse(buildRequest);
		JsonNode root = o.readTree(res.getBody().toString());
		String lattitude = null;
		String longitude = null;
				
		if(root != null) {
			ArrayNode array = (ArrayNode) root.get("Response").get("View");
			
			if(array != null) {
				for(int i = 0; i <array.size(); i++) {
					ArrayNode results = (ArrayNode) array.get(i).get("Result");
					if(results != null && results.size() > 0) {
						for (int j = 0; j < results.size();) {
								lattitude = results.get(j).get("Location").get("DisplayPosition").get("Latitude").asText();
								longitude = results.get(j).get("Location").get("DisplayPosition").get("Longitude").asText();
								break;
						}
					}
				}
			}
		}
		if(lattitude != null && longitude != null) {
			CompletableFuture<ResponseEntity<String>> searchByResturant = getLocationByResturant(buildRequest(DISCOVER_ENDPOINT, lattitude+","+longitude, "resturant"));
			CompletableFuture<ResponseEntity<String>> searchByCharging =  getLocationByResturant(buildRequest(DISCOVER_ENDPOINT, lattitude+","+longitude, "Charging Stations"));
			CompletableFuture<ResponseEntity<String>> searchByParking =   getLocationByResturant(buildRequest(DISCOVER_ENDPOINT, lattitude+","+longitude, "parking"));
			try {
				String body1 = searchByResturant.get().getBody();
				String body2 = searchByCharging.get().getBody();
				String body3 = searchByParking.get().getBody();
			
			
				AddressResponse response1 = o.readValue(body1, AddressResponse.class);
				
				AddressResponse response2 = o.readValue(body2, AddressResponse.class);
				AddressResponse response3 = o.readValue(body3, AddressResponse.class);
				
			List<Items> items = new ArrayList<>();
					
				
			items.addAll(response1.getItems());
			items.addAll(response2.getItems());
			items.addAll(response3.getItems());
			response.setItems(items);
				
			} 
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
} 
