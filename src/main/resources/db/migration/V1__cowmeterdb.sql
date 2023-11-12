CREATE TYPE Role AS ENUM ('ADMIN', 'CRIADOR');
CREATE TYPE RoleSexo AS ENUM ('MACHO', 'FEMEA');
CREATE TYPE TipoBovino AS ENUM ('VACA','BOI', 'TOURO', 'BEZERRO', 'NOVILHA');

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role Role NOT NULL,
    active BOOLEAN NOT NULL
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
    usuarioId BIGINT NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuario(id),
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

-- Inserir Usuários
INSERT INTO usuario (id, nome, cpf, senha, role, active) VALUES
     (1, 'João Silva', '12345678901', '1234', 'ADMIN', true),
     (2, 'Maria Oliveira', '98765432101', '1234', 'CRIADOR', true);

-- Inserir Raças
INSERT INTO raca (id, nome, descricao, criado_em, atualizado_em) VALUES
     (1, 'holandesa', 'Raça leiteira originária dos Países Baixos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (2, 'nelore', 'Raça de gado zebu criada no Brasil para corte', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserir Bovinos
INSERT INTO bovino (id, racaId, nome, dataNasc, sexo, observacoes, castrado, tipoBovino, criado_em, atualizado_em, usuarioId) VALUES
      (1, 1, 'Bela', '2020-01-15', 'FEMEA', 'Vaca leiteira', false, 'VACA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
      (2, 2, 'Cabron', '2019-05-20', 'MACHO', 'Boi de Corte', true, 'BOI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

-- Inserir Dados de Saúde
INSERT INTO saude (id, bovinoId, peso, dataEntradaCio, tipoTratamento, dataTratamento, medicamentos, observacoes) VALUES
      (1, 1, 600, '2022-11-01', 'Preventivo', CURRENT_DATE, 'Vitamina A', 'Check-up anual'),
      (2, 2, 800, NULL, 'Curativo', '2022-10-15', 'Antibiótico', 'Tratamento após ferimento');
