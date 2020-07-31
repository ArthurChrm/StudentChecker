package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import managers.UserManager;

public class Compte {
	
	private int idUtilisateur;
	private String nomUtilisateur;
	private String mdpUtilisateur;
	private String prenom;
	private String nom;
	private boolean isAdmin;
	public boolean present;

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getMdpUtilisateur() {
		return mdpUtilisateur;
	}

	public void setMdpUtilisateur(String mdpUtilisateur) {
		this.mdpUtilisateur = mdpUtilisateur;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	// Get la liste des cours
	public static Boolean getUtilisateur(String nomUtilisateur, String mdpUtilisateur) {
		try {
			String query = "SELECT * FROM Compte WHERE nomUtilisateur = ? AND mdpUtilisateur = ?;";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setString(1, nomUtilisateur);
			ps.setString(2, mdpUtilisateur);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getBoolean("isAdmin")) {
					UserManager.setEstAdministrateur(true);
					UserManager.setId(rs.getInt("idutilisateur"));
				} else {
					UserManager.setEstAdministrateur(false);
					UserManager.setId(rs.getInt("idutilisateur"));

				}
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.print(e.getMessage());
			return false;
		}
	}
}
