package ec.com.ws.rest.tienda.controllers;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.ws.rest.tienda.dto.OrdenClientDto;
import ec.com.ws.rest.tienda.model.EnumResponse;
import ec.com.ws.rest.tienda.model.ResponseModel;
import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientOrderEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.DetailOrderEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.OrderEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;
import ec.com.ws.rest.tienda.persistence.postgres.service.ClientService;
import ec.com.ws.rest.tienda.persistence.postgres.service.ClienteOrdenService;
import ec.com.ws.rest.tienda.persistence.postgres.service.DetailOrderService;
import ec.com.ws.rest.tienda.persistence.postgres.service.OrdenService;

@RestController
@RequestMapping("/api/orden")
//@Slf4j
public class OrdenClienteController{
	
//	 private static final Logger LOGGER = LogManager.getLogger(OrdenClienteController.class);

	@Autowired
	private ClientService clientService;

	@Autowired
	private OrdenService ordenService;

	@Autowired
	private DetailOrderService detailOrderService;

	@Autowired
	private ClienteOrdenService clienteOrdenService;

	@PostMapping("/saveClientOrden")
	public ResponseEntity<ResponseModel> saveOrdenClient(@RequestBody OrdenClientDto ordenClient) {

		ResponseModel response = new ResponseModel(EnumResponse.OK.code());
		try {
			ClientEntity client = clientService.findByIdentification(ordenClient.getClient().getIdentification());

			if (client == null || client.getId() == null) {
				client = clientService.saveClient(ordenClient.getClient());
			}

			OrderEntity orden = new OrderEntity();
			orden.setDateOrder(new Date());
			orden.setState(EnumResponse.ACTIVO.code());
			OrderEntity ordenSave = ordenService.saveOrden(orden);

			ClientOrderEntity clientOrden = new ClientOrderEntity();
			clientOrden.setClient(client);
			clientOrden.setOrden(ordenSave);
			clientOrden.setDateClientOrder(new Date());
			clienteOrdenService.saveClientOrden(clientOrden);

			if (ordenClient.getListProduct() != null && ordenClient.getListProduct().size() > 0) {
				for (ProductEntity prod : ordenClient.getListProduct()) {
					DetailOrderEntity detail = new DetailOrderEntity();
					detail.setAmount(0);
					detail.setDescription("Compra");
					detail.setOrden(ordenSave);
					detail.setProduct(prod);
					detailOrderService.saveDetailOrder(detail);
				}
			} else {
				DetailOrderEntity detail = new DetailOrderEntity();
				detail.setAmount(0);
				detail.setDescription("Comprea");
				detail.setOrden(ordenSave);
				detail.setProduct(null);
				detailOrderService.saveDetailOrder(detail);
			}

		} catch (Exception e) {
			e.printStackTrace();
//			LOGGER.error("Error:" + e.getMessage());
			response = new ResponseModel(EnumResponse.ERROR.code());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
