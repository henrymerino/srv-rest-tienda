package ec.com.ws.rest.tienda.persistence.postgres.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "persona")
public class PersonaEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Basic
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Basic
    @Column(name = "usuario", nullable = true, length = 20)
    private String usuario;

    @Basic
    @Column(name = "clave", nullable = true, length = 100)
    private String clave;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", nullable = true)
    private Date fechaNacimiento;

    @Basic
    @Column(name = "direccion", nullable = true, length = 80)
    private String direccion;

    @Basic
    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;
    
  
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolEntity idRol;
    
    
    
    public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public RolEntity getIdRol() {
		return idRol;
	}

	public void setIdRol(RolEntity idRol) {
		this.idRol = idRol;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaEntity that = (PersonaEntity) o;

        if (idPersona != that.idPersona) return false;
        if (!Objects.equals(cedula, that.cedula))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idPersona != null ? idPersona.hashCode() : 0;
        result = 31 * result + (cedula != null ? cedula.hashCode() : 0);
        return result;
    }


}
