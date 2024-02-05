package ec.com.ws.rest.tienda.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ec.com.ws.rest.tienda.dto.StoreDto;
import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.StoreEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.ProductRepository;
import ec.com.ws.rest.tienda.persistence.postgres.repository.StoreRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.StoreService;
//import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/store")
//@Slf4j
public class StoreController {
	
	 private static final Logger LOGGER = LogManager.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/saveStore")
	public ResponseEntity<StoreEntity> saveStore(@RequestBody StoreEntity store) {
		StoreEntity saveStore = storeService.saveStore(store);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveStore.getId())
				.toUri();
		return ResponseEntity.created(ubicacion).body(saveStore);
	}

	@PutMapping("/editStore/{id}")
	public ResponseEntity<StoreEntity> editStore(@PathVariable Integer id, @RequestBody StoreEntity store) {
		Optional<StoreEntity> storeOptional = storeService.editStoreEntiry(id);
		if (!storeOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		store.setId(storeOptional.get().getId());
		storeService.saveStore(store);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deleteStore/{id}")
	public ResponseEntity<StoreEntity> deleteStore(@PathVariable Integer id) {
		Optional<StoreEntity> storeOptional = storeService.editStoreEntiry(id);
		if (!storeOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		storeService.deleteStoreEntity(storeOptional.get());
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/getStore/{id}")
	public ResponseEntity<StoreEntity> getStoreById(@PathVariable Integer id) {
		Optional<StoreEntity> storeOptional = storeService.editStoreEntiry(id);
		if (!storeOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		return ResponseEntity.ok(storeOptional.get());
	}

	@GetMapping("/getAllStore")
	public ResponseEntity<Page<StoreEntity>> getAllStoreById(Pageable pageable) {
//		log.info("Obtiene todas las tiendas");
		ResponseEntity<Page<StoreEntity>> pages = null;
		try {
//			List<ProductEntity> produ = productRepository.findAll();
			pages = ResponseEntity.ok(storeRepository.findAll(pageable));
		} catch (Exception e) {
			e.printStackTrace();
//			LOGGER.error(e.getMessage());
		}

		return pages;
	}

	@GetMapping("/getAllStores")
	public ResponseEntity<List<StoreEntity>> getAllStoreById() {
		LOGGER.info("Obtiene todas las tiendas");
		return ResponseEntity.ok(storeRepository.findAll());
	}

	@GetMapping("/getStoreById/{id}")
	public ResponseEntity<StoreDto> getStoreId(@PathVariable Integer id) {
		StoreDto storeDto = new StoreDto();
		List<ProductEntity> listProd = null;
		StoreEntity store = null;
		store = storeService.getStore(id);
		listProd = productRepository.listProducByIdStore(id);
		if (store != null) {
			storeDto.setName(store.getName());
			storeDto.setDateCreation(store.getDateCreation());
			store.setProducts(null);
			if (listProd != null) {
				storeDto.setListProd(listProd);
			}
			return ResponseEntity.ok(storeDto);
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}

	}

}
