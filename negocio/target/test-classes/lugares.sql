insert into ciudad(nombre) values ("Calarca");


insert into tipo(nombre) values ("Baile");
insert into tipo(nombre) values ("Comida");
insert into tipo(nombre) values ("Hotel");

insert into administrador(id,email,nickname,nombre,password)values("3","@yahoo.com","Ghostbit","Braian","123braian");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("1","@hotmail.com","Lekoon","Melissa","meli123","3");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("2","@yopmail.com","Cemar","Cesar","cemar123","3");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("2","@gmail","Botsorio","Sebastian","sebas123",1);
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("5","sert@gmail","Kamado","Tanjiro","123345",1);


insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile",true,"2021/01/04","2021/08/04",12,14,"Pepitos",1,"1",1,"2");
insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("venta de computadores",true,"2021/02/04","2021/07/04",10,16,"Taquitos",1,"2",2,"2");
insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de comida",true,"2021/04/04","2021/06/04",8,2,"Ya no se que poner",1,"2",3,"5");

insert into telefono(telefono_lugar,lugar_id)values("3045994932",1);
insert into telefono(telefono_lugar,lugar_id)values("3045994933",2);
insert into telefono(telefono_lugar,lugar_id)values("3045994934",3);


insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (3,"Muy buen lugar","2021/01/04","Gracias por el aporte",1,"2");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (5,"Muy buen lugar","2021/01/04","Gracias por el aporte",3,"2");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (4,"Muy buen lugar","2021/01/04","Gracias por el aporte",2,"2");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (2,"Muy buen lugar","2021/01/04","Gracias por el aporte",1,"2");


insert into horario(dia_semana,hora_fin,hora_inicio) values ("Lunes","22:00","10:00");
insert into horario(dia_semana,hora_fin,hora_inicio) values ("Lunes","22:00","10:03");
insert into horario(dia_semana,hora_fin,hora_inicio) values ("Martes","20:00","09:00");
insert into horario(dia_semana,hora_fin,hora_inicio) values ("Miercoles","02:00","08:00");

insert into lugar_horarios(lugares_id,horarios_id) values (1,1);
insert into lugar_horarios(lugares_id,horarios_id) values (2,2);
