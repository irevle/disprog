CREATE DATABASE IF NOT EXISTS food_reservation;
USE food_reservation;

CREATE TABLE users (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(50)  UNIQUE NOT NULL,
    password    VARCHAR(255) NOT NULL,
    full_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100),
    phone       VARCHAR(20),
    role        ENUM('ADMIN','CUSTOMER','STAFF') DEFAULT 'CUSTOMER',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tables (
    id           INT PRIMARY KEY AUTO_INCREMENT,
    table_number VARCHAR(10) UNIQUE NOT NULL,
    capacity     INT NOT NULL,
    status       ENUM('AVAILABLE','RESERVED','OCCUPIED') DEFAULT 'AVAILABLE'
);

CREATE TABLE menu_items (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    category    VARCHAR(50)  NOT NULL,
    price       DECIMAL(10,2) NOT NULL,
    description TEXT,
    available   TINYINT(1) DEFAULT 1
);

CREATE TABLE reservations (
    id               INT PRIMARY KEY AUTO_INCREMENT,
    user_id          INT NOT NULL,
    table_id         INT NOT NULL,
    reservation_date DATE NOT NULL,
    reservation_time TIME NOT NULL,
    end_time         TIME NOT NULL,
    guest_count      INT NOT NULL,
    status           ENUM('PENDING','CONFIRMED','CANCELLED','COMPLETED') DEFAULT 'PENDING',
    notes            TEXT,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)  REFERENCES users(id),
    FOREIGN KEY (table_id) REFERENCES tables(id),
    UNIQUE KEY uq_reservation (table_id, reservation_date, reservation_time)
);

CREATE TABLE order_items (
    id             INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id INT NOT NULL,
    menu_item_id   INT NOT NULL,
    quantity       INT NOT NULL DEFAULT 1,
    subtotal       DECIMAL(10,2) NOT NULL DEFAULT 0,
    status         ENUM('PENDING','PREPARING','READY','SERVED') DEFAULT 'PENDING',
    FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id)   REFERENCES menu_items(id)
);

CREATE TABLE payments (
    id               INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id   INT NOT NULL,
    amount           DECIMAL(10,2) NOT NULL,
    payment_method   ENUM('CASH','DEBIT','CREDIT','EWALLET') NOT NULL,
    payment_status   ENUM('PENDING','PAID','REFUNDED') DEFAULT 'PENDING',
    paid_at          TIMESTAMP NULL DEFAULT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);

