package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;






public class ElevesSQL {
	private Connection conn;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Statement st = null;

	/**
	 * @param conn
	 */
	public ElevesSQL(Connection conn) {
		this.conn = conn;
	}
	
	//Création de la personne
			public Eleves create(Eleves eleve) {
				Eleves eleveToReturn = eleve;
				try {
					String sql = "";
					if(eleve.getIdEleve() != null) {
						sql = "INSERT INTO person VALUES (?,?,?,?)";
						ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						ps.setObject(1,eleve.getIdEleve(),Types.INTEGER);
						ps.setObject(2,eleve.getNomEleve(), Types.VARCHAR); 
						ps.setObject(3,eleve.getPrenomEleve(),Types.VARCHAR); 
						ps.setObject(4,eleve.getDateNaissance(),Types.DATE);
						
					} 
					else {
						sql = "INSERT INTO person (first_name,last_name,dob) VALUES (?,?,?);";
						ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						ps.setObject(1,eleve.getNomEleve(), Types.VARCHAR); 
						ps.setObject(2,eleve.getPrenomEleve(),Types.VARCHAR); 
						ps.setObject(3,eleve.getDateNaissance(),Types.DATE);
					}
					
					ps.executeUpdate();
					
					ResultSet generatedKeys = ps.getGeneratedKeys();
		            if (generatedKeys.next()) {
		            //	eleveToReturn.setIdEleve(idEleve);(generatedKeys.getInt(1));
		            	return eleveToReturn;
		            }
		            else {
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
				}  catch(SQLException se) {
					
				} 
				return eleveToReturn;
			}
}
