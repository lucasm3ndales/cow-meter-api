-- Inserts for 'usuario' table
INSERT INTO usuario (nome, cpf, senha, roles, active)
VALUES ('Admin User', '123.456.789-00', 'adminpassword', 'ADMIN', true),
       ('Criador User', '987.654.321-00', 'criadorpassword', 'CRIADOR', true),
       ('John Doe', '111.222.333-44', 'userpassword', 'CRIADOR', true),
       ('Jane Doe', '555.666.777-88', 'userpassword', 'CRIADOR', true);

-- Inserts for 'raca' table
INSERT INTO raca (nome, descricao, criado_em, atualizado_em)
VALUES ('Holstein', 'Black and white dairy cattle', '2023-01-01', '2023-01-01'),
       ('Angus', 'Black beef cattle', '2023-01-02', '2023-01-02'),
       ('Hereford', 'Red and white beef cattle', '2023-01-03', '2023-01-03'),
       ('Jersey', 'Brown dairy cattle', '2023-01-04', '2023-01-04');

-- Inserts for 'bovino' table
INSERT INTO bovino (raca_id, nome, data_nasc, sexo, observacoes, castrado, tipo_bovino, criado_em, atualizado_em,
                    usuario_id)
VALUES (1, 'Bessie', '2020-05-15', 'FEMEA', 'Black and white spotted', false, 'VACA', '2023-01-05', '2023-01-05', 1),
       (2, 'Bob', '2021-02-20', 'MACHO', 'Solid black color', true, 'TOURO', '2023-01-06', '2023-01-06', 2),
       (3, 'Daisy', '2022-09-10', 'FEMEA', 'Red and white markings', false, 'NOVILHA', '2023-01-07', '2023-01-07', 3),
       (4, 'Charlie', '2023-04-01', 'MACHO', 'Brown color', true, 'BEZERRO', '2023-01-08', '2023-01-08', 4);

-- Inserts for 'saude' table
INSERT INTO saude (bovino_id, peso, data_entradaCio, tipo_tratamento, data_tratamento, medicamentos, observacoes)
VALUES (1, 500.0, '2023-01-09', 'Vaccination', '2023-01-10', 'Vaccine A, Vaccine B', 'Regular checkup'),
       (2, 700.0, '2023-01-11', 'Deworming', '2023-01-12', 'Dewormer X', 'Monitoring weight gain'),
       (3, 450.0, NULL, 'Antibiotic', '2023-01-13', 'Antibiotic Y', 'Recovering from illness'),
       (4, 300.0, NULL, 'Vitamin supplement', '2023-01-14', 'Vitamin C', 'Healthy and active');