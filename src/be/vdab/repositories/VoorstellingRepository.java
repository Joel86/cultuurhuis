package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Voorstelling;

public class VoorstellingRepository extends AbstractRepository {
	private static final String SELECT_BY_ID = "select id, titel, uitvoerders, datum, prijs, vrijeplaatsen"
			+ " from voorstellingen where id = ?";
	private static final String FIND_PERFORMANCES_FROM_DATE_BY_GENRE = 
			"select id, titel, uitvoerders, datum, prijs, vrijeplaatsen from voorstellingen where genreid = ?"
			+ " and datum >= ? order by datum asc";
	private static final String UPDATE_PLAATSEN = "update voorstellingen set vrijeplaatsen = vrijeplaatsen - ?"
			+ " where id = ? and vrijeplaatsen >= ?";
	public List<Voorstelling> findFuturePerformancesByGenre(long id, LocalDateTime datumTijd) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_PERFORMANCES_FROM_DATE_BY_GENRE)) {
			List<Voorstelling> voorstellingen = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1,id);
			statement.setTimestamp(2, Timestamp.valueOf(datumTijd));
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
	public Optional<Voorstelling> read(long id) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1, id);
			Voorstelling voorstelling = new Voorstelling();
			try(ResultSet resultSet = statement.executeQuery()) {
				if(resultSet.next()) {
					voorstelling = resultSetRijNaarVoorstelling(resultSet);
				}
			}
			connection.commit();
			return Optional.ofNullable(voorstelling);
		} catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	public boolean updateVrijePlaatsen(long id, int aantal) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PLAATSEN)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setInt(1, aantal);
			statement.setLong(2, id);
			statement.setInt(3, aantal);
			int aantalGewijzigd = statement.executeUpdate();
			connection.commit();
			return aantalGewijzigd > 0;
		} catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException {
		return new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"), 
				resultSet.getString("uitvoerders"), resultSet.getTimestamp("datum").toLocalDateTime(),
				resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijePlaatsen"));
	}
}
