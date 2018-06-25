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

public class BuscarFilmesIdioma implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmeService ls = new FilmeService();
		String sIdioma = request.getParameter("idioma");
		int iIdioma = Integer.parseInt(sIdioma);
		ArrayList<FilmeTO> filmes = ls.BuscarIdioma(iIdioma);

		IdiomaService lsIdioma = new IdiomaService();
		ArrayList<IdiomaTO> idiomas = lsIdioma.listar();
		
		//enviar para o jsp
		request.setAttribute("idiomas", idiomas);
		request.setAttribute("filmes", filmes.toArray());
		RequestDispatcher view = 
		request.getRequestDispatcher("ListarFilmes.jsp");
		view.forward(request, response);

	}

}
