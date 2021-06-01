INSERT INTO usuario (email, senha, cadastrado_em) VALUES ('alex@gmail.com', '$2a$10$vVVCINXFUWJD78E/e1FRUeMJH9lmuBg3095VQiPx31.VoACi1HtLG', NOW());
INSERT INTO usuario (email, senha, cadastrado_em) VALUES ('tanjiro@gmail.com', '$2a$10$vVVCINXFUWJD78E/e1FRUeMJH9lmuBg3095VQiPx31.VoACi1HtLG', NOW());
INSERT INTO usuario (email, senha, cadastrado_em) VALUES ('rodrigo@gmail.com', '$2a$10$vVVCINXFUWJD78E/e1FRUeMJH9lmuBg3095VQiPx31.VoACi1HtLG', NOW());

INSERT INTO perfil (authority) VALUES ('ROLE_CLIENT');
INSERT INTO perfil (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuario_perfil(usuario_id, perfil_id) VALUES (1,2);
INSERT INTO tb_usuario_perfil(usuario_id, perfil_id) VALUES (2,1);
INSERT INTO tb_usuario_perfil(usuario_id, perfil_id) VALUES (3,1);
