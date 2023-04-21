CREATE TABLE if not exists livros (
    id BIGINT NOT NULL auto_increment,
    nome VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    tema VARCHAR(50) NOT NULL,
    paginas VARCHAR(6) NOT NULL,
    preco VARCHAR(6) NOT NULL,

    PRIMARY KEY(id)
);