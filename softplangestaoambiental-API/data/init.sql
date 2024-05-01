CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    limite INT NOT NULL,
    saldo_inicial INT NOT NULL
);

CREATE TABLE IF NOT EXISTS transacoes (
    id SERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    valor INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    descricao VARCHAR(255),
    realizada_em TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

INSERT INTO cliente (limite, saldo_inicial)
VALUES
    (100000, 0),
    (80000, 0),
    (1000000, 0),
    (10000000, 0),
    (500000, 0);

--
--CREATE TABLE IF NOT EXISTS transacoes (
--	id SERIAL PRIMARY KEY,
--    id_cliente INT,
--    valor INT DEFAULT 0 NOT NULL,
--	tipo int NOT NULL,
--	descricao VARCHAR(10) NOT NULL,
--	realizada_em TIMESTAMP NOT NULL,
--	CONSTRAINT fk_cliente_transacoes_id
--		FOREIGN KEY (id_cliente) REFERENCES cliente(id)
--);

--DO $$
--BEGIN
--INSERT INTO cliente (limite, saldo_inicial)
--VALUES
--    ('diablo', 100000, 0),
--    ('baldurs gate', 80000, 0),
--    ('world of warcraft', 1000000, 0),
--    ('pokemon', 10000000, 0),
--    ('magic', 500000, 0);

--END;
--$$;
--INSERT INTO cliente (limite,saldo_inicial) VALUES (100000,0);
--INSERT INTO cliente (limite,saldo_inicial) VALUES (80000,0);
--INSERT INTO cliente (limite,saldo_inicial) VALUES (1000000,0);
--INSERT INTO cliente (limite,saldo_inicial) VALUES (10000000,0);
--INSERT INTO cliente (limite,saldo_inicial) VALUES (500000,0);