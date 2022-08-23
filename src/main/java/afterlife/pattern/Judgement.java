package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.LX;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import afterlife.util.*;
import afterlife.midi.AfterlifeMidi;
import java.util.Random;


@LXCategory("AllClouds")
public class Judgement extends IsolationPattern {
  private List<Integer> cloudProgress;
  private List<Integer> incrementIntervals;
  private int currentIncrementPeriod;
  private int cummulativeTimePassed;
  private int cummulativeTimePassedSinceLastIncrement;
  private int cummulativeTimePassedSinceLastAnimationUpdate;
  private boolean isAnimating;
  private boolean isBecomingHeaven;

  private List<Integer> colorList;


  public Judgement(LX lx) {
    super(lx,"AllClouds");
    cummulativeTimePassedSinceLastIncrement = 0;
    cummulativeTimePassedSinceLastAnimationUpdate = 0;
    currentIncrementPeriod = 0;
    cummulativeTimePassed = 0;
    incrementIntervals = Arrays.asList(5000, 5000, 5000, 5000, 5000, 5000, 3000);
    cloudProgress = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    colorList = Arrays.asList(Colors.WHITE, Colors.RED);
    isAnimating = false;
    isBecomingHeaven = true;
  }

  public void run(double deltaMs) {
      cummulativeTimePassedSinceLastIncrement += deltaMs;
      cummulativeTimePassedSinceLastAnimationUpdate += deltaMs;
      cummulativeTimePassed += deltaMs;
      if (incrementIntervals.size() > currentIncrementPeriod && cummulativeTimePassedSinceLastIncrement > incrementIntervals.get(currentIncrementPeriod)) {
          cummulativeTimePassedSinceLastIncrement = 0;
          currentIncrementPeriod += 1;
          int color = colorList.get(isBecomingHeaven ? 0 : 1);
              for (LXModel cloud : model.children) {
                  setColor(cloud, color);
              }
              isBecomingHeaven = !isBecomingHeaven;
      }
  }
}