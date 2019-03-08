package com.algaworks.curso.jpa2.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.ModeloDAO;
import com.algaworks.curso.jpa2.modelo.Modelo;

public class LazyModeloDataModel extends LazyDataModel<Modelo> implements Serializable {

	private static final long serialVersionUID = 1L;
	private ModeloDAO modeloDAO;
	
	public LazyModeloDataModel(ModeloDAO modeloDAO) {
		this.modeloDAO = modeloDAO;
	}
	
	@Override
	public List<Modelo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {		
		List<Modelo> modelos = modeloDAO.buscarTodosPaginacao(first, pageSize);
		
		this.setRowCount(modeloDAO.buscarTotalRegistro().intValue());
		
		return modelos;
	}
}
