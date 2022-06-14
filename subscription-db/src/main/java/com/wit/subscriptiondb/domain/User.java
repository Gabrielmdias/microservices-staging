package com.wit.subscriptiondb.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Subscription> subscriptions;

	public User() {
	}
	
	public User(@NotNull UUID id) {
		this.id = id;
	}

	public User(@NotNull UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public User(@NotNull UUID id, String name, List<Subscription> subscriptions) {
		this.id = id;
		this.name = name;
		this.subscriptions = subscriptions;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", subscriptions=" + subscriptions + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
