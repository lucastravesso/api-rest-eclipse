INSERT INTO CURSO (nome_curso, tipo_curso) values ('Engenharia', 'exatas'),('ADS','exatas'),('Linguas','humanas');
INSERT INTO ALUNO (nome, email, senha, id_curso) values ('Lucas', 'lucas@awd', '123', 2),('Leonardo', 'leo@awd', '123', 2),('Pedro', 'pedro@awd', '123', 1),('Thiago', 'thiago@awd', '123', 3);
INSERT INTO AULA (nome_aula, modulo, semestre, id_curso) values ('Calculo', 1, 1, 1),('Calculo', 1, 1, 2),('POO', 1, 1, 2),('Estatistica', 1, 1, 2),('Estatistica', 1, 1, 1),('Portugues', 1, 1, 3),('Ingles', 1, 1, 3),('Espanhol', 1, 1, 3),('Estruturada', 1, 1, 2),('Estruturas', 1, 1, 1);
INSERT INTO MATRICULA (id_aluno, id_aula) values (1,2),(1,3),(1,4),(1,9),(2,2),(2,3),(2,4),(2,9),(3,1),(3,5),(3,10),(4,6),(4,7),(4,8)
