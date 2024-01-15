package ec.com.ws.rest.tienda.persistence.postgres.service;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientEntity;

public interface ClientService {
	
	ClientEntity findByIdentification(String identification);
	
	ClientEntity saveClient(ClientEntity client);
	
	

}
