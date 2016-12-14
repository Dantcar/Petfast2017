-- select * from DAC.AERONAVE;
--DROP TABLE CLIENTE
--dia 15-09-2016
--USE Petfast.DAC
--GO
CREATE TABLE CLIENTE (
idCliente int not null,
nome VARCHAR(60)not null,
nascimento VARCHAR(10)not null,
endereco VARCHAR(60)not null,
numero VARCHAR(30)not null,
bairro VARCHAR(60)not null,
cidade VARCHAR(60)not null,
uf VARCHAR(2)not null,
cep VARCHAR(10)not null,
email VARCHAR(60)not null,
telefone VARCHAR(20)not null,
RG VARCHAR(16)not null,
CPF VARCHAR(18)not null primary key
)
--go

-- DROP TABLE ENDCEP
--dia 15-09-2016
CREATE TABLE endcep(
idCep int not null,
endereco VARCHAR(60)not null,
complemento VARCHAR(60)not null,
bairro VARCHAR(60)not null,
cidade VARCHAR(60)not null,
uf VARCHAR(2)not null,
cep VARCHAR(8)not null primary key
)

-- DROP TABLE PAGAMENTO
--dia 15-09-2016
CREATE TABLE PAGAMENTO(
idPagamento int not null,
dataPagamento VARCHAR(10) not null,
nomePagador VARCHAR(60) not null,
rgPagador VARCHAR(18) not null,
cpfPagador VARCHAR(14)not null primary key,
vlTotal float,
formaPagamento VARCHAR(20)not null,
referenciaPagamento VARCHAR(20) not null
)

-- DROP TABLE USUARIO
--dia 15-09-2016
CREATE TABLE USUARIO (
LOGIN VARCHAR(30) NOT NULL, 
SENHA VARCHAR(12) NOT NULL, 
PODER DECIMAL(1) NOT NULL, 
PRIMARY KEY (LOGIN));



--tabela DAC.CLIENTE
--dia 15-09-2016
INSERT INTO DAC.CLIENTE (IDCLIENTE, NOME, NASCIMENTO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, EMAIL, TELEFONE, RG, CPF) 
	VALUES (1, 'Mario Almeida', '15/11/1954', 'Largo do Pelourinho', '70', 'Pelourinho', 'Salvador', 'BA', '40026-280', 'baiano@uou.com', '(74)98523-6644', '55.555.555-5', '555.555.555-55');
INSERT INTO DAC.CLIENTE (IDCLIENTE, NOME, NASCIMENTO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, EMAIL, TELEFONE, RG, CPF) 
	VALUES (2, 'dfsdf', '18/05/2016', 'Rua Maviael Prudente de Souza', '343', 'Vila Mariana', 'São Paulo', 'SP', '04020-010', 'dfafads@gm.com', '(11)11111-1111', '11.111.111-1', '232.323.232-33');
INSERT INTO DAC.CLIENTE (IDCLIENTE, NOME, NASCIMENTO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, EMAIL, TELEFONE, RG, CPF) 
	VALUES (3, 'dfadsf', '17/05/2016', 'Rua Maviael Prudente de Souza', '', 'Vila Mariana', 'São Paulo', 'SP', '04020-010', 'dkfd@gmail.com', '(11)11111-1111', '11.111.111-1', '082.407.688-51');
INSERT INTO DAC.CLIENTE (IDCLIENTE, NOME, NASCIMENTO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, EMAIL, TELEFONE, RG, CPF) 
	VALUES (4, 'Daniella Maratz', '27/11/1995', 'Rua Senador Jaguaribe', '29', 'Moura Brasil', 'Fortaleza', 'CE', '60010-010', 'DaniCeara@uol.com', '(50)98989-8989', '22.222.222-2', '222.222.222-22');
INSERT INTO DAC.CLIENTE (IDCLIENTE, NOME, NASCIMENTO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, EMAIL, TELEFONE, RG, CPF) 
	VALUES (5, 'Mario Sergio', '06/08/1990', 'Rua Engenheiro Portela', '1000', 'Setor Central', 'Anápolis', 'GO', '75024-970', 'MarioSergio@gmail.com', '(11)03030-4050', '99.999.999-9', '999.999.999-90');
