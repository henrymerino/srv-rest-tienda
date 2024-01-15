package ec.com.ws.rest.tienda.persistence.postgres.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.OrderEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.OrderRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.OrdenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrdenImplement implements OrdenService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderEntity saveOrden(OrderEntity client) {
		return orderRepository.save(client);
	}
}
