package controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;

/**
 * Servlet implementation class FilmeControler
 */
@WebServlet("/FilmeControler.do")
public class ManterFilmeControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManterFilmeControler() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doExecute(HttpServletRequest request, HttpServletResponse
    		response) throws ServletException, IOException {
    	try {
    		request.setCharacterEncoding("UTF-8");
    		Command comando =
    				(Command)Class.forName("command."+request.getParameter("command")).newInstance();
    		comando.executar(request, response);
    	} catch (InstantiationException | IllegalAccessException
    			| ClassNotFoundException e) {
    		e.printStackTrace();
    		throw new ServletException(e);
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request,response);	
		
	}

}
