insert into ciudad(nombre) values ("Calarcá");
insert into tipo(nombre) values ("Baile");

insert into administrador(id,email,nickname,nombre,password)values("10","braian@yahoo.com","Ghostbit","Braian","123braian");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("16","melissa@hotmail.com","Lekoon","Melissa","meli123","10");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("25","sebas@gmail","Botsorio","Sebastian","sebas123",1);

insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile",true,"2021/01/04","2021/08/04",12,14,"Pepitos",1,"16",1,"25");


insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (10,"Muy buen lugar","2021/01/04","Gracias por el aporte",1,"25");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (5,"Excelente para bailar","2021/02/05","Gracias por el aporte",1,"25");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values (10,"Tiene buenas pistas","2021/06/09","Gracias por el aporte",1,"25");
