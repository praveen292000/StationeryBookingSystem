package com.cg.sbs.entity;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;

@Entity
public class StationaryShop {

	@Id
	private String stationaryShopId;
	private String stationaryShopName;

	@OneToOne
	private Address address;

	@ManyToMany
	private List<Product> productList;

	private String managerName;
	private String contactNumber;

	public StationaryShop() {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
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

	@Override
	public String toString() {
		return "StationaryShop [stationaryShopId=" + stationaryShopId + ", stationaryShopName=" + stationaryShopName + ", address="
				+ address + ", managerName=" + managerName + ", contactNumber=" + contactNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stationaryShopId == null) ? 0 : stationaryShopId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StationaryShop other = (StationaryShop) obj;
		if (stationaryShopId == null) {
			if (other.stationaryShopId != null)
				return false;
		} else if (!stationaryShopId.equals(other.stationaryShopId))
			return false;
		return true;
	}

}