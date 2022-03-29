package com.example.demo.service;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.AtendimentoEntity;
import com.example.demo.entity.FimAtendimentoEntity;
import com.example.demo.erro.ResourceDuplicatedEntityException;
import com.example.demo.erro.ResourceNotFoundException;
import com.example.demo.repository.FimAtendimentoRepository;

@Service
public class FimAtendimentoService {
	
	@Autowired
	private FimAtendimentoRepository fimAtendimentoRepository;
	
	private AtendimentoEntity atendimentoEntity;

	public FimAtendimentoEntity save(FimAtendimentoEntity fimAtend) throws ParseException {
		VerificaExisteAtendimento(fimAtend);
		CalculaDuracao(fimAtend);
		return fimAtendimentoRepository.save(fimAtend);
	}

	public List<FimAtendimentoEntity> findall() {
		VerificaTodosAtendimentos();
		if (fimAtendimentoRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return fimAtendimentoRepository.findAll();
		}

	}
	
	
	public FimAtendimentoEntity update(@PathVariable("id") Integer id, @RequestBody FimAtendimentoEntity atend) {
		VerificaExisteId(id);
		return fimAtendimentoRepository.save(atend);
	}

	public Optional<FimAtendimentoEntity> findId(Integer id) {
		if (fimAtendimentoRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			return fimAtendimentoRepository.findById(id);
		}

	}

	public String delete(Integer id) {
		if (fimAtendimentoRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(null);
		} else {
			fimAtendimentoRepository.deleteById(id);
			return "Atendimento " + id + " deletado";
		}

	}
	
	private void VerificaTodosAtendimentos() {
		if (fimAtendimentoRepository.findAll().isEmpty()) {
			throw new ResourceNotFoundException("Não existem atendimentos Cadastrados");
		}
	}

	public void VerificaExisteId(Integer id) {
		if (fimAtendimentoRepository.findById(id).isEmpty())
			throw new ResourceDuplicatedEntityException("Atendimento " + id + " não encontrado");
	}
	
	
	public void VerificaExisteAtendimento(FimAtendimentoEntity atend) {
		Integer id = atend.getIdAtendimento();
		VerificaExisteId(id);
	}
	
	public void CalculaDuracao (FimAtendimentoEntity fim) throws ParseException {
		LocalDateTime I = atendimentoEntity.getDatahoraatend();
		LocalDateTime F = fim.getDatahoraencer();
		
		long D = ChronoUnit.DAYS.between(I, F);
		
		String duracao = String.valueOf(D);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date time = formatter.parse(duracao);
		
		fim.setDuracao(time);
	}
	
}
