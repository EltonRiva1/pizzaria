package br.com.eltonriva.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.eltonriva.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.eltonriva.pizzaria.modelo.entidades.Ingrediente;
import br.com.eltonriva.pizzaria.modelo.entidades.Pizza;
import br.com.eltonriva.pizzaria.modelo.enumeracoes.CategoriaDePizza;
import br.com.eltonriva.pizzaria.modelo.servicos.ServicoIngrediente;
import br.com.eltonriva.pizzaria.modelo.servicos.ServicoPizza;
import br.com.eltonriva.pizzaria.propertyeditors.IngredientePropertyEditor;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	@Autowired
	private IngredientePropertyEditor ingredientePropertyEditor;
	@Autowired
	private ServicoPizza pizza;
	@Autowired
	private ServicoIngrediente ingrediente;

	@RequestMapping(method = RequestMethod.GET)
	public String listarPizzas(Model model) {
		model.addAttribute("pizzas", this.pizza.listar());
		model.addAttribute("categorias", CategoriaDePizza.values());
		model.addAttribute("ingredientes", this.ingrediente.listar());
		return "pizza/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarPizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IngredienteInvalidoException();
		} else {
			this.pizza.salvar(pizza);
		}
		model.addAttribute("pizzas", this.pizza.listar());
		return "pizza/tabela-pizzas";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{pizzaId}")
	public ResponseEntity<String> deletarPizza(@PathVariable Long pizzaId) {
		try {
			this.pizza.remover(pizzaId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{pizzaId}")
	public ResponseEntity<Pizza> buscarPizza(@PathVariable Long pizzaId) {
		Pizza pizza = this.pizza.buscar(pizzaId);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Ingrediente.class, this.ingredientePropertyEditor);
	}
}
