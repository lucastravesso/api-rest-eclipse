package com.projeto.main.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.main.dto.MatriculaDTO;
import com.projeto.main.entity.Aluno;
import com.projeto.main.entity.Aula;
import com.projeto.main.entity.Matricula;
import com.projeto.main.repository.AlunoRepository;
import com.projeto.main.repository.AulaRepository;
import com.projeto.main.repository.MatriculaRepository;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository mRepository; 
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	private Mapper mapper;
	
	public ResponseEntity<Matricula> inserirAulaAluno(MatriculaDTO dto)
	{
		Aluno aluno = alunoRepository.findOneById(dto.getAluno().getId());
		Aula aula = aulaRepository.findOneById(dto.getAula().getId());
		
		if(Objects.nonNull(aluno) && Objects.nonNull(aula))
		{
			Matricula matricula = new Matricula(aluno, aula);
			mRepository.save(matricula);
			return ResponseEntity.ok(matricula);
		}
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<?> deleteMatricula(Integer id)
	{
		Optional<Matricula> matricula = mRepository.findById(id);
		if(Objects.nonNull(matricula))
		{
			mRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	public List<MatriculaDTO> listar()
	{
		List<Matricula> matricula = mRepository.findAll();
		return matricula.stream().map(m ->{
			MatriculaDTO dto = mapper.map(m, MatriculaDTO.class);
			return dto;
		}).collect(Collectors.toList());
	}
	
}

