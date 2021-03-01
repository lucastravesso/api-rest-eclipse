package com.projeto.main.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicUpdate
@Entity
public class Matricula {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(targetEntity = Aluno.class)
	@JoinColumn(name = "id_aluno", foreignKey = @ForeignKey(name = "fk_id_aluno"))
	private Aluno aluno;
	@OneToOne(targetEntity = Aula.class)
	@JoinColumn(name = "id_aula", foreignKey = @ForeignKey(name = "fk_id_aula"))
	private Aula aula;
	
	public Matricula(Aluno aluno, Aula aula)
	{
		this.aluno = aluno;
		this.aula = aula;
	}
}
