-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
DROP DATABASE IF EXISTS `BaseDeDatosLab8`;
CREATE SCHEMA IF NOT EXISTS `BaseDeDatosLab8` DEFAULT CHARACTER SET utf8mb3 ;
USE `BaseDeDatosLab8` ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `BaseDeDatosLab8`.`Eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BaseDeDatosLab8`.`Eventos` (
  `idEventos` INT AUTO_INCREMENT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Categoria` VARCHAR(45) NOT NULL,
  `CapacidadMax` INT NOT NULL,
  `NumReservasActual` INT NOT NULL,
  PRIMARY KEY (`idEventos`))
ENGINE = InnoDB;

-- Inserciones en la tabla Eventos
INSERT INTO `BaseDeDatosLab8`.`Eventos` (`idEventos`, `Nombre`, `Fecha`, `Categoria`, `CapacidadMax`, `NumReservasActual`) VALUES
(1, 'Concierto de Rock', '2024-11-05', 'Música', 200, 50),
(2, 'Taller de Pintura', '2024-11-06', 'Arte', 30, 10),
(3, 'Conferencia de Tecnología', '2024-11-07', 'Tecnología', 100, 60),
(4, 'Competencia de Ajedrez', '2024-11-08', 'Deporte', 40, 20),
(5, 'Feria de Libros', '2024-11-09', 'Cultura', 150, 80),
(6, 'Clase de Yoga', '2024-11-10', 'Salud', 25, 15),
(7, 'Evento de Networking', '2024-11-11', 'Negocios', 75, 30),
(8, 'Curso de Fotografía', '2024-11-12', 'Arte', 20, 15),
(9, 'Exposición de Arte', '2024-11-13', 'Arte', 60, 45),
(10, 'Torneo de Videojuegos', '2024-11-14', 'Entretenimiento', 50, 25),
(11, 'Fiesta Temática', '2024-11-15', 'Social', 200, 150),
(12, 'Clínica de Golf', '2024-11-16', 'Deporte', 35, 10),
(13, 'Simposio de Ciencias', '2024-11-17', 'Académico', 120, 70),
(14, 'Festival de Comida', '2024-11-18', 'Gastronomía', 300, 200),
(15, 'Maratón', '2024-11-19', 'Deporte', 500, 400),
(16, 'Show de Comedia', '2024-11-20', 'Entretenimiento', 100, 90),
(17, 'Clase de Cocina', '2024-11-21', 'Gastronomía', 20, 10),
(18, 'Exposición de Autos', '2024-11-22', 'Exposición', 250, 125),
(19, 'Concurso de Fotografía', '2024-11-23', 'Arte', 80, 40),
(20, 'Seminario de Liderazgo', '2024-11-24', 'Negocios', 60, 55);

-- -----------------------------------------------------
-- Table `BaseDeDatosLab8`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BaseDeDatosLab8`.`Persona` (
  `idPersona` INT AUTO_INCREMENT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;

-- Inserciones en la tabla Persona
INSERT INTO `BaseDeDatosLab8`.`Persona` (`idPersona`, `Nombre`, `Correo`) VALUES
(1, 'Ana García', 'ana.garcia@example.com'),
(2, 'Luis Pérez', 'luis.perez@example.com'),
(3, 'Carlos Martínez', 'carlos.martinez@example.com'),
(4, 'María López', 'maria.lopez@example.com'),
(5, 'Juan Torres', 'juan.torres@example.com'),
(6, 'Laura Sánchez', 'laura.sanchez@example.com'),
(7, 'Pedro Ramírez', 'pedro.ramirez@example.com'),
(8, 'Elena Fernández', 'elena.fernandez@example.com'),
(9, 'Rosa Jiménez', 'rosa.jimenez@example.com'),
(10, 'Miguel Moreno', 'miguel.moreno@example.com'),
(11, 'Sofía Gómez', 'sofia.gomez@example.com'),
(12, 'Diego Castillo', 'diego.castillo@example.com'),
(13, 'Andrea Herrera', 'andrea.herrera@example.com'),
(14, 'Oscar Cruz', 'oscar.cruz@example.com'),
(15, 'Pablo Rojas', 'pablo.rojas@example.com'),
(16, 'Carolina Reyes', 'carolina.reyes@example.com'),
(17, 'Gabriel Vázquez', 'gabriel.vazquez@example.com'),
(18, 'Isabel Peña', 'isabel.pena@example.com'),
(19, 'Tomás Delgado', 'tomas.delgado@example.com'),
(20, 'Valeria Méndez', 'valeria.mendez@example.com');

-- -----------------------------------------------------
-- Table `BaseDeDatosLab8`.`Registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BaseDeDatosLab8`.`Registro` (
  `idRegistro` INT AUTO_INCREMENT  NOT NULL,
  `Eventos_idEventos` INT NOT NULL,
  `Persona_idPersona` INT NOT NULL,
  PRIMARY KEY (`idRegistro`),
  INDEX `fk_Registro_Eventos_idx` (`Eventos_idEventos` ASC) VISIBLE,
  INDEX `fk_Registro_Persona1_idx` (`Persona_idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Registro_Eventos`
    FOREIGN KEY (`Eventos_idEventos`)
    REFERENCES `BaseDeDatosLab8`.`Eventos` (`idEventos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Registro_Persona1`
    FOREIGN KEY (`Persona_idPersona`)
    REFERENCES `BaseDeDatosLab8`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Inserciones en la tabla Registro
INSERT INTO `BaseDeDatosLab8`.`Registro` (`idRegistro`, `Eventos_idEventos`, `Persona_idPersona`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
