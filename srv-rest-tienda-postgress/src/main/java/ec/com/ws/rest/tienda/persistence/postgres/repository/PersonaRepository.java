package ec.com.ws.rest.tienda.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ec.com.ws.rest.tienda.persistence.postgres.entity.PersonaEntity;


public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer>{
	
	@Query("select c from PersonaEntity c where c.usuario = :user")
	PersonaEntity getPersona(@Param("user") String user);

}
