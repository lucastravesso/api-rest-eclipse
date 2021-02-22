package com.projeto.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

	@Query("SELECT a from Curso a where a.id = ?1")
	Curso findOneById(Integer id);
	
	List<Curso> findByNome(String nome);
	

}
