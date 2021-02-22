package com.projeto.main.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.main.dto.CursoDTO;
import com.projeto.main.entity.Curso;

import com.projeto.main.repository.CursoRepository;
import com.projeto.main.validator.Validator;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	@Autowired
	private Validator valid;
	
	private Mapper mapper = new DozerBeanMapper();
	

	public ResponseEntity<Curso> insertCurso(CursoDTO dto) 
	{
		boolean validar = valid.ValidateClassFields(dto);
		
		if(!validar)
		{
			Curso curso = new Curso(dto);
			repository.save(curso);
			return ResponseEntity.ok(curso);
		}else
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}


	public List<CursoDTO> listar()
	{
		List<Curso> curso = repository.findAll();
		return curso.stream().map(m -> {
			CursoDTO dto = mapper.map(m, CursoDTO.class);
			return dto;
		}).collect(Collectors.toList());
		
	}
	

	public ResponseEntity<CursoDTO> procurar(Integer id)
	{
		Optional<Curso> curso = repository.findById(id);
		if(curso.isPresent())
		{
			return ResponseEntity.ok(mapper.map(curso.get(), CursoDTO.class));
		}
		return ResponseEntity.notFound().build();
	}
	

	public ResponseEntity<?> deletar(Integer id) {

		Optional<Curso> curso = repository.findById(id);

		if (curso.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();			
		}
		return ResponseEntity.notFound().build();
	}
	

	public ResponseEntity<?> atualizar(Integer id, CursoDTO dto)
	{
		Curso curso = repository.findOneById(id);
		
		if(Objects.nonNull(curso))
		{
			curso.setNome(dto.getNome());
			curso.setTipo(dto.getTipo());
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}


