package ec.com.ws.rest.tienda.persistence.postgres.service;

import ec.com.ws.rest.tienda.persistence.postgres.entity.OrderEntity;

public interface OrdenService {
	
	OrderEntity saveOrden(OrderEntity client);

}
