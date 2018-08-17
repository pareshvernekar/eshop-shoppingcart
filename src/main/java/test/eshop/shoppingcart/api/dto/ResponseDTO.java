package test.eshop.shoppingcart.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO<T>{

	T data;
	public T getData() {
		return data;
	}

	public ErrorDTO getError() {
		return error;
	}

	ErrorDTO error;
	
	
	public ResponseDTO(@JsonProperty("data") T data) {
		this.data = data;
	}
	
	@JsonCreator
	public ResponseDTO(@JsonProperty("data") T data,@JsonProperty("error") ErrorDTO error) {
		this.data = data;
		this.error=error;
	}
	
	
}
