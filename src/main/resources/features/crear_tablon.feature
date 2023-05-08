Feature: Crear tablón


  Scenario: Crear tablón success
    Given no hay ningún tablón creado
    And el usuario es THE Company
    When el usuario crea un anuncio
    Then se crea un tablón
    And se publica el anuncio en el tablón.
    And el numero de anuncios publicados es 1
    And el sistema avisa al usuario de que se ha creado el tablón correctamente

  Scenario: Create tablón fail (Ya existe un tablón)
    Given hay un tablón de anuncios creado
    When el usuario crea un tablón
    Then el tablón no es creado
    And el sistema avisa al usuario de que no se ha podido crear el tablón

  Scenario: Crear tablón fail (No es THE Company)
    Given no hay ningún tablón creado
    And el usuario no es THE Company
    When el usuario crea un tablón
    Then el tablón no es creado
    And el sistema avisa al usuario de que no se ha podido crear el tablón
