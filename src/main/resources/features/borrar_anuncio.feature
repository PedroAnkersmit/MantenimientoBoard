Feature: Borrar anuncio


  Scenario: Borrar anuncio success
    Given hay un tablón de anuncios creado
    And el tablón tiene al menos un anuncio del usuario
    When el usuario borra un anuncio suyo que está en el tablón
    Then el anuncio es borrado del tablón
    And el numero de anuncios publicados disminuye
    And el sistema avisa al usuario de que se ha borrado el anuncio

  Scenario: Borrar anuncio THE Company
    Given hay un tablón de anuncios creado
    And hay al menos un anuncio en el tablón
    And el usuario es THE Company
    When el usuario borra un anuncio cualquiera
    Then el anuncio es borrado del tablón
    And el numero de anuncios publicados disminuye
    And el sistema avisa al usuario de que se ha borrado el anuncio

  Scenario: Borrar anuncio fail (El anuncio no está en el tablón)
    Given hay un tablón de anuncios creado
    And el tablón tiene al menos un anuncio
    When el usuario borra un anuncio suyo que no está en el tablón
    Then el sistema avisa el usuario de que no se ha podido encontrar el anuncio
    And el numero de anuncios publicados no cambia

  Scenario: Borrar anuncio fail (No hay tablón creado)
    Given no hay ningún tablón creado
    When el usuario borra un anuncio suyo
    Then el sistema avisa al usuario de que no hay ningún tablón creado

  Scenario: Borrar anuncio fail (Intenta borrar un anuncio que no es suyo)
    Given hay un tablón de anuncios creado
    And el usuario no es THE Company
    When el usuario borra un anuncio que no es suyo
    Then el sistema avisa el usuario de que no se ha podido encontrar el anuncio
    And el numero de anuncios publicados no cambia
