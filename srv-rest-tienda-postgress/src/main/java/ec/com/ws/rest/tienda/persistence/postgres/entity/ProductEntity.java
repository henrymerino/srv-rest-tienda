package ec.com.ws.rest.tienda.persistence.postgres.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String amount;

	private Float price;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private StoreEntity store;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<DetailOrderEntity> detailsOrders = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StoreEntity getStore() {
		return store;
	}

	public void setStore(StoreEntity store) {
		this.store = store;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}



	public List<DetailOrderEntity> getDetailsOrders() {
		return detailsOrders;
	}

	public void setDetailsOrders(List<DetailOrderEntity> detailsOrders) {
		this.detailsOrders = detailsOrders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, detailsOrders, id, name, price, store);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(detailsOrders, other.detailsOrders)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(store, other.store);
	}
	
	
	
}
