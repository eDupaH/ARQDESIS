package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmeDAO;
import models.Idioma;
import service.FilmeService;
import service.FilmeTO;

public class CriarFilme implements Command {
	@Override
	public void executar(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
			
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pGenero = request.getParameter("genero");
		String pIdioma[] = request.getParameterValues("idioma");
		
		
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {
		}
		
		FilmeTO filme = new FilmeTO();	
		filme.setId(id);
		filme.setNome(pNome);
		filme.setGenero(pGenero);
		
	    for (String idIdioma : pIdioma) {
			filme.getIdioma().add(new Idioma(Integer.parseInt(idIdioma)));
		}
			
		//instanciar o service
		FilmeService ls = new FilmeService();
		ls.criar(filme);
		filme = ls.carregar(filme.getId());
		
		//enviar para o jsp
		request.setAttribute("filme", filme);
		RequestDispatcher view = 
		request.getRequestDispatcher("VisualizarFilme.jsp");
		view.forward(request, response);
			
	}
	
	public int busca(FilmeTO filme, ArrayList<FilmeTO> lista) {
		FilmeTO to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == filme.getId()){
				return i;
			}
		}
		return -1;
	}
}