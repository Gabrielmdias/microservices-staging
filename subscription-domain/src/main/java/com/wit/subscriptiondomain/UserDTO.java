package com.wit.subscriptiondomain;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO implements Serializable, Entity {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private String name;
	private Operation operacao;
	private String requestId;

	public UserDTO() {
	}

	public UserDTO(UUID id, String name, Operation operacao) {
		this.id = id;
		this.name = name;
		this.operacao = operacao;
	}

	@Override
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

	@JsonIgnore
	@Override
	public Operation getOperacao() {
		return operacao;
	}

	public void setOperacao(Operation operacao) {
		this.operacao = operacao;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@JsonIgnore
	@Override
	public String getRequestId() {
		return requestId;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", operacao=" + operacao + ", requestId=" + requestId + "]";
	}

}
