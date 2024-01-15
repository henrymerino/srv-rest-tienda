package ec.com.ws.rest.tienda.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.ws.rest.tienda.persistence.postgres.entity.DetailOrderEntity;

public interface DetailOrderRepository extends JpaRepository<DetailOrderEntity, Integer> {

}
