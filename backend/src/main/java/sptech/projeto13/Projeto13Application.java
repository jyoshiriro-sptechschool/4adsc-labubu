package sptech.projeto13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


//@EnableScheduling // Habilita o agendamento de tarefas
@EnableCaching // Habilita o cache
@SpringBootApplication
public class Projeto13Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto13Application.class, args);
	}

}
