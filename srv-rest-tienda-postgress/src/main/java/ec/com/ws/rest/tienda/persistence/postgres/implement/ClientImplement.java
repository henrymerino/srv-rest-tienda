package ec.com.ws.rest.tienda.persistence.postgres.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.ClientRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.ClientService;

//@Slf4j
@Component
public class ClientImplement implements ClientService {
	
	@Autowired
	private  ClientRepository clientRepository;
	
	@Override
	public ClientEntity findByIdentification(String identification) {
		return clientRepository.findByIdentification(identification);
	}

	@Override
	public ClientEntity saveClient(ClientEntity client) {
		return clientRepository.save(client);
	}

}
