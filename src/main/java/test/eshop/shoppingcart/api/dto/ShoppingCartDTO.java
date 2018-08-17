package test.eshop.shoppingcart.api.dto;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ShoppingCartDTO.ShoppingCartDTOBuilder.class)
public class ShoppingCartDTO{

	public  String shoppingCartId;
	
	public  String userId;
	
	public  Set<ItemDTO> items;
	
	@JsonPOJOBuilder(withPrefix = "")
    public static class ShoppingCartDTOBuilder {

    }
}
