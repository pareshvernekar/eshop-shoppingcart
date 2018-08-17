package test.eshop.shoppingcart.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.eshop.shoppingcart.api.dto.ErrorDTO;
import test.eshop.shoppingcart.api.dto.ResponseDTO;
import test.eshop.shoppingcart.api.dto.ShoppingCartDTO;
import test.eshop.shoppingcart.service.ShoppingCartService;

@RestController
@RequestMapping(value="/v1/shoppingCart")
public class ShoppingCartController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	ShoppingCartService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
		
		LOGGER.debug("Received request to save shoppingcart");
		try {
			service.saveShoppingCart(shoppingCartDTO);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.CREATED);
		} catch (Exception ex) {
			LOGGER.error("Error when saving shopping cart:"+ex.getMessage(),ex);
			ErrorDTO error = new ErrorDTO("Error saving shopping cart",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<ShoppingCartDTO> responseDTO = new ResponseDTO<ShoppingCartDTO>(null, error);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
		
		LOGGER.debug("Received request to save shoppingcart");
		try {
			service.saveShoppingCart(shoppingCartDTO);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error when saving shopping cart:"+ex.getMessage(), ex);
			ErrorDTO error = new ErrorDTO("Error saving shopping cart",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<ShoppingCartDTO> responseDTO = new ResponseDTO<ShoppingCartDTO>(null, error);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{shoppingCartId}")
	public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable(value = "shoppingCartId") String shoppingCartId) {
		
		LOGGER.debug("Received request to retrieve shoppingcart");
		try {
			service.getShoppingCart(shoppingCartId);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error when saving shhopping cart:"+ex.getMessage());
			ErrorDTO error = new ErrorDTO("Error saving shopping cart",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<ShoppingCartDTO> responseDTO = new ResponseDTO<ShoppingCartDTO>(null, error);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{shoppingCartId}", method = RequestMethod.DELETE)
	public ResponseEntity<ShoppingCartDTO> deleteShoppingCart(@PathVariable(value = "shoppingCartId") String shoppingCartId) {
		
		LOGGER.debug("Received request to delete shoppingcart");
		try {
			service.deleteShoppingCart(shoppingCartId);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error when deleting shopping cart:"+ex.getMessage());
			ErrorDTO error = new ErrorDTO("Error deleting shopping cart",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<ShoppingCartDTO> responseDTO = new ResponseDTO<ShoppingCartDTO>(null, error);
			return new ResponseEntity<ShoppingCartDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
