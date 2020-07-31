package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;

public class Cours {

	private Integer idCours;
	private String nomCours;
	private Date dateDebut;
	private Date dateFin;

	/**
	 * @param nomCours
	 * @param dateDebut
	 * @param dateFin
	 * @param dateNaissanceAdmin
	 */
	public Cours(String nomCours, Date dateDebut, Date dateFin) {
		this.nomCours = nomCours;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Cours() {

	}

	public Integer getIdCours() {
		return idCours;
	}

	public void setIdCours(Integer idCours) {
		this.idCours = idCours;
	}

	public String getNomCours() {
		return nomCours;
	}

	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	// Get la liste des cours
	public static List<Cours> getCours() {
		try {
			String query = "select * from cours";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<Cours> ls = new ArrayList();

			while (rs.next()) {
				Cours cours = new Cours();
				cours.setIdCours(rs.getInt("idCours"));
				cours.setNomCours(rs.getString("nomCours"));
				cours.setDateDebut(rs.getDate("dateDebut"));
				cours.setDateFin(rs.getDate("dateFin"));
				ls.add(cours);
			}
			return ls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Cours>();
		}
	}

	// ajoute un cours
	public static int add(Cours cours, int idClasse) {
		try {
			String query = "insert into cours(nomCours, dateDebutCours ,dateFinCours, idClasse) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setString(1, cours.getNomCours());
			ps.setDate(2, cours.getDateDebut());
			ps.setDate(3, cours.getDateFin());
			ps.setInt(4, idClasse);
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	// supprime un cours
	public static void delete(int id) {
		try {
			String query = "delete from cours where idCours = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// met à jour un cours
	public static void update(Cours cours) {

		try {
			String query = "update cours set nomCours=?,  dateDebut= ?, dateFin=? where idCours = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setString(1, cours.getNomCours());
			ps.setDate(2, cours.getDateDebut());
			ps.setDate(3, cours.getDateFin());
			ps.setInt(4, cours.getIdCours());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Cours> getCoursFromClasse(Classe classe) {
		try {
			String query = "SELECT * FROM COURS WHERE Cours.idClasse = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setInt(1, classe.getIdClasse());
			ResultSet rs = ps.executeQuery();

			List<Cours> cours = new ArrayList();
			while (rs.next()) {
				Cours c = new Cours();
				c.setIdCours(rs.getInt("idCours"));
				c.setNomCours(rs.getString("nomCours"));
				c.setDateDebut(rs.getDate("dateDebutCours"));
				c.setDateFin(rs.getDate("dateFinCours"));
				cours.add(c);
			}
			return cours;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Cours>();
		}
	}

}
