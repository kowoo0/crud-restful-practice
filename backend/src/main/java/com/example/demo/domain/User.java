package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	@Setter
	@Getter
	@JsonProperty
	private Long no;
	
	@Setter
	@Getter
	@JsonProperty
	private String id;
	
	@Setter
	@Getter
	@JsonProperty
	private String pw;
	
	@Setter
	@Getter
	@JsonProperty
	private String name;
	
	public User() {}
	
	public User(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public String getName() {
		return this.name;
	}
}
