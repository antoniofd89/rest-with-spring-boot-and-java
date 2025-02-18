package br.com.dias.rest_with_spring_boot_and_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// config do spring
// habilita a configuração automática.
// ativa a varredura de componentes no pacote e sub pacotes onde está localizada.
@SpringBootApplication
public class Startup {

	public static void main(String[] args) {

		SpringApplication.run(Startup.class, args);
	}

}
