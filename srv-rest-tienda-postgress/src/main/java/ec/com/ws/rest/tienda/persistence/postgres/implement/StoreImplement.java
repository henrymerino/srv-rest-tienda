package ec.com.ws.rest.tienda.persistence.postgres.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.StoreEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.StoreRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.StoreService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StoreImplement implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public StoreEntity saveStore(StoreEntity store) {
		return storeRepository.save(store);
	}

	@Override
	public Optional<StoreEntity> editStoreEntiry(Integer id) {
		return storeRepository.findById(id);
//		return storeRepository.getStore(id);
	}

	@Override
	public void deleteStoreEntity(StoreEntity store) {
		storeRepository.delete(store);
	}

	@Override
	public Page<StoreEntity> getAllStoreEntiry(Pageable pageable) {
		return storeRepository.findAll(pageable);
	}

	@Override
	public Optional<StoreEntity> findById(Integer id) {
		return storeRepository.findById(id);
	}

	@Override
	public StoreEntity getStore(Integer id) {
		return storeRepository.getStore(id);
	}
	


}
