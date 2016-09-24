CREATE TABLE "PROO".usuario
(
 "iduser" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
  "NOME" varchar (100) NOT NULL, 
  "USUARIO" varchar (100) NOT NULL,
  "SENHA" varchar (100) NOT NULL
);

CREATE TABLE "PROO".evento
(
  "idevento" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  "NOMEEVENTO" varchar (100) NOT NULL,
  "DATAINICIO" date NOT NULL,
  "HORAINICIO" time NOT NULL,   
  "DATAFIM" date NOT NULL,
  "HORAFIM" time NOT NULL,
  "DESCEVENTO" varchar (200) NOT NULL,
   "iduser" INT NOT NULL
);

  