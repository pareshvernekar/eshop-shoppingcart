package test.eshop.shoppingcart.inventory.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import test.eshop.shoppingcart.api.dto.ItemDTO;
import test.eshop.shoppingcart.api.dto.ResponseDTO;

@Component
public class EshopInventorySpringDiscoveryClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(EshopInventorySpringDiscoveryClient.class);
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@Value("${eshop.inventory.serviceid}")
	private String serviceId;
	
	public ItemDTO getProductDetails(String productId) {
		
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
		if (instances == null || instances.size()==0) throw new RuntimeException("No service available for "+serviceId);
		String url = String.format("%s/v1/inventory/products/%s", instances.get(0).getUri().toString(),productId);
		LOGGER.info("URL:"+url);
		ResponseEntity<ResponseDTO<ItemDTO>> restExchange= restTemplate.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<ItemDTO>>() {}, productId);
		ResponseDTO<ItemDTO> responseDTO = restExchange.getBody();
		LOGGER.info("Received product id:"+responseDTO.getData().getProductId());
		return responseDTO.getData();
	}
	
}
