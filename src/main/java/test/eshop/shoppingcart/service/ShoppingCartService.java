package test.eshop.shoppingcart.service;

import test.eshop.shoppingcart.api.dto.ShoppingCartDTO;

public interface ShoppingCartService {

	public ShoppingCartDTO saveShoppingCart(ShoppingCartDTO shoppingCartDTO);
	
	public ShoppingCartDTO getShoppingCart(String shoppingCartId);
	
	public ShoppingCartDTO getShoppingCartForUser(String userId);
	
	public void deleteShoppingCart(String shoppingCartId);
	
	
}
