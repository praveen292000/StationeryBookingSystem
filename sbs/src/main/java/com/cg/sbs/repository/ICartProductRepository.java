package com.cg.sbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.CartProduct;
import com.cg.sbs.entity.Product;

public interface ICartProductRepository extends JpaRepository<CartProduct,String>{
	
void deleteByCart(Cart cart);
    
    List<CartProduct> findByCart(Cart cart);

    @Query("select product from CartProduct where cart=:cart")
    List<Product>findProductsByCart(@Param("cart") Cart cart);


}
