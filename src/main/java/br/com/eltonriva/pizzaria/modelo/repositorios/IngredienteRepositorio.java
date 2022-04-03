package br.com.eltonriva.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.eltonriva.pizzaria.modelo.entidades.Ingrediente;
import br.com.eltonriva.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface IngredienteRepositorio extends CrudRepository<Ingrediente, Long> {
	public List<Ingrediente> findAllByDono(Pizzaria dono);

	public Ingrediente findByIdAndDono(Long id, Pizzaria dono);
}
