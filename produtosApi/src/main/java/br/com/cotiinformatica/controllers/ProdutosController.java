package br.com.cotiinformatica.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

	private ModelMapper mapper = new ModelMapper();
	
	@Operation(summary = "Serviço para cadastro de produto.")
	@PostMapping("cadastrar")
	public ResponseEntity<String> cadastrar(@RequestBody @Valid ProdutoRequestDto request) {

		try {
			
			//verificar se a categoria não existe no banco de dados
			var categoriaRepository = new CategoriaRepository();
			
			if(!categoriaRepository.exists(request.getCategoriaId())) {
				return ResponseEntity.status(400).body("Categoria não encontrada, verifique o ID informado.");
			}
						
			var produto = mapper.map(request, Produto.class); //capturar os dados do produto
			produto.setId(UUID.randomUUID()); //gerando um ID para gravar o produto
			
			//gravar o produto no banco de dados
			var produtoRepository = new ProdutoRepository();
			produtoRepository.inserir(produto, request.getCategoriaId());
			
			return ResponseEntity.status(201).body("Produto cadastrado com sucesso.");
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}		
	}

	@Operation(summary = "Serviço para atualização de dados de produto.")
	@PutMapping("atualizar")
	public void atualizar() {
		// TODO Implementar a atualização
	}

	@Operation(summary = "Serviço para exclusão de um produto.")
	@DeleteMapping("excluir")
	public void excluir() {
		// TODO Implementar a exclusão
	}

	@Operation(summary = "Serviço para consulta de todos os produtos.")
	@GetMapping("consultar")
	public void consultar() {
		// TODO Implementar a exclusão
	}
}
