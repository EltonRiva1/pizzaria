package br.com.eltonriva.pizzaria.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eltonriva.pizzaria.modelo.entidades.Ingrediente;
import br.com.eltonriva.pizzaria.modelo.repositorios.IngredienteRepositorio;

@Component
public class IngredientePropertyEditor extends PropertyEditorSupport {
	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;

	@Override
	public void setAsText(String string) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		long l = Long.parseLong(string);
		Ingrediente ingrediente = this.ingredienteRepositorio.findOne(l);
		this.setValue(ingrediente);
	}
}
