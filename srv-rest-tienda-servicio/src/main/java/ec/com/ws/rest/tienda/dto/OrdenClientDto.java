package ec.com.ws.rest.tienda.dto;

import java.io.Serializable;
import java.util.List;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;
import lombok.Data;

@Data
public class OrdenClientDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ClientEntity client;
	
	List<ProductEntity> listProduct;
}
