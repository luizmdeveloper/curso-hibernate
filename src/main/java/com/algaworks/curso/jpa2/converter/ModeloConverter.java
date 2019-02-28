package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.ModeloDAO;
import com.algaworks.curso.jpa2.modelo.Modelo;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Modelo.class)
public class ModeloConverter implements Converter {
	
	private ModeloDAO modeloDAO;
	
	public ModeloConverter() {
		this.modeloDAO = CDIServiceLocator.getBean(ModeloDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Modelo modelo = null;
		
		if (value != null) {
			modelo = modeloDAO.buscarPorCodigo(new Long(value));
		}
		
		return modelo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Modelo) value).getCodigo();
			return codigo == null ? null : codigo.toString(); 
		}
		
		return "";
	}

}
