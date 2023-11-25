INSERT INTO users (id, archive, email, name, password, role)
VALUES (1, false, 'mail@mail.ru', 'admin', '$2a$10$B76nc/SRel9TVrQPKfbM0.VCgvnSIJoaoejtv9OdF74xY/RDVENTW', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;