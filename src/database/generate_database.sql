
CREATE TABLE Classe(
	idClasse     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomClasse    TEXT NOT NULL
);

CREATE TABLE Cours(
	idCours           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	dateDebutCours    NUMERIC NOT NULL ,
	dateFinCours      NUMERIC NOT NULL ,
	idClasse          INTEGER NOT NULL

	,CONSTRAINT Cours_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
);

CREATE TABLE Compte(
	idutilisateur     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomUtilisateur    TEXT NOT NULL ,
	mdpUtilisateur    TEXT NOT NULL
);

CREATE TABLE Eleve(
	idEleve          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomEleve         TEXT NOT NULL ,
	prenomEleve      TEXT NOT NULL ,
	dateNaissance    NUMERIC NOT NULL ,
	idClasse         INTEGER NOT NULL ,
	idutilisateur    INTEGER

	,CONSTRAINT Eleve_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
	,CONSTRAINT Eleve_Compte0_FK FOREIGN KEY (idutilisateur) REFERENCES Compte(idutilisateur)
	,CONSTRAINT Eleve_Compte_AK UNIQUE (idutilisateur)
);

CREATE TABLE Administrateur(
	idAdmin               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomAdmin              TEXT NOT NULL ,
	prenomAdmin           TEXT NOT NULL ,
	dateNaissanceAdmin    NUMERIC NOT NULL ,
	idutilisateur         INTEGER

	,CONSTRAINT Administrateur_Compte_FK FOREIGN KEY (idutilisateur) REFERENCES Compte(idutilisateur)
	,CONSTRAINT Administrateur_Compte_AK UNIQUE (idutilisateur)
);

CREATE TABLE Presence(
	idCours      INTEGER NOT NULL ,
	idEleve      INTEGER NOT NULL ,
	estAbsent    INTEGER NOT NULL,
	CONSTRAINT Presence_PK PRIMARY KEY (idCours,idEleve)

	,CONSTRAINT Presence_Cours_FK FOREIGN KEY (idCours) REFERENCES Cours(idCours)
	,CONSTRAINT Presence_Eleve0_FK FOREIGN KEY (idEleve) REFERENCES Eleve(idEleve)
);

INSERT INTO COMPTE(nomUtilisateur, mdpUtilisateur) VALUES("ACHERAMY", "azerty");
INSERT INTO Administrateur(nomAdmin, prenomAdmin, dateNaissanceAdmin, idutilisateur) VALUES("CHERAMY", "Arthur", "1999-04-07", 1);