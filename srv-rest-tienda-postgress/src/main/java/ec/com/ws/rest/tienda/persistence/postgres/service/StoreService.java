package ec.com.ws.rest.tienda.persistence.postgres.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ec.com.ws.rest.tienda.persistence.postgres.entity.StoreEntity;

public interface StoreService {

	StoreEntity saveStore(StoreEntity store);

	Optional<StoreEntity> editStoreEntiry(Integer id);

	void deleteStoreEntity(StoreEntity store);

	Page<StoreEntity> getAllStoreEntiry(Pageable pageable);

	Optional<StoreEntity> findById(Integer id);
	
	StoreEntity getStore(Integer id);

}
