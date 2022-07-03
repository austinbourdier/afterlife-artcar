package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.model.LXPoint;
import afterlife.pattern.AfterlifeBasePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PixelblazePatternParallel extends AfterlifeBasePattern {
  public static final int N_THREADS = 4;
  private ArrayList<Wrapper> wrappers = new ArrayList<>();

  ExecutorService es = Executors.newFixedThreadPool(N_THREADS, new ThreadFactory() {
    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r);
      t.setDaemon(true);
      t.setPriority(Thread.MIN_PRIORITY);
      return t;
    }
  });

  public PixelblazePatternParallel(LX lx) {
    super(lx);
  }

  public void runAfterlifeBasePattern(double deltaMs) {
    if (wrappers.size() == 0)
      return;
    try {
      ArrayList<Future<Void>> futures = new ArrayList<>(wrappers.size());
      for (Wrapper wrapper : wrappers) {
        futures.add(es.submit((Callable<Void>) () -> {
            wrapper.reloadIfNecessary();
            wrapper.render(deltaMs);
            return null;
        }));
      }

      for (Future<Void> future : futures) {
        future.get(); //block until done
      }
    } catch (Exception e) {
      e.printStackTrace();
      LX.error(e);
      return;
    }

  }
}
