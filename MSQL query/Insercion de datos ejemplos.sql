-- Insertar usuarios
INSERT INTO users (email, hashed_password, username, created_at, phone)
VALUES
    ('admin@ecottlali.com', 'hashedpass123', 'AdminUser', NOW(), '555-111-2222'),
    ('vendor1@eco.com', 'hashedpass456', 'GreenTech', NOW(), '555-333-4444'),
    ('vendor2@eco.com', 'hashedpass789', 'SolarCo', NOW(), '555-666-7777'),
    ('customer1@eco.com', 'hashedpass999', 'JuanPerez', NOW(), '555-888-9999');
SELECT * FROM users;

INSERT INTO admins (user)
VALUES (1); -- admin@ecottlali.com


-- Insertar vendedores
INSERT INTO vendors (user, rfc, company_name, address, approved_by, approval_status, approval_comments, reviewed_at)
VALUES
    (1, 'GRT123456789', 'GreenTech Solutions', 'Av. Verde 123, CDMX', '1', 'Approved', 'Todo en orden', '2024-06-05 14:00:00'),
    (2, 'SLC987654321', 'Solar Company', 'Calle Sol 456, Monterrey', '1', 'Approved', 'Verificado', '2024-06-05 14:00:00');
SELECT * FROM vendors;


-- Insertar productos
INSERT INTO `products` (`product_id`, `vendor`, `name`, `description`, `price`, `stock`, `electricity_produced`, `electricity_consumption`, `approval_status`, `created_at`, `last_modify_date`, `approved_by`, `approval_comments`, `reviewed_at`) VALUES
                                                                                                                                                                                                                                                        (1, 1, 'Panel Solar 300W', 'Panel solar de alta eficiencia', 3500.00, 50, 300, 0, 1, '2024-06-05 14:00:00', '2024-06-05 14:00:00', 100, 'Approved', '2024-06-05 15:00:00'),
                                                                                                                                                                                                                                                        (2, 2, 'Inversor 5kW', 'Inversor para sistemas solares', 12000.00, 20, 0, 5000, 1, '2024-06-06 16:00:00', '2024-06-06 16:00:00', 101, 'Approved', '2024-06-06 17:00:00');

SELECT * FROM products;