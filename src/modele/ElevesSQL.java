package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;

import java.sql.Date;

public class ElevesSQL {
	private Connection conn;

	/**
	 * @param conn
	 */
	public ElevesSQL(Connection conn) {
		this.conn = conn;
	}

	// static Connection conn = DataConnection.getConnection();

	// Get un eleve
	public Eleves getEleve(int id) throws SQLException {

		String query = "select * from eleve where idEleve= ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		Eleves eleves = new Eleves();
		ResultSet rs = ps.executeQuery();
		boolean check = false;

		while (rs.next()) {
			check = true;
			eleves.setIdEleve(rs.getInt("idEleve"));
			eleves.setNomEleve(rs.getString("nomEleve"));
			eleves.setPrenomEleve(rs.getString("prenomEleve"));
			eleves.setDateNaissance(rs.getDate("dateNaissance"));
		}

		if (check == true) {
			return eleves;
		} else
			return null;
	}

	// Get la liste des eleves
	public List<Eleves> getEleves() throws SQLException {
		String query = "select * from eleve";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<Eleves> ls = new ArrayList();

		while (rs.next()) {
			Eleves eleves = new Eleves();
			eleves.setIdEleve(rs.getInt("idEleve"));
			eleves.setNomEleve(rs.getString("nomEleve"));
			eleves.setPrenomEleve(rs.getString("prenomEleve"));
			eleves.setDateNaissance(rs.getDate("dateNaissance"));
			ls.add(eleves);
		}
		return ls;
	}

	// ajoute un eleve
	public static int add(Eleves eleve, int idClasse) {
		try {
			String query = "insert into eleve(nomEleve, prenomEleve, dateNaissance, idClasse) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setString(1, eleve.getNomEleve());
			ps.setString(2, eleve.getPrenomEleve());
			ps.setDate(3, eleve.getDateNaissance());
			ps.setInt(4,  idClasse);
			int n = ps.executeUpdate();
			System.out.println(n +" lignes ont été ajoutés (élève)");
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	// supprime un eleve
	public void delete(int id) throws SQLException {
		String query = "delete from eleve where idEleve = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	// met à jour un eleve
	public void update(Eleves eleve) throws SQLException {

		String query = "update eleve set nomEleve=?,  prenomEleve= ?, dateNaissance=?, idClasse=? where idEleve = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, eleve.getNomEleve());
		ps.setString(2, eleve.getPrenomEleve());
		ps.setDate(3, eleve.getDateNaissance());
		ps.setInt(4, eleve.getIdEleve());
		ps.executeUpdate();
	}
	
	public static List<Eleves> getElevesFromClasse(Classe classe) {
		try {
			String query = "SELECT * FROM Eleve WHERE Eleve.idClasse = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setInt(1, classe.getIdClasse());
			ResultSet rs = ps.executeQuery();

			List<Eleves> eleves = new ArrayList<Eleves>();
			while (rs.next()) {
				Eleves e = new Eleves();
				e.setIdEleve(rs.getInt("idEleve"));
				e.setNomEleve(rs.getString("nomEleve"));
				e.setPrenomEleve(rs.getString("prenomEleve"));
				e.setDateNaissance(rs.getDate("dateNaissance"));
				eleves.add(e);
			}
			System.out.println("getElevesFromClasse size : "+eleves.size());
			return eleves;
		} catch (Exception e) {
			return new ArrayList<Eleves>();
		}
	}

}
