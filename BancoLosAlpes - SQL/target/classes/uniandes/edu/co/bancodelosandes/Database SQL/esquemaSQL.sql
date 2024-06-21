CREATE TABLE USUARIOS (
    TIPO_IDENTIFICACION VARCHAR2(2),
    NUM_IDENTIFICACION VARCHAR2(500) PRIMARY KEY,
    CONTRASENA VARCHAR2(255) NOT NULL,
    NOMBRE VARCHAR2(100) NOT NULL,
    APELLIDO VARCHAR2(100) NOT NULL,
    NACIONALIDAD VARCHAR2(60) NOT NULL,
    DIRECCION VARCHAR2(255) NOT NULL,
    EMAIL VARCHAR2(255) NOT NULL,
    TELEFONO VARCHAR2(500) NULL,
    CIUDAD VARCHAR2(100) NOT NULL,
    DEPARTAMENTO VARCHAR2(100) NOT NULL,
    CODIGO_POSTAL NUMBER NOT NULL,
    CHECK (TIPO_IDENTIFICACION IN ('CC', 'TI')),
    CHECK (REGEXP_LIKE(EMAIL, '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')),
    CHECK (CODIGO_POSTAL > 1000 AND CODIGO_POSTAL < 99999));


CREATE TABLE OFICINAS (
    NOMBRE VARCHAR2(100) PRIMARY KEY,
    NUM_PUNTOS_ATENCION NUMBER NOT NULL,
    DIRECCION VARCHAR2(255) NOT NULL,
    HORARIO VARCHAR2(100) NOT NULL,
    CHECK (NUM_PUNTOS_ATENCION > 0 AND NUM_PUNTOS_ATENCION < 50),
    CHECK (HORARIO IN ('LUNES_A_VIERNES', 'SABADO', 'FULL_TIME'))
);

CREATE TABLE EMPLEADOS (
    TIPO_EMPLEADO VARCHAR2(20),
    NUM_IDENTIFICACION VARCHAR2(500)  REFERENCES USUARIOS(NUM_IDENTIFICACION),
    OFICINA VARCHAR2(100) REFERENCES OFICINAS(NOMBRE),
    PRIMARY KEY (NUM_IDENTIFICACION), 
    CHECK (TIPO_EMPLEADO IN ('CAJERO', 'GERENTE_OFICINA', 'GERENTE_GENERAL'))
);

CREATE TABLE PUNTOSATENCION (
    UBICACION_GEOGRAFICA VARCHAR2(500) PRIMARY KEY,
    OPERACIONES_REALIZADAS NUMBER NOT NULL,
    TIPO_PUNTO_ATENCION VARCHAR2(50) NOT NULL,
    OFICINA_NAME VARCHAR2(100) REFERENCES OFICINAS(NOMBRE),
    CHECK (OPERACIONES_REALIZADAS >= 0),
    CHECK (TIPO_PUNTO_ATENCION IN ('CAJERO_AUTOMATICO', 'APPWEB'))
);

CREATE TABLE CLIENTES (
    TIPO_PERSONA VARCHAR2(20),
    NUM_IDENTIFICACION VARCHAR2(500) REFERENCES USUARIOS(NUM_IDENTIFICACION),
    PRIMARY KEY (NUM_IDENTIFICACION),
    UBICACION_GEOGRAFICA VARCHAR2(500) REFERENCES PUNTOSATENCION(UBICACION_GEOGRAFICA),
    CHECK (TIPO_PERSONA IN ('NATURAL', 'JURIDICA'))
);

CREATE TABLE CUENTAS (
    NUM_CUENTA NUMBER PRIMARY KEY,
    TIPO_CUENTA VARCHAR2(20) NOT NULL,
    ESTADO_CUENTA VARCHAR2(20) NOT NULL,
    SALDO NUMBER NOT NULL,
    FECHA_UTIMA_TRANSACCION DATE NOT NULL,
    FECHA_CREACION DATE NOT NULL,
    NUM_IDENTIFICACION VARCHAR2(500) REFERENCES CLIENTES(NUM_IDENTIFICACION),
    CHECK (TIPO_CUENTA IN ('AHORRO', 'CORRIENTE')),
    CHECK (ESTADO_CUENTA IN ('ACTIVA', 'DESACTIVADA', 'CERRADA')),
    CHECK (SALDO >= 0)
    );

CREATE TABLE PRESTAMOS (
    ID NUMBER PRIMARY KEY,
    TIPO_PRESTAMO VARCHAR2(20) NOT NULL,
    ESTADO_PRESTAMO VARCHAR2(20) NOT NULL,
    MONTO NUMBER NOT NULL,
    INTERES NUMBER NOT NULL,
    NUM_CUOTAS NUMBER NOT NULL,
    DIA_PAGO INTEGER NOT NULL,
    VALOR_CUOTA NUMBER NOT NULL,
    CHECK (TIPO_PRESTAMO IN ('VIVIENDA', 'ESTUDIO', 'AUTOMOVIL', 'LIBRE_INVERSION')),
    CHECK (ESTADO_PRESTAMO IN ('SOLICITADO', 'APROVADO', 'RECHAZADO', 'PAGADO')),
    CHECK (MONTO > 0),
    CHECK (INTERES >= 0),
    CHECK (NUM_CUOTAS > 0 AND NUM_CUOTAS <= 72),
    CHECK (DIA_PAGO > 0 AND DIA_PAGO <= 31),
    CHECK (VALOR_CUOTA > 0)  
);

CREATE TABLE OPERACIONESBANCARIAS (
    ID INTEGER PRIMARY KEY,
    MONTO NUMBER NOT NULL,
    FECHA DATE NOT NULL,
    HORA TIMESTAMP NOT NULL,
    UBICACION_GEOGRAFICA VARCHAR2(500) REFERENCES PUNTOSATENCION(UBICACION_GEOGRAFICA)
);

CREATE TABLE OPERACIONESCUENTA (
    ID INTEGER PRIMARY KEY,
    TIPO_OPERACION_CUENTA VARCHAR2(50) NOT NULL,
    NUM_CUENTA NUMBER REFERENCES CUENTAS(NUM_CUENTA),
    OPERACION_BANCARIA NUMBER REFERENCES OPERACIONESBANCARIAS(ID),
    CHECK (TIPO_OPERACION_CUENTA IN ('RETIRO', 'CONSIGNACION', 'TRANSFERENCIA', 'CREAR', 'CERRAR'))
);

CREATE TABLE OPERACIONESPRESTAMO (
    ID INTEGER PRIMARY KEY,
    TIPO_OPERACION VARCHAR2(50) NOT NULL,
    PRESTAMO NUMBER REFERENCES PRESTAMOS(ID),
    OPERACION_BANCARIA NUMBER REFERENCES OPERACIONESBANCARIAS(ID),
    CHECK (TIPO_OPERACION IN ('PAGO_EXTRAORDINARIO', 'PAGO_ORDINARIO'))
);




DECLARE
     CURSOR c IS SELECT table_name FROM user_tables;
BEGIN
     FOR i IN c LOOP
         BEGIN
             EXECUTE IMMEDIATE 'DROP TABLE ' || i.table_name || ' CASCADE CONSTRAINTS';
         EXCEPTION
             WHEN OTHERS THEN
                 IF SQLCODE = -942 THEN
                     NULL; -- Table does not exist, do nothing
                 ELSE
                     RAISE; -- Rethrow the exception
                 END IF;
             END;
         END LOOP;
END;

ALTER SESSION SET CURRENT_SCHEMA = ISIS2304B16202410;