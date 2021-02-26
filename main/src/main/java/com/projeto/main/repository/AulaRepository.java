package com.projeto.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.main.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{

}
