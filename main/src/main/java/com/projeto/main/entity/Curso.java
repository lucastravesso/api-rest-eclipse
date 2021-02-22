package com.projeto.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Curso {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(name = "nome_curso")
	protected String nome;
	@Column(name = "tipo_curso")
	protected String tipo;


}