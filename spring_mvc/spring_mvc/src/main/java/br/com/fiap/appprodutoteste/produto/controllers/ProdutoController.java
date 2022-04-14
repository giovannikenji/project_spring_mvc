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

import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.produto.dto.ProdutoDto;
import br.com.fiap.appprodutoteste.produto.repositories.ProdutoRepository;

@Controller
public class ProdutoController {

	// criação de uma propriedade do tipo ProdutoRepository
	@Autowired // injeção de dependência, não tem a necessidade de dar um new
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	// criação de um acesso
	@GetMapping("/produtos")
	public ModelAndView index() {

		// onde a controller achará a View - é o parâmetro do ModelAndView - ao
		// instanciar
		ModelAndView modelView = new ModelAndView("produtos/index"); // passar como parametro pasta e nome

		// Long id, String nome, Integer quantidade, BigDecimal valor
		Produto produto1 = new Produto(new Long(1), "chocolate", 3, new BigDecimal(2));
		Produto produto2 = new Produto(new Long(2), "chocolate belga", 100, new BigDecimal(40));
		Produto produto3 = new Produto(new Long(4), "chocolate branco", 30, new BigDecimal(20));

		List<Produto> produtos = Arrays.asList(produto1, produto2, produto3);

		// ("String attributeName", Object attributeValue)
		modelView.addObject("listarProdutos", produtos);// controller precisa enviar objetos para serem manipulados

		List<Produto> produtoDaRepository = produtoRepository.findAll(); // findAll(); select * from produto
		modelView.addObject("listarProdutos", produtoDaRepository);

		return modelView; // retorno para o HTML
	}

	@GetMapping("/produtos/criar") // Get pois a primeira entrada eh pra ele criar a telinha da nova página
	public ModelAndView criar(ProdutoDto produto) {
		ModelAndView modelView = new ModelAndView("produtos/criar");
		return modelView;
	}

	// Post recebe as informações da própria Model, no caso da /produtos
	@PostMapping("/produtos")
	// BindingResult para retornar o resultado da validação
	public ModelAndView salvar(@Valid ProdutoDto produto, BindingResult bindingResult) {

		// identifica se o result da validation deu erro, se sim, ele retorna um texto
		// na console e retorna a tela
		// vê se os atributos como NotBlank e NotNull estão preenchidos ou nãos
		if (bindingResult.hasErrors()) {
			System.out.println("Temos erros!");
			return new ModelAndView("/produtos/criar");
		}

		// objeto para mapear a informação do produto recebido pela model e injetar no
		// database
		// os parâmetros do .map são (objeto recebido na solicitação, classe a ser
		// mapeada)
		// mapper vai converter o ProdutoDto em Produto
		Produto produtoEntity = modelMapper.map(produto, Produto.class);

		// insert das informações atribuídas a produtoEntity, na base de dados MySql
		produtoRepository.save(produtoEntity);

		return new ModelAndView("redirect:/produtos"); // devolve o output para a tela de listagem dos produtos
	}

	// quando o cliente clicar no details a gnt vai chamar esse Get
	@GetMapping("produtos/{id}")
	public ModelAndView mostrar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isPresent()) {
			Produto produtoGet = produto.get();
			ModelAndView modelView = new ModelAndView("produtos/detalhe");
			modelView.addObject("produto", produtoGet);
			return modelView;
		}
		System.out.println("não encontrado!");
		return new ModelAndView("redirect:/produtos");
	}

}
