package be.vdab.servlets;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;

/**
 * Servlet implementation class reservatieBevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class ReservatieBevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final KlantRepository klantRepository = new KlantRepository();
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			String gebruikersnaamString = (String)session.getAttribute("gebruikersnaamSession");
			if(gebruikersnaamString != null) {
				Optional<Klant> optionalKlant = klantRepository.read(gebruikersnaamString);
				request.setAttribute("klant", optionalKlant.get());
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Optional<Klant> optionalKlant = klantRepository.read(request.getParameter("gebruikersnaam"));
		if(optionalKlant.isPresent() && request.getParameter("paswoord").equals(optionalKlant.get().getPaswoord())) {
			request.setAttribute("klant", optionalKlant.get());
			HttpSession session = request.getSession();
			session.setAttribute("gebruikersnaamSession", request.getParameter("gebruikersnaam"));
			session.setAttribute("paswoordSession", request.getParameter("paswoord"));
		}else {
			request.setAttribute("fout", "Verkeerde gebruikersnaam of paswoord");
		}
		request.getRequestDispatcher(VIEW).forward(request,response);
	}

}
