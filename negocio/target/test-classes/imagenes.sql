insert into ciudad(nombre) values ("Calarcá");
insert into tipo(nombre) values ("Baile");
insert into administrador(id,email,nickname,nombre,password)values("08","@cm","Ghods","Braian","123braian");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("1656","@hotsf","Legf","Melissa","meli123","08");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("254543","@gdfs","Botssf","Sebastian","sebas123",1);

insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile",true,"2021/01/04","2021/08/04",12,14,"Pepitos",1,"1656",1,"254543");

insert into imagen(url,lugar_id) values ("abcdef",1);
insert into imagen(url,lugar_id)values ("ghijkl",1);
insert into imagen(url,lugar_id) values ("mnopqr",1);