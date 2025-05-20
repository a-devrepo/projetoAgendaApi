package br.com.cotiinformatica.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Controle de categorias", description = "Serviços para gerenciamento de dados de categorias.")
public class CategoriasController {

	@GetMapping
	@Operation(summary = "Consulta de categorias", 
	description = "Retorna todas as categorias cadastradas no sistema.")
	public void get() {
		// TODO implementar o serviço para consulta de categorias
	}
}
