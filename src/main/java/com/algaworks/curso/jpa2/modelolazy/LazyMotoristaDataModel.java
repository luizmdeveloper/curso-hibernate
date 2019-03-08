package com.algaworks.curso.jpa2.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;

public class LazyMotoristaDataModel extends LazyDataModel<Motorista> implements Serializable {

	private static final long serialVersionUID = 1L;
	private MotoristaDAO motoristaDAO;
	
	public LazyMotoristaDataModel(MotoristaDAO motoristaDAO) {
		this.motoristaDAO = motoristaDAO;
	}
	
	@Override
	public List<Motorista> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		List<Motorista> motoristas = motoristaDAO.buscarTodosPaginacao(first, pageSize);
		
		this.setRowCount(motoristaDAO.buscarTotalRegitro().intValue());
		
		return motoristas;
	}

}
