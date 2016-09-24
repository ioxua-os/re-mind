CREATE TABLE "bdAgenda".tbUsuario
(
 "codUsuario" INT NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
  "nomeUsuario" VARCHAR (100) NOT NULL, 
  "loginUsuario" VARCHAR (100) NOT NULL,
  "senhaUsuario" VARCHAR (100) NOT NULL
);

CREATE TABLE "bdAgenda".tbEvento
(
  "codEvento" INT NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  "nomeEvento" VARCHAR (100) NOT NULL,
  "dtInicioEvento" DATE NOT NULL,
  "hrInicioEvento" TIME NOT NULL,   
  "dtFimEvento" DATE NOT NULL,
  "hrFimEvento" TIME NOT NULL,
  "descEvento" VARCHAR (200) NOT NULL,
  "codUsuario" INT NOT NULL
);

  