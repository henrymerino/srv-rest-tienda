package ec.com.ws.rest.tienda.persistence.postgres.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "client_order")
public class ClientOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date_client_order")
	private Date dateClientOrder;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "orden_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private OrderEntity orden;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private ClientEntity client;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderEntity getOrden() {
		return orden;
	}

	public void setOrden(OrderEntity orden) {
		this.orden = orden;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public Date getDateClientOrder() {
		return dateClientOrder;
	}

	public void setDateClientOrder(Date dateClientOrder) {
		this.dateClientOrder = dateClientOrder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, dateClientOrder, id, orden);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientOrderEntity other = (ClientOrderEntity) obj;
		return Objects.equals(client, other.client) && Objects.equals(dateClientOrder, other.dateClientOrder)
				&& Objects.equals(id, other.id) && Objects.equals(orden, other.orden);
	}

}
