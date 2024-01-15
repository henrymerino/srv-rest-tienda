package ec.com.ws.rest.tienda.persistence.postgres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;

public interface ProductService {

	ProductEntity saveProduct(ProductEntity store);

	Optional<ProductEntity> editProductEntiry(Integer id);

	void deleteProductEntity(ProductEntity store);

	Page<ProductEntity> getAllProductEntiry(Pageable pageable);
	
	Optional<ProductEntity> findById(Integer id);
	
	List<ProductEntity> listProducByIdStore(Integer id);

}
