package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IdiomaDAO;
import service.FilmeService;
import service.FilmeTO;
import service.IdiomaService;
import service.IdiomaTO;

public class EditarFilmeTela implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//instanciar o service
		IdiomaService lsIdioma = new IdiomaService();
		ArrayList<IdiomaTO> idiomas = lsIdioma.listar();
		
		FilmeService ls = new FilmeService();
		String sId = request.getParameter("id");
		int iId = Integer.parseInt(sId);
		FilmeTO filme = ls.carregar(iId);
		
		//enviar para o jsp
		request.setAttribute("filme", filme);
		request.setAttribute("idiomas", idiomas);
		RequestDispatcher view = 
		request.getRequestDispatcher("EditarFilme.jsp");
		view.forward(request, response);
			
	}

}
