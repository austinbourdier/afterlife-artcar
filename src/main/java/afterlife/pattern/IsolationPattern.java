package afterlife.pattern;
import heronarts.lx.pattern.LXPattern;
import heronarts.lx.LXCategory;

import heronarts.lx.LX;

@LXCategory("SmallCloud")
public abstract class IsolationPattern extends LXPattern {
  public IsolationPattern(LX lx, String t) {
    super(lx);
    tag = t;
  }

  private String tag;

}
