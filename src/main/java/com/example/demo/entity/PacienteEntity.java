package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.Validate.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paciente")
public class PacienteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "datanascimento")
	private LocalDate datanascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Gender sexo;
	
	@Column(name = "gestante")
	private String gestante;
	
}
