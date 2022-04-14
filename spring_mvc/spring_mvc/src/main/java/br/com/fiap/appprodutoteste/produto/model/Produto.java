package br.com.fiap.appprodutoteste.produto.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//classe responsável por cuidar do banco de dados

//ctrl + shift + o = atalho para fazer os imports 
@Entity //informa que a classe será usada como uma Entidade no banco de dados
public class Produto {
	@Id //indica que esse atributo será utilizado como chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //indica que o id será gerado automaticamente
	private Long id; 
	
	@Column(nullable = false) //define que as colunas não serão null
	private String nome; 
	private Integer quantidade; 
	private BigDecimal valor;
	
	public Produto() {
	}
	
	public Produto(Long id, String nome, Integer quantidade, BigDecimal valor) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	} 
}
