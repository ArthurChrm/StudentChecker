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

	private Connection conn;
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
    public List<Absence> getAbsence()
            throws SQLException
    {
        String query = "select * from absence";
        PreparedStatement ps = conn.prepareStatement(query);
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
    }

    //ajoute une absence
    public int add(Absence absence)throws SQLException{
        String query = "insert into absence(eleve, cours ) VALUES (?, ?)";
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
       // ps.setString(1, absence.getEleve());
       // ps.setString(2, absence.getCours());
        int n = ps.executeUpdate();
        return n;
    }
    
    //supprime une absence
    public void delete(int id) throws SQLException {
        String query = "delete from absence where idAbsence = ?";
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    //met à jour une absence
    public void update(Absence absence) throws SQLException {

        String query = "update absence set eleve=?,  cours= ? where idAbsence = ?";
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
     //   ps.setString(1, absence.getEleve());
     //   ps.setDate(2, absence.getCours());
        ps.setInt(3, absence.getIdAbscence());
        ps.executeUpdate();
    }
	
}
