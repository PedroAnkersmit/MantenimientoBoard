Feature: Buscar anuncio


  Scenario: Buscar anuncio success
    Given hay un tablón de anuncios creado
    Given el tablón tiene al menos un anuncio del usuario
    When el usuario busca un anuncio suyo que está en el tablón
    Then el tablón encuentra el anuncio pedido
    And el sistema avisa al usuario de que se ha publicado el anuncio

  Scenario: Buscar anuncio fail
    Given hay un tablón de anuncios creado
    And el usuario no es THE Company
    And el tablón no tiene un anuncio del usuario
    When el usuario busca un anuncio suyo
    Then no se encuentra el anuncio
    And el sistema avisa el usuario de que no se ha podido encontrar el anuncio

  Scenario: Buscar anuncio fail (No hay tablón)
    Given no hay ningún tablón creado
    When el usuario busca un anuncio suyo
    Then el sistema avisa al usuario de que no hay ningún tablón creado
