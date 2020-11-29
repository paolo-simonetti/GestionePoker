package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.registrazioneAccesso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrazioneAccesso/PrepareRegistrazioneServlet")
public class PrepareRegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareRegistrazioneServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/generali/registrazione.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
