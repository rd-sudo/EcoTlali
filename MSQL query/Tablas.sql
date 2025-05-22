CREATE SCHEMA ecottlali;
DROP SCHEMA ecottlali;
USE ecottlali;
SELECT * FROM users;

DROP table vendors;

-- TABLAS
CREATE TABLE `users`(
                        `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `email` VARCHAR(50) NOT NULL,
                        `hashed_password` VARCHAR(128) NOT NULL,
                        `username` VARCHAR(255) NOT NULL,
                        `created_at` DATETIME NOT NULL,
                        `phone` VARCHAR(255) NOT NULL
);
CREATE TABLE `admins`(
                         `admin_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `user` INT UNSIGNED NOT NULL
);
CREATE TABLE `vendors`(
                          `vendor_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `user` INT UNSIGNED NOT NULL,
                          `rfc` VARCHAR(255) NOT NULL,
                          `company_name` VARCHAR(255) NOT NULL,
                          `address` VARCHAR(255) NOT NULL,
                          `approved_by` VARCHAR(255) NOT NULL,
                          `approval_status` ENUM('Approved', 'Declined', 'Pending') NOT NULL DEFAULT 'Pending',
                          `approval_comments` VARCHAR(255) NOT NULL,
                          `reviewed_at` DATETIME NOT NULL
);
CREATE TABLE `customers`(
                            `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `user` INT UNSIGNED NOT NULL,
                            `address` VARCHAR(255) NOT NULL,
                            `electricity_footprint` DECIMAL(10, 2) NOT NULL,
                            `water_footprint` DECIMAL(10, 2) NOT NULL,
                            `gas_footprint` INT NOT NULL
);
CREATE TABLE `products`(
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
                           `approved_by` VARCHAR(255) NOT NULL,
                           `approval_comments` VARCHAR(255) NOT NULL,
                           `reviewed_at` DATETIME NOT NULL
);
CREATE TABLE `order`(
                        `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `customer` INT NOT NULL,
                        `order_status` BIGINT NOT NULL,
                        `total_amount` DECIMAL(10, 2) NOT NULL,
                        `created_at` DATETIME NOT NULL,
                        `payment` BIGINT NOT NULL,
                        `transaction_id` BIGINT NOT NULL
);
CREATE TABLE `order_items`(
                              `order_items_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `order` INT NOT NULL,
                              `product_id` INT NOT NULL,
                              `quantity` BIGINT NOT NULL,
                              `unit_price` DECIMAL(10, 2) NOT NULL
);
CREATE TABLE `quote_items`(
                              `quote_items_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `quote` INT NOT NULL,
                              `product` BIGINT NOT NULL,
                              `quantity` BIGINT NOT NULL,
                              `total_amount` DECIMAL(10, 2) NOT NULL
);
CREATE TABLE `quotes`(
                         `quotes_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `customer` BIGINT NOT NULL,
                         `quote_status` BIGINT NOT NULL,
                         `created_at` DATETIME NOT NULL,
                         `total_amount` DECIMAL(10, 2) NOT NULL,
                         `installation` INT NOT NULL
);
CREATE TABLE `bills`(
                        `bill_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `customer` INT NOT NULL,
                        `bill_type` BIGINT NOT NULL,
                        `image_url` LONGTEXT NOT NULL,
                        `uploaded_at` DATETIME NOT NULL,
                        `bill_status` ENUM('') NOT NULL
);
CREATE TABLE `installations`(
                                `installation_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                `status` ENUM('Scheduled', 'Instaled', 'Pending') NOT NULL DEFAULT 'Pending',
                                `scheduled_date` DATETIME NOT NULL,
                                `notes` VARCHAR(255) NOT NULL,
                                `installation_cost` DECIMAL(10, 2) NOT NULL
);
CREATE TABLE `images`(
                         `image_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `product` INT NOT NULL,
                         `url` VARCHAR(255) NOT NULL,
                         `orden` INT NOT NULL
);
ALTER TABLE
    `admins` ADD CONSTRAINT `admins_user_foreign` FOREIGN KEY(`user`) REFERENCES `users`(`user_id`);
ALTER TABLE
    `products` ADD CONSTRAINT `products_vendor_foreign` FOREIGN KEY(`vendor`) REFERENCES `vendors`(`vendor_id`);
ALTER TABLE
    `order` ADD CONSTRAINT `order_customer_foreign` FOREIGN KEY(`customer`) REFERENCES `customers`(`customer_id`);
ALTER TABLE
    `order_items` ADD CONSTRAINT `order_items_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `products`(`product_id`);
ALTER TABLE
    `order_items` ADD CONSTRAINT `order_items_order_foreign` FOREIGN KEY(`order`) REFERENCES `order`(`order_id`);
ALTER TABLE
    `quote_items` ADD CONSTRAINT `quote_items_quote_foreign` FOREIGN KEY(`quote`) REFERENCES `quotes`(`quotes_id`);
ALTER TABLE
    `vendors` ADD CONSTRAINT `vendors_user_foreign` FOREIGN KEY(`user`) REFERENCES `users`(`user_id`);
ALTER TABLE
    `images` ADD CONSTRAINT `images_product_foreign` FOREIGN KEY(`product`) REFERENCES `products`(`product_id`);
ALTER TABLE
    `quote_items` ADD CONSTRAINT `quote_items_product_foreign` FOREIGN KEY(`product`) REFERENCES `products`(`product_id`);
ALTER TABLE
    `quotes` ADD CONSTRAINT `quotes_customer_foreign` FOREIGN KEY(`customer`) REFERENCES `customers`(`customer_id`);
ALTER TABLE
    `bills` ADD CONSTRAINT `bills_customer_foreign` FOREIGN KEY(`customer`) REFERENCES `customers`(`customer_id`);
ALTER TABLE
    `customers` ADD CONSTRAINT `customers_user_foreign` FOREIGN KEY(`user`) REFERENCES `users`(`user_id`);
ALTER TABLE
    `quotes` ADD CONSTRAINT `quotes_installation_foreign` FOREIGN KEY(`installation`) REFERENCES `installations`(`installation_id`);
ALTER TABLE
    `products` ADD CONSTRAINT `products_approved_by_foreign` FOREIGN KEY(`approved_by`) REFERENCES `admins`(`user`);