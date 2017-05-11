CREATE TABLE usuario(
	nome VARCHAR(100),
	dataNascimento DATE,
	sexo VARCHAR(100),
	email VARCHAR(100),
	senha VARCHAR(100),
	CONSTRAINT usuarioCP PRIMARY KEY(email)
);

CREATE TABLE agenda(
	nome VARCHAR(100),
	emailUsuario VARCHAR(100) NOT NULL,
	CONSTRAINT agendaCP PRIMARY KEY(nome, emailUsuario),
	CONSTRAINT emailUsuairoAgendaCE FOREIGN KEY(emailUsuario) REFERENCES usuario(email) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE compromisso(
	emailUsuario VARCHAR(100),
	nomeAgenda VARCHAR(100),
	dataHora TIMESTAMP,
	descricao VARCHAR(100),
	localComp VARCHAR(100),
	CONSTRAINT compromissoCP PRIMARY KEY(emailUsuario, nomeAgenda, dataHora),
	CONSTRAINT emailUsuairoCompromissoCE FOREIGN KEY(emailUsuario) REFERENCES usuario(email) ON UPDATE CASCADE ON DELETE CASCADE
)