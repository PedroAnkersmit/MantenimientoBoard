Feature: Publicar anuncio


  Scenario: Publicar anuncio success
    Given hay un tablon de anuncios creado
    And el usuario tiene suficiente saldo para hacer el pago
    When el usuario crea un anuncio
    Then se crea el anuncio.
    And se cobra al anunciante.
    And se publica el anuncio en el tablon.
    And crece el número de anuncios publicados en el tablon
    And el sistema avisa al usuario de que se ha publicado el anuncio

  Scenario: Publicar anuncio THE Company
    Given hay un tablon de anuncios creado
    And el usuario es THE Company
    When el usuario crea un anuncio
    Then se publica el anuncio en el tablon.
    And crece el número de anuncios publicados en el tablon
    And el sistema avisa al usuario de que se ha publicado el anuncio

  Scenario: Publicar anuncio falla
    Given hay un tablon de anuncios creado
    And el usuario no es THE Company
    And el usuario no tiene sufiente saldo
    When el usuario crea un anuncio
    Then su anuncio es rechazado
    And el numero de anuncios publicados no cambia
    And el sistema avisa al usuario de que no se ha publicado el anuncio

  Scenario: Publicar anuncio fail (El tablon está lleno)
    Given hay un tablon de anuncios creado
    And el numero de anuncios publicados es igual al numero máximo de anuncios permitidos en el tablon
    When el usuario crea un anuncio
    Then el sistema avisa el usuario de que no se ha podido agregar el anuncio
    And el numero de anuncios publicados no cambia
