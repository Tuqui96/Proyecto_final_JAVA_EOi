/*
CREATE OR REPLACE PROCEDURE crear_trigger_actualizar_estado AS
BEGIN
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER actualizar_estado_habitacion
        AFTER INSERT OR UPDATE ON reservas
        FOR EACH ROW
        DECLARE
            v_fecha_actual DATE;
        BEGIN
            v_fecha_actual := SYSDATE;
            IF :new.fecha_salida < v_fecha_actual AND :new.estado_reserva = ''Aceptada'' THEN
                UPDATE habitacion
                SET estado_habitacion = ''Disponible''
                WHERE id_habitacion = :new.id_habitacion;
            END IF;
        END;';
        
    dbms_output.put_line('El trigger actualizar_estado_habitacion se ha creado correctamente.');
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error: ' || SQLCODE || ' - ' || SQLERRM);
        RAISE;
END;
*/



