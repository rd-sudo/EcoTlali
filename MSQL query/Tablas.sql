CREATE SCHEMA ecottlali;
DROP SCHEMA ecottlali;
USE ecottlali;

-- CREACIÓN DE TABLAS
-- PENDING Updated
CREATE TABLE `users` (
                         `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `email` VARCHAR(255) UNIQUE NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `uuid` VARCHAR(255),
                         `role` ENUM('ADMIN','VENDOR','CUSTOMER')
);

CREATE TABLE `admins` (
                          `admin_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `user_id` INT UNSIGNED NOT NULL UNIQUE,
                          `name` VARCHAR(255) NOT NULL,
                          FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
);
-- Updated
CREATE TABLE `vendors` (
                           `vendor_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `user_id` INT UNSIGNED NOT NULL UNIQUE,
                           `rfc` VARCHAR(255) NOT NULL,
                           `company_name` VARCHAR(255) NOT NULL,
                           `tax_address` VARCHAR(255) NOT NULL,
                           `approval_status` ENUM('APPROVED', 'DECLINE', 'PENDING') NOT NULL DEFAULT 'PENDING',
                           `reviewed_at` DATETIME,
                           FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
    -- FOREIGN KEY (`approved_by`) REFERENCES `admins`(`admin_id`)
);
-- Updated
CREATE TABLE `customers` (
                             `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `user_id` INT UNSIGNED NOT NULL UNIQUE,
                             `phone` VARCHAR(255) NOT NULL,
                             `name` VARCHAR(255) NOT NULL,
                             FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
);

CREATE TABLE `products` (
                            `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `vendor` INT UNSIGNED NOT NULL,
                            `name` VARCHAR(255) NOT NULL,
                            `description` VARCHAR(255) NOT NULL,
                            `price` DOUBLE NOT NULL,
                            `stock` INT NOT NULL,
                            `company_name` VARCHAR(100) NOT NULL,
                            `installation_option` ENUM('SIMPLE','COMPLEX') NOT NULL,
                            `product_category` ENUM('ENERGIA_RENOVABLE',
    'MOVILIDAD_SOSTENIBLE',
    'HOGAR_SUSTENTABLE',
    'CONSUMO_RESPONSABLE',
    'MODA_Y_CUIDADO_PERSONAL_ECOLOGICOS'
    ),
                            `product_energy_category` ENUM('WATER', --
    'ELECTRICITY',
    'GAS'
    ),
                            `requires_sun` BOOLEAN NOT NULL DEFAULT FALSE, --
                            `smart_device` boolean NOT NULL,
                            `electricity_produced` DOUBLE,
                            `electricity_consumption` DOUBLE,
                            `saving_cost` DOUBLE,
                            `approval_status` ENUM('APPROVED', 'DECLINED', 'PENDING') NOT NULL DEFAULT 'PENDING',
                            `created_at` DATETIME NOT NULL,
                            `last_modify_date` DATETIME NOT NULL,
                            `approved_by` VARCHAR(255) NOT NULL,
                            `approval_comments` VARCHAR(255),
                            `reviewed_at` DATETIME,
                            FOREIGN KEY (`vendor`) REFERENCES `vendors`(`vendor_id`) ON DELETE CASCADE
    -- FOREIGN KEY (`approved_by`) REFERENCES `admins`(`admin_id`)
);

CREATE TABLE `order` (
                         `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `customer` INT UNSIGNED NOT NULL,
                         `order_status` BIGINT NOT NULL,
                         `total_amount` DECIMAL(10, 2) NOT NULL,
                         `created_at` DATETIME NOT NULL,
                         `payment` BIGINT NOT NULL,
                         `transaction_id` BIGINT NOT NULL,
                         FOREIGN KEY (`customer`) REFERENCES `customers`(`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `order_items` (
                               `order_items_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               `order` INT UNSIGNED NOT NULL,
                               `product_id` INT UNSIGNED NOT NULL,
                               `quantity` BIGINT NOT NULL,
                               `unit_price` DECIMAL(10, 2) NOT NULL,
                               FOREIGN KEY (`order`) REFERENCES `order`(`order_id`) ON DELETE CASCADE,
                               FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`) ON DELETE CASCADE
);

CREATE TABLE `quotes` (
                          `quote_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `customer_id` INT UNSIGNED NOT NULL,
                          `created_at` DATETIME NOT NULL,
                          `total_amount`DOUBLE NOT NULL,
                          `installation` BOOLEAN NOT NULL,
                          FOREIGN KEY (`customer_id`) REFERENCES `customers`(`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `quote_items` (
                               `quote_item_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               `quote_id` INT UNSIGNED NOT NULL,
                               `product_id` INT UNSIGNED NOT NULL,
                               `quantity` BIGINT NOT NULL,
                               UNIQUE (quote_id, product_id), -- Clave única para evitar duplicados
                               FOREIGN KEY (`quote_id`) REFERENCES `quotes`(`quote_id`) ON DELETE CASCADE,
                               FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`) ON DELETE CASCADE
);

CREATE TABLE `bills` (
                         `bill_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `customer_id` INT UNSIGNED NOT NULL,
                         `bill_type` ENUM('WATER','GAS','ELECTRICITY') NOT NULL,
                         `bill_cost` DOUBLE NOT NULL,
                         `bill_consume` DOUBLE NOT NULL,
                         `uploaded_at` DATETIME NOT NULL,
                         FOREIGN KEY (`customer_id`) REFERENCES `customers`(`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `installations` (
                                 `installation_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `status` ENUM('SCHEDULED', 'INSTALLED', 'PENDING') NOT NULL DEFAULT 'Pending',
                                 `scheduled_date` DATETIME NOT NULL,
                                 `notes` VARCHAR(255) NOT NULL,
                                 `installation_cost` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE footprints(
                           `footprint_id` INT UNSIGNED NOT NULL PRIMARY KEY,
                           `customer_id` INT UNSIGNED NOT NULL,
                           FOREIGN KEY (`customer_id`) REFERENCES `customers`(`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `images` (
                          `image_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `product` INT UNSIGNED NOT NULL,
                          `url` VARCHAR(255) NOT NULL,
                          `order` INT NOT NULL,
                          FOREIGN KEY (`product`) REFERENCES `products`(`product_id`) ON DELETE CASCADE
);
