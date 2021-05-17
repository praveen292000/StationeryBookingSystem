package com.cg.sbs.dto.stationaryShop;

import javax.validation.constraints.NotBlank;

public class UpdateStationaryShopRequest {

	@NotBlank(message="StationaryShop Id cannot be null")
	private String stationaryShopId;

	private String stationaryShopName;
	private String managerName;
	private String contactNumber;

	public UpdateStationaryShopRequest() {
		// Do Nothing
	}

	public String getStationaryShopId() {
		return stationaryShopId;
	}

	public void setStationaryShopId(String stationaryShopId) {
		this.stationaryShopId = stationaryShopId;
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

}