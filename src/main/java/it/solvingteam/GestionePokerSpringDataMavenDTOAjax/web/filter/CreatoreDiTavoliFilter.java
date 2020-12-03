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



@WebFilter(filterName="/CreatoreDiTavoliFilter", urlPatterns= {"/accessoEffettuato/gestioneTavoli/*", "/jsp/gestioneTavoli/*"})
public class CreatoreDiTavoliFilter implements Filter {
	
	private static final String[] EXCLUDED_URLS = {"/accessoEffettuato/gestioneTavoli/SchermataGeneraleGestioneTavoliServlet",
			"/jsp/gestioneTavoli/schermataGenerale.jsp","/jsp/gestioneTavoli/test/*","/jsp/gestioneTavoli/inserimento/*",
			"/accessoEffettuato/gestioneTavoli/test/","/accessoEffettuato/gestioneTavoli/inserimento/"};
	private static final String HOME_PATH = "";
	
    public CreatoreDiTavoliFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		HttpSession session=servletRequest.getSession();
		String pathAttuale = servletRequest.getServletPath();
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);

		/* Se non ha creato tavoli, lo butto fuori dal sito, invalidando la sessione 
		 * (ha provato ad accedere a una servlet o jsp "barando", visto che 
		 * il pulsante di ricerca dei tavoli è oscurato, se non ne ha creati)*/
		Boolean haCreatoTavoli=(boolean) session.getAttribute("haCreatoTavoli");
		if (!isInWhiteList && !haCreatoTavoli) {
			session.invalidate();
			request.setAttribute("errorMessage","Non puoi accedere a questa funzionalità");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
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