CREATE TABLE audit_log (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT NOT NULL,
    action      VARCHAR(50) NOT NULL,
    entity_type VARCHAR(50) NOT NULL,
    entity_id   INT NOT NULL,
    details     TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (username, password, full_name, email, phone, role) VALUES
('admin', SHA2('admin123', 256), 'Administrator',   'admin@restaurant.com',  '081234567890', 'ADMIN'),
('budi',  SHA2('budi123',  256), 'Budi Santoso',    'budi@email.com',        '082345678901', 'CUSTOMER'),
('sari',  SHA2('sari123',  256), 'Sari Dewi',       'sari@email.com',        '083456789012', 'CUSTOMER'),
('doni',  SHA2('doni123',  256), 'Doni Prasetyo',   'doni@email.com',        '084567890123', 'CUSTOMER'),
('rina',  SHA2('rina123',  256), 'Rina Wahyuni',    'rina@email.com',        '085678901234', 'STAFF');

INSERT INTO tables (table_number, capacity, status) VALUES
('T01', 2, 'AVAILABLE'),
('T02', 2, 'AVAILABLE'),
('T03', 4, 'AVAILABLE'),
('T04', 4, 'AVAILABLE'),
('T05', 6, 'AVAILABLE'),
('T06', 6, 'AVAILABLE'),
('T07', 8, 'AVAILABLE'),
('T08', 8, 'AVAILABLE');

INSERT INTO menu_items (name, category, price, description, available) VALUES
('Nasi Goreng Spesial',  'MAKANAN',  28000, 'Nasi goreng dengan telur, ayam, dan sayuran', 1),
('Mie Goreng Seafood',   'MAKANAN',  32000, 'Mie goreng dengan campuran seafood segar',    1),
('Ayam Bakar Bumbu',     'MAKANAN',  38000, 'Ayam bakar dengan bumbu kecap pedas',         1),
('Soto Ayam',            'MAKANAN',  25000, 'Soto ayam kuah bening dengan lontong',        1),
('Gado-Gado',            'MAKANAN',  22000, 'Sayuran segar dengan saus kacang',            1),
('Rendang Sapi',         'MAKANAN',  45000, 'Rendang sapi empuk bumbu rempah khas Padang', 1),
('Es Teh Manis',         'MINUMAN',   8000, 'Teh manis dingin menyegarkan',                1),
('Jus Alpukat',          'MINUMAN',  18000, 'Jus alpukat segar dengan susu',               1),
('Jus Jeruk',            'MINUMAN',  15000, 'Jus jeruk peras segar',                       1),
('Air Mineral',          'MINUMAN',   5000, 'Air mineral botol 600ml',                     1),
('Es Campur',            'MINUMAN',  20000, 'Es campur dengan berbagai topping',            1),
('Kopi Hitam',           'MINUMAN',  12000, 'Kopi hitam tubruk tradisional',               1);

INSERT INTO reservations (user_id, table_id, reservation_date, reservation_time, end_time, guest_count, status, notes) VALUES
(2, 3, CURDATE() + INTERVAL 1 DAY, '12:00:00', '13:30:00', 4, 'CONFIRMED', 'Meja dekat jendela'),
(3, 5, CURDATE() - INTERVAL 2 DAY, '18:00:00', '19:30:00', 5, 'COMPLETED', 'Pesta ulang tahun'),
(4, 1, CURDATE() + INTERVAL 3 DAY, '10:00:00', '11:00:00', 2, 'CANCELLED', 'Dibatalkan oleh pelanggan'),
(2, 6, CURDATE() + INTERVAL 2 DAY, '19:00:00', '21:00:00', 6, 'CONFIRMED', 'Rapat keluarga'),
(5, 2, CURDATE() - INTERVAL 1 DAY, '11:30:00', '12:30:00', 2, 'COMPLETED', NULL);

INSERT INTO order_items (reservation_id, menu_item_id, quantity, subtotal, status) VALUES
(1, 1, 2, 56000,  'PREPARING'),
(1, 7, 4, 32000,  'PREPARING'),
(1, 10, 2, 10000, 'PENDING'),
(2, 3, 3, 114000, 'SERVED'),
(2, 6, 2, 90000,  'SERVED'),
(2, 8, 3, 54000,  'SERVED'),
(4, 4, 2, 50000,  'PENDING'),
(4, 11, 2, 40000, 'PENDING'),
(5, 12, 2, 24000, 'READY');

INSERT INTO payments (reservation_id, amount, payment_method, payment_status, paid_at) VALUES
(2, 258000, 'DEBIT', 'PAID', CURDATE() - INTERVAL 2 DAY + INTERVAL 19 HOUR + INTERVAL 45 MINUTE),
(5, 24000,  'CASH',  'PAID', CURDATE() - INTERVAL 1 DAY + INTERVAL 12 HOUR + INTERVAL 40 MINUTE);

INSERT INTO audit_log (user_id, action, entity_type, entity_id, details) VALUES
(1, 'LOGIN',               'USER',         1, 'Admin login'),
(2, 'CREATE_RESERVATION',  'RESERVATION',  1, 'Reservasi meja T03 untuk 4 orang'),
(3, 'CREATE_RESERVATION',  'RESERVATION',  2, 'Reservasi meja T05 untuk 5 orang'),
(4, 'CANCEL_RESERVATION',  'RESERVATION',  3, 'Pembatalan oleh pelanggan'),
(5, 'UPDATE_ORDER_STATUS', 'ORDER_ITEM',   9, 'Status order item #9 menjadi READY'),
(1, 'RECEIVE_PAYMENT',     'PAYMENT',      1, 'Pembayaran DEBIT Rp258.000 untuk reservasi #2');
