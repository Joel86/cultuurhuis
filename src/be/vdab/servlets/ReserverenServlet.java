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
import be.vdab.repositories.VoorstellingRepository;

/**
 * Servlet implementation class ReserverenServlet
 */
@WebServlet("/reserveren.htm")
public class ReserverenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		request.setAttribute("voorstelling", voorstellingRepository.read(Long.parseLong(idString)));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	@Resource(name = GenreRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingRepository.setDataSource(dataSource);
	}
}
