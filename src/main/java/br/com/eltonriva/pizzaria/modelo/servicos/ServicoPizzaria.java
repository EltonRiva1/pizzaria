package br.com.eltonriva.pizzaria.modelo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.eltonriva.pizzaria.modelo.entidades.Pizzaria;
import br.com.eltonriva.pizzaria.modelo.repositorios.PizzariaRepositorio;

@Service
public class ServicoPizzaria {
	@Autowired
	private PizzariaRepositorio pizzariaRepositorio;

	public Pizzaria getPizzariaLogada() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			throw new AuthenticationCredentialsNotFoundException("Usuário não logado!");
		UserDetails details = (UserDetails) authentication.getPrincipal();
		return this.pizzariaRepositorio.findOneByLogin(details.getUsername());
	}

	public List<Pizzaria> listarPizzariasQueContem(String nomePizza) {
		// TODO Auto-generated method stub
		return this.pizzariaRepositorio.listarPizzariasPorNomePizza(nomePizza);
	}
}
