package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.datiPersonali;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/accessoEffettuato/datiPersonali/VisualizzaDatiPersonaliServlet")
public class VisualizzaDatiPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VisualizzaDatiPersonaliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/datiPersonali/visualizzaDatiPersonali.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
