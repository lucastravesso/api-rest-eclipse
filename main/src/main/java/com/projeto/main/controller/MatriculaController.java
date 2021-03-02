package com.projeto.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.main.dto.MatriculaDTO;
import com.projeto.main.entity.Matricula;
import com.projeto.main.service.MatriculaService;

@RequestMapping("/matricula")
@Controller
public class MatriculaController {

	@Autowired
	private MatriculaService service;
	
	@PostMapping("/inserir-matricula")
	public ResponseEntity<Matricula> inserirMatricula(@RequestBody @Valid MatriculaDTO dto)
	{
		return service.inserirAulaAluno(dto);
	}
	@DeleteMapping("/deletar-matricula")
	public ResponseEntity<?> deletarMatricula(Integer id)
	{
		return service.deleteMatricula(id);
	}
	@GetMapping("/listar-tudo")
	public List<MatriculaDTO> listarTudo()
	{
		return service.listar();
	}
	@GetMapping("/listar-matricula/{id}")
	public ResponseEntity<MatriculaDTO> listarUm(@PathVariable Integer id)
	{
		return service.listarUm(id);
	}
	
}
