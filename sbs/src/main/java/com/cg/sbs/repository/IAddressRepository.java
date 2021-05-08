package com.cg.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sbs.entity.Address;

public interface IAddressRepository extends JpaRepository<Address, String> {

	Address findAddressByArea(String location);

}
