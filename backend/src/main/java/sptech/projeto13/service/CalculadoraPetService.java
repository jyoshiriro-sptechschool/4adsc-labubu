package sptech.projeto13.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalculadoraPetService {

    /* Fórmula Atualizada:
    Até 1kg: 2% do peso corporal em gramas
    + de 1kg a 5kg: 1.5% do peso corporal em gramas
    Acima de 5kg: 1% do peso corporal em gramas
     */
    public Double getRacaoDiaGramasCaes(Double pesoGramas) {
        if (pesoGramas <= 0) {
            throw new IllegalArgumentException("Peso inválido");
        }
        return pesoGramas * 0.02;
    }

    /*
    Usando teste parametrizado quando necesspario,
    implemente cenários de teste para situações corretas
    e de erro para o método abaixo.
    Ele deve implementar: Valor do cachorro por meses
    0 de 6 meses: 6000.00
    7 a 12 meses: 4000.00
    Mais de 12 meses: 2000.00
    Entrada data de nascimento (LocalDate) do cão
    Validação: data futura é inválida (só aceita até hoje)
     */
    public Double getValorCachorro(LocalDate nascimento) {
        LocalDate hoje = LocalDate.now();

        if (nascimento.isAfter(hoje)) {
            throw new IllegalArgumentException(
                    "Data de nascimento inválida");
        }

        long meses =
                ChronoUnit.MONTHS.between(nascimento, hoje);

        /*
        com Period:
        Period periodo = Period.between(nascimento, hoje);
        int meses = periodo.getDays()/30 +
                periodo.getMonths() + periodo.getYears()*12;*/
        /*
        Com Period:
        entre 2010-01-01 e 2009-02-01 =
        1 mes / 1 ano / 30 dias
         */

        if (meses <= 6) {
            return 6000.0;
        } else if (meses <= 12) {
            return 4000.0;
        }
        return 2000.0;
    }
}
