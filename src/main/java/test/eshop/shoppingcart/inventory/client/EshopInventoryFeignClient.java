package test.eshop.shoppingcart.inventory.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import test.eshop.shoppingcart.api.dto.ItemDTO;
import test.eshop.shoppingcart.api.dto.ResponseDTO;

@FeignClient("${eshop.inventory.serviceid}")
public interface EshopInventoryFeignClient {

	@RequestMapping(method=RequestMethod.GET, value="/v1/inventory/products/{productId}",consumes="application/json")
	public ResponseDTO<ItemDTO> getItem(@PathVariable("productId") String productId);
	
}
