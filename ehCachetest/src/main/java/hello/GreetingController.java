package hello;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sf.ehcache.Ehcache;

@RestController
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private EhCacheCacheManager cacheManager;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingRepository.getGreeting(name);
    }

    @RequestMapping("/allContent")
    @SuppressWarnings("unchecked")
    public Collection<String> allContent() {
        return ((Ehcache) cacheManager.getCache("greetings").getNativeCache()).getKeys();
    }

    @RequestMapping("/remove")
    public Greeting removeGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingRepository.cacheRemove(name);
    }
}