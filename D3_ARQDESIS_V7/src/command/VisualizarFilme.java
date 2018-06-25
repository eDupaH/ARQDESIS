package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FilmeService;
import service.FilmeTO;

public class VisualizarFilme implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FilmeService ls = new FilmeService();
		String sId = request.getParameter("id");
		int iId = Integer.parseInt(sId);
		FilmeTO filme = ls.carregar(iId);
		
		//enviar para o jsp
		request.setAttribute("filme", filme);
		RequestDispatcher view = 
		request.getRequestDispatcher("VisualizarFilme.jsp");
		view.forward(request, response);
	}

}
