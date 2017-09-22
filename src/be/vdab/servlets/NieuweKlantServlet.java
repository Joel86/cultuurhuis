package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Adres;
import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;

/**
 * Servlet implementation class NieuweKlantServlet
 */
@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "WEB-INF/JSP/nieuweklant.jsp";
	private final transient KlantRepository klantRepository = new KlantRepository();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Klant nieuweKlant = new Klant(request.getParameter("voornaam"), request.getParameter("familienaam"), 
				new Adres(request.getParameter("straat"), Integer.parseInt(request.getParameter("huisnr")), 
						Integer.parseInt(request.getParameter("postcode")), request.getParameter("gemeente")), 
					request.getParameter("gebruikersnaam"), request.getParameter("paswoord"));
		klantRepository.create(nieuweKlant);
	}

}
