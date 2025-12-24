CREATE TABLE IF NOT EXISTS roles(
	role_id BIGINT AUTO_INCREMENT,
    role_name VARCHAR(25),
    PRIMARY KEY(role_id)
);

CREATE TABLE IF NOT EXISTS users(
	user_id BIGINT AUTO_INCREMENT,
    user_name VARCHAR(65) NOT NULL UNIQUE,
    user_last_name VARCHAR(65) NOT NULL,
    user_phone_number VARCHAR(15) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS permissions(
	permission_id BIGINT AUTO_INCREMENT,
    permission_name VARCHAR(55) UNIQUE NOT NULL,
    PRIMARY KEY(permission_id)
);

CREATE TABLE IF NOT EXISTS users_permissions(
	user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY(user_id, permission_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(permission_id) REFERENCES permissions(permission_id)
);

CREATE TABLE IF NOT EXISTS customers(
	customer_id BIGINT AUTO_INCREMENT,
    customer_name VARCHAR(65) NOT NULL,
    customer_last_name VARCHAR(65) NOT NULL,
    customer_phone_number VARCHAR(25) UNIQUE NOT NULL,
    PRIMARY KEY(customer_id)
);

CREATE TABLE IF NOT EXISTS branches(
	branch_id BIGINT AUTO_INCREMENT,
    branch_name VARCHAR(100) NOT NULL,
    branch_address VARCHAR(200) UNIQUE NOT NULL,
    branch_phone VARCHAR(15) UNIQUE NOT NULL,
    is_active BOOLEAN NOT NULL,
    PRIMARY KEY(branch_id)
);

CREATE TABLE IF NOT EXISTS categories(
	category_id BIGINT AUTO_INCREMENT,
    category_name VARCHAR(75) UNIQUE NOT NULL,
    PRIMARY KEY(category_id)
);

CREATE TABLE IF NOT EXISTS clothe_types(
	clothe_type_id BIGINT AUTO_INCREMENT,
    clothe_type_name VARCHAR(75) UNIQUE NOT NULL,
    PRIMARY KEY(clothe_type_id)
);

CREATE TABLE IF NOT EXISTS industries(
	industry_id BIGINT AUTO_INCREMENT,
    industry_name VARCHAR(50) UNIQUE NOT NULL,
    PRIMARY KEY(industry_id)
);

CREATE TABLE IF NOT EXISTS colors(
    color_id BIGINT AUTO_INCREMENT,
    color_name VARCHAR(60) UNIQUE NOT NULL,
    PRIMARY KEY(color_id)
);

CREATE TABLE IF NOT EXISTS products(
	product_id BIGINT AUTO_INCREMENT,
    product_name varchar(100) NOT NULL,
    category_id BIGINT NOT NULL,
    type_id BIGINT NOT NULL,
    industry_id BIGINT NOT NULL,
    product_color varchar(55),
    product_size varchar(5),
    product_photo_url VARCHAR(255),
    product_bar_code_number varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY(product_id),
    FOREIGN KEY(category_id) REFERENCES categories(category_id),
    FOREIGN KEY(type_id) REFERENCES clothe_types(clothe_type_id),
    FOREIGN KEY(industry_id) REFERENCES industries(industry_id)
);

CREATE TABLE IF NOT EXISTS inventories(
	inventory_id BIGINT AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    cost_price DOUBLE NOT NULL,
    sale_price DOUBLE NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    branch_id BIGINT NOT NULL,
    PRIMARY KEY(inventory_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id),
    FOREIGN KEY(branch_id) REFERENCES branches(branch_id)
);

CREATE TABLE IF NOT EXISTS sales(
	sale_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sale_description VARCHAR(255),
    inventory_id BIGINT NOT NULL,
    stock INT, 
    sale_amount DOUBLE,
    sale_discount DOUBLE,
    sale_total DOUBLE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    customer_id BIGINT,
    user_id BIGINT,
    branch_id BIGINT,
    FOREIGN KEY (inventory_id) REFERENCES inventories(inventory_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (branch_id) REFERENCES branches(branch_id)
);


CREATE TABLE IF NOT EXISTS reservations(
	reservation_id BIGINT AUTO_INCREMENT,
    inventory_id BIGINT NOT NULL,
    reservation_description VARCHAR(255),
	reservation_deposit_amount DOUBLE NOT NULL,
    reservation_balance_due DOUBLE NOT NULL,
    reservation_status VARCHAR(20) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    expiration_date TIMESTAMP NULL,
    customer_id BIGINT NOT NULL,
    user_id BIGINT,
    branch_id BIGINT NOT NULL,
    PRIMARY KEY(reservation_id),
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(branch_id) REFERENCES branches(branch_id),
    FOREIGN KEY(inventory_id) REFERENCES inventories(inventory_id)
);