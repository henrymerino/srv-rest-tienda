package ec.com.ws.rest.tienda.persistence.postgres.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.ProductRepository;
import ec.com.ws.rest.tienda.persistence.postgres.repository.StoreRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductImplement implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public ProductEntity saveProduct(ProductEntity product) {
		return productRepository.save(product);
	}

	@Override
	public Optional<ProductEntity> editProductEntiry(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public void deleteProductEntity(ProductEntity store) {
		productRepository.delete(store);
	}

	@Override
	public Page<ProductEntity> getAllProductEntiry(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Optional<ProductEntity> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public List<ProductEntity> listProducByIdStore(Integer id) {
		return productRepository.listProducByIdStore(id);
	}

}
