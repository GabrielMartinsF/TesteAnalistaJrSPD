package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FimAtendimentoEntity;

public interface FimAtendimentoRepository extends JpaRepository<FimAtendimentoEntity, Integer> {

	FimAtendimentoEntity findByIdAtendimento(Integer id);
	
}
