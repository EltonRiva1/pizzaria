package br.com.eltonriva.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.eltonriva.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface PizzariaRepositorio extends CrudRepository<Pizzaria, Long> {
	@Query("select p from Pizzaria p where p.usuario.login=?")
	public Pizzaria findOneByLogin(String login);

	@Query("select p1 from Pizzaria p1 inner join p1.pizzas p2 where p2.nome=?")
	public List<Pizzaria> listarPizzariasPorNomePizza(String nomePizza);
}
