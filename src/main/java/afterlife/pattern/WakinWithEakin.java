package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.audio.GraphicMeter;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;
import afterlife.util.*;
import heronarts.lx.model.LXPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@LXCategory("AllClouds")
public class WakinWithEakin extends IsolationPattern {
  // CONTROLS - PROBABLY NEED TO TWEAK THESE AFTER TESTING ON THE CAR
  private double bassThreshold = .5;
  private double ultraBassThreshold = 1.4;

  private double midtoneThreshold = .6;

  private double trebleThreshold = .6;
  private double ultraTrebleThreshold = 1.3;


  // LEVEL ONE
  private final List<LXPoint> levelOneLeftPoints;
  private final List<LXPoint> levelOneRightPoints;
  private int[] levelOneLeftColors;
  private int[] levelOneRightColors;

  // LEVEL TWO
  private final List<LXPoint> levelTwoLeftPoints;
  private final List<LXPoint> levelTwoRightPoints;
  private int[] levelTwoLeftColors;
  private int[] levelTwoRightColors;

  // LEVEL THREE
  private final List<LXPoint> levelThreeLeftPoints;
  private final List<LXPoint> levelThreeRightPoints;
  private int[] levelThreeLeftColors;
  private int[] levelThreeRightColors;

  public WakinWithEakin(LX lx) {
    super(lx,"AllClouds");

    this.levelOneLeftPoints = new ArrayList<>();
    this.levelOneRightPoints = new ArrayList<>();

    this.levelTwoLeftPoints = new ArrayList<>();
    this.levelTwoRightPoints = new ArrayList<>();

    this.levelThreeLeftPoints = new ArrayList<>();
    this.levelThreeRightPoints = new ArrayList<>();

    getPoints("LEVEL_ONE");
    getPoints("LEVEL_TWO");
    getPoints("LEVEL_THREE");
  }

  // split list into left and right halves of the car
  private void getPoints(String level) {
    // z value of points gives which side of car
    int midpoint = (0 + 60) / 2;

    List<LXPoint> leftList;
    List<LXPoint> rightList;

    switch (level) {
      case "LEVEL_ONE" -> {
        leftList = levelOneLeftPoints;
        rightList = levelOneRightPoints;
      }
      case "LEVEL_TWO" -> {
        leftList = levelTwoLeftPoints;
        rightList = levelTwoRightPoints;
      }
      case "LEVEL_THREE" -> {
        leftList = levelThreeLeftPoints;
        rightList = levelThreeRightPoints;
      }
      default -> throw new Error("Incorrect level value!");
    }

    for (LXModel cloud : model.children(level)) {
      for (LXModel strip : cloud.children) {
        for (LXPoint point : strip.getPoints()) {
          if (point.z < midpoint) {
            leftList.add(point);
          } else if (point.z > midpoint) {
            rightList.add(point);
          }
        }
      }
    }

    leftList.sort((LXPoint a, LXPoint b) -> {
      // the front bumper cloud has x == 0, so go by z coordinates
      if(a.z > 10 || b.z > 10) {
        return a.z > b.z ? -1 : 1;
      }
      return a.x < b.x ? -1 : 1;
    });

    rightList.sort((LXPoint a, LXPoint b) -> {
      // the front bumper cloud has x == 0, so go by z coordinates
      if(a.z < 49 || b.z < 49) {
        return a.z < b.z ? -1 : 1;
      }
      return a.x < b.x ? -1 : 1;
    });

    switch (level) {
      case "LEVEL_ONE" -> {
        levelOneLeftColors = new int[leftList.size()];
        Arrays.fill(levelOneLeftColors, Colors.HELL_BASELINE);
        levelOneRightColors = new int[rightList.size()];
        Arrays.fill(levelOneRightColors, Colors.HELL_BASELINE);
      }
      case "LEVEL_TWO" -> {
        levelTwoLeftColors = new int[leftList.size()];
        Arrays.fill(levelTwoLeftColors, Colors.BLUE);
        levelTwoRightColors = new int[rightList.size()];
        Arrays.fill(levelTwoRightColors, Colors.BLUE);
      }
      case "LEVEL_THREE" -> {
        levelThreeLeftColors = new int[leftList.size()];
        Arrays.fill(levelThreeLeftColors, Colors.HEAVEN_BASELINE);
        levelThreeRightColors = new int[rightList.size()];
        Arrays.fill(levelThreeRightColors, Colors.HEAVEN_BASELINE);
      }
    }
  }

