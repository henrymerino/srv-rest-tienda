package ec.com.ws.rest.tienda.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.ws.rest.tienda.persistence.postgres.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{

	@Query("select c from StoreEntity c where c.id = :id")
	StoreEntity getStore(@Param("id") Integer id);

}
