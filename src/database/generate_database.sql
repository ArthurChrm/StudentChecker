------------------------------------------------------------
--        Script SQLite  
------------------------------------------------------------


------------------------------------------------------------
-- Table: Classe
------------------------------------------------------------
CREATE TABLE Classe(
	idClasse     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomClasse    TEXT NOT NULL
);


------------------------------------------------------------
-- Table: Cours
------------------------------------------------------------
CREATE TABLE Cours(
	idCours           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	dateDebutCours    NUMERIC NOT NULL ,
	dateFinCours      NUMERIC NOT NULL ,
	nomCours          TEXT NOT NULL ,
	idClasse          INTEGER NOT NULL

	,CONSTRAINT Cours_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
);


------------------------------------------------------------
-- Table: Compte
------------------------------------------------------------
CREATE TABLE Compte(
	idutilisateur     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
	nomUtilisateur    TEXT NOT NULL ,
	mdpUtilisateur    TEXT NOT NULL ,
	prenom            TEXT NOT NULL ,
	nom               TEXT NOT NULL ,
	isAdmin           INTEGER NOT NULL ,
	idClasse          INTEGER

	,CONSTRAINT Compte_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
);


------------------------------------------------------------
-- Table: Presence
------------------------------------------------------------
CREATE TABLE Presence(
	idutilisateur    INTEGER NOT NULL ,
	idCours          INTEGER NOT NULL ,
	estAbsent        INTEGER NOT NULL,
	CONSTRAINT Presence_PK PRIMARY KEY (idutilisateur,idCours)

	,CONSTRAINT Presence_Compte_FK FOREIGN KEY (idutilisateur) REFERENCES Compte(idutilisateur)
	,CONSTRAINT Presence_Cours0_FK FOREIGN KEY (idCours) REFERENCES Cours(idCours)
);

INSERT INTO CLASSE(nomClasse) VALUES("RIL 2019-2020");

INSERT INTO COURS(dateDebutCours, dateFinCours, nomCours, idClasse) VALUES(date('2007-01-01 10:00:00'),date('2007-01-01 10:00:00'), "Java", 1);
INSERT INTO COURS(dateDebutCours, dateFinCours, nomCours, idClasse) VALUES(date('2007-01-01 10:00:00'),date('2007-01-01 10:00:00'), "Java", 1);
INSERT INTO COURS(dateDebutCours, dateFinCours, nomCours, idClasse) VALUES(date('2007-01-01 10:00:00'),date('2007-01-01 10:00:00'), "Java JEE", 1);

INSERT INTO COMPTE(nomUtilisateur, mdpUtilisateur, prenom, nom, isAdmin, idClasse) VALUES("ACHERAMY", "azerty", "Arthur", "Cheramy", true, 1);
INSERT INTO COMPTE(nomUtilisateur, mdpUtilisateur, prenom, nom, isAdmin, idClasse) VALUES("MVILLERMIN", "azerty", "Maxime", "Villermin", false, 1);