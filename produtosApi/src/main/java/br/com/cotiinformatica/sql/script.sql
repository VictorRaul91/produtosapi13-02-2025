CREATE TABLE CATEGORIA (
ID   UUID 			PRIMARY KEY,
NOME VARCHAR(50)	NOT NULL UNIQUE
);

CREATE TABLE PRODUTO (
ID  			UUID 			PRIMARY KEY,
NOME 			VARCHAR(100) 	NOT NULL,
PRECO  			DECIMAL(5,2)  	NOT NULL,
QUANTIDADE 		INT		 		NOT NULL,
QUANTIDADE_ID 	UUID	 		NOT NULL,
FOREIGN KEY(QUANTIDADE_ID) REFERENCES CATEGORIA(ID)
);

INSERT INTO CATEGORIA(ID, NOME) VALUES(GEN_RANDOM_UUID(),'INFORMÁTICA');
INSERT INTO CATEGORIA(ID, NOME) VALUES(GEN_RANDOM_UUID(),'ELETRÔNICOS');
INSERT INTO CATEGORIA(ID, NOME) VALUES(GEN_RANDOM_UUID(),'VASTUÁRIO');
INSERT INTO CATEGORIA(ID, NOME) VALUES(GEN_RANDOM_UUID(),'PAPELARIA');
INSERT INTO CATEGORIA(ID, NOME) VALUES(GEN_RANDOM_UUID(),'OUTROS');
