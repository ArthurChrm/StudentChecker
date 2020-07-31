package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

	    
		//Get un eleve
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
	        }
	        else
	            return null;
	    }
	    
	    //Get la liste des eleves
	    public List<Eleves> getEleves()
	            throws SQLException
	    {
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

	    //ajoute un eleve
	    public int add(Eleves eleve)throws SQLException{
	        String query = "insert into eleve(nomEleve, prenomEleve, dateNaissance) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, eleve.getNomEleve());
	        ps.setString(2, eleve.getPrenomEleve());
	        ps.setDate(3, eleve.getDateNaissance());
	        int n = ps.executeUpdate();
	        return n;
	    }
	    
	    //supprime un eleve
	    public void delete(int id) throws SQLException {
	        String query = "delete from eleve where idEleve = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    }
	    
	    //met à jour un eleve
	    public void update(Eleves eleve) throws SQLException {

	        String query = "update eleve set nomEleve=?,  prenomEleve= ?, dateNaissance=?, idClasse=? where idEleve = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, eleve.getNomEleve());
	        ps.setString(2, eleve.getPrenomEleve());
	        ps.setDate(3, eleve.getDateNaissance());
	        ps.setInt(4, eleve.getIdEleve());
	        ps.executeUpdate();
	    }

}
