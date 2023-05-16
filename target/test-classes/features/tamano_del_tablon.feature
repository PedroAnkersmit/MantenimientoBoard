Feature: Tamaño del Tablon


  Scenario: GetSize success
    Given hay un tablon de anuncios creado
    When el usuario pide saber el número de anuncios publicados en el tablon
    Then el sistema le dice cuantos anuncios hay publicados en ese tablon

  Scenario: GetSize fail
    Given no hay ningún tablon creado
    When el usuario pide saber el número de anuncios publicados en el tablon
    Then el sistema avisa al usuario de que no hay ningún tablon creado
