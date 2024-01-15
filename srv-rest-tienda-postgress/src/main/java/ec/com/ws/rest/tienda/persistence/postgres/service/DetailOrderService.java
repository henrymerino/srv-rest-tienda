package ec.com.ws.rest.tienda.persistence.postgres.service;

import ec.com.ws.rest.tienda.persistence.postgres.entity.DetailOrderEntity;

public interface DetailOrderService {

	DetailOrderEntity saveDetailOrder(DetailOrderEntity detailOrder);

}
