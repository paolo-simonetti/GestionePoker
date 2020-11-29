package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.registrazioneAccesso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registrazioneAccesso/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public LogoutServlet() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Non è necessario salvare le informazioni dell'utente, prima che si scolleghi.
		 * Avrei dovuto farlo se, quando un utente gioca, la possibilità di capitalizzare
		 * il credito accumulato gli fosse stata data solo abbandonando il tavolo */
		request.getSession().invalidate();
		request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
