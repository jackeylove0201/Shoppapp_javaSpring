CREATE DATABASE shopapp;
USE shopapp;
-- Khách hàng muốn mua hàng phải đăng ký tài khoản -> bảng users;
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) NOT NULL DEFAULT '',
    phone_number VARCHAR(10) NOT NULL DEFAULT '',
    address VARCHAR(200) NOT NULL DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '',
    create_at DATETIME,
    update_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);
-- Bảng token - Khách hàng đc cấp token để đăng nhập và thực hiện mọi thao tác khi có token
CREATE TABLE tokens(
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expired_at DATETIME,
    revoked_at TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Hỗ trợ đăng nhập facebook và google
CREATE TABLE social_accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'facebook, google',
    provider_id VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL COMMENT 'email từ mạng xã hội',
    name VARCHAR(100) NOT NULL COMMENT 'tên người dùng từ mạng xã hội',
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);