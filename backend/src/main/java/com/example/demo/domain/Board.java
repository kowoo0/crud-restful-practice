package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "boards")
public class Board {
	
	@Id
	@GeneratedValue
	@Getter
	@Setter
	@JsonProperty
	private Long no;
	
	@Getter
	@Setter
	@JsonProperty
	private int user_no;
	
	@Getter
	@Setter
	@JsonProperty
	private String title;
	
	@Getter
	@Setter
	@JsonProperty
	private String content;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_board_writer"))
	private User writer;
}
