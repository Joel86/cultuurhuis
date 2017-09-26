package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Voorstelling;
import be.vdab.repositories.GenreRepository;
import be.vdab.repositories.KlantRepository;
import be.vdab.repositories.VoorstellingRepository;
import be.vdab.util.StringUtils;

/**
 * Servlet implementation class ReserverenServlet
 */
@WebServlet("/reserveren.htm")
public class ReserverenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
	private static final String MANDJE = "mandje";
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	@Resource(name = VoorstellingRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingRepository.setDataSource(dataSource);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Map<String, String> fouten = new HashMap<>();
		if(!StringUtils.isLong(idString)) {
			fouten.put("voorstelling", "Voorstelling niet gevonden");
		}else {
			Long id = Long.parseLong(idString);
			voorstellingRepository.read(id).ifPresent(
					voorstelling -> request.setAttribute("voorstelling", voorstelling));
			HttpSession session = request.getSession(false);
			if(session != null) {
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = ((Map<Long, Integer>)session.getAttribute(MANDJE));
				if(mandje != null && mandje.containsKey(id)) {
					request.setAttribute("aantalPlaatsenGereserveerd", mandje.get(id));
				}		
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aantalString = request.getParameter("aantal");
		String idString = request.getParameter("id");
		Long id = Long.parseLong(idString);
		voorstellingRepository.read(id).ifPresent(
				voorstelling -> request.setAttribute("voorstelling", voorstelling));
		Map<String, String> fouten = new HashMap<>();
		if(aantalString == null) {
			fouten.put("aantal", "Verplicht");
		}
		if(!StringUtils.isInt(aantalString)) {
			fouten.put("aantal", "Tik een cijfer");
		}else {
			if(Integer.parseInt(aantalString) < 1) {
				fouten.put("aantal", "Tik een getal tussen 1 en ");
			} else if(Integer.parseInt(aantalString) > voorstellingRepository.read(
					Long.parseLong(idString)).get().getVrijePlaatsen()) {
				fouten.put("aantal", "Tik een getal tussen 1 en ");
			}
		}
		if(fouten.isEmpty()) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = ((Map<Long, Integer>)session.getAttribute(MANDJE));
			if(mandje == null) {
				mandje = new HashMap<>();
			}
			mandje.put(Long.parseLong(request.getParameter("id")),
			Integer.parseInt(request.getParameter("aantal")));
			session.setAttribute(MANDJE, mandje);
			response.sendRedirect(request.getContextPath() + "/reservatiemandje.htm");
		}else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
