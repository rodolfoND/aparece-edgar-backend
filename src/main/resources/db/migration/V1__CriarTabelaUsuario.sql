CREATE TABLE public.usuario (
                                id int8 NOT NULL,
                                nome varchar(255) NOT NULL,
                                CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

insert into public.usuario(id, nome) values (1,  'Rodolfo');
insert into public.usuario(id, nome) values (2,  'Breno');
insert into public.usuario(id, nome) values (3,  'Bruno');
insert into public.usuario(id, nome) values (4,  'Juliana');
insert into public.usuario(id, nome) values (5,  'Vinícius');