package com.cg.sbs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.sbs.dto.stationaryShop.StationaryShopDetails;
import com.cg.sbs.entity.StationaryShop;

@Component
public class StationaryShop_Util {
	
	public StationaryShop getStationaryShop() {
		return new StationaryShop();
	}
	
	public StationaryShopDetails toStationaryShopDetails(StationaryShop res) {
		StationaryShopDetails resNew = new StationaryShopDetails();
		resNew.setContactNumber(res.getContactNumber());
		resNew.setManagerName(res.getManagerName());
		resNew.setStationaryShopId(res.getStationaryShopId());
		resNew.setStationaryShopName(res.getStationaryShopName());
		return resNew;
		}
	
	public List<StationaryShopDetails> toStationaryShopDetailsList(List<StationaryShop> res){
		List<StationaryShopDetails> restauarntDetails = new ArrayList<>();
		for(StationaryShop r : res) {
			restauarntDetails.add(toStationaryShopDetails(r));
		}
		return restauarntDetails;
	}
	
	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}
}