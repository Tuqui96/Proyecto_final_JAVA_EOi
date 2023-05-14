--TRIGGER PARA ACTUALIZAR EL ESTADO DE LA HABITACION A OCUPADA CUANDO SE ACEPTA LA VISITA

CREATE OR REPLACE PROCEDURE crear_trigger_habitacion_ocupada AS
BEGIN
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER habitacion_ocupada
        AFTER UPDATE OF estado_reserva ON reservas
        FOR EACH ROW
    BEGIN
        IF :new.estado_reserva = ''Aceptada'' THEN
            UPDATE habitacion
            SET estado_habitacion = ''Ocupada''
            WHERE id_habitacion = :new.id_habitacion;
        END IF;
    END;';
    
    dbms_output.put_line('El trigger habitacion_ocupada ha sido creado correctamente.');
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error: ' || sqlcode || ' - ' || sqlerrm);
        RAISE;
END;


