package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseManager;

public class Classe {

	private Connection conn;
	private Integer idClasse;
	private String nomClasse;
	private List<Cours> listeCours;
	private List<Eleves> listeEleves;

	/**
	 * @param idClasse
	 * @param nomClasse
	 * @param ListeCours
	 * @param ListeEleves
	 */
	public Classe(String nomClasse, List<Cours> listeCours, List<Eleves> listeEleves) {
		this.nomClasse = nomClasse;
		this.listeCours = listeCours;
		this.listeEleves = listeEleves;
	}

	public Classe() {

	}

	public Integer getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Integer idClasse) {
		this.idClasse = idClasse;
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public List<Eleves> getListeEleves() {
		return listeEleves;
	}

	public void setListeEleves(List<Eleves> listeEleves) {
		this.listeEleves = listeEleves;
	}

	// Get la liste des classes
	public static List<Classe> getClasses() {
		try {
			String query = "select * from classe";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<Classe> ls = new ArrayList();

			while (rs.next()) {
				Classe classe = new Classe();
				classe.setIdClasse(rs.getInt("idClasse"));
				classe.setNomClasse(rs.getString("nomClasse"));
				classe.setListeCours(Cours.getCoursFromClasse(classe));
				classe.setListeEleves(ElevesSQL.getElevesFromClasse(classe));
				ls.add(classe);
			}
			return ls;
		} catch (Exception e) {
			return null;
		}
	}

	// ajoute une classe
	public static int add(Classe classe) {
		try {
			String query = "insert into classe(nomClasse) VALUES (?)";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setString(1, classe.getNomClasse());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			return -1;
		}
	}

	// supprime une classe
	public static void delete(int id) {
		try {
			String query = "delete from classe where idClasse = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {

		}
	}

	// met à jour un admin
	public void update(Classe classe) throws SQLException {

		String query = "update classe set nomClasse=?,  listeEleve= ?, listeCours=? where idClasse = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, classe.getNomClasse());
		// ps.setString(2, classe.getListeEleves());
		// ps.setDate(3, classe.getListeCours());
		ps.setInt(4, classe.getIdClasse());
		ps.executeUpdate();
	}
}
