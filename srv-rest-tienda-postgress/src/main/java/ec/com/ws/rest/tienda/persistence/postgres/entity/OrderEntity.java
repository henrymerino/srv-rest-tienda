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

@Entity
@Table(name = "orden")
public class OrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String state;

	@Column(name = "date_order")
	private Date dateOrder;

	@OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
	private List<DetailOrderEntity> detailsOrders = new ArrayList<>();

	@OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
	private List<ClientOrderEntity> clientsOrders = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public List<DetailOrderEntity> getDetailsOrders() {
		return detailsOrders;
	}

	public void setDetailsOrders(List<DetailOrderEntity> detailsOrders) {
		this.detailsOrders = detailsOrders;
		for (DetailOrderEntity detailOrder : detailsOrders) {
			detailOrder.setOrden(this);
		}
	}

	public List<ClientOrderEntity> getClientsOrders() {
		return clientsOrders;
	}

	public void setClientsOrders(List<ClientOrderEntity> clientsOrders) {
		this.clientsOrders = clientsOrders;
		for (ClientOrderEntity clientOrder : clientsOrders) {
			clientOrder.setOrden(this);

		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientsOrders, dateOrder, detailsOrders, id, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		return Objects.equals(clientsOrders, other.clientsOrders) && Objects.equals(dateOrder, other.dateOrder)
				&& Objects.equals(detailsOrders, other.detailsOrders) && Objects.equals(id, other.id)
				&& Objects.equals(state, other.state);
	}
	
}
