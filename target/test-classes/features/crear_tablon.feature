Feature: Crear tablon


  Scenario: Crear tablon success
    Given no hay ningún tablon creado
    When el usuario crea un tablon
    Then se crea un tablon
    And se publica un anuncio predeterminado en el tablon.
    And el numero de anuncios publicados es 1



