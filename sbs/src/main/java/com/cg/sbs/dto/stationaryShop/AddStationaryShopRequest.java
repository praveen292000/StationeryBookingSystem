package com.cg.sbs.dto.stationaryShop;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class AddStationaryShopRequest {
	@NotBlank(message="StationaryShop name cannot be null")
	private String stationaryShopName;
	private String managerName;
	
	@Size(min=10,max=10,message="Contact length for the stationaryShop should be equal to 10")
	private String contactNumber;
	private String buildingName;
	
	private String area;
	private String streetNo;
	private String city;
	private String state;
	private String country;
	@NotBlank(message="StationaryShop pincode cannot be null")
	private String pincode;

	public AddStationaryShopRequest() {
		// Do Nothing
	}

	public String getStationaryShopName() {
		return stationaryShopName;
	}

	public void setStationaryShopName(String stationaryShopName) {
		this.stationaryShopName = stationaryShopName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
