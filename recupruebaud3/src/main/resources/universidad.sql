DROP DATABASE IF EXISTS universidad_recu_ud3;
CREATE DATABASE universidad_recu_ud3 CHARACTER SET utf8mb4;
USE universidad_recu_ud3;

CREATE TABLE departamento (
                              id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(50) NOT NULL
);

CREATE TABLE persona (
                         id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         nif VARCHAR(9) UNIQUE,
                         nombre VARCHAR(25) NOT NULL,
                         apellido1 VARCHAR(50) NOT NULL,
                         apellido2 VARCHAR(50),
                         ciudad VARCHAR(25) NOT NULL,
                         direccion VARCHAR(50) NOT NULL,
                         telefono VARCHAR(9),
                         fecha_nacimiento DATE NOT NULL,
                         tipo ENUM('catedratico', 'profesor', 'alumno') NOT NULL
);

CREATE TABLE profesor (
                          id_profesor INT UNSIGNED PRIMARY KEY,
                          id_departamento INT UNSIGNED NOT NULL,
                          FOREIGN KEY (id_profesor) REFERENCES persona(id),
                          FOREIGN KEY (id_departamento) REFERENCES departamento(id)
);

CREATE TABLE asignatura (
                            id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL,
                            creditos FLOAT UNSIGNED NOT NULL,
                            tipo ENUM('básica', 'obligatoria', 'optativa') NOT NULL,
                            curso TINYINT UNSIGNED NOT NULL,
                            cuatrimestre TINYINT UNSIGNED NOT NULL,
                            id_profesor INT UNSIGNED,
                            FOREIGN KEY(id_profesor) REFERENCES profesor(id_profesor)
);