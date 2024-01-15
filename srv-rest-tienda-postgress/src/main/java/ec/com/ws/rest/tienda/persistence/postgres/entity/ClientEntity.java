package ec.com.ws.rest.tienda.persistence.postgres.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "client", uniqueConstraints = { @UniqueConstraint(columnNames = { "identification" }) })
public class ClientEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	private String identification;

	@Column(name = "date_creation")
	private Date dateCreation;

	private String address;

	private String email;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<ClientOrderEntity> clientsOrders = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ClientOrderEntity> getClientsOrders() {
		return clientsOrders;
	}

	public void setClientsOrders(List<ClientOrderEntity> clientsOrders) {
		this.clientsOrders = clientsOrders;
		for (ClientOrderEntity clientOrder : clientsOrders) {
			clientOrder.setClient(this);

		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, clientsOrders, dateCreation, email, fullName, id, identification);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientEntity other = (ClientEntity) obj;
		return Objects.equals(address, other.address) && Objects.equals(clientsOrders, other.clientsOrders)
				&& Objects.equals(dateCreation, other.dateCreation) && Objects.equals(email, other.email)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(id, other.id)
				&& Objects.equals(identification, other.identification);
	}

}
