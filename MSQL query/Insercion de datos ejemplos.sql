
-- USERS
INSERT INTO users (email, hashed_password, username, created_at, phone) VALUES
('admin@example.com', 'hashed_pw1', 'adminUser', NOW(), '5551234567'),
('vendor@example.com', 'hashed_pw2', 'greenStore', NOW(), '5559876543'),
('customer1@example.com', 'hashed_pw3', 'ecoUser1', NOW(), '5551112233'),
('customer2@example.com', 'hashed_pw4', 'ecoUser2', NOW(), '5552223344');

-- ADMINS
INSERT INTO admins (user) VALUES (1);

-- APPROVAL STATUS
INSERT INTO approval_status (status) VALUES ('pending'), ('approved'), ('rejected');

-- VENDORS
INSERT INTO vendors (user, rfc, company_name, address, approved_by, approval_status, approval_comments, reviewed_at) VALUES
(2, 'XEXX010101000', 'Green Store S.A.', 'Av. Verde 123', 1, 2, 'Cumple requisitos', NOW());

-- CUSTOMERS
INSERT INTO customers (user, address, electricity_footprint, water_footprint, gas_footprint) VALUES
(3, 'Calle Árbol 456', 1200, 150, 80),
(4, 'Calle Río 789', 1000, 130, 60);

-- PRODUCTS
INSERT INTO products (vendor, name, description, price, stock, co2_reduction, energy_saving, status, metadata_images, created_at, last_modify_date, approved_by, approval_comments, reviewed_at) VALUES
(1, 'Panel Solar 200W', 'Panel solar eficiente de 200W', 2500.00, 10, 300, 500, 2, '["img1.jpg", "img2.jpg"]', NOW(), NOW(), 1, 'Producto aprobado', NOW()),
(1, 'Aire Acondicionado Eficiente', 'Reduce el consumo de energía hasta un 40%', 4500.00, 5, 200, 300, 2, '["aire1.jpg"]', NOW(), NOW(), 1, 'Producto verificado', NOW());

-- ORDER STATUS
INSERT INTO order_status (status_name) VALUES ('pending'), ('shipped'), ('delivered');

-- ORDERS
INSERT INTO orders (customer, order_status, total_amount, created_at, payment, transaction_id) VALUES
(1, 1, 2500.00, NOW(), 1, 1001);

-- ORDER ITEMS
INSERT INTO order_items (order_, product_id, quantity, unit_price) VALUES
(1, 1, 1, 2500.00);

-- QUOTE STATUS
INSERT INTO quote_status (status_name) VALUES ('pending'), ('accepted'), ('rejected');

-- INSTALLATIONS
INSERT INTO installations (status, scheduled_date, notes, installation_cost) VALUES
(1, '2025-06-01 09:00:00', 'Cliente solicitó instalación matutina', 500.00);

-- QUOTES
INSERT INTO quotes (customer, quote_status, created_at, total_amount, installation) VALUES
(1, 1, NOW(), 5000.00, 1);

-- QUOTE ITEMS
INSERT INTO quote_items (quote, product, quantity, total_amount) VALUES
(1, 2, 1, 4500.00);

-- BILLS
INSERT INTO bills (customer, bill_type, image_url, uploaded_at, bill_status) VALUES
(1, 1, 'bill1.jpg', NOW(), 'pending');

-- SURVEYS
INSERT INTO surveys (customer, completed_at, response) VALUES
(1, NOW(), '{"q1": "Sí", "q2": "No", "q3": 5}');
