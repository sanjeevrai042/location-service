package com.parkingspot.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResponse {
	
	public AddressResponse() {}
	@JsonProperty("items")
	private List<Items> items;

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
}
