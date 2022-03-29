package com.example.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FimAtendimentoEntity;
import com.example.demo.erro.ResourceNotFoundException;
import com.example.demo.service.FimAtendimentoService;

@RestController
@RequestMapping("/fimAtendimento")
public class FimAtendimentoController {
	
	@Autowired
	private FimAtendimentoService fimAtendimentoService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody FimAtendimentoEntity atend) throws ParseException {
		try {
			return new ResponseEntity<>(fimAtendimentoService.save(atend), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<?> find() {
		try {
			return new ResponseEntity<>(fimAtendimentoService.findall(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findId(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(fimAtendimentoService.findId(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(fimAtendimentoService.delete(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}
}
