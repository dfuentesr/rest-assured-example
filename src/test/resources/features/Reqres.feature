#language:es
Característica: Rest-assured

  @TEST1
  Escenario: Listar usuarios
    Dado listo usuarios
    Entonces valido codigo de respuesta 200

  @TEST2
  Escenario: Crear usuarios
    Dado creo usuario
    Entonces valido codigo de respuesta 201

  @TEST2
  Escenario: Crear usuarios
    Dado creo usuario
    Entonces valido codigo de respuesta 201