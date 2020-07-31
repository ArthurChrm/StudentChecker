package managers;

public class UserManager {
	
	private static String nom;
	private static boolean estAdministrateur;
	private static int id;
	
	public static String getNom() {
		return nom;
	}
	public static void setNom(String nom) {
		UserManager.nom = nom;
	}
	public static boolean isEstAdministrateur() {
		return estAdministrateur;
	}
	public static void setEstAdministrateur(boolean estAdministrateur) {
		System.out.println("Administrateur : "+estAdministrateur);
		UserManager.estAdministrateur = estAdministrateur;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		UserManager.id = id;
	}
	
}
