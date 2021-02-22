package com.projeto.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

	@Query("SELECT a from Aluno a where a.id = ?1")
	Aluno findOneById(Integer id);
	
	@Query("SELECT a from Aluno a where a.nome = ?1")
	Aluno findOneByName(String nome);
	
	@Query("SELECT a from Aluno a where a.email = ?1")
	Aluno findOneByEmail(String email);
}
