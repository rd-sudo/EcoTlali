USE ecottlali;

-- Tabla de usuarios
CREATE TABLE `users` (
    `user_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `hashed_password` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `created_at` DATETIME NOT NULL,
    `phone` VARCHAR(255) NOT NULL
);

-- Tabla de administradores
CREATE TABLE `admins` (
    `admin_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user` BIGINT UNSIGNED NOT NULL
);

-- Tabla de estados de aprobación
CREATE TABLE `approval_status` (
    `approval_status_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status` VARCHAR(255) NOT NULL
);

-- Tabla de vendedores
CREATE TABLE `vendors` (
    `vendor_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user` BIGINT UNSIGNED NOT NULL,
    `rfc` VARCHAR(255) NOT NULL,
    `company_name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `approved_by` BIGINT UNSIGNED NOT NULL,
    `approval_status` BIGINT UNSIGNED NOT NULL,
    `approval_comments` VARCHAR(255) NOT NULL,
    `reviewed_at` DATETIME NOT NULL
);

-- Tabla de clientes
CREATE TABLE `customers` (
    `customer_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user` BIGINT UNSIGNED NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `electricity_footprint` BIGINT NOT NULL,
    `water_footprint` BIGINT NOT NULL,
    `gas_footprint` BIGINT NOT NULL
);

-- Tabla de productos
CREATE TABLE `products` (
    `product_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `vendor` BIGINT UNSIGNED NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    `stock` INT NOT NULL,
    `co2_reduction` BIGINT NOT NULL,
    `energy_saving` BIGINT NOT NULL,
    `status` BIGINT UNSIGNED NOT NULL,
    `metadata_images` JSON NOT NULL,
    `created_at` DATETIME NOT NULL,
    `last_modify_date` DATETIME NOT NULL,
    `approved_by` BIGINT UNSIGNED NOT NULL,
    `approval_comments` VARCHAR(255) NOT NULL,
    `reviewed_at` DATETIME NOT NULL
);

-- Tabla de estados de orden
CREATE TABLE `order_status` (
    `order_status_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status_name` VARCHAR(255) NOT NULL
);

-- Tabla de órdenes
CREATE TABLE `orders` (
    `order_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer` BIGINT UNSIGNED NOT NULL,
    `order_status` BIGINT UNSIGNED NOT NULL,
    `total_amount` DECIMAL(10, 2) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `payment` BIGINT NOT NULL,
    `transaction_id` BIGINT NOT NULL
);

-- Tabla de elementos de la orden
CREATE TABLE `order_items` (
    `order_items_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `order` BIGINT UNSIGNED NOT NULL,
    `product_id` BIGINT UNSIGNED NOT NULL,
    `quantity` BIGINT NOT NULL,
    `unit_price` DECIMAL(10, 2) NOT NULL
);

-- Tabla de cotizaciones
CREATE TABLE `quote_status` (
    `quote_status_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status_name` VARCHAR(255) NOT NULL
);

CREATE TABLE `installations` (
    `installation_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status` BIGINT UNSIGNED NOT NULL,
    `scheduled_date` DATETIME NOT NULL,
    `notes` VARCHAR(255) NOT NULL,
    `installation_cost` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `quotes` (
    `quotes_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer` BIGINT UNSIGNED NOT NULL,
    `quote_status` BIGINT UNSIGNED NOT NULL,
    `created_at` DATETIME NOT NULL,
    `total_amount` DECIMAL(10, 2) NOT NULL,
    `installation` BIGINT UNSIGNED NOT NULL
);

CREATE TABLE `quote_items` (
    `quote_items_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `quote` BIGINT UNSIGNED NOT NULL,
    `product` BIGINT UNSIGNED NOT NULL,
    `quantity` BIGINT NOT NULL,
    `total_amount` DECIMAL(10, 2) NOT NULL
);

-- Tabla de facturas
CREATE TABLE `bills` (
    `bill_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer` BIGINT UNSIGNED NOT NULL,
    `bill_type` BIGINT NOT NULL,
    `image_url` LONGTEXT NOT NULL,
    `uploaded_at` DATETIME NOT NULL,
    `bill_status` ENUM('pending', 'approved', 'rejected') NOT NULL
);

-- Tabla de encuestas
CREATE TABLE `surveys` (
    `survey_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer` BIGINT UNSIGNED NOT NULL,
    `completed_at` DATETIME NOT NULL,
    `response` JSON NOT NULL
);

-- Foreign Keys
ALTER TABLE `admins` ADD CONSTRAINT `fk_admins_user` FOREIGN KEY (`user`) REFERENCES `users`(`user_id`);
ALTER TABLE `vendors` 
    ADD CONSTRAINT `fk_vendors_user` FOREIGN KEY (`user`) REFERENCES `users`(`user_id`),
    ADD CONSTRAINT `fk_vendors_approval_status` FOREIGN KEY (`approval_status`) REFERENCES `approval_status`(`approval_status_id`),
    ADD CONSTRAINT `fk_vendors_approved_by` FOREIGN KEY (`approved_by`) REFERENCES `admins`(`user`);

ALTER TABLE `customers` ADD CONSTRAINT `fk_customers_user` FOREIGN KEY (`user`) REFERENCES `users`(`user_id`);
ALTER TABLE `products` 
    ADD CONSTRAINT `fk_products_vendor` FOREIGN KEY (`vendor`) REFERENCES `vendors`(`vendor_id`),
    ADD CONSTRAINT `fk_products_status` FOREIGN KEY (`status`) REFERENCES `approval_status`(`approval_status_id`),
    ADD CONSTRAINT `fk_products_approved_by` FOREIGN KEY (`approved_by`) REFERENCES `admins`(`user`);

ALTER TABLE `orders` 
    ADD CONSTRAINT `fk_orders_customer` FOREIGN KEY (`customer`) REFERENCES `customers`(`customer_id`),
    ADD CONSTRAINT `fk_orders_status` FOREIGN KEY (`order_status`) REFERENCES `order_status`(`order_status_id`);

ALTER TABLE `order_items` 
    ADD CONSTRAINT `fk_order_items_order` FOREIGN KEY (`order`) REFERENCES `orders`(`order_id`),
    ADD CONSTRAINT `fk_order_items_product` FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`);

ALTER TABLE `quotes` 
    ADD CONSTRAINT `fk_quotes_customer` FOREIGN KEY (`customer`) REFERENCES `customers`(`customer_id`),
    ADD CONSTRAINT `fk_quotes_status` FOREIGN KEY (`quote_status`) REFERENCES `quote_status`(`quote_status_id`),
    ADD CONSTRAINT `fk_quotes_installation` FOREIGN KEY (`installation`) REFERENCES `installations`(`installation_id`);

ALTER TABLE `quote_items` 
    ADD CONSTRAINT `fk_quote_items_quote` FOREIGN KEY (`quote`) REFERENCES `quotes`(`quotes_id`),
    ADD CONSTRAINT `fk_quote_items_product` FOREIGN KEY (`product`) REFERENCES `products`(`product_id`);

ALTER TABLE `bills` ADD CONSTRAINT `fk_bills_customer` FOREIGN KEY (`customer`) REFERENCES `customers`(`customer_id`);
ALTER TABLE `surveys` ADD CONSTRAINT `fk_surveys_customer` FOREIGN KEY (`customer`) REFERENCES `customers`(`customer_id`);
