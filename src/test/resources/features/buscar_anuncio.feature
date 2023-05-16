Feature: Buscar anuncio


  Scenario: Buscar anuncio success
    Given hay un tablon de anuncios creado
    And el tablon tiene al menos un anuncio del usuario
    When el usuario busca un anuncio suyo que está en el tablon
    Then el tablon encuentra el anuncio pedido
    And el sistema avisa al usuario de que se ha encontrado el anuncio

  Scenario: Buscar anuncio fail
    Given hay un tablon de anuncios creado
    And el usuario no es THE Company
    And el tablon no tiene un anuncio del usuario
    When el usuario busca un anuncio suyo
    Then no se encuentra el anuncio
    And el sistema avisa el usuario de que no se ha podido encontrar el anuncio

  Scenario: Buscar anuncio fail (No hay tablon)
    Given no hay ningún tablon creado
    When el usuario busca un anuncio suyo
    Then el sistema avisa al usuario de que no hay ningún tablon creado
