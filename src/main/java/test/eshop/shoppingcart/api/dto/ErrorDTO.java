package test.eshop.shoppingcart.api.dto;

public class ErrorDTO {
	
	private String errorDesc;
	
	private int statusCode;
	
	public ErrorDTO(String errorDesc, int statusCode) {
		this.errorDesc=errorDesc;
		this.statusCode=statusCode;
	}
	
	public String getErrorDesc() {
		return errorDesc;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
