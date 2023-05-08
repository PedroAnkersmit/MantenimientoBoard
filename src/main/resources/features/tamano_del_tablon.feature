Feature: Tamaño del Tablón


  Scenario: GetSize success
    Given hay un tablón de anuncios creado
    When el usuario pide saber el número de anuncios publicados en el tablón
    Then el sistema le dice cuantos anuncios hay publicados en ese tablón

  Scenario: GetSize fail
    Given no hay ningún tablón creado
    When el usuario pide saber el número de anuncios publicados en el tablón
    Then el sistema avisa al usuario de que no hay ningún tablón creado
