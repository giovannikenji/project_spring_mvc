package br.com.fiap.appprodutoteste.produto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.appprodutoteste.produto.model.Produto;

@Repository // mapea a entidade e faz a criação na base de dados
//possui os creates, inserts, deletes, updates, etc
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	// JpaRepository<qual a classe que ele vai fazer o mapeamento, tipo do
	// identificador>
	// padrão de projeto que nos auxilia na persistência de dados
	// interface expõe apenas os métodos a serem usados para a persistência de dados
	// exposição de um contrato da nossa repository
	// JPA Repository possui as interfaces que tem so comandos CRUD

}
