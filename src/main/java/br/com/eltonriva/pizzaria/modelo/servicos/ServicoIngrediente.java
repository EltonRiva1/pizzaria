package br.com.eltonriva.pizzaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eltonriva.pizzaria.modelo.entidades.Ingrediente;
import br.com.eltonriva.pizzaria.modelo.entidades.Pizzaria;
import br.com.eltonriva.pizzaria.modelo.repositorios.IngredienteRepositorio;

@Service
public class ServicoIngrediente {
	@Autowired
	private ServicoPizzaria servicoPizzaria;
	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;

	public void salvar(Ingrediente ingrediente) {
		ingrediente.setDono(this.servicoPizzaria.getPizzariaLogada());
		this.ingredienteRepositorio.save(ingrediente);
	}

	public Iterable<Ingrediente> listar() {
		Pizzaria dono = this.servicoPizzaria.getPizzariaLogada();
		return this.ingredienteRepositorio.findAllByDono(dono);
	}

	public Ingrediente buscar(long id) {
		Pizzaria dono = this.servicoPizzaria.getPizzariaLogada();
		return this.ingredienteRepositorio.findByIdAndDono(id, dono);
	}

	public void remover(long id) {
		Ingrediente ingrediente = this.buscar(id);
		if (ingrediente != null)
			this.ingredienteRepositorio.delete(ingrediente);
	}
}
