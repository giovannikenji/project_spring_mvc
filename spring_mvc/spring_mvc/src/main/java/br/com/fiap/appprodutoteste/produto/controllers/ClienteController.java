package br.com.fiap.appprodutoteste.produto.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.appprodutoteste.produto.model.Cliente;
import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.produto.dto.ClienteDto;
import br.com.fiap.appprodutoteste.produto.repositories.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired // injeção de dependência, não tem a necessidade de dar um new nos objetos
	private ClienteRepository clienteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/clientes")
	public ModelAndView index() {
		
		ModelAndView modelView = new ModelAndView("clientes/index");

		// Long id, String nome, Integer quantidade, BigDecimal valor
		Cliente cliente1 = new Cliente(new Long(1), "Abel", "123", "Rua x");
		Cliente cliente2 = new Cliente(new Long(2), "Rogério", "234", "Rua y");
		Cliente cliente3 = new Cliente(new Long(4), "Silvinho", "345", "Rua z");

		List<Cliente> clientes = Arrays.asList(cliente1, cliente2, cliente3);

		// ("String attributeName", Object attributeValue)
		modelView.addObject("listarProdutos", clientes);// controller precisa enviar objetos para serem manipulados

		List<Cliente> clienteDaRepository = clienteRepository.findAll(); // findAll(); select * from produto
		modelView.addObject("listarClientes", clienteDaRepository);
		
		return modelView;
	}

	@GetMapping("/clientes/criar")
	public ModelAndView criar(ClienteDto cliente) {
		
		return new ModelAndView("clientes/criar");
	}

	@PostMapping("/clientes")
	public ModelAndView salvar(@Valid ClienteDto clienteModel, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("/clientes/criar");
		}
		
		Cliente cliente = modelMapper.map(clienteModel, Cliente.class);

		clienteRepository.save(cliente);

		return new ModelAndView("redirect:/clientes"); // devolver para listagem dos produtos (index.html)
	}


	@GetMapping("/clientes/{id}")
	public ModelAndView mostrarDetalhes(@PathVariable Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
	
		if(cliente.isPresent()) {
			Cliente clienteGet = cliente.get();
			ModelAndView model = new ModelAndView("/clientes/detalhe");
			model.addObject("cliente", clienteGet);
			return model;
		}
		
		System.out.println("Cliente não encontrado");		
		return new ModelAndView("/produtos");
	}
}	




