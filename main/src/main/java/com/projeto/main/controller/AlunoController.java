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

import com.projeto.main.dto.AlunoDTO;
import com.projeto.main.entity.Aluno;
import com.projeto.main.entity.Curso;
import com.projeto.main.exception.ExceptionCode;
import com.projeto.main.exception.NotFoundException;
import com.projeto.main.exception.WebException;
import com.projeto.main.repository.AlunoRepository;
import com.projeto.main.repository.CursoRepository;

@RequestMapping("/aluno")
@RestController
public class AlunoController {

	@Autowired
	private AlunoRepository repository;
	@Autowired
	private CursoRepository cursoRepository;
	
	Mapper mapper = new DozerBeanMapper();
	AlunoDTO entidade = mapper.map(Aluno.class, AlunoDTO.class);
	
	@PostMapping(value = "/inserir-aluno")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insertAluno(@RequestBody @Valid AlunoDTO dto) 
	{
		Aluno aluno = new Aluno();

		aluno.setNome(dto.getNome());
		aluno.setEmail(dto.getEmail());
		aluno.setSenha(dto.getSenha());

		Curso curso = cursoRepository.findOneById(dto.getCurso().getId());
		if(Objects.nonNull(curso))
		{
			aluno.setCurso(curso);
			repository.save(aluno);
		}else
		{
			throw new NotFoundException(Curso.class);
		}
	}

	@GetMapping(value = "/listar-tudo")
	public List<AlunoDTO> listar()
	{
		List<Aluno> aluno = repository.findAll();
		return aluno.stream().map(m -> {
			AlunoDTO dto = mapper.map(m, AlunoDTO.class);
			return dto;
		}).collect(Collectors.toList());
		
	}
	
	@GetMapping(value = "/listar-aluno/{id}")
	public AlunoDTO procurar(@PathVariable("id") Integer id)
	{
		Aluno aluno = repository.findOneById(id);
		//caso for retornar todos atributos do DTO
		AlunoDTO dto = mapper.map(aluno, AlunoDTO.class);;
		
		if (Objects.isNull(aluno)) {
			throw new WebException(HttpStatus.NOT_FOUND, "Nenhum valor encontrado no bd para ser listado . . ", ExceptionCode.STUDENT_NOT_FOUND);
		} else {
			//retornar todos valores do DTO
			return dto;
			//Maneira de escolher os campos que eu quero passar
			//return AlunoDTO.builder().nome(aluno.getNome()).email(aluno.getEmail()).senha("************").build();
		}
	}
	
	@DeleteMapping("/deletar-aluno/{id}")
	public void deletar(@PathVariable("id") Integer id) {

		Aluno aluno = repository.findOneById(id);

		if (Objects.isNull(aluno)) {
			throw new WebException(HttpStatus.NOT_FOUND, "Nenhum valor encontrado no bd para ser deletado . . ", ExceptionCode.STUDENT_NOT_FOUND);
		} else {
			repository.delete(aluno);
		}
	}
	
	@PutMapping("/atualizar-aluno/{id}")
	public void atualizar(@RequestBody @Valid AlunoDTO dto, @PathVariable Integer id)
	{
		Aluno aluno = repository.findOneById(id);

		if (Objects.isNull(aluno)) {
			throw new WebException(HttpStatus.NOT_FOUND, "Nenhum valor encontrado no bd para ser atualizado . . ", ExceptionCode.STUDENT_EQUALS_VALUE);
		} else {
			aluno.setNome(dto.getNome());
			repository.save(aluno);
		}
	}
	
}
