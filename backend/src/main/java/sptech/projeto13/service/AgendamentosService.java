package sptech.projeto13.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentosService {

    @Scheduled(fixedRate = 3_000) // Executa a cada 3 segundos
    public void enviarCuponsAniversario() {
        System.out.println("Enviando cupons de aniversário...");
    }

    /*
    Método agendado para as 17h23 (depois mude o minuto)
       de toda 2a feira
     */
    @Scheduled(cron = "0 28 17 * * MON")
    public void gerarRelatorioSemanal() {
        System.out.println("Gerando relatório semanal...");
    }
}
