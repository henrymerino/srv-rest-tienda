package ec.com.ws.rest.tienda.persistence.postgres.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.DetailOrderEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.DetailOrderRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.DetailOrderService;

//@Slf4j
@Component
public class DetailOrderImplement implements DetailOrderService {

	@Autowired
	private DetailOrderRepository detailOrderRepository;

	@Override
	public DetailOrderEntity saveDetailOrder(DetailOrderEntity detailOrder) {

		return detailOrderRepository.save(detailOrder);
	}

}
