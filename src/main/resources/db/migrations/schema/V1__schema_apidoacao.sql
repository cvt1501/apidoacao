--Creates
CREATE TABLE tb_01_endereco(id_endereco INT AUTO_INCREMENT UNIQUE, rua VARCHAR(500) NOT NULL, bairro VARCHAR(500) NOT NULL, cep VARCHAR(9) NOT NULL, numero INT NOT NULL, cidade CHAR(50) NOT NULL, estado CHAR(50) NOT NULL, PRIMARY KEY (id_endereco));

CREATE TABLE tb_02_polo(id_polo INT AUTO_INCREMENT UNIQUE, nome_polo CHAR(255) NOT NULL, codigo_polo LONG NOT NULL, id_endereco INT, PRIMARY KEY(id_polo), CONSTRAINT fk_polo_1 FOREIGN KEY(id_endereco) REFERENCES tb_01_endereco(id_endereco));

CREATE TABLE tb_03_doador(id_doador INT AUTO_INCREMENT UNIQUE, nome_doador CHAR(255) NOT NULL, cpf_doador VARCHAR(11) NOT NULL UNIQUE, pontos_doador INT NOT NULL, id_endereco INT, PRIMARY KEY(id_doador), CONSTRAINT fk_doador_1 FOREIGN KEY(id_endereco) REFERENCES tb_01_endereco(id_endereco));

CREATE TABLE tb_04_transacao(id_transacao INT AUTO_INCREMENT UNIQUE, pontos_transacao LONG NOT NULL, status_transacao VARCHAR(100) NOT NULL, data_transacao TIMESTAMP, id_polo INT, id_doador INT, PRIMARY KEY(id_transacao), CONSTRAINT fk_transacao_1 FOREIGN KEY(id_polo) REFERENCES tb_02_polo(id_polo), CONSTRAINT fk_transacao_2 FOREIGN KEY(id_doador) REFERENCES tb_03_doador(id_doador));

CREATE TABLE tb_05_livro(id_livro INT AUTO_INCREMENT UNIQUE, nome_livro VARCHAR(255) NOT NULL, autor_livro VARCHAR(255) NOT NULL, editora_livro VARCHAR(255) NOT NULL, id_transacao INT, PRIMARY KEY(id_livro), CONSTRAINT fk_livro_1 FOREIGN KEY(id_transacao) REFERENCES tb_04_transacao(id_transacao));

CREATE TABLE tb_06_user(id_user INT AUTO_INCREMENT UNIQUE, email VARCHAR(255) NOT NULL UNIQUE, senha VARCHAR(255) NOT NULL, conta_ativa BOOLEAN, id_doador INT, PRIMARY KEY(id_user), CONSTRAINT fk_user_1 FOREIGN KEY(id_doador) REFERENCES tb_03_doador(id_doador));