package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;

import java.sql.Date;

public class Administrateur {

	   	private int idAdmin;
	   	private String nomAdmin;
	    private String prenomAdmin;
	    private Date dateNaissanceAdmin;
	    
	    /**
		 * @param idAdmin
		 * @param nomAdmin
		 * @param prenomAdmin
		 * @param dateNaissanceAdmin
		 */
	    public Administrateur(int idAdmin,String nomAdmin, String prenomAdmin, Date dateNaissanceAdmin) {
	    	this.idAdmin = idAdmin;
	        this.nomAdmin = nomAdmin;
	        this.prenomAdmin = prenomAdmin;
	        this.dateNaissanceAdmin = dateNaissanceAdmin;
	    }

	    public Administrateur() {
	    	
	    }
	    
	    public int getIdAdmin() {
			return idAdmin;
		}

		public void setIdAdmin(int idAdmin) {
			this.idAdmin = idAdmin;
		}

		public String getNomAdmin() {
			return nomAdmin;
		}

		public void setNomAdmin(String nomAdmin) {
			this.nomAdmin = nomAdmin;
		}

		public String getPrenomAdmin() {
			return prenomAdmin;
		}

		public void setPrenomAdmin(String prenomAdmin) {
			this.prenomAdmin = prenomAdmin;
		}

		public Date getDateNaissanceAdmin() {
			return dateNaissanceAdmin;
		}

		public void setDateNaissanceAdmin(Date dateNaissanceAdmin) {
			this.dateNaissanceAdmin = dateNaissanceAdmin;
		}

		//Get un admin
	    public static Administrateur getAdmin(int id) {

	        try {
				String query = "select * from admin where idAdmin= ?";
				PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
				ps.setInt(1, id);
				Administrateur admin = new Administrateur();
				ResultSet rs = ps.executeQuery();
				boolean check = false;

				while (rs.next()) {
				    check = true;
				    admin.setIdAdmin(rs.getInt("idAdmin"));
				    admin.setNomAdmin(rs.getString("nomAdmin"));
				    admin.setPrenomAdmin(rs.getString("prenomAdmin"));
				    admin.setDateNaissanceAdmin(rs.getDate("dateNaissanceAdmin"));
				}

				if (check == true) {
				    return admin;
				}
				else
				    return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	    }
	    
	    //Get la liste des admins
	    public static List<Administrateur> getAdmin()
	    {
	        try {
				String query = "select * from admin";
				PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				List<Administrateur> ls = new ArrayList();

				while (rs.next()) {
				    Administrateur admin = new Administrateur();
				    admin.setIdAdmin(rs.getInt("idAdmin"));
				    admin.setNomAdmin(rs.getString("nomAdmin"));
				    admin.setPrenomAdmin(rs.getString("prenomAdmin"));
				    admin.setDateNaissanceAdmin(rs.getDate("dateNaissanceAdmin"));
				    ls.add(admin);
				}
				return ls;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ArrayList<Administrateur>();
			}
	    }

	    //ajoute un admin
	    public static int add(Administrateur admin) {
	        try {
				String query = "insert into admin(nomAdmin, prenomAdmin, dateNaissanceAdmin) VALUES (?, ?, ?)";
				PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
				ps.setString(1, admin.getNomAdmin());
				ps.setString(2, admin.getPrenomAdmin());
				ps.setDate(3, admin.getDateNaissanceAdmin());
				int n = ps.executeUpdate();
				return n;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
	    }
	    
	    //supprime un admin
	    public static void delete(int id) {
	        try {
				String query = "delete from admin where idAdmin = ?";
				PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    //met à jour un admin
	    public static void update(Administrateur admin) {
	        try {
				String query = "update admin set nomAdmin=?,  prenomAdmin= ?, dateNaissanceAdmin=? where idAdmin = ?";
				PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
				ps.setString(1, admin.getNomAdmin());
				ps.setString(2, admin.getPrenomAdmin());
				ps.setDate(3, admin.getDateNaissanceAdmin());
				ps.setInt(4, admin.getIdAdmin());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
}
