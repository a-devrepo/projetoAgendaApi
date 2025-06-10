package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.TarefaRequestDTO;
import br.com.cotiinformatica.dtos.TarefaResponseDTO;
import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.TarefaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Controle de tarefas", description = "Serviços para gerenciamento de tarefas da agenda.")
public class TarefasController {

	private final TarefaRepository tarefaRepository;
	private final CategoriaRepository categoriaRepository;
	private final ModelMapper modelMapper;

	public TarefasController(TarefaRepository tarefaRepository, CategoriaRepository categoriaRepository,
			ModelMapper modelMapper) {
		this.tarefaRepository = tarefaRepository;
		this.categoriaRepository = categoriaRepository;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	@Operation(summary = "Cadastro de tarefas", description = "Cria uma nova tarefa no sistema.")
	public ResponseEntity<TarefaResponseDTO> post(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {
		var categoria = categoriaRepository.findById(tarefaRequestDTO.getCategoriaID())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o ID informado."));

		var tarefa = modelMapper.map(tarefaRequestDTO, Tarefa.class);
		tarefa.setCategoria(categoria);
		tarefaRepository.save(tarefa);
		var tarefaResponseDTO = modelMapper.map(tarefa, TarefaResponseDTO.class);

		return ResponseEntity.ok(tarefaResponseDTO);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Edição de tarefas", description = "Atualiza uma tarefa existente no sistema.")
	public ResponseEntity<TarefaResponseDTO> put(@PathVariable UUID id,
			@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {

		if (!tarefaRepository.existsById(id))
			throw new IllegalArgumentException("Tarefa não encontrada para edição. Verifique o ID informado.");

		var categoria = categoriaRepository.findById(tarefaRequestDTO.getCategoriaID())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o ID informado."));

		var tarefa = modelMapper.map(tarefaRequestDTO, Tarefa.class);
		tarefa.setId(id);
		tarefa.setCategoria(categoria);

		tarefaRepository.save(tarefa);

		var responseDTO = modelMapper.map(tarefa, TarefaResponseDTO.class);
		return ResponseEntity.ok(responseDTO);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclusão de tarefas", description = "Apaga uma tarefa no sistema.")
	public ResponseEntity<?> delete(@PathVariable UUID id) {
		
		var tarefa = tarefaRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Tarefa não encontrada para exclusão. Verifique o ID informado."));

		tarefaRepository.delete(tarefa);
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@Operation(summary = "Consulta de tarefas", description = "Retorna as tarefas cadastradas no sistema.")
	public ResponseEntity<List<TarefaResponseDTO>> get() {

		var tarefas = tarefaRepository.findAll();

		return tarefas.stream().map(tarefa -> modelMapper.map(tarefa, TarefaResponseDTO.class))
				.collect(Collectors.toList()).stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), ResponseEntity::ok));
	}
}
