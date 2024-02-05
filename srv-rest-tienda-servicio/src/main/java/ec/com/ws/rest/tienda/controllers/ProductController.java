package ec.com.ws.rest.tienda.controllers;

import java.net.URI;
import java.util.Optional;


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

import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;
import ec.com.ws.rest.tienda.persistence.postgres.entity.StoreEntity;
import ec.com.ws.rest.tienda.persistence.postgres.service.ProductService;
import ec.com.ws.rest.tienda.persistence.postgres.service.StoreService;

@RestController
@RequestMapping("/api/product")
//@Slf4j
public class ProductController {

//	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private StoreService storeService;

	@Autowired
	private ProductService productService;

	@PostMapping("/saveProduct")
	public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity product) {
		Optional<StoreEntity> storeOptional = storeService.findById(product.getStore().getId());
		if (!storeOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		product.setStore(storeOptional.get());

		ProductEntity productSave = productService.saveProduct(product);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productSave.getStore())
				.toUri();
		return ResponseEntity.created(ubicacion).body(productSave);

	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<ProductEntity> updateProduct(@PathVariable Integer id, @RequestBody ProductEntity product) {
		Optional<StoreEntity> storeOptional = storeService.findById(product.getStore().getId());

		if (!storeOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Optional<ProductEntity> productOptional = productService.findById(id);

		if (!productOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		product.setStore(storeOptional.get());
		product.setId(productOptional.get().getId());
		productService.saveProduct(product);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<ProductEntity> deleteProduct(@PathVariable Integer id) {
		Optional<ProductEntity> productOptional = productService.findById(id);
		if (!productOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		productService.deleteProductEntity(productOptional.get());
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<Page<ProductEntity>> listProduct(Pageable pageable) {
//		LOGGER.info("Todos los productos" );
		return ResponseEntity.ok(productService.getAllProductEntiry(pageable));

	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductEntity> getProductById(@PathVariable Integer id) {
		Optional<ProductEntity> productOptional = productService.findById(id);
		if (!productOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		return ResponseEntity.ok(productOptional.get());
	}
}
