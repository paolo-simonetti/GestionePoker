package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;


@WebFilter(filterName="/AutenticazioneFilter", urlPatterns={"/accessoEffettuato/*","/jsp/*"})
public class AutenticazioneFilter implements Filter {

	private static final String[] EXCLUDED_URLS = {"/jsp/generali/*","/assets/","/WEB-INF/"};
	private static final String HOME_PATH = "";
	
    public AutenticazioneFilter() {
    }

	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		HttpSession session=servletRequest.getSession();
		String pathAttuale = servletRequest.getServletPath();
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		boolean isAttivo = ((Utente) session.getAttribute("utenteIdentificato"))
				.getStatoUtente().equals(StatoUtente.conversioneStatoUtente.get("attivo"));				
		if(!isInWhiteList && (session.getAttribute("utenteIdentificato")==null||!isAttivo)) {
			session.invalidate();
			request.setAttribute("errorMessage","Non hai i permessi per effettuare questa operazione!");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
}
