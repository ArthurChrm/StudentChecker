package modele;

import java.util.Date;
import java.util.List;

public class Classe {

	private String nomClasse;
	private List<Cours> listeCours;
	private List<Eleves> listeEleves;
	
	/**
	 * @param idAdmin
	 * @param idAdmin
	 * @param prenomAdmin
	 * @param dateNaissanceAdmin
	 */
    public Classe(int idAdmin,String nomAdmin, String prenomAdmin, Date dateNaissanceAdmin) {
    	this.idAdmin = idAdmin;
        this.nomAdmin = nomAdmin;
        this.prenomAdmin = prenomAdmin;
        this.dateNaissanceAdmin = dateNaissanceAdmin;
    }

    public Classe() {
    	
    }
}
