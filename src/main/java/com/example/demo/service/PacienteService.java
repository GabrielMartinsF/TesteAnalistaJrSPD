package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.PacienteEntity;
import com.example.demo.erro.ResourceDuplicatedEntityException;
import com.example.demo.erro.ResourceNotFoundException;
import com.example.demo.repository.PacienteRepository;


@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public PacienteEntity save(PacienteEntity pac) {
		PacienteDuplicado(pac);
		if (pac.getSexo().toString().contains("M")) {
			pac.setGestante("N");
			return pacienteRepository.save(pac);
		}
		return pacienteRepository.save(pac);
		
		
	}

	public List<PacienteEntity> findall() {
		if (pacienteRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return pacienteRepository.findAll();
		}

	}

	public Optional<PacienteEntity> findId(Integer id) {
		if (pacienteRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return pacienteRepository.findById(id);
		}

	}

	public String delete(Integer id) {
		if (pacienteRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			pacienteRepository.deleteById(id);
			return "Usuario " + id + " deletado";
		}

	}

	public PacienteEntity update(@PathVariable("id") Integer id, @RequestBody PacienteEntity pac) {
		VerificaExisteId(id);
		PacienteDuplicado(pac);
		return pacienteRepository.save(pac);
	}

	private void PacienteDuplicado(PacienteEntity cpf) {
		PacienteEntity usr = pacienteRepository.findByCpf(cpf.getCpf());

		if (usr != null)
			throw new ResourceDuplicatedEntityException("Paciente já cadastrado");
	}

	public void VerificaExisteId(Integer id) {
		if (pacienteRepository.findById(id).isEmpty())
			throw new ResourceDuplicatedEntityException("Paciente " + id + " não encontrado");
	}
	
}