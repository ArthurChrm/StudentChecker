CREATE TABLE Administrateur(
	idAdmin               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomAdmin              TEXT NOT NULL ,
	prenomAdmin           TEXT NOT NULL ,
	dateNaissanceAdmin    NUMERIC NOT NULL
);

CREATE TABLE Classe(
	idClasse     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomClasse    TEXT NOT NULL
);

CREATE TABLE Eleve(
	idEleve          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomEleve         TEXT NOT NULL ,
	prenomEleve      TEXT NOT NULL ,
	dateNaissance    NUMERIC NOT NULL ,
	idClasse         INTEGER NOT NULL

	,CONSTRAINT Eleve_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
);

CREATE TABLE Cours(
	idCours           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	dateDebutCours    NUMERIC NOT NULL ,
	dateFinCours      NUMERIC NOT NULL ,
	idClasse          INTEGER NOT NULL

	,CONSTRAINT Cours_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
);

CREATE TABLE Presence(
	idCours      INTEGER NOT NULL ,
	idEleve      INTEGER NOT NULL ,
	estAbsent    INTEGER NOT NULL,
	CONSTRAINT Presence_PK PRIMARY KEY (idCours,idEleve)

	,CONSTRAINT Presence_Cours_FK FOREIGN KEY (idCours) REFERENCES Cours(idCours)
	,CONSTRAINT Presence_Eleve0_FK FOREIGN KEY (idEleve) REFERENCES Eleve(idEleve)
);


