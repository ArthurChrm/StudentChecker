package modele;

import java.util.Date;

public class Eleves {

    private int idEleve;
    private String nomEleve;
    private String prenomEleve;
    private Date dateNaissance;

    /**
	 * @param idEleve
	 * @param nomEleve
	 * @param prenomEleve
	 * @param dateNaissance
	 */
    public Eleves(int idEleve,String nomEleve, String prenomEleve, Date dateNaissance) {
    	this.idEleve = idEleve;
        this.nomEleve = nomEleve;
        this.prenomEleve = prenomEleve;
        this.dateNaissance = dateNaissance;
    }

    public Eleves() {
    	
    }
    
    @Override
    public String toString() {
        return "Eleves{" +
                "idEleve=" + idEleve +
                ", nomEleve='" + nomEleve + '\'' +
                ", prenomEleve='" + prenomEleve + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }

    public Integer getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Integer idEleve) {
        this.idEleve = idEleve;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public String getPrenomEleve() {
        return prenomEleve;
    }

    public void setPrenomEleve(String prenomEleve) {
        this.prenomEleve = prenomEleve;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
