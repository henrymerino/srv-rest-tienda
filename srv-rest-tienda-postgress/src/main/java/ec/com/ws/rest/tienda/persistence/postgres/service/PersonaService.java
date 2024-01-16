package ec.com.ws.rest.tienda.persistence.postgres.service;

import javax.persistence.PersistenceException;

import ec.com.ws.rest.tienda.persistence.postgres.entity.PersonaEntity;

public interface PersonaService {
	
	PersonaEntity getPersona(String user) throws PersistenceException;

}
