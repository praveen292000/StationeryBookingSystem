package com.cg.sbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sbs.entity.Address;
import com.cg.sbs.entity.StationaryShop;

public interface IStationaryShopRepository extends JpaRepository<StationaryShop, String> {

	List<StationaryShop> findByStationaryShopName(String name);

	List<StationaryShop> findByAddress(Address address);
	
	StationaryShop findStationaryShopByAddress(Address address);

}