insert into ciudad(nombre) values ("Calarcá");
insert into tipo(nombre) values ("Baile");
insert into administrador(id,email,nickname,nombre,password)values("187","@hoo.com","yx","zt","12323ds");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("16","@.com","on","ssa","msd","187");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("25","@gmail","Botsorio","Sebastian","sebas123",1);

insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile",true,"2021/01/04","2021/08/04",12,14,"Pepitos",1,"16",1,"25");

insert into telefono(telefono_lugar,lugar_id) values ("3226346138",1);
insert into telefono(telefono_lugar,lugar_id) values ("3045994932",1);
insert into telefono(telefono_lugar) values ("3226347698");