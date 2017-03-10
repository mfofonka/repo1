package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleGreetingRepository implements GreetingRepository {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Override
    @Cacheable("greetings")
    public Greeting getGreeting(String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @Override
    @CacheEvict(cacheNames = "greetings", key = "#name")
    public Greeting cacheRemove(String name) {
        return new Greeting();
    }

}
