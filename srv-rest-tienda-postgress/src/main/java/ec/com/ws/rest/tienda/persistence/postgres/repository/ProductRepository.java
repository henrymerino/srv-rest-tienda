package ec.com.ws.rest.tienda.persistence.postgres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	
	@Query("select c from ProductEntity c where c.id = :id")
	List<ProductEntity> listProducByIdStore(@Param("id") Integer id);

}
