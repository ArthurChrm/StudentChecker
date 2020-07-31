package managers;

public class UserManager {
	
	private static String nom;
	private static String prenom;
	private static boolean estAdministrateur;
	
	public static String getNom() {
		return nom;
	}
	public static void setNom(String nom) {
		UserManager.nom = nom;
	}
	public static String getPrenom() {
		return prenom;
	}
	public static void setPrenom(String prenom) {
		UserManager.prenom = prenom;
	}
	public static boolean isEstAdministrateur() {
		return estAdministrateur;
	}
	public static void setEstAdministrateur(boolean estAdministrateur) {
		UserManager.estAdministrateur = estAdministrateur;
	}
}
