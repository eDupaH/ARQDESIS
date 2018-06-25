package service;

import java.util.ArrayList;

import dao.IdiomaDAO;

public class IdiomaService {
	IdiomaDAO dao;

	public IdiomaService() {
		dao = new IdiomaDAO();
	}

	public ArrayList<IdiomaTO> listar() {
		return dao.listar();
	}
	
	
}
