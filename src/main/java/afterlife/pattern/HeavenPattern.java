package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;

import java.awt.Color;

@LXCategory("AllClouds")
public class HeavenPattern extends IsolationPattern {
  public HeavenPattern(LX lx) {
    super(lx,"AllClouds");
  }

  public void run(double deltaMs) {
      for (LXModel strip : model.children) {
          setColor(strip, Integer.valueOf(Color.WHITE.getRGB()));
      }
  }
}