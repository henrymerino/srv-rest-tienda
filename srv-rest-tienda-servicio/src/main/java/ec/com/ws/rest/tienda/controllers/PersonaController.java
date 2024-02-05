package ec.com.ws.rest.tienda.controllers;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.ws.rest.tienda.auth.AuthorizationFilter;
import ec.com.ws.rest.tienda.dto.AutorizacionDto;
import ec.com.ws.rest.tienda.dto.LoginDto;
import ec.com.ws.rest.tienda.exception.WsServiceException;
import ec.com.ws.rest.tienda.model.EnumResponse;
import ec.com.ws.rest.tienda.model.ResponseData;
import ec.com.ws.rest.tienda.persistence.postgres.entity.PersonaEntity;
import ec.com.ws.rest.tienda.persistence.postgres.service.PersonaService;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
//	private static final Logger LOGGER = LogManager.getLogger(PersonaController.class);

	@Autowired
	private PersonaService personaService;

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseData<AutorizacionDto>> getLogin(@RequestBody LoginDto request) {

		ResponseData<AutorizacionDto> response = new ResponseData<>(EnumResponse.OK.code());
		try {
			PersonaEntity persona = personaService.getPersona(request.getUser());

			if (!request.getPass().equals(persona.getClave())) {
				throw new WsServiceException("Persona no autorizada.");
			}
			if (Objects.isNull(persona.getIdRol())) {
				throw new WsServiceException("Perfil no registrado.");
			}
			AutorizacionDto autorizado = new AutorizacionDto();
			autorizado.setId(persona.getIdPersona());
			autorizado.setUsuario(persona.getUsuario());
			autorizado.setRol(persona.getIdRol().getDescripcion());

			String token = AuthorizationFilter.getJWTToken(persona.getUsuario(), persona.getIdRol().getDescripcion(),
					request.isKeepSession());
			autorizado.setToken(token);
			response.setData(autorizado);
		} catch (Exception e) {
//			LOGGER.error("Error:" + e.getMessage());
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
