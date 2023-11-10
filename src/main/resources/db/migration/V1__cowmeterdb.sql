CREATE TYPE Role AS ENUM ('ROLE_ADMIN', 'ROLE_USUARIO');
CREATE TYPE RoleSexo AS ENUM ('MACHO', 'FEMEA');
CREATE TYPE TipoBovino AS ENUM ('Vaca','Boi', 'Touro', 'Bezerro', 'Novilha');

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role Role NOT NULL,
    active BOOLEAN NOT NULL,
);

CREATE TABLE raca (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    criado_em DATE NOT NULL,
    atualizado_em DATE NOT NULL
);

CREATE TABLE bovino (
    id BIGINT PRIMARY KEY,
    racaId INT,
    nome VARCHAR(100) NOT NULL,
    dataNasc DATE NOT NULL,
    sexo RoleSexo NOT NULL,
    observacoes TEXT,
    castrado BOOLEAN NOT NULL,
    tipoBovino  TipoBovino,
    criado_em DATE NOT NULL,
    atualizado_em DATE NOT NULL,
    usuarioId BIGINT NOT NULL ,
    FOREIGN KEY (usuarioId) REFERENCES usuario(id)
    FOREIGN KEY (racaId) REFERENCES raca(id)
);

CREATE TABLE saude (
    id BIGINT PRIMARY KEY,
    bovinoId INT,
    peso DECIMAL(10, 2) NOT NULL,
    dataEntradaCio DATE,
    tipoTratamento VARCHAR(100) NOT NULL,
    dataTratamento DATE NOT NULL,
    medicamentos TEXT,
    observacoes TEXT,
    FOREIGN KEY (bovinoId) REFERENCES bovino(id)
);