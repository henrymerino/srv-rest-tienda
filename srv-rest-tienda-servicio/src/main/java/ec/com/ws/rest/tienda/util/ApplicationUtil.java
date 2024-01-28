package ec.com.ws.rest.tienda.util;

import java.util.MissingResourceException;

import ec.com.ws.rest.tienda.comun.properties.LectorProperties;

public class ApplicationUtil {

	private static final String PREFIJO_APLICACION = "srv.rest.tienda";
	private static final LectorProperties lectorPropiedades = new LectorProperties(PREFIJO_APLICACION);

	/** The Constant SEPARADOR. */
	private static final String SEPARADOR = "&param";

	private ApplicationUtil() {
	}

	/**
	 * Metodo que permite Recuperar la propiedad referente al key pasado
	 *
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return lectorPropiedades.getPropiedad(key);
		} catch (Exception e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Metodo que permite Recuperar la propiedad referente al key pasado
	 * complementada su perzonalizaci�n con los par�metros adicionales enviados.
	 *
	 * @param key
	 * @param parameters
	 * @return
	 */
	public static String getString(String key, String... parameters) {
		try {
			String message = "";
			String[] partsMessage = lectorPropiedades.getPropiedad(key).split(SEPARADOR);

			for (String part : partsMessage) {
				try {
					int indice = Integer.parseInt(part);

					if (parameters.length > indice) {
						message = message.concat(parameters[indice]);
					}
				} catch (NumberFormatException e) {
					message = message.concat(part);
				}
			}

			return message;
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

}
