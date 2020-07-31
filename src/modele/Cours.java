package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cours {

	private static Connection conn;
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
	public List<Cours> getCours() throws SQLException {
		String query = "select * from cours";
		PreparedStatement ps = conn.prepareStatement(query);
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
	}

	// ajoute un cours
	public int add(Cours cours) throws SQLException {
		String query = "insert into cours(nomCours, dateDebut ,dateFin ) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, cours.getNomCours());
		ps.setDate(2, cours.getDateDebut());
		ps.setDate(3, cours.getDateFin());
		int n = ps.executeUpdate();
		return n;
	}

	// supprime un cours
	public void delete(int id) throws SQLException {
		String query = "delete from cours where idCours = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	// met à jour un cours
	public void update(Cours cours) throws SQLException {

		String query = "update cours set nomCours=?,  dateDebut= ?, dateFin=? where idCours = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, cours.getNomCours());
		ps.setDate(2, cours.getDateDebut());
		ps.setDate(3, cours.getDateFin());
		ps.setInt(4, cours.getIdCours());
		ps.executeUpdate();
	}

	public static List<Cours> getCoursFromClasse(Classe classe) {
		try {
			String query = "SELECT * FROM COURS INNER JOIN CLASSE ON COURS.idClasse = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, classe.getIdClasse());
			ResultSet rs = ps.executeQuery();

			List<Cours> cours = new ArrayList();
			while (rs.next()) {
				Cours c = new Cours();
				c.setIdCours(rs.getInt("idCours"));
				c.setNomCours(rs.getString("nomCours"));
				c.setDateDebut(rs.getDate("dateDebut"));
				c.setDateFin(rs.getDate("dateFin"));
				cours.add(c);
			}
			return cours;
		} catch (Exception e) {
			return new ArrayList<Cours>();
		}
	}

}
