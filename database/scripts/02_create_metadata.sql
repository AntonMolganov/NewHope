CREATE TABLE newhope.clients (
    client_id   NUMBER NOT NULL,
    s_name      VARCHAR2(300 CHAR),
    s_phone     VARCHAR2(30 CHAR),
    s_email     VARCHAR2(300 CHAR)
);

COMMENT ON TABLE newhope.clients IS
    'Клиенты';

COMMENT ON COLUMN newhope.clients.client_id IS
    'ИД клиента';

COMMENT ON COLUMN newhope.clients.s_name IS
    'имя/никнейм';

COMMENT ON COLUMN newhope.clients.s_phone IS
    'телефон';

COMMENT ON COLUMN newhope.clients.s_email IS
    'e-mail';

ALTER TABLE newhope.clients ADD CONSTRAINT pk_clients PRIMARY KEY ( client_id );
-----------------------------------------------------
CREATE TABLE newhope.estimate_types (
    estimate_type_id   NUMBER NOT NULL,
    s_name             VARCHAR2(100 CHAR)
);

COMMENT ON TABLE newhope.estimate_types IS
    'Виды оценок (что оцениваем)';

COMMENT ON COLUMN newhope.estimate_types.estimate_type_id IS
    'ИД вида оценки';

COMMENT ON COLUMN newhope.estimate_types.s_name IS
    'Наименование вида оценки (что оцениваем)';

ALTER TABLE newhope.estimate_types ADD CONSTRAINT pk_estimate_types PRIMARY KEY ( estimate_type_id );
-----------------------------------------------------
CREATE TABLE newhope.estimates (
    estimate_id        NUMBER NOT NULL,
    estimate_type_id   NUMBER NOT NULL,
    trip_id            NUMBER NOT NULL,
    i_value            NUMBER,
    s_comment          VARCHAR2(4000 CHAR)
);

COMMENT ON TABLE newhope.estimates IS
    'Оценки поездок';

COMMENT ON COLUMN newhope.estimates.estimate_id IS
    'ИД оценки';

COMMENT ON COLUMN newhope.estimates.estimate_type_id IS
    'Что оцениваем';

COMMENT ON COLUMN newhope.estimates.trip_id IS
    'ИД поездки, к которой относится данная оценка';

COMMENT ON COLUMN newhope.estimates.i_value IS
    'Значение оценки';

COMMENT ON COLUMN newhope.estimates.s_comment IS
    'Комментарий к оценке';

CREATE INDEX newhope.i1_estimates$type ON
    newhope.estimates ( estimate_type_id ASC );

CREATE INDEX newhope.i2_estimates$trip ON
    newhope.estimates ( trip_id ASC );

ALTER TABLE newhope.estimates ADD CONSTRAINT pk_estimates PRIMARY KEY ( estimate_id );
-----------------------------------------------------
CREATE TABLE newhope.routes (
    route_id            NUMBER NOT NULL,
    s_name              VARCHAR2(100 CHAR),
    transport_type_id   NUMBER NOT NULL
);

COMMENT ON TABLE newhope.routes IS
    'Маршруты перевозчиков';

COMMENT ON COLUMN newhope.routes.route_id IS
    'Идентификатор маршрута';

COMMENT ON COLUMN newhope.routes.s_name IS
    'Номер/наименование маршрута';

COMMENT ON COLUMN newhope.routes.transport_type_id IS
    'ИД вида транспортного средства';

CREATE INDEX newhope.i1_routes$transptype ON
    newhope.routes ( transport_type_id ASC );

ALTER TABLE newhope.routes ADD CONSTRAINT pk_routes PRIMARY KEY ( route_id );
-----------------------------------------------------
CREATE TABLE newhope.shippers (
    shipper_id   NUMBER NOT NULL,
    s_name       VARCHAR2(1000 CHAR)
);

COMMENT ON TABLE newhope.shippers IS
    'Перевозчик';

COMMENT ON COLUMN newhope.shippers.shipper_id IS
    'ИД транспортного средства';

COMMENT ON COLUMN newhope.shippers.s_name IS
    'Наименование';

ALTER TABLE newhope.shippers ADD CONSTRAINT pk_shippers PRIMARY KEY ( shipper_id );
-----------------------------------------------------
CREATE TABLE newhope.transport_types (
    transport_type_id   NUMBER NOT NULL,
    s_name              VARCHAR2(100 CHAR)
);

COMMENT ON TABLE newhope.transport_types IS
    'Виды транспортных средств';

COMMENT ON COLUMN newhope.transport_types.transport_type_id IS
    'ИД вида транспортного средства';

COMMENT ON COLUMN newhope.transport_types.s_name IS
    'Наименование вида транспортного средства';

ALTER TABLE newhope.transport_types ADD CONSTRAINT pk_transport_types PRIMARY KEY ( transport_type_id );
-----------------------------------------------------
CREATE TABLE newhope.trips (
    trip_id       NUMBER NOT NULL,
    client_id     NUMBER NOT NULL,
    d_trip_date   DATE,
    route_id      NUMBER NOT NULL,
    d_update      TIMESTAMP,
    shipper_id    NUMBER NOT NULL
);

COMMENT ON TABLE newhope.trips IS
    'Поездки';

COMMENT ON COLUMN newhope.trips.trip_id IS
    'Ид поездки';

COMMENT ON COLUMN newhope.trips.client_id IS
    'ИД Клиента';

COMMENT ON COLUMN newhope.trips.d_trip_date IS
    'Дата поездки';

COMMENT ON COLUMN newhope.trips.route_id IS
    'Идентификатор маршрута по которому ехали';

COMMENT ON COLUMN newhope.trips.d_update IS
    'Дата модификации (системная)';

COMMENT ON COLUMN newhope.trips.shipper_id IS
    'ИД Перевозчика';

CREATE INDEX newhope.i1_trips$client ON
    newhope.trips ( client_id ASC );

CREATE INDEX newhope.i2_trips$route_id ON
    newhope.trips ( route_id ASC );

CREATE INDEX newhope.i3_trips$d_estimate ON
    newhope.trips ( d_trip_date ASC );

CREATE INDEX newhope.i4_trips$shipper ON
    newhope.trips ( shipper_id ASC );

ALTER TABLE newhope.trips ADD CONSTRAINT pk_trips PRIMARY KEY ( trip_id );

ALTER TABLE newhope.estimates
    ADD CONSTRAINT fk1_estimates$type FOREIGN KEY ( estimate_type_id )
        REFERENCES newhope.estimate_types ( estimate_type_id );

ALTER TABLE newhope.trips
    ADD CONSTRAINT fk1_trips$client FOREIGN KEY ( client_id )
        REFERENCES newhope.clients ( client_id );

ALTER TABLE newhope.estimates
    ADD CONSTRAINT fk2_estimates$trip FOREIGN KEY ( trip_id )
        REFERENCES newhope.trips ( trip_id );

ALTER TABLE newhope.routes
    ADD CONSTRAINT fk2_routes$transp_type FOREIGN KEY ( transport_type_id )
        REFERENCES newhope.transport_types ( transport_type_id );

ALTER TABLE newhope.trips
    ADD CONSTRAINT fk2_trips$route FOREIGN KEY ( route_id )
        REFERENCES newhope.routes ( route_id );

ALTER TABLE newhope.trips
    ADD CONSTRAINT fk3_trips$shipper FOREIGN KEY ( shipper_id )
        REFERENCES newhope.shippers ( shipper_id );
