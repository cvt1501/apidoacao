--Inserts
INSERT INTO tb_01_endereco(rua, bairro, cep, numero, cidade, estado) VALUES ('Alameda Quinze de dezembro', 'Jardim Recreio', 12908040, 630, 'Bragança Paulista', 'SP');

INSERT INTO tb_01_endereco(rua, bairro, cep, numero, cidade, estado) VALUES ('Rua Augusta', 'Augusta', 12908015, 1331, 'São Paulo', 'SP');

INSERT INTO tb_01_endereco(rua, bairro, cep, numero, cidade, estado) VALUES ('Rua Shitaki', 'Moinhos', 12908018, 6001, 'São Paulo', 'SP');

INSERT INTO tb_02_polo(nome_polo, codigo_polo, id_endereco) VALUES ('Posto Morro Grande', 1230, 3);

INSERT INTO tb_03_doador(nome_doador, cpf_doador, id_endereco, pontos_doador) VALUES ('Isabella Akemi Kimishima Cavenatti', 12345678910, 1, 100000);

INSERT INTO tb_04_transacao(pontos_transacao, status_transacao, id_polo, id_doador) VALUES (250, 'PENDENTE', 1, 1);

INSERT INTO tb_05_livro(nome_livro, autor_livro, editora_livro, id_transacao) VALUES ('O senhor dos aneis: A sociedade do anel', 'J.R.R TOLKIEN', 'Abril', 1);

INSERT INTO tb_06_user(email, senha, conta_ativa, id_doador) VALUES ('arq.kimishima@gmail.com', '$2a$10$q1NlkQ7Q2yIM4wTdfiyvNewTCxmGPdLJHwf6ub00l4eQOY.C7XqDG', false, 1)