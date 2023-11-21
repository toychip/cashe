package nice.cache.Controller;

import nice.cache.domain.Cache;
import nice.cache.domain.Exdata;
import nice.cache.view.OutputView;

public class CacheController {
    private final OutputView outputView;

    public CacheController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        Cache cache = new Cache();
        outputView.printPut();

        String key = "kettttt";
        cache.put(key, "3", "2023.11.20 16:41");
        Exdata person1 = new Exdata(24, "임준형");
        Exdata person2 = new Exdata(25, "김경민");
        cache.put(key, person1, person2);
        cache.getAll();

    }


}
