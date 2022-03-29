package com.example.demo.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AtendimentoEntity;

public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Integer> {

	Optional<AtendimentoEntity> findById(Integer id);
	
}
