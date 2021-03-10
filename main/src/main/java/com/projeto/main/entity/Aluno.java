package com.projeto.main.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projeto.main.dto.AlunoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ALUNO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicUpdate
@Entity
public class Aluno  {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_aluno")
	protected Integer id;
	protected String nome;
	protected String email;
	protected String senha;
	@ManyToOne(targetEntity = Curso.class)
	@JoinColumn(name = "id_curso", foreignKey = @ForeignKey(name = "fk_id_curso_aula"))
	private Curso curso;
	
	public Aluno (AlunoDTO dto, Curso curso) {
		
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.senha = new BCryptPasswordEncoder().encode(dto.getSenha());
		this.curso = curso;
	}

}
