package com.algaworks.curso.jpa2.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;

public class LazyAcessorioDataModel extends LazyDataModel<Acessorio> implements Serializable {

	private static final long serialVersionUID = -5389102065600334116L;
	private AcessorioDAO acessorioDAO;
	
	public LazyAcessorioDataModel(AcessorioDAO acessorioDAO) {
		this.acessorioDAO = acessorioDAO;
	}
	
	@Override
	public List<Acessorio> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		List<Acessorio> acessorios = acessorioDAO.buscarTodosPaginacao(first, pageSize);
		
		this.setRowCount(acessorioDAO.buscarTotalRegistro().intValue());
		
 		return acessorios;
	}

}
