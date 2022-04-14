package br.com.fiap.appprodutoteste.produto.produto.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//DTO - Data Transfer Object - objeto de transferêcia de dados
//Classe destinada a receber os inputs do formulário web
//A model deve ter como função, modelar os dados do databases, enquanto que a Dto pode fazer esse trabalho
//de receber as informações
public class ProdutoDto {

	//validações de inserção de dados
	
	@NotBlank
	@NotNull
	private String nome;
	private Integer quantidade;
	
	//número deve ser no mínimo 0.0
	@DecimalMin(value="0.0")
	@NotNull
	private BigDecimal valor;
		
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
}
