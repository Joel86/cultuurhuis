package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> fouten = new HashMap<>();
		Optional<Klant> optionalKlant = klantRepository.read(request.getParameter("gebruikersnaam"));
		if(optionalKlant.isPresent()) {
			fouten.put("gebruikersnaamInGebruik", "Gebruikersnaam bestaat al. Kies een andere.");
		}
		if(request.getParameter("voornaam") == null) {
			fouten.put("voornaamLeeg", "Voornaam niet ingevuld");
		}
		if(request.getParameter("familienaam") == null) {
			fouten.put("familienaamLeeg", "Familienaam niet ingevuld");
		}
		if(request.getParameter("straat") == null) {
			fouten.put("straatLeeg", "Straat niet ingevuld");
		}
		if(request.getParameter("huisnr") == null) {
			fouten.put("huisnrLeeg", "Huisnr. niet ingevuld");
		}
		if(request.getParameter("postcode") == null) {
			fouten.put("postcodeLeeg", "Postcode niet ingevuld");
		}
		if(request.getParameter("gemeente") == null) {
			fouten.put("gemeenteLeeg", "Gemeente niet ingevuld");
		}
		if(request.getParameter("gebruikersnaam") == null) {
			fouten.put("gebruikersnaamLeeg", "Gebruikersnaam niet ingevuld");
		}
		if(request.getParameter("paswoord") == null) {
			fouten.put("paswoord", "Paswoord niet ingevuld");
		}
		if(request.getParameter("herhaalpaswoord") == null) {
			fouten.put("herhaalpaswoordLeeg", "Herhaal paswoord niet ingevuld");
		}
		if(!(request.getParameter("paswoord")).equals(request.getParameter("herhaalpaswoord"))) {
			fouten.put("paswoordFout", "Paswoorden komen niet overeen");
		}
		if(fouten.isEmpty()) {
			Klant nieuweKlant = new Klant(request.getParameter("voornaam"), request.getParameter("familienaam"), 
				new Adres(request.getParameter("straat"), Integer.parseInt(request.getParameter("huisnr")), 
						Integer.parseInt(request.getParameter("postcode")), request.getParameter("gemeente")), 
					request.getParameter("gebruikersnaam"), request.getParameter("paswoord"));
			klantRepository.create(nieuweKlant);
			response.sendRedirect(request.getContextPath() + "/bevestigen.htm");
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
