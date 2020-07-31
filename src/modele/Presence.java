package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;

public class Presence {
	private int idUtilisateur;
	private int idCours;
	private boolean estAbsent;

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}

	public boolean isEstAbsent() {
		return estAbsent;
	}

	public void setEstAbsent(boolean estAbsent) {
		this.estAbsent = estAbsent;
	}

	public static List<Presence> all() {
		try {
			String query = "select * from Presence;";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<Presence> presences = new ArrayList<Presence>();

			while (rs.next()) {
				Presence p = new Presence();
				p.setIdCours(rs.getInt("idCours"));
				p.setIdUtilisateur(rs.getInt("idutilisateur"));
				p.setEstAbsent(rs.getBoolean("estAbsent"));
				presences.add(p);
			}
			
			return presences;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static int add(Presence presence) {
		try {
			String query = "insert into Presence(idutilisateur, idCours, estAbsent) VALUES (?, ?, ?)";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setInt(1, presence.idUtilisateur);
			ps.setInt(2, presence.idCours);
			ps.setBoolean(3, presence.estAbsent);
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
}
