package ec.com.ws.rest.tienda.persistence.postgres.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class RolEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol", nullable = false)
	private Integer idRol;

	@Basic
	@Column(name = "descripcion", nullable = true, length = 50)
	private String descripcion;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RolEntity that = (RolEntity) o;

		if (idRol != that.idRol)
			return false;
		if (!Objects.equals(descripcion, that.descripcion))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = idRol != null ? idRol.hashCode() : 0;
		result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
		return result;
	}
}
