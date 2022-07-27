package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;
import afterlife.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@LXCategory("AllClouds")
public class RandomCloudColorsAlternate extends IsolationPattern {
  private List<Integer> colorList;
  public RandomCloudColorsAlternate(LX lx) {
    super(lx,"AllClouds");
    this.colorList = Arrays.asList(Colors.WHITE, Colors.RED, Colors.BLUE, Colors.GREEN, Colors.MAGENTA, Colors.PINK, Colors.CYAN, Colors.YELLOW);
  }

  public void run(double deltaMs) {
      Random randCloud = new Random();
      LXModel randomCloud = model.children[randCloud.nextInt(model.children.length)];

      if (lx.engine.tempo.beat()) {
          for (LXModel cloud : model.children) {
              Random randColor = new Random();
              int randomColor = colorList.get(randColor.nextInt(colorList.size()));
              setColor(cloud, randomColor);
          }
      }
  }
}