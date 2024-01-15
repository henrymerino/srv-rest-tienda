package ec.com.ws.rest.tienda.model;

import lombok.Getter;
import lombok.Setter;


public class ResponseData<T> extends ResponseModel{

	/**
	 * Serializado 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private T data;
	
	public ResponseData(String code) {
		super(code);
	}
}
