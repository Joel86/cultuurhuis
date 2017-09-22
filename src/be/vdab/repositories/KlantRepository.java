package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Adres;
import be.vdab.entities.Klant;

public class KlantRepository extends AbstractRepository {
	private static final String FIND_BY_USERNAME = "select id, voornaam, familienaam,"
			+ " straat, huisnr, postcode, gemeente, gebruikersnaam"
			+ " from klanten where gebruikersnaam = ?";
	public Klant read(String gebruikersnaam) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, gebruikersnaam);
			Klant klant = new Klant();
			try(ResultSet resultSet = statement.executeQuery()) {
				if(resultSet.next()) {
					klant = resultSetRijNaarKlant(resultSet);
				}
			}
			connection.commit();
			return klant;
		} catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getLong("id"), resultSet.getString("voornaam"), 
				resultSet.getString("familienaam"), new Adres(resultSet.getString("straat"), 
						resultSet.getInt("huisnr"), resultSet.getInt("postcode"), 
						resultSet.getString("gemeente")),
				resultSet.getString("gebruikersnaam"));
	}
}
