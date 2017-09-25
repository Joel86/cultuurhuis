package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;
import be.vdab.entities.Voorstelling;
import be.vdab.repositories.KlantRepository;
import be.vdab.repositories.ReservatieRepository;
import be.vdab.repositories.VoorstellingRepository;

/**
 * Servlet implementation class reservatieBevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class ReservatieBevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final String SUCCESS_VIEW = "/WEB-INF/JSP/overzicht.jsp";
	private final transient KlantRepository klantRepository = new KlantRepository();
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	private final transient ReservatieRepository reservatieRepository = new ReservatieRepository();
	private static final String MANDJE = "mandje";
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
		voorstellingRepository.setDataSource(dataSource);
		reservatieRepository.setDataSource(dataSource);
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("zoekMeOp") != null) {
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
		else if(request.getParameter("bevestigen") != null) {
			HttpSession session = request.getSession();
			String gebruikersnaamString = (String) session.getAttribute("gebruikersnaamSession");
			Klant klant = klantRepository.read(gebruikersnaamString).get();
			List<Reservatie> gelukteReservaties = new LinkedList<>();
			List<Reservatie> mislukteReservaties = new LinkedList<>();
			Reservatie reservatie;
			Voorstelling voorstelling;
			Map<Long, Integer> mandje = (Map<Long, Integer>)session.getAttribute(MANDJE);
			for(Map.Entry<Long, Integer> mandjeEntry : mandje.entrySet()) {
				voorstelling = voorstellingRepository.read(mandjeEntry.getKey()).get();
				reservatie = new Reservatie(voorstelling, klant, mandjeEntry.getValue());
				if(voorstellingRepository.updateVrijePlaatsen(mandjeEntry.getKey(), mandjeEntry.getValue())) {
					reservatieRepository.create(reservatie);
					gelukteReservaties.add(reservatie);
				}else {
					mislukteReservaties.add(reservatie);
				}
			}
			request.setAttribute("gelukteReservaties", gelukteReservaties);
			request.setAttribute("mislukteReservaties", mislukteReservaties);
			session.removeAttribute(MANDJE);
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		}
	}

}
