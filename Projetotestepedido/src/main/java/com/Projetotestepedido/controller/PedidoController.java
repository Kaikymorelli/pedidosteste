package com.Projetotestepedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projetotestepedido.entties.Pedido;
import com.Projetotestepedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "pedidos", description = "API REST DE GERECIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
	
	private final PedidoService pedidoService;

	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}


	@GetMapping("/{id}")
	@Operation(summary = "Localiza um pedido por ID")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
		Pedido Pedido = pedidoService.getPedidoById(id);
		if (Pedido != null) {
			return ResponseEntity.ok(Pedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todos os pedido")
	public ResponseEntity<List<Pedido>> getAllPedido() {
		List<Pedido> Pedido = pedidoService.getAllPedido();
		return ResponseEntity.ok(Pedido);
	}

	@PostMapping
	@Operation(summary = "Cadastra um pedido")
	public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid Pedido pedido) {
		Pedido criarPedido = pedidoService.salvarPedido(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarPedido);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um pedido")
	public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
		Pedido updatedPedido = pedidoService.updatePedido(id, pedido);
		if (updatedPedido != null) {
			return ResponseEntity.ok(updatedPedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um pedido")
	public ResponseEntity<String> deletePedido(@PathVariable Long id) {
		boolean deleted = pedidoService.deletePedido(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
