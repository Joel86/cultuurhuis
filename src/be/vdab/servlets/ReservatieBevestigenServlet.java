package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.GenreRepository;
import be.vdab.repositories.KlantRepository;

/**
 * Servlet implementation class reservatieBevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class ReservatieBevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final KlantRepository klantRepository = new KlantRepository();
	@Resource(name = GenreRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean gebruikersnaamBestaat = klantRepository.read(request.getParameter("gebruikersnaam")) != null;
		boolean paswoordMatchesGebruikersnaam = (request.getParameter("paswoord")).equals(
				klantRepository.read(request.getParameter("gebruikersnaam")).getPaswoord());
		if(!gebruikersnaamBestaat || !paswoordMatchesGebruikersnaam) {
			request.setAttribute("fout", "Verkeerde gebruikernaam of paswoord");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
