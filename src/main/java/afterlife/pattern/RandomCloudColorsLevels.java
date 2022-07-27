package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;
import afterlife.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@LXCategory("AllClouds")
public class RandomCloudColorsLevels extends IsolationPattern {
  private List<Integer> colorList;
  private List<String> levelList;
  public RandomCloudColorsLevels(LX lx) {
    super(lx,"AllClouds");
    this.colorList = Arrays.asList(Colors.WHITE, Colors.RED, Colors.BLUE, Colors.GREEN, Colors.MAGENTA, Colors.PINK, Colors.CYAN, Colors.YELLOW);
    this.levelList = Arrays.asList("LEVEL_ONE", "LEVEL_TWO", "LEVEL_THREE");
  }

  public void run(double deltaMs) {
      Random randLevel = new Random();
      String randomLevel = levelList.get(randLevel.nextInt(3));

      if (lx.engine.tempo.beat()) {
              Random randColor = new Random();
              int randomColor = colorList.get(randColor.nextInt(colorList.size()));
          for (LXModel cloud : model.sub(randomLevel)) {
              setColor(cloud, randomColor);
          }
      }
  }
}