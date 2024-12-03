-- db/migration/V1__init_tables.sql
CREATE TABLE "user" (
    id_user SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    role VARCHAR(15) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO "user" (username, email, password, phone_number, role)
VALUES
    ('mareanexx', 'mareanexx@mail.ru', '$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu', '0987654321', 'ADMIN');
