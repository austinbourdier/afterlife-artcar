package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;
import afterlife.util.*;
import heronarts.lx.model.LXPoint;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@LXCategory("AllClouds")
public class RainbowRoad extends IsolationPattern {
  private List<Integer> colorList;
  private Integer offset;
  public RainbowRoad(LX lx) {
    super(lx,"AllClouds");
    this.colorList = Arrays.asList(Colors.RED, Colors.ORANGE, Colors.YELLOW, Colors.GREEN, Colors.BLUE, Colors.MAGENTA);
    this.offset = 0;
  }

  public void run(double deltaMs) {
      if (lx.engine.tempo.beat()) {
          for (LXModel cloud : model.children("LEVEL_THREE")) {
              for (LXModel strip : cloud.children("UPPER")) {
                  setColor(strip, colorList.get((1 + offset) % 6));
              }
              for (LXModel strip : cloud.children("LOWER")) {
                  setColor(strip, colorList.get((0 + offset) % 6));
              }
          } 
          for (LXModel cloud : model.children("LEVEL_TWO")) {
              for (LXModel strip : cloud.children("UPPER")) {
                  setColor(strip, colorList.get((3 + offset) % 6));
              }
              for (LXModel strip : cloud.children("LOWER")) {
                  setColor(strip, colorList.get((2 + offset) % 6));
              }
          }
          for (LXModel cloud : model.children("LEVEL_ONE")) {
              for (LXModel strip : cloud.children("UPPER")) {
                  setColor(strip, colorList.get((5 + offset) % 6));
              }
              for (LXModel strip : cloud.children("LOWER")) {
                  setColor(strip, colorList.get((4 + offset) % 6));
              }
          }
          offset++;
      }
  }
}