package ec.com.ws.rest.tienda.persistence.postgres.service;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientOrderEntity;

public interface ClienteOrdenService {

	ClientOrderEntity saveClientOrden(ClientOrderEntity clienteOrden);

}
