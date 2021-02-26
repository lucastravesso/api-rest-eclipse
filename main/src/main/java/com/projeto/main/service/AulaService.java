package com.projeto.main.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.main.dto.AulaDTO;
import com.projeto.main.entity.Aula;
import com.projeto.main.entity.Curso;
import com.projeto.main.repository.AulaRepository;
import com.projeto.main.repository.CursoRepository;

@Service
public class AulaService {

	@Autowired
	private AulaRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	private Mapper mapper = new DozerBeanMapper();
	
	public ResponseEntity<Aula> insertAula(AulaDTO dto)
	{
		Curso curso = cursoRepository.findOneById(dto.getCurso().getId());
		if (Objects.nonNull(curso)) {
			
			Aula aula = new Aula(dto, curso);
			repository.save(aula);
			return ResponseEntity.ok(aula);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	public ResponseEntity<?> atualizarAula(Integer id, AulaDTO dto)
	{
		Optional<Aula> aula = repository.findById(id);
		Curso curso = cursoRepository.findOneById(dto.getCurso().getId());
		if(aula.isPresent() && Objects.nonNull(curso))
		{
			aula.get().setNome(dto.getNome());
			aula.get().setModulo(dto.getModulo());
			aula.get().setSemestre(dto.getSemestre());
			aula.get().setCurso(curso);
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	public List<AulaDTO> listar() {

		List<Aula> aula = repository.findAll();
		return aula.stream().map(m -> {
			AulaDTO dto = mapper.map(m, AulaDTO.class);
			return dto;
		}).collect(Collectors.toList());
	}
	
	
}