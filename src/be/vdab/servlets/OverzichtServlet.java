package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.KlantRepository;
import be.vdab.repositories.ReservatieRepository;

/**
 * Servlet implementation class OverzichtServlet
 */
@WebServlet("/overzicht.htm")
public class OverzichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "WEB-INF/JSP/overzicht.jsp";
	private final transient ReservatieRepository reservatieRepository = new ReservatieRepository();
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		reservatieRepository.setDataSource(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
