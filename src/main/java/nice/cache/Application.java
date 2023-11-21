package nice.cache;

import nice.cache.Controller.CacheController;
import nice.cache.view.OutputView;

public class Application {
    public static void main(String[] args) {
        CacheController cacheController = new CacheController(new OutputView());
        cacheController.run();
    }


}