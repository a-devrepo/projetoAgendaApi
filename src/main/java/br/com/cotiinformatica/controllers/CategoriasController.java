package br.com.cotiinformatica.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriaResponseDTO;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Controle de categorias", description = "Serviços para gerenciamento de dados de categorias.")
public class CategoriasController {

	private CategoriaRepository repository;

	public CategoriasController(CategoriaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	@Operation(summary = "Consulta de categorias", description = "Retorna todas as categorias cadastradas no sistema.")
	public List<CategoriaResponseDTO> get() {

		var categorias = repository.findAll();
		var mapper = new ModelMapper();

		return categorias.stream()
				.map(categoria -> 
				mapper.map(categoria, CategoriaResponseDTO.class))
				.toList();
	}
}
