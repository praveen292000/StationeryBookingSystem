package com.cg.sbs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sbs.dto.stationaryShop.AddStationaryShopRequest;
import com.cg.sbs.dto.stationaryShop.StationaryShopDetails;
import com.cg.sbs.dto.stationaryShop.UpdateStationaryShopRequest;

import com.cg.sbs.entity.Address;
import com.cg.sbs.entity.StationaryShop;
import com.cg.sbs.service.IStationaryShopService;
import com.cg.sbs.util.Address_Util;
import com.cg.sbs.util.StationaryShop_Util;

@Validated
@RequestMapping("/stationaryShops")
@RestController
public class StationaryShop_Controller {

	@Autowired
	private IStationaryShopService stationaryShopService;

	@Autowired
	private Address_Util addressUtil;

	@Autowired
	private StationaryShop_Util stationaryShopUtil;

	@PostMapping("/add")
	public StationaryShopDetails addStationaryShop(@RequestBody @Valid AddStationaryShopRequest request) {
		StationaryShop res = new StationaryShop();
		res.setStationaryShopId(stationaryShopUtil.generateId());
		res.setContactNumber(request.getContactNumber());
		res.setManagerName(request.getManagerName());
		res.setStationaryShopName(request.getStationaryShopName());
		Address address = addressUtil.getAddress();
		address.setAddressId(addressUtil.generateId());
		address.setArea(request.getArea());
		address.setBuildingName(request.getBuildingName());
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setPincode(request.getPincode());
		address.setState(request.getState());
		address.setStreetNo(request.getStreetNo());
		res.setAddress(address);
		return stationaryShopUtil.toStationaryShopDetails(stationaryShopService.addStationaryShop(res));
	}

	@PutMapping("/update")
	public StationaryShopDetails updateStationaryShop(@RequestBody @Valid UpdateStationaryShopRequest request) {
		StationaryShop res = stationaryShopService.viewStationaryShop(request.getStationaryShopId());
		res.setContactNumber(request.getContactNumber());
		res.setManagerName(request.getManagerName());
		res.setStationaryShopName(request.getStationaryShopName());
		return stationaryShopUtil.toStationaryShopDetails(stationaryShopService.updateStationaryShop(res));
	}

	@DeleteMapping("/delete/{id}")
	public StationaryShopDetails deleteStationaryShop(@PathVariable @NotBlank(message="StationaryShop Id cannot be null") String id) {
		StationaryShop res = stationaryShopService.viewStationaryShop(id);
		return stationaryShopUtil.toStationaryShopDetails(stationaryShopService.removeStationaryShop(res));
	}

	@GetMapping("/view/{id}")
	public StationaryShopDetails viewStationaryShop(@PathVariable @NotBlank(message="StationaryShop Id cannot be null") String id) {

		return stationaryShopUtil.toStationaryShopDetails(stationaryShopService.viewStationaryShop(id));
	}

	@GetMapping("/viewbyname/{name}")
	public List<StationaryShopDetails> viewByNameStationaryShop(@PathVariable @NotBlank(message="StationaryShop name cannot be null") String name) {

		return stationaryShopUtil.toStationaryShopDetailsList(stationaryShopService.viewStationaryShopByItemName(name));
	}

	@GetMapping("/view-all")
	public List<StationaryShopDetails> viewAllStationaryShop() {

		return stationaryShopUtil.toStationaryShopDetailsList(stationaryShopService.viewAllStationaryShops());
	}

	@GetMapping("/viewbylocation/{location}")
	public List<StationaryShopDetails> viewByLocationStationaryShop(@PathVariable @NotBlank(message="Location cannot be null") String location) {

		return stationaryShopUtil.toStationaryShopDetailsList(stationaryShopService.viewNearByStationaryShop(location));
	}

}