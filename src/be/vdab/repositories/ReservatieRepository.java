package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.vdab.entities.Reservatie;

public class ReservatieRepository extends AbstractRepository {
	private static final String CREATE = "insert into reservaties("
			+ "klantid, voorstellingsid, plaatsen) values(?,?,?)";
	public void create(Reservatie reservatie) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						CREATE, Statement.RETURN_GENERATED_KEYS)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1, reservatie.getKlant().getId());
			statement.setLong(2, reservatie.getVoorstelling().getId());
			statement.setInt(3, reservatie.getAantalPlaatsen());
			statement.executeUpdate();
			try(ResultSet resultSet = statement.getGeneratedKeys()) {
				resultSet.next();
				reservatie.setId(resultSet.getLong(1));
			}
			connection.commit();
		} catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
}
