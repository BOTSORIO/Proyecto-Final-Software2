insert into ciudad(nombre) values ("Calarcá");
insert into tipo(nombre) values ("Baile");
insert into administrador(id,email,nickname,nombre,password)values("875","@","G7","try","08rf");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("162",".com","sesz","bgf","yga","875");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("252","@ga","Bot","Sebastian","sebas123",1);

insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile",true,"2021/01/04","2021/08/04",12,14,"Pepitos",1,"162",1,"252");

insert into favorito(aporte,lugar_id,usuario_id) values ("Lugar favorito de baile",1,"252");
insert into favorito(aporte,lugar_id,usuario_id) values ("Nada",1,"252");
insert into favorito(aporte,lugar_id,usuario_id) values ("Cualquier cosa",1,"252");
