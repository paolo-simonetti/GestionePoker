package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.inserimento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/accessoEffettuato/gestioneTavoli/inserimento/PrepareInsertTavoliServlet")
public class PrepareInsertTavoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareInsertTavoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext()
			.getRequestDispatcher("/jsp/gestioneTavoli/inserimento/inserisciNuovo.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
