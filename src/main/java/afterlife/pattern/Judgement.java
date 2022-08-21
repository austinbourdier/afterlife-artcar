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
          isAnimating = true;
          cummulativeTimePassedSinceLastIncrement = 0;
          currentIncrementPeriod += 1;
      }
      if (isAnimating) {
        animate();
      }
      if (cummulativeTimePassed >= 35500 && cummulativeTimePassed < 51000) {

        if (lx.engine.tempo.beat()) {
              int color = colorList.get(isBecomingHeaven ? 0 : 1);
              for (LXModel cloud : model.children) {
                  setColor(cloud, color);
              }
              isBecomingHeaven = !isBecomingHeaven;
          }
      }
      if (cummulativeTimePassed > 51000 && cummulativeTimePassed < 57000) {
        for (LXModel cloud : model.children) {
                  setColor(cloud, Integer.valueOf(Color.TRANSLUCENT));
              }
      }
      if (cummulativeTimePassed > 57000) {
          if(AfterlifeMidi.hellCounter > AfterlifeMidi.heavenCounter) {
              for (LXModel cloud : model.children) {
                setColor(cloud, Integer.valueOf(Color.RED.getRGB()));
            }
          } else {
            for (LXModel cloud : model.children) {
                setColor(cloud, Integer.valueOf(Color.WHITE.getRGB()));
            }
          }
      }
  }

  private void animate () {
      if ((cummulativeTimePassedSinceLastAnimationUpdate > 50 && cummulativeTimePassed < 35500)) {
            for (int index = 0; index < model.children.length; index++) {
                LXModel cloud = model.children[index];
                int stripLength = cloud.tags.contains("SMALL") ? 30 : 60;
                if (cloudProgress.get(index) < stripLength - 1) {
                    if (isBecomingHeaven) {
                        for (LXModel strip : cloud.children) {
                            setColor(strip.getPoints().get(cloudProgress.get(index)), Integer.valueOf(Color.WHITE.getRGB()));
                        }
                    } else {
                        // turn to hell
                        for (LXModel strip : cloud.children) {
                            setColor(strip.getPoints().get(stripLength - 1 - cloudProgress.get(index)), Integer.valueOf(Color.RED.getRGB()));
                        }
                    }
                    cloudProgress.set(index, cloudProgress.get(index) + 1);
                } else {
                    cloudProgress.set(index, 0);
                    if (cloud.tags.contains("LARGE")) {
                        isAnimating = false;
                    }
                    if (!isAnimating && index == 0) {
                        isBecomingHeaven = !isBecomingHeaven;
                    }
                }
            }
            cummulativeTimePassedSinceLastAnimationUpdate = 0;
      }

  }
}