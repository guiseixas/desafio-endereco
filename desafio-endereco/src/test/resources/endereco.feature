Feature: Consulta Endereço

  Scenario: Consulta endereço com CEP válido
    Given um endereço com o CEP "01001-000"
    When o usuário consulta o endereço
    Then o serviço retorna o endereço

  Scenario: Consulta endereço com CEP inválido
    Given um endereço com o CEP "12345"
    When o usuário consulta o endereço
    Then o serviço retorna erro
