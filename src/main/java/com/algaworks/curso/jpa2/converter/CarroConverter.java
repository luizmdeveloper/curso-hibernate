package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Carro.class)
public class CarroConverter implements Converter {
	
	private CarroDAO carroDAO;
	
	public CarroConverter() {
		this.carroDAO = CDIServiceLocator.getBean(CarroDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Carro carro = null;
		
		if (value != null) {
			carro = carroDAO.bucarPorCodigo(new Long(value));
		}

		return carro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Carro) value).getCodigo();
			return codigo == null ? "": codigo.toString(); 
		}
		return "";
	}

}
