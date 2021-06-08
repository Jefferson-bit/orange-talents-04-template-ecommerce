INSERT INTO usuario (email, senha, cadastrado_em) VALUES ('alex@gmail.com', '$2a$10$vVVCINXFUWJD78E/e1FRUeMJH9lmuBg3095VQiPx31.VoACi1HtLG', NOW());
INSERT INTO usuario (email, senha, cadastrado_em) VALUES ('tanjiro@gmail.com', '$2a$10$vVVCINXFUWJD78E/e1FRUeMJH9lmuBg3095VQiPx31.VoACi1HtLG', NOW());
INSERT INTO usuario (email, senha, cadastrado_em) VALUES ('rodrigo@gmail.com', '$2a$10$vVVCINXFUWJD78E/e1FRUeMJH9lmuBg3095VQiPx31.VoACi1HtLG', NOW());

INSERT INTO perfil (authority) VALUES ('ROLE_CLIENT');
INSERT INTO perfil (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuario_perfil(usuario_id, perfil_id) VALUES (1,2);
INSERT INTO tb_usuario_perfil(usuario_id, perfil_id) VALUES (2,2);
INSERT INTO tb_usuario_perfil(usuario_id, perfil_id) VALUES (3,2);

INSERT INTO categoria (nome) VALUES ('Computadores');
INSERT INTO produto (nome, quantidade, descricao, valor, categoria_id, usuario_id, cadastrado_em)
VALUES ('Notebook Dell', 4, 'Excelente notebook', 50000, 1, 1, NOW());

INSERT INTO compra (produto_id, quantidade, usuario_id, carteira) VALUES
(1, 4, 1, 'PAG_SEGURO');

