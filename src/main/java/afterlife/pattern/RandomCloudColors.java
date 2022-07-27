package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;
import afterlife.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@LXCategory("AllClouds")
public class RandomCloudColors extends IsolationPattern {
  private List<Integer> colorList;
  public RandomCloudColors(LX lx) {
    super(lx,"AllClouds");
    this.colorList = Arrays.asList(Colors.WHITE, Colors.RED, Colors.BLUE, Colors.GREEN, Colors.MAGENTA, Colors.PINK, Colors.CYAN, Colors.YELLOW);
  }

  public void run(double deltaMs) {

      if (lx.engine.tempo.beat()) {
              Random rand = new Random();
              int randomColor = colorList.get(rand.nextInt(colorList.size()));
          for (LXModel strip : model.children) {
              setColor(strip, randomColor);
          }
      }
  }
}