package modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;

public class Absence {

	private Integer idAbscence;
	private Cours cours;
	private Eleves eleve;
	
	public Absence(Integer idAbscence, Cours cours, Eleves eleve) {
		this.idAbscence = idAbscence;
		this.cours = cours;
		this.eleve = eleve;
	}
	
	public Absence() {
		
	}
	
	public Integer getIdAbscence() {
		return idAbscence;
	}
	public void setIdAbscence(Integer idAbscence) {
		this.idAbscence = idAbscence;
	}
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	public Eleves getEleve() {
		return eleve;
	}
	public void setEleve(Eleves eleve) {
		this.eleve = eleve;
	}
	
	//Get la liste des absence
    public static List<Absence> getAbsence()
    {
        try {
			String query = "select * from absence";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<Absence> ls = new ArrayList();

			while (rs.next()) {
				Absence absence = new Absence();
				absence.setIdAbscence(rs.getInt("idAbsence"));
			//	absence.setEleve(rs.getString("eleve"));
			//	absence.setCours(rs.getDate("cours"));
			    ls.add(absence);
			}
			return ls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Absence>();
		}
    }

    //ajoute une absence
    public static int add(Absence absence) {
        try {
			String query = "insert into absence(eleve, cours ) VALUES (?, ?)";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
      // ps.setString(1, absence.getEleve());
      // ps.setString(2, absence.getCours());
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
    }
    
    //supprime une absence
    public static void delete(int id) {
        try {
			String query = "delete from absence where idAbsence = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //met à jour une absence
    public static void update(Absence absence) {

        try {
			String query = "update absence set eleve=?,  cours= ? where idAbsence = ?";
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
    //   ps.setString(1, absence.getEleve());
    //   ps.setDate(2, absence.getCours());
			ps.setInt(3, absence.getIdAbscence());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
