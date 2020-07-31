package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import managers.UserManager;

public class Compte {

	// Get la liste des cours
	public static Boolean getUtilisateur(String nomUtilisateur, String mdpUtilisateur) {
		try {
			String query = "SELECT * FROM Compte WHERE nomUtilisateur = ? AND mdpUtilisateur = ?;";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setString(1, nomUtilisateur);
			ps.setString(2, mdpUtilisateur);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UserManager.setNom(rs.getString("nomUtilisateur"));

				String query2 = "SELECT * FROM Administrateur WHERE Administrateur.idutilisateur = ?;";
				PreparedStatement ps2 = DatabaseManager.getConnection().prepareStatement(query2);
				ps2.setInt(1, rs.getInt("idutilisateur"));
				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					UserManager.setEstAdministrateur(true);
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
