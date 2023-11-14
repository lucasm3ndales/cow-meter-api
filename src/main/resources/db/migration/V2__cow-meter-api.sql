INSERT INTO usuario (id, nome, cpf, senha, role, active) VALUES
    (1, 'João Silva', '12345678901', '1234', 'ADMIN', true),
    (2, 'Maria de Oliveira', '98765432101', '1234', 'CRIADOR', true),
    (3, 'Carlos Henrique', '11223344556', '5678', 'CRIADOR', true),
    (4, 'Fernanda Costa', '98765432100', '5178', 'CRIADOR', true),
    (5, 'Eduardo Colombelli', '12345678902', '5378', 'CRIADOR', true);

INSERT INTO raca (id, nome, descricao, criado_em, atualizado_em) VALUES
    (1, 'holandesa', 'Raça leiteira originária dos Países Baixos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'nelore', 'Raça de gado zebu criada no Brasil para corte', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'angus', 'Raça de gado de corte originária da Escócia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'brangus', 'Híbrido de Angus e Brahman', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'girolando', 'Raça leiteira originária do cruzamento de Gir e Holandês', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO bovino (id, racaId, nome, dataNasc, sexo, observacoes, castrado, tipoBovino, criado_em, atualizado_em, usuarioId) VALUES
    (1, 1, 'Bela', '2020-01-15', 'FEMEA', 'Vaca leiteira', false, 'VACA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    (2, 2, 'Cabron', '2019-05-20', 'MACHO', 'Boi de Corte', true, 'BOI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    (3, 3, 'Vermelhinho', '2021-03-10', 'MACHO', 'Bezerro Angus', false, 'BEZERRO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
    (4, 4, 'Negão', '2022-02-28', 'MACHO', 'Bezerro brangus', false, 'BEZERRO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
    (5, 5, 'Mancinha', '2021-07-10', 'FEMEA', 'Vaca girolando', false, 'VACA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5);


INSERT INTO saude (id, bovinoId, peso, dataEntradaCio, tipoTratamento, dataTratamento, medicamentos, observacoes) VALUES
    (1, 1, 600, '2022-11-01', 'Preventivo', CURRENT_DATE, 'Vitamina A', 'Check-up anual'),
    (2, 2, 800, NULL, 'Curativo', '2022-10-15', 'Antibiótico', 'Tratamento após ferimento'),
    (3, 3, 420, '2022-09-15', 'Preventivo', '2022-09-20', 'Vitamina D', 'Acompanhamento mensal'),
    (4, 4, 280, NULL, 'Vacinação', '2022-11-10', 'Vacina contra febre aftosa', 'Imunização semestral');