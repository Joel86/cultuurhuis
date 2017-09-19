package be.vdab.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Genre;
import be.vdab.repositories.GenreRepository;
import be.vdab.repositories.VoorstellingRepository;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient GenreRepository genreRepository = new GenreRepository();
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Genre> genres = genreRepository.findAll();
		request.setAttribute("genres", genres);
		LocalDateTime vandaag = LocalDateTime.now();
		if(request.getParameter("id") != null) {
			String idString = request.getParameter("id");
			request.setAttribute("genreVoorstellingen",
					voorstellingRepository.findFuturePerformancesByGenre(Long.parseLong(idString), vandaag));
		}
		request.getRequestDispatcher(VIEW).forward(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	@Resource(name = GenreRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		genreRepository.setDataSource(dataSource);
		voorstellingRepository.setDataSource(dataSource);
	}
}
