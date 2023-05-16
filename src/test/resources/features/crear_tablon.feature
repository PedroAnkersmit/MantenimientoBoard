Feature: Crear tablon


  Scenario: Crear tablon success
    Given no hay ningún tablon creado
    When el usuario crea un tablon
    Then se crea un tablon
    And se publica un anuncio predeterminado en el tablon.
    And el numero de anuncios publicados es 1
    And el sistema avisa al usuario de que se ha creado el tablon correctamente

  Scenario: Create tablon fail (Ya existe un tablon)
    Given hay un tablon de anuncios creado
    When el usuario crea un tablon
    Then el tablon no es creado
    And el sistema avisa al usuario de que no se ha podido crear el tablon


