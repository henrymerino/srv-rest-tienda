package ec.com.ws.rest.tienda.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user;
	private String pass;
	private boolean keepSession;

}
