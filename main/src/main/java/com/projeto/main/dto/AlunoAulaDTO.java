package com.projeto.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AlunoAulaDTO {

	private int id;
	private String nome;
	private int modulo;
	private int semestre;
	
}
