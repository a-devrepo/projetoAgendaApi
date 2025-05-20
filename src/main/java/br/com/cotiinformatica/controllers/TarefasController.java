package br.com.cotiinformatica.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

	@PostMapping
	public void post() {
		// TODO: implementar o método para incluir uma tarefa
	}
}
