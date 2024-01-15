package ec.com.ws.rest.tienda.persistence.postgres.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientOrderEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.ClientOrderRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.ClienteOrdenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClienteOrdenImplement implements ClienteOrdenService {

	@Autowired
	private ClientOrderRepository clientOrderRepository;

	@Override
	public ClientOrderEntity saveClientOrden(ClientOrderEntity clienteOrden) {
		return clientOrderRepository.save(clienteOrden);
	}


}
