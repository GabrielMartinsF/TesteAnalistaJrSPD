package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.AtendimentoEntity;
import com.example.demo.erro.ResourceDuplicatedEntityException;
import com.example.demo.erro.ResourceNotFoundException;
import com.example.demo.repository.AtendimentoRepository;
import com.example.demo.repository.PacienteRepository;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	public AtendimentoEntity save(AtendimentoEntity atend) {
		VerificaExisteUser(atend);
		return atendimentoRepository.save(atend);
	}

	public List<AtendimentoEntity> findall() {
		VerificaTodosAtendimentos();
		if (atendimentoRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return atendimentoRepository.findAll();
		}

	}

	public Optional<AtendimentoEntity> findId(Integer id) {
		if (atendimentoRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return atendimentoRepository.findById(id);
		}

	}

	public String delete(Integer id) {
		if (atendimentoRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			atendimentoRepository.deleteById(id);
			return "Atendimento " + id + " deletado";
		}

	}

	public AtendimentoEntity update(@PathVariable("id") Integer id, @RequestBody AtendimentoEntity atend) {
		VerificaExisteId(id);
		return atendimentoRepository.save(atend);
	}
	
	private void VerificaTodosAtendimentos() {
		if (atendimentoRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException("Não existem atendimentos Cadastrados");
		}
	}

	public void VerificaExisteId(Integer id) {
		if (atendimentoRepository.findById(id).isEmpty())
			throw new ResourceDuplicatedEntityException("Atendimento " + id + " não encontrado");
	}
	
	public void BuscarUser(Integer id) {
		if (pacienteRepository.findById(id).isEmpty())
			throw new ResourceDuplicatedEntityException("Paciente " + id + " não encontrado");
	}
	
	public void VerificaExisteUser(AtendimentoEntity atend) {
		Integer id = atend.getIdPaciente();
		BuscarUser(id);
	}
	
}
