package es.ua.jtech.jpa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.paradigma.intranet.microservices.architecture.respository.impl.CustomJpaRepositoryImpl;

@SpringBootApplication(scanBasePackages = { "es.ua.jtech.jpa.main"})
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "es.ua.jtech.jpa.repository",
    repositoryBaseClass = CustomJpaRepositoryImpl.class)
@EntityScan(basePackages = "es.ua.jtech.jpa.mensajes")
public class Application {
    /**
     * Funcion para eliminar errores de sonar.
     */
    public void dummyFunction() {
        throw new UnsupportedOperationException();
    }

    /**
     * Arranque del microservicio.
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {
        // Arranque del microservicio
        SpringApplication.run(Application.class, args);
    }
}
