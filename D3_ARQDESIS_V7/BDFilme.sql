drop database if exists FilmesDB;
create database FilmesDB;

use FilmesDB;

create table Filme(
	id int,
	nome varchar(255),
	genero varchar(255),
PRIMARY KEY (id)
);

create table Idioma(
	id int,
	nome varchar(255),
PRIMARY KEY (id)
);

create table Filme_Idioma(
	idFilme int,
	idIdioma int,
    FOREIGN KEY (idFilme) REFERENCES Filme(id),
	FOREIGN KEY (idIdioma) REFERENCES Idioma(id)
);

-- select * from Idioma;
-- select * from Filme;

insert into Idioma values (1, 'Português');
insert into Idioma values (2, 'Inglês');
insert into Idioma values (3, 'Russo');

