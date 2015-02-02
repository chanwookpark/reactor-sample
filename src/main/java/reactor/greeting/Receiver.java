package reactor.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.event.Event;
import reactor.function.Consumer;

import java.util.concurrent.CountDownLatch;

/**
 * Created by chanwook on 2015. 1. 22..
 */
@Service
public class Receiver implements Consumer<Event<Integer>> {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    CountDownLatch latch;

    @Override
    public void accept(Event<Integer> event) {
        QuoteResource quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);
        System.out.println("Quote " + event.getData() + ": " + quote.getValue().getQuote() +
                "[thread: " + Thread.currentThread().getName() + "]");
        latch.countDown();
    }
}
