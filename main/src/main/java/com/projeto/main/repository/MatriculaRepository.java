package com.projeto.main.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.main.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{
	
	@Query("SELECT a from Matricula a where a.id = ?1")
	Matricula findOneById(Integer id);
	
}
