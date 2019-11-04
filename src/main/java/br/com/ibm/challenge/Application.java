package br.com.ibm.challenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class Application {

    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Scheduled(fixedRate = MINUTO * 10)
	public void transferenciasAgendadas() {
        /**
         * @TODO implementar agendamento de transferencias.
         */
    }

}
