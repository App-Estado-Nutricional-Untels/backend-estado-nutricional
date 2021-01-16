USE estadonutricional;

INSERT INTO `rol`
VALUES
    (1, 'ROLE_ADMINISTRADOR'),
    (2, 'ROLE_MEDICO'),
    (3, 'ROLE_ALUMNO');

INSERT INTO `codigo_medico_valido`
VALUES
    (1, '1111111111'),
    (2, '2222222222'),
    (3, '3333333333'),
    (4, '4444444444'),
    (5, '5555555555'),
    (6, '6666666666'),
    (7, '7777777777'),
    (8, '8888888888'),
    (9, '9999999999'),
    (10, '1000000000'),
    (11, '2000000000'),
    (12, '3000000000'),
    (13, '4000000000'),
    (14, '5000000000'),
    (15, '6000000000'),
    (16, '7000000000'),
    (17, '8000000000'),
    (18, '9000000000');

INSERT INTO `codigo_uni_valido`
VALUES
    (1, '1111111111'),
    (2, '2222222222'),
    (3, '3333333333'),
    (4, '4444444444'),
    (5, '5555555555'),
    (6, '6666666666'),
    (7, '7777777777'),
    (8, '8888888888'),
    (9, '9999999999'),
    (10, '1000000000'),
    (11, '2000000000'),
    (12, '3000000000'),
    (13, '4000000000'),
    (14, '5000000000'),
    (15, '6000000000'),
    (16, '7000000000'),
    (17, '8000000000'),
    (18, '9000000000');

INSERT INTO `persona` VALUES 
(1,'Administrador','Administrador','11111111','1995-01-01 00:01:00','Administrador','M'),
(2,'Boada','Rueda','74536964','1996-12-29 00:07:00','Ignacio','M'),
(3,'Ozorio','Santos','09982341','1984-01-01 00:11:00','Irma','F'),
(4,'Castillo','Mendez','78934013',NULL,'Felipe','M'),
(5,'Salamanca','Santos','99013101',NULL,'María','F'),
(6,'Ramirez','Hermosa','09812310','1995-01-01 00:10:00','David Jair','M'),
(7,'Tapia','Urugiyo','31012076',NULL,'Antonio Uriel','M'),
(8,'Frias','Bayón','88194012',NULL,'José Ramón','M'),
(9,'Ávila','Saenz','56010310','1995-12-31 00:01:00','Verónica María','F'),
(10,'Vadillo','Gutierrez','76641918',NULL,'Isabel Karla','F');

INSERT INTO `medico` VALUES (1,'1000000000',3);

INSERT INTO `alumno` VALUES 
(1,'1000000000',2),
(2,'1111111111',4),
(3,'2222222222',5),
(4,'3333333333',6),
(5,'4444444444',7),
(6,'5555555555',8),
(7,'7777777777',9),
(8,'9999999999',10);

-- La contraseña en todos es 123abc
INSERT INTO `usuario` VALUES 
(1,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','administrador@localhost.com',1,1),
(2,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','1823110062@untels.edu.pe',2,3),
(3,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','irma_santos@untels.edu.pe',3,2),
(4,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','felgamer777@outlook.com',4,3),
(5,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','mariasalamanca@outlook.com',5,3),
(6,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','david_909@gmail.com',6,3),
(7,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','uriel_peru@outlook.com',7,3),
(8,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','joseramonbayon_2010@hotmail.com',8,3),
(9,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','vero2002@hotmail.com',9,3),
(10,'$2a$10$0GQSAAJkC4BPpEQXOTAFNue3NK2si1AA5pySdi5gclmNMC1urODrG','isabel_gutierrez_900@hotmail.com',10,3);

