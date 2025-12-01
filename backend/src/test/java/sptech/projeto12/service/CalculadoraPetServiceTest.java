package sptech.projeto12.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Testes unitários p/ CalculadoraPetService
class CalculadoraPetServiceTest {

    // obejto da classe a ser testada que será usado por todos os testes
    CalculadoraPetService service = new CalculadoraPetService();

    @Test // configura que é um método de teste (sem isso é um método normal e não pode ser executado pelo JUnit)
    @DisplayName("Deve calcular a quantidade diária de ração para cães em gramas") // descrição do teste
    void getRacaoDiaGramasCaes() {
        // Parte 1: preparação dos dados de entrada e saída
        Double pesoGramas = 1000.0; // 1 kg
        Double esperado = 20.0; // 2% de 1000g

        // Parte 2: execução do método a ser testado
        Double resultado = service
                           .getRacaoDiaGramasCaes(pesoGramas);

        // Parte 3: validação do resultado
        assertEquals(esperado, resultado);
        // esse método compara o valor esperado com o valor retornado
    }

    @Test
    @DisplayName("Não deve calcular p/ quant inválida")
    void getRacaoDiaGramasCaesInvalido() {
        Double pesoGramas = -900.0; // peso inválido

        // valida que o método lança a exceção esperada
        // se não lançar a exceção esperada, o teste falha
        assertThrows(IllegalArgumentException.class, () ->
            service.getRacaoDiaGramasCaes(pesoGramas)
        );
    }

    @ParameterizedTest // teste parametrizado (ou seja, com vários dados)
    /* usando CSV (valores separados por vírgula)
        para fornecer os dados de entrada e saída.
        Cada linha é um conjunto de dados:
        1º valor: peso em gramas (entrada)
        2º valor: ração esperada em gramas (saída)

        Os valores são passados como parâmetros do método de teste
     */
    @CsvSource(value = {
        "100.0, 2.0",
        "1000.0, 20.0",
        "1000.01, 15.00015",
        "5000.0, 75.0",
        "5000.01, 50.0001",
        "10000.0, 100.0"
    })
    @DisplayName("Deve calcular correto p/ todos os pesos")
    void getRacaoDiaTodosPesos(
    // o primeiro parâmetro (pesoGramas) é o primeiro valor em casa linha do @CSVSource
    // o segundo parâmetro (racaoEsperada) é o segundo valor em cada linha do @CSVSource
        Double pesoGramas, Double racaoEsperada
    ) {
        // este método será executado 6 (seis) vezes, uma para cada linha do @CsvSource
        double resultado = service.getRacaoDiaGramasCaes
                                    (pesoGramas);
        assertEquals(racaoEsperada, resultado);
    }

    @Test
    @DisplayName("Não deve calcular preço p/ data futura")
    void getValorCachorroDataFutura() {
        LocalDate dataFutura = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
            service.getValorCachorro(dataFutura)
        );
    }

    @ParameterizedTest
    // Como o primeiro valor é uma data no formato ISO, o JUnit converte a String para LocalDate automaticamente
    @CsvSource(value = {
        "2025-10-01, 6000.0", // < 6 meses
        "2025-02-01, 4000.0", // entre 7 e 12 meses
        "2020-01-01, 2000.0"  // > 12 meses
    })
    @DisplayName("Deve calcular valor correto p/ idades")
    void getValorCachorroIdades(
            LocalDate nascimento, Double valorEsperado
    ) {
        var resultado = service.getValorCachorro(nascimento);

        assertEquals(valorEsperado, resultado);
    }

}