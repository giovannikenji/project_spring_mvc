package br.com.fiap.appprodutoteste.produto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//classe de configuração para inicializar o Mapper


@Configuration
public class SpringConfig {
	@Bean //inicializa o mapper automaticamente
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
