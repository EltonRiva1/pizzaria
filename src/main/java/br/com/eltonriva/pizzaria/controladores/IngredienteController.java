package br.com.eltonriva.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eltonriva.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.eltonriva.pizzaria.modelo.entidades.Ingrediente;
import br.com.eltonriva.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;
import br.com.eltonriva.pizzaria.modelo.servicos.ServicoIngrediente;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
	@Autowired
	private ServicoIngrediente servicoIngrediente;

	@RequestMapping(method = RequestMethod.GET)
	public String listarIngredientes(Model model) {
		Iterable<Ingrediente> ingredientes = this.servicoIngrediente.listar();
		model.addAttribute("titulo", "Listagem de Ingredientes");
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			throw new IngredienteInvalidoException();
		} else {
			this.servicoIngrediente.salvar(ingrediente);
		}
		model.addAttribute("ingredientes", this.servicoIngrediente.listar());
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/tabela-ingredientes";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarIngrediente(@PathVariable Long id) {
		try {
			this.servicoIngrediente.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Ingrediente buscarIngrediente(@PathVariable Long id) {
		Ingrediente ingrediente = this.servicoIngrediente.buscar(id);
		return ingrediente;
	}
}
