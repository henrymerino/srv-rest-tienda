package ec.com.ws.rest.tienda.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.ws.rest.tienda.persistence.postgres.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

}
