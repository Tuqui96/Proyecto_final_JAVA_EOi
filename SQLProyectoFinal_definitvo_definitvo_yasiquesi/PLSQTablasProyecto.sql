DROP TABLE RESERVAS;
DROP TABLE HABITACION;
DROP TABLE HOTEL;
DROP TABLE USUARIOS;
--Tablas
CREATE TABLE usuarios (
    id_user          NUMBER,
    dni              VARCHAR2(9) NOT NULL,
    nombreuser       VARCHAR2(100) NOT NULL,
    apellidos        VARCHAR2(200) NOT NULL,
    fecha_nacimiento DATE,
    contrasenya      VARCHAR2(50) NOT NULL,
    telefono         VARCHAR2(20) NOT NULL,
    email            VARCHAR(50),
    rol              NUMBER NOT NULL,
    CONSTRAINT pk_id_user PRIMARY KEY ( id_user ),
    UNIQUE ( dni )
);

CREATE TABLE hotel (
    id_hotel    NUMBER,
    nombrehotel VARCHAR2(100) NOT NULL,
    ubicacion   VARCHAR2(100) NOT NULL,
    capacidad   NUMBER NOT NULL,
    descripcion VARCHAR2(200),
    CONSTRAINT pk_id_hotel PRIMARY KEY ( id_hotel )
);

CREATE TABLE habitacion (
    id_hotel          NUMBER,
    id_habitacion     NUMBER,
    estado_habitacion VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_id_habitacion PRIMARY KEY ( id_habitacion ),
    CONSTRAINT fk_id_habitacion_hotel FOREIGN KEY ( id_hotel )
        REFERENCES hotel ( id_hotel )
);

CREATE TABLE reservas (
    id_reserva     NUMBER,
    id_cliente     NUMBER,
    id_hotel       NUMBER,
    id_habitacion  NUMBER,
    fecha_entrada  DATE NOT NULL,
    fecha_salida   DATE NOT NULL,
    estado_reserva VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_id_reserva PRIMARY KEY ( id_reserva ),
    CONSTRAINT fk_id_cliente FOREIGN KEY ( id_cliente )
        REFERENCES usuarios ( id_user ),
    CONSTRAINT fk_id_hotel FOREIGN KEY ( id_hotel )
        REFERENCES hotel ( id_hotel ),
    CONSTRAINT fk_id_habitacion FOREIGN KEY ( id_habitacion )
        REFERENCES habitacion ( id_habitacion )
);
 
BEGIN
crear_sqs;
crear_trigger_habitacion_ocupada;
crear_trigger_actualizar_estado;
INSERT INTO hotel VALUES (
        1,
        'Suites del Mar',
        'Alicante',
        10,
        'Las Suites del Mar by Meliá forman parte del complejo Spa Porta Maris Hotel.'
    );
    FOR i IN 1..10 LOOP
        INSERT INTO habitacion VALUES (
            1,
            i,
            'Disponible'
        );

    END LOOP;

    INSERT INTO usuarios VALUES (
        sqs_id_usuario.nextVal,
        '00000000A',
        'admin',
        'admin',
        TO_DATE('12/05/2023','dd/mm/yy'),
        'adminpass',
        '123456789',
        'admin@correo.com',
        9
    );
     DBMS_OUTPUT.PUT_LINE('Los datos se han insertado correctamente.');
      COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLCODE || ' - ' || SQLERRM);
        RAISE;
END;
  