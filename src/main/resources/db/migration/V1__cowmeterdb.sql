CREATE TYPE Role AS ENUM ('ROLE_ADMIN', 'ROLE_USUARIO');
CREATE TYPE RoleSexo AS ENUM ('MACHO', 'FEMEA');


CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role Role NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE Raca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TYPE TipoBovino AS ENUM ('Vaca','Boi', 'Touro', 'Bezerro', 'Novilha');

CREATE TABLE bovino (
    id SERIAL PRIMARY KEY,
    idRaca INT,
    nome VARCHAR(100) NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    dataNasc DATE NOT NULL,
    sexo RoleSexo NOT NULL,
    dataEntradaCio DATE,
    observacoes TEXT,
    castrado BOOLEAN NOT NULL,
    tipoBovino  TipoBovino,
    FOREIGN KEY (idRaca) REFERENCES Raca(id)
);

CREATE TABLE saude (
    IdRegistro SERIAL PRIMARY KEY,
    idBovino INT,
    tipoTratamento VARCHAR(100) NOT NULL,
    dataTratamento DATE NOT NULL,
    medicamentos TEXT,
    observacoes TEXT,
    FOREIGN KEY (idBovino) REFERENCES Bovino(id)
);