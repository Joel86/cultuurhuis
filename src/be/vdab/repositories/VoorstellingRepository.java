package be.vdab.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Voorstelling;

public class VoorstellingRepository extends AbstractRepository {
	private static final String FIND_PERFORMANCES_FROM_DATE_BY_GENRE = 
			"select id, titel, uitvoerders, datum, prijs, vrijeplaatsen from voorstellingen where genreid = ?"
			+ " and datum >= ?";
	public List<Voorstelling> findFuturePerformancesByGenre(long id, LocalDate datum) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_PERFORMANCES_FROM_DATE_BY_GENRE)) {
			List<Voorstelling> voorstellingen = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1,id);
			statement.setDate(2, Date.valueOf(datum));
			try(ResultSet resultSet = statement.executeQuery()) {
				while(resultSet.next()) {
					voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
				}
			}
			connection.commit();
			return voorstellingen;
		} catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException {
		return new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"), 
				resultSet.getString("uitvoerders"), resultSet.getDate("datum").toLocalDate(),
				resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijePlaatsen"));
	}
}
