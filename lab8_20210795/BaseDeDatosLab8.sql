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
  `idEventos` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Categoria` VARCHAR(45) NOT NULL,
  `CapacidadMax` INT NOT NULL,
  `NumReservasActual` INT NOT NULL,
  PRIMARY KEY (`idEventos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BaseDeDatosLab8`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BaseDeDatosLab8`.`Persona` (
  `idPersona` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BaseDeDatosLab8`.`Registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BaseDeDatosLab8`.`Registro` (
  `idRegistro` INT NOT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
