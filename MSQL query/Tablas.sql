CREATE SCHEMA ecottlali;
DROP SCHEMA ecottlali;
USE ecottlali;

-- CREACIÓN DE TABLAS

CREATE TABLE `users` (
                         `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `email` VARCHAR(50) UNIQUE NOT NULL,
                         `hashed_password` VARCHAR(128) NOT NULL,
                         `username` VARCHAR(255) UNIQUE NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `phone` VARCHAR(255) NOT NULL,
                         `uuid` VARCHAR(50)
);

CREATE TABLE `admins` (
                          `admin_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `user_id` INT UNSIGNED NOT NULL UNIQUE,
                          FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
);

CREATE TABLE `vendors` (
                           `vendor_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `user_id` INT UNSIGNED NOT NULL UNIQUE,
                           `rfc` VARCHAR(255) NOT NULL,
                           `company_name` VARCHAR(255) NOT NULL,
                           `address` VARCHAR(255) NOT NULL,
                           `approved_by` INT UNSIGNED,
                           `approval_status` ENUM('Approved', 'Declined', 'Pending') NOT NULL DEFAULT 'Pending',
                           `approval_comments` VARCHAR(255),
                           `reviewed_at` DATETIME,
                           FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
    -- FOREIGN KEY (`approved_by`) REFERENCES `admins`(`admin_id`)
);

CREATE TABLE `customers` (
                             `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `user_id` INT UNSIGNED NOT NULL UNIQUE,
                             `electricity_footprint` DECIMAL(10, 2) NOT NULL,
                             `water_footprint` DECIMAL(10, 2) NOT NULL,
                             `gas_footprint` INT NOT NULL,
                             FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
);

CREATE TABLE `products` (
                            `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `vendor` INT UNSIGNED NOT NULL,
                            `name` VARCHAR(255) NOT NULL,
                            `description` VARCHAR(255) NOT NULL,
                            `price` DECIMAL(10, 2) NOT NULL,
                            `stock` INT NOT NULL,
                            `electricity_produced` DECIMAL(10, 2) NOT NULL,
                            `electricity_consumption` DECIMAL(10, 2) NOT NULL,
                            `approval_status` ENUM('Approved', 'Declined', 'Pending') NOT NULL DEFAULT 'Pending',
                            `created_at` DATETIME NOT NULL,
                            `last_modify_date` DATETIME NOT NULL,
                            `approved_by` INT UNSIGNED NOT NULL,
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
                          `total_amount` DECIMAL(10, 2) NOT NULL,
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
                         `customer` INT UNSIGNED NOT NULL,
                         `bill_type` BIGINT NOT NULL,
                         `image_url` LONGTEXT NOT NULL,
                         `uploaded_at` DATETIME NOT NULL,
                         `bill_status` ENUM('Processed', 'Not Processed', 'Pending') NOT NULL DEFAULT 'Pending',
                         FOREIGN KEY (`customer`) REFERENCES `customers`(`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `installations` (
                                 `installation_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `status` ENUM('Scheduled', 'Installed', 'Pending') NOT NULL DEFAULT 'Pending',
                                 `scheduled_date` DATETIME NOT NULL,
                                 `notes` VARCHAR(255) NOT NULL,
                                 `installation_cost` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `images` (
                          `image_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `product` INT UNSIGNED NOT NULL,
                          `url` VARCHAR(255) NOT NULL,
                          `order` INT NOT NULL,
                          FOREIGN KEY (`product`) REFERENCES `products`(`product_id`) ON DELETE CASCADE
);
