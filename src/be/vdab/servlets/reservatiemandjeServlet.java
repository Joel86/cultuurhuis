package be.vdab.servlets;

import java.io.IOException;
import java.util.Map;
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

import be.vdab.repositories.GenreRepository;
import be.vdab.repositories.VoorstellingRepository;

/**
 * Servlet implementation class reservatiemandjeServlet
 */
@WebServlet("/reservatiemandje.htm")
public class reservatiemandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatiemandje.jsp";
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	@Resource(name = GenreRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingRepository.setDataSource(dataSource);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Long, Integer> reservatiesHashMap = (Map<Long, Integer>)session.getAttribute("reservatiemandje");
		request.setAttribute("reservaties", reservatiesHashMap);
		Set<Long> reservatieIdsSet = reservatiesHashMap.keySet();
		request.setAttribute("gereserveerdeVoorstellingen",
			reservatieIdsSet.stream()
				.map(id -> voorstellingRepository.read(id))
				.collect(Collectors.toSet()));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
