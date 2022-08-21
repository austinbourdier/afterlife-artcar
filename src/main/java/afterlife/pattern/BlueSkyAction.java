package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.LX;
import afterlife.util.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;


@LXCategory("AllClouds")
public class BlueSkyAction extends IsolationPattern {
  private List<Integer> colorList;
  private List<Integer> cloudStatus;
  private List<Integer> cloudProgress;
  private int cummulativeTimePassed;
  public BlueSkyAction(LX lx) {
    super(lx,"AllClouds");
    cummulativeTimePassed = 0;
    cloudStatus = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    cloudProgress = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
  }

  public void run(double deltaMs) {
      cummulativeTimePassed += deltaMs;
          if (lx.engine.tempo.beat()) {
                for (int index = 0; index < model.children.length; index++) {
                  int rand = new Random().nextInt(2);

                  if (rand == 0) {
                      startAnimation(index);
                  }
              }
          }
          if (cummulativeTimePassed > 50) {
            animate();
            cummulativeTimePassed = 0;
          }
  }

  private void startAnimation (int index) {
      cloudStatus.set(index, 1);
  }
  private void animate () {

      for (int index = 0; index < model.children.length; index++) {
          if (cloudStatus.get(index) == 1) {
              LXModel cloud = model.children[index];
              int stripLength = cloud.tags.contains("SMALL") ? 30 : 60;
                  if (cloudProgress.get(index) >= (stripLength / 2) - 1) {
                      for (LXModel strip : cloud.children) {
                          setColor(strip, Integer.valueOf(Color.TRANSLUCENT));
                      }
                      cloudProgress.set(index, 0);
                      cloudStatus.set(index, 0);
                  } else {
                      for (LXModel strip : cloud.children) {
                          setColor(strip.getPoints().get((stripLength / 2) - 1 - cloudProgress.get(index)), Integer.valueOf(Color.WHITE.getRGB()));
                          setColor(strip.getPoints().get((stripLength / 2) + cloudProgress.get(index)), Integer.valueOf(Color.WHITE.getRGB()));
                      }
                    cloudProgress.set(index, cloudProgress.get(index) + 1);
                  }
              }

      }
  }
}