package com.cg.sbs.service;

import java.util.List;

import com.cg.sbs.entity.StationaryShop;

public interface IStationaryShopService {

	 StationaryShop addStationaryShop(StationaryShop res);
	 StationaryShop removeStationaryShop(StationaryShop res);
	 StationaryShop updateStationaryShop(StationaryShop res);
	 StationaryShop viewStationaryShop(String name);
	 List<StationaryShop> viewAllStationaryShops();
	 List<StationaryShop> viewNearByStationaryShop(String location);
	 List<StationaryShop> viewStationaryShopByItemName(String name);
}
