package test.eshop.shoppingcart.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@Builder
@JsonDeserialize(builder = ItemDTO.ItemDTOBuilder.class)
public class ItemDTO{
	
	String productId;
	
	BigDecimal price;
	
	int quantity;
	
	@EqualsAndHashCode.Exclude
	ShoppingCartDTO shoppingCart;
	
	@JsonPOJOBuilder(withPrefix = "")
    public static class ItemDTOBuilder {

    }
}
