package ec.com.ws.rest.tienda.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer>{
	
	@Query("select c from ClientEntity c where c.identification = :identification")
	ClientEntity findByIdentification(@Param("identification") String identification);

}
