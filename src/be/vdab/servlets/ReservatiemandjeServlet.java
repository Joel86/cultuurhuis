package be.vdab.servlets;

import java.io.IOException;
import java.util.Map;
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
public class ReservatiemandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatiemandje.jsp";
	private static final String MANDJE = "mandje";
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	@Resource(name = GenreRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingRepository.setDataSource(dataSource);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Map<Long, Integer> mandje = (Map<Long, Integer>)session.getAttribute(MANDJE);
			if(mandje != null) {
				request.setAttribute("voorstellingenInMandje",
						(mandje.keySet()).stream()
						.map(id -> voorstellingRepository.read(id))
						.collect(Collectors.toSet()));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
