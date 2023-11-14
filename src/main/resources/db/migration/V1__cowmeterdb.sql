CREATE TYPE Roles AS ENUM ('ADMIN', 'CRIADOR');
CREATE TYPE RoleSexo AS ENUM ('MACHO', 'FEMEA');
CREATE TYPE TipoBovino AS ENUM ('VACA','BOI', 'TOURO', 'BEZERRO', 'NOVILHA');

CREATE TABLE usuario
(
    id     BIGSERIAL PRIMARY KEY,
    nome   VARCHAR(100) NOT NULL,
    cpf    VARCHAR(14)  NOT NULL UNIQUE,
    senha  VARCHAR(255) NOT NULL,
    roles   Roles        NOT NULL,
    active BOOLEAN      NOT NULL
);

CREATE TABLE raca
(
    id            BIGSERIAL PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    descricao     TEXT,
    criado_em     DATE         NOT NULL,
    atualizado_em DATE         NOT NULL
);

CREATE TABLE bovino
(
    id            BIGSERIAL PRIMARY KEY,
    raca_id       INT,
    nome          VARCHAR(100) NOT NULL,
    data_nasc     DATE         NOT NULL,
    sexo          RoleSexo     NOT NULL,
    observacoes   TEXT,
    castrado      BOOLEAN      NOT NULL,
    tipo_bovino   TipoBovino,
    criado_em     DATE         NOT NULL,
    atualizado_em DATE         NOT NULL,
    usuario_id    BIGINT       NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    FOREIGN KEY (raca_id) REFERENCES raca (id)
);

CREATE TABLE saude
(
    id              BIGSERIAL PRIMARY KEY,
    bovino_id       INT,
    peso            DECIMAL(10, 2) NOT NULL,
    data_entradacio DATE,
    tipo_tratamento VARCHAR(100)   NOT NULL,
    data_tratamento DATE           NOT NULL,
    medicamentos    TEXT,
    observacoes     TEXT,
    FOREIGN KEY (bovino_id) REFERENCES bovino (id)
);


