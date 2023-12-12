-- Inserts for 'usuario' table
INSERT INTO usuario (id ,nome, cpf, senha, roles, active)
VALUES (1, 'Mark', '13688811062', '$2a$12$5EnUPOUtkOSrDiOvLM.GROe6xIpBrs9l5NuscONyWGcY36WS0421a', 'ADMIN', true),
       (2, 'Gordon', '34856757091', '$2a$12$5EnUPOUtkOSrDiOvLM.GROe6xIpBrs9l5NuscONyWGcY36WS0421a', 'CRIADOR', true),
       (3, 'Zac', '23512586066', '$2a$12$5EnUPOUtkOSrDiOvLM.GROe6xIpBrs9l5NuscONyWGcY36WS0421a', 'CRIADOR', true),
       (4, 'Jane', '07020974031', '$2a$12$5EnUPOUtkOSrDiOvLM.GROe6xIpBrs9l5NuscONyWGcY36WS0421a', 'CRIADOR', true);

-- Inserts for 'raca' table
INSERT INTO raca (id, nome, descricao, criado_em, atualizado_em)
VALUES (1, 'Holstein', 'Black and white dairy cattle', '2023-01-01', '2023-01-01'),
       (2, 'Angus', 'Black beef cattle', '2023-01-02', '2023-01-02'),
       (3, 'Hereford', 'Red and white beef cattle', '2023-01-03', '2023-01-03'),
       (4, 'Jersey', 'Brown dairy cattle', '2023-01-04', '2023-01-04');

-- Inserts for 'bovino' table
INSERT INTO bovino (id, raca_id, nome, data_nasc, sexo, observacoes, castrado, tipo_bovino, criado_em, atualizado_em,
                    usuario_id)
VALUES (1, 1, 'Bessie', '2020-05-15', 'FEMEA', 'Black and white spotted', false, 'VACA', '2023-01-05', '2023-01-05', 1),
       (2, 2, 'Bob', '2021-02-20', 'MACHO', 'Solid black color', true, 'TOURO', '2023-01-06', '2023-01-06', 2),
       (3, 3, 'Daisy', '2022-09-10', 'FEMEA', 'Red and white markings', false, 'NOVILHA', '2023-01-07', '2023-01-07', 3),
       (4, 4, 'Charlie', '2023-04-01', 'MACHO', 'Brown color', true, 'BEZERRO', '2023-01-08', '2023-01-08', 4);

-- Inserts for 'saude' table
INSERT INTO saude (id, bovino_id, peso, data_entradaCio, tipo_tratamento, data_tratamento, medicamentos, observacoes, criado_em, atualizado_em)
VALUES (1, 1, 500.0, '2023-01-09', 'Vaccination', '2023-01-10', 'Vaccine A, Vaccine B', 'Regular checkup', '2023-03-05', '2023-03-05'),
       (2, 2, 700.0, '2023-01-11', 'Deworming', '2023-01-12', 'Dewormer X', 'Monitoring weight gain','2023-01-05', '2023-01-05'),
       (3, 3, 450.0, NULL, 'Antibiotic', '2023-01-13', 'Antibiotic Y', 'Recovering from illness','2024-01-05','2024-01-05'),
       (4, 4, 300.0, NULL, 'Dipirona', '2023-03-14', 'Dipirona Xx', 'obses', '2023-01-28', '2023-05-23');