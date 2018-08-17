package test.eshop.shoppingcart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.eshop.shoppingcart.bo.ShoppingCartBO;

@Repository
public interface ShoppingCartBORepository extends JpaRepository<ShoppingCartBO,Long>{
	
	public ShoppingCartBO getByShoppingCartId(String shoppingCartId);
	
	public ShoppingCartBO getByUserId(String userId);
	
	public void deleteByShoppingCartId(String shoppingCartId);
	
	public ShoppingCartBO save(ShoppingCartBO shoppingCart);

}
