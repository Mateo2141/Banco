

db.createCollection("usuarios", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["nombre", "password", "clienteId", "empleadoId"],
      properties: {
        nombre: {
          bsonType: "string"
        },
        password: {
          bsonType: "string"
        },
        clienteId: {
          bsonType: "objectId"
        },
        empleadoId: {
          bsonType: "objectId"
        }
      }
    }
  }
});


db.createCollection("clientes", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["persona", "cuentas"],
      properties: {
        persona: {
          bsonType: "string",
          enum: ["natural", "juridica"]
        },
        cuentas: {
          bsonType: "array",
          items: {
            bsonType: "objectId"
          }
        }
      }
    }
  }
});


db.createCollection("empleados", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["tipo", "oficinaId"],
      properties: {
        tipo: {
          bsonType: "string",
          enum: ["cajero", "gerente_oficina", "gerente_general"]
        },
        oficinaId: {
          bsonType: "objectId"
        }
      }
    }
  }
});


db.createCollection("oficinas", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["nombre", "numPuntosAtencion", "direccion", "horario", "puntosAtencion"],
      properties: {
        nombre: {
          bsonType: "string"
        },
        numPuntosAtencion: {
          bsonType: "int"
        },
        direccion: {
          bsonType: "string"
        },
        horario: {
          bsonType: "date"
        },
        puntosAtencion: {
          bsonType: "array",
          items: {
            bsonType: "object",
            required: ["ubicacionGeografica", "operacionesRealizadas", "tipo"],
            properties: {
              ubicacionGeografica: {
                bsonType: "string"
              },
              operacionesRealizadas: {
                bsonType: "int"
              },
              tipo: {
                bsonType: "string",
                enum: ["cajero_automatico", "AppWeb"]
              }
            }
          }
        }
      }
    }
  }
});


db.createCollection("cuentas", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["numCuenta", "tipo", "estado", "saldo", "fechaUltimaTransaccion", "fechaCreacion", "operaciones"],
      properties: {
        numCuenta: {
          bsonType: "int"
        },
        tipo: {
          bsonType: "string",
          enum: ["ahorro", "corriente"]
        },
        estado: {
          bsonType: "string",
          enum: ["activa", "cerrada", "desactivada"]
        },
        saldo: {
          bsonType: "double"
        },
        fechaUltimaTransaccion: {
          bsonType: "date"
        },
        fechaCreacion: {
          bsonType: "date"
        },
        operaciones: {
          bsonType: "array",
          items: {
            bsonType: "objectId"
          }
        }
      }
    }
  }
});

// Operaciones Bancarias
db.createCollection("operaciones_bancarias", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["monto", "fecha", "hora", "tipoOperacion", "cuentaId", "puntoAtencionId"],
      properties: {
        monto: {
          bsonType: "double"
        },
        fecha: {
          bsonType: "date"
        },
        hora: {
          bsonType: "string"
        },
        tipoOperacion: {
          bsonType: "string",
          enum: ["consignacion", "retiro", "transferencia", "crear", "cerrar"]
        },
        cuentaId: {
          bsonType: "objectId"
        },
        puntoAtencionId: {
          bsonType: "objectId"
        }
      }
    }
  }
});