  private int[] ripple(int[] list, int firstValue) {
    int[] results = new int [list.length];
    results[0] = firstValue;

    if (list.length - 2 >= 0) System.arraycopy(list, 0, results, 1, list.length - 2);

    return results;
  }

  public void run(double deltaMs) {
    GraphicMeter meter = lx.engine.audio.meter;
    List<Double> meterBassValues = Arrays.asList(meter.bands[0].getValue(), meter.bands[1].getValue(), meter.bands[2].getValue(), meter.bands[3].getValue());
    List<Double> meterMidtoneValues = Arrays.asList(meter.bands[6].getValue(), meter.bands[7].getValue(), meter.bands[8].getValue(), meter.bands[9].getValue());
    List<Double> meterTrebleValues = Arrays.asList(meter.bands[12].getValue(), meter.bands[13].getValue(), meter.bands[14].getValue(), meter.bands[15].getValue());

    double highestBass = Collections.max(meterBassValues);
    double highestMidtone = Collections.max(meterMidtoneValues);
    double highestTreble = Collections.max(meterTrebleValues);

    // ALL ABOUT THE BASS!
    // y = 0 or 2
    int color = highestBass > bassThreshold ? Colors.HELL : Colors.HELL_BASELINE;

    levelOneLeftColors = ripple(levelOneLeftColors, color);
    levelOneRightColors = ripple(levelOneRightColors, color);

    for (int i = 0; i < levelOneLeftColors.length; i++) {
      // second level gets special treatment if it's loud!
      if (highestBass > ultraBassThreshold * bassThreshold && levelOneLeftPoints.get(i).y == 2) {
        setColor(levelOneLeftPoints.get(i), levelOneLeftColors[i] == Colors.HELL ? Colors.ULTRA_HELL : levelOneLeftColors[i]);
      } else {
        setColor(levelOneLeftPoints.get(i), levelOneLeftColors[i]);
      }
    }

    for (int i = 0; i < levelOneRightColors.length; i++) {
      // second level gets special treatment if it's loud!
      if (highestBass > ultraBassThreshold * bassThreshold && levelOneRightPoints.get(i).y == 2) {
        setColor(levelOneRightPoints.get(i), levelOneRightColors[i] == Colors.HELL ? Colors.ULTRA_HELL : levelOneRightColors[i]);
      } else {
        setColor(levelOneRightPoints.get(i), levelOneRightColors[i]);
      }
    }

    // MIDTOWN FUNK YOU UP
    color = highestMidtone > midtoneThreshold ? Colors.BLUE : Colors.NEUTRAL_GREEN;

    levelTwoLeftColors = ripple(levelTwoLeftColors, color);
    levelTwoRightColors = ripple(levelTwoRightColors, color);

    for (int i = 0; i < levelTwoLeftColors.length; i++) {
      setColor(levelTwoLeftPoints.get(i), levelTwoLeftColors[i]);
    }
    for (int i = 0; i < levelTwoRightColors.length; i++) {
      setColor(levelTwoRightPoints.get(i), levelTwoRightColors[i]);
    }

    // YOU'RE IN TREBLE NOW!
    // y == 80 or 82
    color = highestTreble > trebleThreshold ? Colors.HEAVEN : Colors.HEAVEN_BASELINE;

    levelThreeLeftColors = ripple(levelThreeLeftColors, color);
    levelThreeRightColors = ripple(levelThreeRightColors, color);

    for (int i = 0; i < levelThreeLeftColors.length; i++) {
      // first level gets special treatment if it's loud!
      if (highestTreble > ultraTrebleThreshold * trebleThreshold && levelThreeLeftPoints.get(i).y == 80) {
        setColor(levelThreeLeftPoints.get(i), levelThreeLeftColors[i] == Colors.HEAVEN ? Colors.ULTRA_HEAVEN : levelThreeLeftColors[i]);
      } else {
        setColor(levelThreeLeftPoints.get(i), levelThreeLeftColors[i]);
      }
    }

    for (int i = 0; i < levelThreeRightColors.length; i++) {
      // first level gets special treatment if it's loud!
      if (highestTreble > ultraTrebleThreshold * trebleThreshold && levelThreeRightPoints.get(i).y == 80) {
        setColor(levelThreeRightPoints.get(i), levelThreeRightColors[i] == Colors.HEAVEN ? Colors.ULTRA_HEAVEN : levelThreeRightColors[i]);
      } else {
        setColor(levelThreeRightPoints.get(i), levelThreeRightColors[i]);
      }
    }
  }
}
