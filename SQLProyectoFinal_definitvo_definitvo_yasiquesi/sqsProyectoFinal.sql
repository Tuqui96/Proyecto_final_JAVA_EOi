--SECUENCIAS

CREATE OR REPLACE PROCEDURE crear_sqs AS
    seq_count NUMBER;
BEGIN
    -- Comprobar la existencia de la secuencia sqs_id_reserva
    SELECT COUNT(*)
    INTO seq_count
    FROM user_sequences
    WHERE sequence_name = 'SQS_ID_RESERVA';

    IF seq_count > 0 THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE sqs_id_reserva';
    END IF;

    -- Crear la secuencia sqs_id_reserva
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sqs_id_reserva MINVALUE 1 START WITH 1';

    -- Comprobar la existencia de la secuencia sqs_id_usuario
    SELECT COUNT(*)
    INTO seq_count
    FROM user_sequences
    WHERE sequence_name = 'SQS_ID_USUARIO';

    IF seq_count > 0 THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE sqs_id_usuario';
    END IF;

    -- Crear la secuencia sqs_id_usuario
    EXECUTE IMMEDIATE 'CREATE SEQUENCE sqs_id_usuario MINVALUE 1 START WITH 1';
END;
