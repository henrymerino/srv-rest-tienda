package ec.com.ws.rest.tienda.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AutorizacionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String usuario;
	private String rol;
	private String token;

}
