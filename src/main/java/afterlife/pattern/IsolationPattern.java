package afterlife.pattern;
import heronarts.lx.pattern.LXPattern;
import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;

public abstract class IsolationPattern extends LXPattern {
  public IsolationPattern(LX lx, String t) {
    super(lx);
    tag = t;
  }
  private String tag;
  public void run(double deltaMs) {
    for (LXModel strip : model.sub(tag)) {
      setColor(strip, 0xffffffff);
    }
  }
}
