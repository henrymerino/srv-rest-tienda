package ec.com.ws.rest.tienda.persistence.postgres.implement;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.com.ws.rest.tienda.persistence.postgres.entity.PersonaEntity;
import ec.com.ws.rest.tienda.persistence.postgres.repository.PersonaRepository;
import ec.com.ws.rest.tienda.persistence.postgres.service.PersonaService;

//@Slf4j
@Component
public class PersonaServiceImplement implements PersonaService{
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public PersonaEntity getPersona(String user) throws PersistenceException {
		return personaRepository.getPersona(user);
	}

}
