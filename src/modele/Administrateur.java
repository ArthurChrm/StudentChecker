package modele;

import java.util.Date;

public class Administrateur {

	   	private int idAdmin;
	    private String nomAdmin;
	    private String prenomAdmin;
	    private Date dateNaissanceAdmin;
	    
	    /**
		 * @param idAdmin
		 * @param idAdmin
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
}
