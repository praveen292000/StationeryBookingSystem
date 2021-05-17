package com.cg.sbs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sbs.entity.Address;
import com.cg.sbs.entity.StationaryShop;
import com.cg.sbs.exception.InvalidStationaryShopException;
import com.cg.sbs.exception.InvalidStationaryShopLocationException;
import com.cg.sbs.exception.InvalidStationaryShopNameException;
import com.cg.sbs.exception.StationaryShopNotFoundException;

import com.cg.sbs.repository.IAddressRepository;
import com.cg.sbs.repository.IStationaryShopRepository;

@Service
public class StationaryShopServiceImp implements IStationaryShopService {
	
	private static final Logger Log=LoggerFactory.getLogger(StationaryShopServiceImp.class);
	
	
	@Autowired
	private IStationaryShopRepository stationaryShopRepository;

	@Autowired
	private IAddressRepository addressRepository;

	/**
	 * scenario : Adding the StationaryShop input: res Object is passed in the parameter
	 * expectation: StationaryShop should be added
	 */

	@Override
	public StationaryShop addStationaryShop(StationaryShop res) {
		Log.info("added stationaryShop sing addStationaryShop function");
		validateStationaryShop(res);
		Address address = res.getAddress();
		addressRepository.save(address);
		StationaryShop addStationaryShop = stationaryShopRepository.save(res);
		return addStationaryShop;
	}

	/**
	 * scenario : Removing the stationaryShop input: res Object is passed in the
	 * parameter expectation: If the stationaryShop is present in the Database, then
	 * stationaryShop is getting removed, or else an exception is thrown
	 */

	@Override
	public StationaryShop removeStationaryShop(StationaryShop res) {
		validateStationaryShop(res);
		String stationaryShopId = res.getStationaryShopId();
		boolean exists = stationaryShopRepository.existsById(stationaryShopId);
		if (!exists) {
			throw new StationaryShopNotFoundException("StationaryShop with id not present=" + res.getStationaryShopId());
		}
		stationaryShopRepository.delete(res);
		return res;
	}

	/**
	 * scenario : Updating the stationaryShop input: res Object is passed in the
	 * parameter expectation: If the stationaryShop is present in the Database, then
	 * stationaryShop is getting updated, or else an exception is thrown
	 */

	@Override
	public StationaryShop updateStationaryShop(StationaryShop res) {
        Log.info("inside updaetStationaryShop value passed");
		validateStationaryShop(res);
		String stationaryShopId = res.getStationaryShopId();
		boolean exists = stationaryShopRepository.existsById(stationaryShopId);
		if (!exists) {
			throw new StationaryShopNotFoundException("StationaryShop with id not present=" + res.getStationaryShopId());
		}
		StationaryShop updatestationaryShop = stationaryShopRepository.save(res);
		return updatestationaryShop;
	}

	/**
	 * scenario : viewing the stationaryShop input: id Object is passed in the parameter
	 * expectation: If the StationaryShop is present in the Database, then stationaryShop is
	 * getting viewed, or else an exception is thrown
	 */
	@Override
	public StationaryShop viewStationaryShop(String id) {
		Log.info("viewStationaryShop function will fetch stationaryShop");
		Optional<StationaryShop> viewStationaryShop = stationaryShopRepository.findById(id);
		if (!viewStationaryShop.isPresent()) {
			throw new StationaryShopNotFoundException("StationaryShop with id not present=" + id);
		}
		return viewStationaryShop.get();
	}

	/**
	 * scenario : viewing all the stationaryShops
	 */

	@Override
	public List<StationaryShop> viewAllStationaryShops() {
		Log.info("fetch all stationaryShop using this function");
		return stationaryShopRepository.findAll();
	}

	/**
	 * scenario : viewing the list of all near by stationaryShops input: location Object
	 * is passed in the parameter expectation: List of stationaryShops should be
	 * returned with respect to the address/location
	 */
	@Override
	public List<StationaryShop> viewNearByStationaryShop(String pincode) {
		/*List<Address> address = addressRepository.findByPincode(pincode);
		List<StationaryShop>res=new ArrayList<>();
		for(Address a:address)
		{
			res.add(stationaryShopRepository.findStationaryShopByAddress(a));
		}*/
		return null;//res;

	}

	/**
	 * scenario : viewing the list of stationaryShop by the ItemName input: name Object
	 * is passed in the parameter expectation: List of stationaryShop should be returned
	 */

	@Override
	public List<StationaryShop> viewStationaryShopByItemName(String name) {
		List<StationaryShop> list = stationaryShopRepository.findByStationaryShopName(name);
		return list;
	}

	void validateStationaryShopName(String name) {
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			throw new InvalidStationaryShopNameException("StationaryShop Name can't be null or empty");
		}
	}

	void validateStationaryShop(StationaryShop stationaryShop) {
		if (stationaryShop == null) {
			throw new InvalidStationaryShopException("StationaryShop can't be null ");
		}

		validateStationaryShopName(stationaryShop.getStationaryShopName());
		validateStationaryShopLocation(stationaryShop.getAddress().getArea());
	}

	void validateStationaryShopLocation(String location) {
		if (location == null || location.isEmpty() || location.trim().isEmpty()) {
			throw new InvalidStationaryShopLocationException("StationaryShop location  can't be null or empty");
		}

	}

}