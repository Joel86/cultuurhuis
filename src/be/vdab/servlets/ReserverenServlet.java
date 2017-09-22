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

import be.vdab.repositories.GenreRepository;
import be.vdab.repositories.VoorstellingRepository;

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
		request.setAttribute("voorstelling", voorstellingRepository.read(Long.parseLong(idString)).get());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Long, Integer> mandje = ((Map<Long, Integer>)session.getAttribute(MANDJE));
		if(mandje == null) {
			mandje = new HashMap<>();
		}
		mandje.put(Long.parseLong(request.getParameter("id")),
			Integer.parseInt(request.getParameter("aantal")));
		session.setAttribute(MANDJE, mandje);
		response.sendRedirect(request.getContextPath() + "/reservatiemandje.htm");
	}
}
