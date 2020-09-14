
package com.parkingspot.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "label", "countryCode", "countryName", "stateCode", "state", "county", "city", "district",
		"subdistrict", "postalCode" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	@JsonProperty("label")
	private String label;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("countryName")
	private String countryName;
	@JsonProperty("stateCode")
	private String stateCode;
	@JsonProperty("state")
	private String state;
	@JsonProperty("county")
	private String county;
	@JsonProperty("city")
	private String city;
	@JsonProperty("district")
	private String district;
	@JsonProperty("subdistrict")
	private String subdistrict;
	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("stateCode")
	public String getStateCode() {
		return stateCode;
	}

	@JsonProperty("stateCode")
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("county")
	public String getCounty() {
		return county;
	}

	@JsonProperty("county")
	public void setCounty(String county) {
		this.county = county;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("district")
	public String getDistrict() {
		return district;
	}

	@JsonProperty("district")
	public void setDistrict(String district) {
		this.district = district;
	}

	@JsonProperty("subdistrict")
	public String getSubdistrict() {
		return subdistrict;
	}

	@JsonProperty("subdistrict")
	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
