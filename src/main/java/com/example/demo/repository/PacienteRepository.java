package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PacienteEntity;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
	
	PacienteEntity findByCpf(String cpf);
	PacienteEntity findBySexo(String sexo);
	PacienteEntity findByGestante(String gestante);

}