INSERT INTO DAC.CLIENTE (IDCLIENTE, NOME, NASCIMENTO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, EMAIL, TELEFONE, RG, CPF) 
	VALUES (6, 'Custódio Mesquita', '10/10/1960', 'Rua Leandro Dupré', '690', 'Vila Clementino', 'São Paulo', 'SP', '04025-010', 'Custodio@Globo.com', '(11)98585-6321', '12.345.678-9', '899.999.999-99');

--tabela DAC.USUARIO
--dia 16-09-2016
INSERT INTO DAC.USUARIO (LOGIN, SENHA, PODER) 
	VALUES ('Teste', '12345abc', 1);

--tabela DAC.USUARIO
-- dia 18-09-2016

UPDATE
DAC.USUARIO 
SET LOGIN = 'Teste',
    SENHA = '@12345abcDEF', 
    PODER = 1
WHERE LOGIN = 'Teste'

--tabela DAC.ANIMAL
--dia 29 Setembro 2016
--DROP TABLE ANIMAL
CREATE TABLE ANIMAL (
IDANIMAL int not null,
IDCLIENTE int not null,
NOME VARCHAR(60) NOT NULL, 
ESPECIE VARCHAR(60) NOT NULL, 
NASCIMENTO VARCHAR(10) NOT NULL,
RACA VARCHAR(60),
PESO varchar(12),
ALTURA varchar(12),
COR VARCHAR(60),
CARACTERISTICA VARCHAR(120),
SEXO VARCHAR(1),
FOTO VARCHAR(250),
PRIMARY KEY (IDANIMAL)
);

//http://www.inf.ufsc.br/~frank.siqueira/INE5404/

INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (1, 1, 'Caçarola', 'Canina', '11/09/2013', 'Viralatas', '12,00', '12,00', 'Marrom', 'Teste', 'F', 'ok');
INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (2, 3, 'Castanhola', 'Canina', '05/10/2014', 'Podlle', '5,30', '17,20', 'Branca', 'Carinhosa e curiosa', 'F', '');


--Base pcSamsung
INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (1, 1, 'Caçarola baby', 'Canina', '09/02/2010', 'Viralata', '23,70', '30,80', 'Marrom', 'Dengoza', 'F', '');
INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (2, 4, 'Bola Preta', 'Canina', '12/01/2014', 'Podlle', '12,00', '23,00', 'Preta', 'Feroz e arrogante', 'F', 'c:\tewst');
INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (3, 6, 'Criolla', 'Felina', '04/10/2016', 'Gata', '8,00', '12,00', 'Preta', 'Peluda', 'F', '');
INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (4, 4, 'Maria Mole', 'Passaro', '04/10/2016', 'Canário', '0,20', '8,00', 'Amarelo ouro', 'Canta muito!', 'F', '');
INSERT INTO DAC.ANIMAL (IDANIMAL, IDCLIENTE, NOME, ESPECIE, NASCIMENTO, RACA, PESO, ALTURA, COR, CARACTERISTICA, SEXO, FOTO) 
	VALUES (5, 3, 'Porco Pig', 'Suino', '05/10/2016', 'Porco', '120,00', '50,00', 'Rosa', 'Porco gordo', 'M', 'c:/porco.png');


/*
 private String idProfissional; 
   private String nome;
   private String FotoProfissional;
   private String celular;
   private String email;
   private String cpf;
   private String rg;
   private String nascimento;
   private String contato;
   private String telefoneContato;
*/
-- DROP TABLE PROFISSIONAL
Create table PROFISSIONAL(
    IDPROFISSIONAL int not null,
    nome varchar (60) not null,
    FotoProfissional varchar (250),
    celular varchar (20) not null,
    email varchar (60) not null,
    cpf varchar (18) not null,
    rg varchar (16) not null,
    nascimento varchar (10) not null,
    contato varchar (60),
    telefoneContato varchar (20),
    PRIMARY KEY (IDPROFISSIONAL)
);

-- DROP TABLE AGENDAMENTO
Create table Agendamento(
    IDAgendamento int not null,
    DataAgendamento varchar(20),
    HoraAgendamento varchar(20),
    AnimalId int not null,
    ClienteId int not null,
    Serviço varchar(60),
    IDServico int not null,
    IDProfissional int,
    PRIMARY KEY (IDAgendamento)
);
