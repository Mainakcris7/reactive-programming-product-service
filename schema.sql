use spring_boot_practice;

CREATE TABLE products(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 3) CHECK(price > 0)
);

SELECT * FROM products;