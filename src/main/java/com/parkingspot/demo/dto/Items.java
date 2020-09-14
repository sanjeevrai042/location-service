package com.parkingspot.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title",  "resultType", "address" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

	@JsonProperty("title")
	private String title;
	@JsonProperty("resultType")
	private String resultType;
	@JsonProperty("address")
	private Address address;

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}


	@JsonProperty("resultType")
	public String getResultType() {
		return resultType;
	}

	@JsonProperty("resultType")
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

}