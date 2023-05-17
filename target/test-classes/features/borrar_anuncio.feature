Feature: Borrar anuncio


  Scenario: Borrar anuncio success
    Given hay un tablon de anuncios creado
    And el tablon tiene al menos un anuncio del usuario
    When el usuario borra un anuncio suyo que está en el tablon
    Then el anuncio es borrado del tablon
    And el numero de anuncios publicados disminuye

  Scenario: Borrar anuncio THE Company
    Given hay un tablon de anuncios creado
    And hay al menos un anuncio en el tablon
    And el usuario es THE Company
    When el usuario borra un anuncio cualquiera
    Then el anuncio es borrado del tablon
    And el numero de anuncios publicados disminuye

  Scenario: Borrar anuncio fail (El anuncio no está en el tablon)
    Given hay un tablon de anuncios creado
    And hay al menos un anuncio en el tablon
    When el usuario borra un anuncio suyo que no está en el tablon
    And el numero de anuncios publicados no cambia

  Scenario: Borrar anuncio fail (No hay tablon creado)
    Given no hay ningún tablon creado
    When el usuario borra un anuncio suyo que no está en el tablon
    Then el sistema avisa al usuario de que no hay ningún tablon creado y no se puede borrar su anuncio

  Scenario: Borrar anuncio fail (Intenta borrar un anuncio que no es suyo)
    Given hay un tablon de anuncios creado
    And el usuario no es THE Company
    When el usuario borra un anuncio que no es suyo
    And el numero de anuncios publicados no cambia
