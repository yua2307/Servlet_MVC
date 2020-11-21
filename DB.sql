Drop Database  if exists Product;
create DATABASE Product;
USE Product;

CREATE TABLE Category(
	idCate INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cateName varchar(50) 
);
CREATE TABLE Product(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idCate int references Category(idCate),
    name varchar(50) ,
    des varchar(50),
    quantity int,
    unitPrice double
);

create table User(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) ,
    userName varchar(50),
    password varchar(50),
    role varchar(50)
);

INSERT INTO `Product`.`Category` (`cateName`) VALUES ('Perfume');
INSERT INTO `Product`.`Category` (`cateName`) VALUES ('Book');
INSERT INTO `Product`.`Category` (`cateName`) VALUES ('Healthy');
INSERT INTO `Product`.`Category` (`cateName`) VALUES ('Game');


INSERT INTO `Product`.`Product` (`idCate`, `name`, `des`, `quantity`) VALUES ('1', 'Dior Savauge', 'Perfume', '2');
