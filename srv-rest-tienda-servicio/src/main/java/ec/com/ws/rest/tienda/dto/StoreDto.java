package ec.com.ws.rest.tienda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;
import lombok.Data;

@Data
public class StoreDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;

	private Date dateCreation;
	
	private List<ProductEntity> listProd = new ArrayList<>();
	
	
	

}
