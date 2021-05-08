package com.cg.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sbs.entity.Product;

/*
* Author : Praveen K
* Description : This is IProductRepository
*/
public interface IProductRepository extends JpaRepository<Product, String> {

}
