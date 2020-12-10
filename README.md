# farmaciaMvnFx

Projeto desenvolvido como material de apoio da 2a fase do curso de Eng. SoftwareUnC 2020.

Professor: Cassio Seffrin

Tecnologias Envolvidas:
- Java 15
- Java FX
- Maven
- Banco de dados MySQL.



DDL do Banco:


-- MySQL 
-- Server version	8.0.21

--
-- Table structure for table `agenda`
--
````
DROP TABLE IF EXISTS `agenda`;
CREATE TABLE `agenda` (
  `id_agenda` int NOT NULL AUTO_INCREMENT,
  `data_agenda` date NOT NULL,
  `horario` varchar(10) NOT NULL,
  `paciente` varchar(50) NOT NULL,
  PRIMARY KEY (`id_agenda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
````


--
-- Table structure for table `enfermeiro`
--
````
DROP TABLE IF EXISTS `enfermeiro`;
CREATE TABLE `enfermeiro` (
  `id_enfermeiro` int NOT NULL AUTO_INCREMENT,
  `nome_enfermeiro` varchar(50) NOT NULL,
  `rg_enfermeiro` int NOT NULL,
  `cpf_enfermeiro` int NOT NULL,
  `sexo_enfermeiro` varchar(10) NOT NULL,
  `dataNascimento_enfermeiro` date NOT NULL,
  `telefone_enfermeiro` int DEFAULT NULL,
  `dataAdmissao` date NOT NULL,
  PRIMARY KEY (`id_enfermeiro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

````

--
-- Table structure for table `medico`
--
````
DROP TABLE IF EXISTS `medico`;
CREATE TABLE `medico` (
  `id_medico` int NOT NULL AUTO_INCREMENT,
  `nome_medico` varchar(50) NOT NULL,
  `rg_medico` int NOT NULL,
  `cpf_medico` int NOT NULL,
  `sexo_medico` varchar(10) NOT NULL,
  `dataNascimento_medico` date NOT NULL,
  `telefone_medico` int DEFAULT NULL,
  `especialidade` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_medico`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
````

--
-- Table structure for table `paciente`
--
````
DROP TABLE IF EXISTS `paciente`;
CREATE TABLE `paciente` (
  `id_paciente` int NOT NULL AUTO_INCREMENT,
  `nome_paciente` varchar(50) NOT NULL,
  `rg_paciente` int NOT NULL,
  `cpf_paciente` int NOT NULL,
  `sexo_paciente` varchar(10) NOT NULL,
  `dataNascimento_paciente` date NOT NULL,
  `telefone_paciente` int DEFAULT NULL,
  `gravidade` int DEFAULT NULL,
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
````

