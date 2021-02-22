package com.projeto.main.controller;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.main.dto.CursoDTO;
import com.projeto.main.entity.Curso;
import com.projeto.main.exception.ConflictException;
import com.projeto.main.exception.ExceptionCode;
import com.projeto.main.exception.WebException;
import com.projeto.main.repository.CursoRepository;

@RequestMapping("/curso")
@RestController
public class CursoController {

	@Autowired
	private CursoRepository repository;

	Mapper mapper = new DozerBeanMapper();
	CursoDTO entidade = mapper.map(Curso.class, CursoDTO.class);
	
	@PostMapping(value = "/inserir-curso")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insertCurso(@RequestBody @Valid CursoDTO dto) 
	{
		Curso curso = new Curso();
		List<Curso> comp = repository.findByNome(dto.getNome());
		
		if(comp.isEmpty())
		{
			curso.setNome(dto.getNome());
			curso.setTipo(dto.getTipo());
			repository.save(curso);
		}else
		{
			throw new ConflictException("Curso ja existe no banco de dados . .");
		}
	}

	@GetMapping(value = "/listar-tudo")
	public List<CursoDTO> listar()
	{
		List<Curso> curso = repository.findAll();
		return curso.stream().map(m -> {
			CursoDTO dto = mapper.map(m, CursoDTO.class);
			return dto;
		}).collect(Collectors.toList());
		
	}
	
	@GetMapping(value = "/listar-curso/{id}")
	public CursoDTO procurar(@PathVariable("id") Integer id)
	{
		Curso curso = repository.findOneById(id);
		
		if (Objects.isNull(curso)) {
			throw new WebException(HttpStatus.NOT_FOUND, "Nenhum valor encontrado no bd para ser listado . . ", ExceptionCode.CLASS_NOT_FOUND);
		} else {
			return CursoDTO.builder().nome(curso.getNome()).tipo(curso.getTipo()).build();
		}
	}
	
	@DeleteMapping("/deletar-curso/{id}")
	public void deletar(@PathVariable("id") Integer id) {

		Curso curso = repository.findOneById(id);

		if (Objects.isNull(curso)) {
			throw new WebException(HttpStatus.NOT_FOUND, "Nenhum valor encontrado no bd para ser deletado . . ", ExceptionCode.CLASS_NOT_FOUND);
		} else {
			repository.delete(curso);
		}
	}
	
	@PutMapping("/atualizar-curso/{id}")
	public void atualizar(@RequestBody @Valid CursoDTO dto, @PathVariable Integer id)
	{
		Curso curso = repository.findOneById(id);

		if (Objects.isNull(curso)) {
			throw new WebException(HttpStatus.NOT_FOUND, "Nenhum valor encontrado no bd para ser atualizado . . ", ExceptionCode.CLASS_NOT_FOUND);
		} else {
			curso.setNome(dto.getNome());
			repository.save(curso);
		}
	}
	
}
