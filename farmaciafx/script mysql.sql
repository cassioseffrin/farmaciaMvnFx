create database farmaciafx

CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `rg` varchar(15) NOT NULL,
  `endereco` varchar(155) DEFAULT NULL,
  `telefone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `rg_UNIQUE` (`rg`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `estoque` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) NOT NULL,
  `datafab` date DEFAULT NULL,
  `datavalidade` date DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  `quantidade` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `funcionario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(25) NOT NULL,
  `rg` varchar(15) NOT NULL,
  `endereco` varchar(155) DEFAULT NULL,
  `pis` varchar(25) NOT NULL,
  `pasep` varchar(25) NOT NULL,
  `carteiratrabalho` varchar(45) DEFAULT NULL,
  `crf` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `rg_UNIQUE` (`rg`),
  UNIQUE KEY `crf_UNIQUE` (`crf`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;