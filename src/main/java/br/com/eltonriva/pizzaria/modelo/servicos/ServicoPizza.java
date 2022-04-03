package br.com.eltonriva.pizzaria.modelo.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eltonriva.pizzaria.modelo.entidades.Pizza;
import br.com.eltonriva.pizzaria.modelo.entidades.Pizzaria;
import br.com.eltonriva.pizzaria.modelo.repositorios.PizzaRepositorio;

@Service
public class ServicoPizza {
	@Autowired
	private ServicoPizzaria servicoPizzaria;
	@Autowired
	private PizzaRepositorio pizzaRepositorio;

	public void salvar(Pizza pizza) {
		pizza.setDono(this.servicoPizzaria.getPizzariaLogada());
		this.pizzaRepositorio.save(pizza);
	}

	public Iterable<Pizza> listar() {
		Pizzaria dono = this.servicoPizzaria.getPizzariaLogada();
		return this.pizzaRepositorio.findAllByDono(dono);
	}

	public Pizza buscar(long id) {
		Pizzaria dono = this.servicoPizzaria.getPizzariaLogada();
		return this.pizzaRepositorio.findByIdAndDono(id, dono);
	}

	public void remover(long id) {
		Pizza pizza = this.buscar(id);
		if (pizza != null)
			this.pizzaRepositorio.delete(pizza);
	}

	public List<String> listarNomesPizzasDisponiveis() {
		// TODO Auto-generated method stub
		List<Pizza> pizzas = this.pizzaRepositorio.findAll();
		List<String> nomesPizzas = pizzas.stream().map((pizza) -> {
			return pizza.getNome();
		}).sorted().collect(Collectors.toList());
		return nomesPizzas;
	}
}
