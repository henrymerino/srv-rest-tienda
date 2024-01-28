package ec.com.ws.rest.tienda.comun.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LectorProperties {

	private static final String CONF = "conf.";
	private static final String PROP = ".properties";
	private String prefijoAplicacion;
	private Set<String> keys;

	public LectorProperties(String prefijoAplicacion) {
		this.prefijoAplicacion = prefijoAplicacion;
		this.loadProperties();
	}

	private void loadProperties() {
		System.out.println("Inicia carga de propiedades prefijo: " + this.prefijoAplicacion);
		String pathProperties = System.getProperty("conf.".concat(this.prefijoAplicacion));
		if (null != pathProperties && pathProperties.endsWith(".properties")) {
			try {
				InputStream input = new FileInputStream(pathProperties);
				Throwable var3 = null;

				try {
					System.out.println("Carga de propiedades file: " + pathProperties);
					System.getProperties().load(input);
					this.generateKeys();
				} catch (Throwable var13) {
					var3 = var13;
					throw var13;
				} finally {
					if (input != null) {
						if (var3 != null) {
							try {
								input.close();
							} catch (Throwable var12) {
								var3.addSuppressed(var12);
							}
						} else {
							input.close();
						}
					}

				}
			} catch (IOException var15) {
				System.out.println("Error Carga de propiedades file: " + pathProperties);
				var15.printStackTrace();
			}
		} else {
			System.out.println("Path no contiene properties");
		}

	}

	private void generateKeys() {
		this.keys = new HashSet();
		Set<String> keysFile = new HashSet(System.getProperties().stringPropertyNames());
		Iterator var3 = keysFile.iterator();

		while (var3.hasNext()) {
			String key = (String) var3.next();
			if (key.indexOf(this.prefijoAplicacion) != -1) {
				String simpleKey = key.replace(this.prefijoAplicacion.concat("."), "");
				this.keys.add(simpleKey);
			}
		}

	}

	public String getPropiedad(String key) {
		String propertyName = this.prefijoAplicacion.concat(".").concat(key);
		String valor = bucarEnPropiedadesDelSistema(propertyName);
		if (valor == null) {
			valor = buscarEnVariableDeAmbiente(propertyName);
		}

		if (valor == null) {
			lanzarExcepcion(key);
		}

		return valor;
	}

	private static String bucarEnPropiedadesDelSistema(String propertyName) {
		return System.getProperty(propertyName);
	}

	private static String buscarEnVariableDeAmbiente(String propertyName) {
		String underscorePorpertyName = propertyName.replace(".", "_");
		return System.getenv(underscorePorpertyName);
	}

	private static void lanzarExcepcion(String key) {
		throw new RuntimeException(String.format(
				"ADVERTENCIA: La propiedad %s no existe, por favor verifique si se encuentra declarada como System Property o como variable de ambiente.",
				key));
	}

	public Set<String> getKeys() {
		return this.keys;
	}

}
