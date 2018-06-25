package service;

import java.util.ArrayList;

import dao.FilmeDAO;

public class FilmeService {
	FilmeDAO dao;

	public FilmeService() {
		dao = new FilmeDAO();
	}

	public void criar(FilmeTO to) {
		dao.incluir(to);
	}
	
	public void editar(FilmeTO to) {
		dao.atualizar(to);
	}
	
	public ArrayList<FilmeTO> BuscarTodos() {
		return dao.listar();
	}
	
	public ArrayList<FilmeTO> BuscarIdioma(int idIdioma) {
		return dao.listarIdioma(idIdioma);
	}

	public FilmeTO carregar(int id) {
		return dao.carregar(id);
	}

	public void deletar(int iId) {
		dao.excluir(iId);
	}
	
}
