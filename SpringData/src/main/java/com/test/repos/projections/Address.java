package com.test.repos.projections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long id;
	public String street, state, country;

	protected Address() {
	}

	public Address(String street, String state, String country) {
		super();
		this.id = id;
		this.street = street;
		this.state = state;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", state=" + state
				+ ", country=" + country + "]";
	}

}
