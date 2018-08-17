package test.eshop.shoppingcart.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.eshop.shoppingcart.api.dto.ItemDTO;
import test.eshop.shoppingcart.api.dto.ResponseDTO;
import test.eshop.shoppingcart.api.dto.ShoppingCartDTO;
import test.eshop.shoppingcart.bo.ItemBO;
import test.eshop.shoppingcart.bo.ShoppingCartBO;
import test.eshop.shoppingcart.dao.ShoppingCartBORepository;
import test.eshop.shoppingcart.inventory.client.EshopInventoryFeignClient;
import test.eshop.shoppingcart.inventory.client.EshopInventorySpringDiscoveryClient;
import test.eshop.shoppingcart.inventory.client.EshopInventorySpringRibbonClient;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	
	@Autowired
	ShoppingCartBORepository repo;
	
	@Autowired
	EshopInventorySpringDiscoveryClient springDiscoveryClient;
	
	@Autowired
	EshopInventoryFeignClient feignClient;
	
	@Autowired
	EshopInventorySpringRibbonClient ribbonClient;
	
	@Override
	public ShoppingCartDTO saveShoppingCart(ShoppingCartDTO shoppingCartDTO) {
		// TODO Auto-generated method stub
		
		//@TODO
		//Add code to retrieve a shopping cart for the user
		//and use that id
		String shoppingCartId = shoppingCartDTO.getShoppingCartId();
		if (shoppingCartId == null || "".equals(shoppingCartId)) {
			shoppingCartId=UUID.randomUUID().toString();
		}
		
		Set<ItemBO> itemBOs = new HashSet<>();
		ShoppingCartBO shoppingCartBO = ShoppingCartBO.builder()
							.shoppingCartId(shoppingCartId)
							.userId(shoppingCartDTO.getUserId())
							.build();
		
		for (ItemDTO item : shoppingCartDTO.getItems()) {
			//ItemDTO itemDTO = springDiscoveryClient.getProductDetails(item.getProductId());
			//ItemDTO itemDTO = ribbonClient.getProductDetails(item.getProductId());
			ResponseDTO<ItemDTO> response = feignClient.getItem(item.getProductId());
			ItemDTO itemDTO = null;
			if (response != null) {
				itemDTO = response.getData();
			}
			LOGGER.info("PRICE:" + itemDTO.getPrice());

			itemBOs.add(ItemBO.builder().price(itemDTO.getPrice()).productId(item.getProductId())
					.quantity(item.getQuantity()).shoppingCart(shoppingCartBO).build());
		}
		shoppingCartBO.setItems(itemBOs);
		repo.save((ShoppingCartBO)shoppingCartBO);
		return shoppingCartDTO;
	}

	@Override
	public ShoppingCartDTO getShoppingCart(String shoppingCartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartDTO getShoppingCartForUser(String userId) {
		// TODO Auto-generated method stub
		ShoppingCartBO bo = repo.getByUserId(userId);
		if (bo == null) {
			throw new RuntimeException("No shopping cart found for user:"+userId);
		}
		Set<ItemDTO> itemDTOs = new HashSet<>();
		ShoppingCartDTO dto = ShoppingCartDTO.builder()
							.shoppingCartId(bo.getShoppingCartId())
							.userId(bo.getUserId())
							.items(itemDTOs)
							.build();
		
		for (ItemBO item:bo.getItems()) {
			ItemDTO idto =  ItemDTO.builder()
							.productId(item.getProductId())
							.price(item.getPrice())
							.quantity(item.getQuantity())
							.shoppingCart(dto)
							.build();
			dto.getItems().add(idto);
		}
		return dto;
	}

	@Override
	public void deleteShoppingCart(String shoppingCartId) {
		repo.deleteByShoppingCartId(shoppingCartId);
	}

}
