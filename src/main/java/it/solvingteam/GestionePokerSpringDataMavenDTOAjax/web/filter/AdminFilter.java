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


@WebFilter(filterName="/AdminFilter", urlPatterns={"/accessoEffettuato/gestioneAmministrazione/*","/jsp/gestioneAmministrazione/*"})
public class AdminFilter implements Filter {

   
    public AdminFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		HttpSession session=servletRequest.getSession();
		if(!(boolean) session.getAttribute("isAdmin")) {
			session.invalidate();
			request.setAttribute("errorMessage","Non hai i permessi per effettuare questa operazione!");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
