Feature: Publicar anuncio


  Scenario: Publicar anuncio success
    Given hay un tablon de anuncios creado
    And el usuario tiene suficiente saldo para hacer el pago
    When el usuario crea un anuncio
    Then se cobra al anunciante.
    And se publica el anuncio en el tablon.
    And crece el número de anuncios publicados en el tablon


  Scenario: Publicar anuncio THE Company
    Given hay un tablon de anuncios creado
    And el usuario es THE Company
    When el usuario crea un anuncio
    Then se publica el anuncio en el tablon.
    And crece el número de anuncios publicados en el tablon


  Scenario: Publicar anuncio falla
    Given hay un tablon de anuncios creado
    And el usuario no es THE Company
    And el usuario no tiene sufiente saldo
    When el usuario crea un anuncio
    Then su anuncio es rechazado
    And el numero de anuncios publicados no cambia


  Scenario: Publicar anuncio fail (El tablon está lleno)
    Given hay un tablon de anuncios creado
    And el numero de anuncios publicados es igual al numero máximo de anuncios permitidos en el tablon
    When el usuario crea un anuncio
    And el numero de anuncios publicados no cambia
