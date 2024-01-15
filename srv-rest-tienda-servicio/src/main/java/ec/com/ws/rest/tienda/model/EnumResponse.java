package ec.com.ws.rest.tienda.model;

public enum EnumResponse {

	OK("OK"),
	ACTIVO("A"),
	INACTIVO("I"),
	ERROR("ERROR");

	private EnumResponse(String code) {
		this.code = code;
	}

	private String code;

	public String code() {
		return code;
	}

}
