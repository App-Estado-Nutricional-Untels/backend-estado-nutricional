INSERT INTO `rol` (`
rol_nombre`)
VALUES
    ('ROLE_ADMINISTRADOR'),
    ('ROLE_MEDICO'),
    ('ROLE_ALUMNO');

INSERT INTO `codigo_medico_valido` (`
codigo_medico`)
VALUES
    ('1111111111'),
    ('2222222222'),
    ('3333333333'),
    ('4444444444'),
    ('5555555555'),
    ('6666666666'),
    ('7777777777'),
    ('8888888888'),
    ('9999999999'),
    ('1000000000'),
    ('2000000000'),
    ('3000000000'),
    ('4000000000'),
    ('5000000000'),
    ('6000000000'),
    ('7000000000'),
    ('8000000000'),
    ('9000000000');

INSERT INTO `codigo_uni_valido` (`
codigo_universitario`)
VALUES
    ('1111111111'),
    ('2222222222'),
    ('3333333333'),
    ('4444444444'),
    ('5555555555'),
    ('6666666666'),
    ('7777777777'),
    ('8888888888'),
    ('9999999999'),
    ('1000000000'),
    ('2000000000'),
    ('3000000000'),
    ('4000000000'),
    ('5000000000'),
    ('6000000000'),
    ('7000000000'),
    ('8000000000'),
    ('9000000000');

INSERT INTO `persona` VALUES 
(1,'Administrador','Administrador','11111111','1995-01-01 00:01:00','Administrador','M'),
(2,'Boada','Rueda','74536964','1996-12-29 00:07:00','Ignacio','M'),
(3,'Ozorio','Santos','09982341','1984-01-01 00:11:00','Irma','F');

INSERT INTO `medico` VALUES (1,'1000000000',3);

INSERT INTO `alumno` VALUES (1,'1000000000',2);

-- La contraseña en todos es 123456789
INSERT INTO `usuario` VALUES 
(1,'$2a$10$TlRf5KQzVWK/faqqGBF1QeeliWPi7qgRYtszJ2d95OwT7nyL7RKmi','administrador@localhost.com',1,1),
(2,'$2a$10$ZpegzLu8QILmd0gUrz0nxOLfGcFUuAc4i2hX9DXVcGt05.5Kgf.hS','1823110062@untels.edu.pe',2,3),
(3,'$2a$10$/xV0QOnH7jqznHpck2gah.Z6AIsZK3LSMZZLhxEkHnvuiJqKvJOsG','irma_santos@untels.edu.pe',3,2);

