package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Funcionario.class)
public class FuncionarioConverter implements Converter {
	
	private FuncionarioDAO funcionarioDAO;
	
	public FuncionarioConverter() {
		this.funcionarioDAO = CDIServiceLocator.getBean(FuncionarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario funcionario = null;
		
		if (value != null) {
			funcionario = funcionarioDAO.buscarPorCodigo(new Long(value));
		}
		
		return funcionario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Long codigo = ((Funcionario) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		
		return "";
	}
}
