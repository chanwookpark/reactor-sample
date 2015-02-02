package reactor.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

import java.util.concurrent.CountDownLatch;

import static reactor.event.selector.Selectors.$;

/**
 * Created by chanwook on 2015. 1. 22..
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

    private static final int NUMBER_OF_QUOTES = 10;
    public static final String QUOTES_KEY = "quotes";

    @Bean
    public Environment env() {
        return new Environment();
    }

    @Bean
    public Reactor createReactor(Environment env) {
        return Reactors.reactor().env(env).dispatcher(Environment.THREAD_POOL).get();
    }

    @Autowired
    Reactor reactor;

    @Autowired
    Receiver receiver;

    @Autowired
    Publisher publisher;

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(NUMBER_OF_QUOTES);
    }

    @Override
    public void run(String... args) throws Exception {
        // QUOTES_KEY로 들어오는 요청을 receiver로 바인딩 receiver
        reactor.on($(QUOTES_KEY), receiver);
        publisher.publishQuotes(NUMBER_OF_QUOTES);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
