mysql -u root -p


create database sat;

CREATE TABLE IF NOT EXISTS `multa` (
`id_multa` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `dni` varchar(8) DEFAULT NULL,
  `tipo_multa` varchar(30) DEFAULT NULL,
  `monto` decimal(6,2) DEFAULT NULL,
  `correo` varchar(80) DEFAULT NULL,
  `punto` int(11) DEFAULT NULL,
  `fec_regi` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;


1. Crear la columna punto entero.
2. Agregar la columna punto en el formulario (en el registro y la tabla)

create table tipo_multa(id_tipo int primary key auto_increment, desc_tipo_multa varchar(40));
 --tabla borrados 
CREATE TABLE IF NOT EXISTS `tb_borrados` (
`id_multa` int(11) NOT NULL ,
  `dni` varchar(8) DEFAULT NULL,
  `tipo_multa` varchar(30) DEFAULT NULL,
  `monto` decimal(6,2) DEFAULT NULL,
  `correo` varchar(80) DEFAULT NULL,
  `punto` int(11) DEFAULT NULL,
  `fec_regi` datetime DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER tr_borrados BEFORE DELETE
ON multa
FOR EACH ROW BEGIN
insert into tb_borrados SELECT * FROM multa WHERE id_multa = old.id_multa;
END;