package ec.com.ws.rest.tienda.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.ws.rest.tienda.persistence.postgres.entity.ClientOrderEntity;

public interface ClientOrderRepository extends JpaRepository<ClientOrderEntity, Integer>{

}
