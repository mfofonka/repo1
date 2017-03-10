package hello;

public interface GreetingRepository {

    Greeting getGreeting(String name);

    Greeting cacheRemove(String name);
}
