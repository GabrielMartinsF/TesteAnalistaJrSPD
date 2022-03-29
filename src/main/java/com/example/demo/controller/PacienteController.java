package com.example.demo.controller;

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

import com.example.demo.entity.PacienteEntity;
import com.example.demo.erro.ResourceNotFoundException;
import com.example.demo.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PacienteEntity pac) {
		try {
			return new ResponseEntity<>(pacienteService.save(pac), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PacienteEntity pac) {
		try {
			return new ResponseEntity<>(pacienteService.update(id, pac), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<?> find() {
		try {
			return new ResponseEntity<>(pacienteService.findall(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findId(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(pacienteService.findId(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(pacienteService.delete(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}
	
}
