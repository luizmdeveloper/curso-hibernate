package com.algaworks.curso.jpa2.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;

public class LazyCarroDataModel extends LazyDataModel<Carro> implements Serializable {

	private static final long serialVersionUID = -2931212668991771622L;
	
	private CarroDAO carroDAO;
	
	public LazyCarroDataModel(CarroDAO carroDAO) {
		this.carroDAO = carroDAO;
	}
	
	@Override
	public List<Carro> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		List<Carro> carros = carroDAO.buscarTodosPaginacao(first, pageSize);
		
		this.setRowCount(carroDAO.buscarTotalRegistro().intValue());
		
		return carros;
	}

}
