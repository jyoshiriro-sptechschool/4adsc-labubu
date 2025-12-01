package sptech.projeto12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


//@EnableScheduling // Habilita o agendamento de tarefas
@EnableCaching // Habilita o cache
@SpringBootApplication
public class Projeto12Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto12Application.class, args);
	}

}
