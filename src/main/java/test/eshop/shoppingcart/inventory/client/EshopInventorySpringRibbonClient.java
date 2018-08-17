package test.eshop.shoppingcart.inventory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import test.eshop.shoppingcart.api.dto.ItemDTO;
import test.eshop.shoppingcart.api.dto.ResponseDTO;

@Component
public class EshopInventorySpringRibbonClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(EshopInventorySpringRibbonClient.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${eshop.inventory.serviceid}")
	private String serviceId;
	
	public ItemDTO getProductDetails(String productId) {
		
		String url = String.format("http://%s/v1/inventory/products/%s", serviceId,productId);
		LOGGER.info("URL:"+url);
		ResponseEntity<ResponseDTO<ItemDTO>> restExchange= restTemplate.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<ItemDTO>>() {}, productId);
		ResponseDTO<ItemDTO> responseDTO = restExchange.getBody();
		LOGGER.info("Received product id:"+responseDTO.getData().getProductId());
		return responseDTO.getData();
	}
	
}
