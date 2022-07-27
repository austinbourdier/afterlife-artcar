package afterlife.pattern;

import heronarts.lx.LXCategory;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.LX;

import afterlife.util.*;
import java.util.ArrayList;

@LXCategory("AllClouds")
public class AlmostHome extends IsolationPattern {
  private ArrayList<Integer> currentRedIndexesLarge;
  private ArrayList<Integer> currentBlueIndexesLarge;
  private ArrayList<Integer> currentRedIndexesSmall;
  private ArrayList<Integer> currentBlueIndexesSmall;

  private int cumulativeTimePassedSinceIncrement;
  private int currentAdjustment;

  public AlmostHome(LX lx) {
    super(lx,"AllClouds");
      this.cumulativeTimePassedSinceIncrement = 0;
      this.currentAdjustment = 0;
      this.generateIndexes();
  }

  private void generateIndexes() {
      this.currentBlueIndexesSmall = new ArrayList<Integer>();
      this.currentRedIndexesSmall = new ArrayList<Integer>();
      this.currentBlueIndexesLarge = new ArrayList<Integer>();
      this.currentRedIndexesLarge = new ArrayList<Integer>();
      for (LXModel cloud : model.children) {
        if (cloud.tags.contains("LARGE")) {
          currentBlueIndexesLarge.add(cloud.getPoints().get(0 + (currentAdjustment % 60)).index);
          currentBlueIndexesLarge.add(cloud.getPoints().get(0 + (currentAdjustment % 60)).index + 60);
        }
        if (cloud.tags.contains("SMALL")) {
        currentBlueIndexesSmall.add(cloud.getPoints().get(0 + (currentAdjustment % 30)).index);
          currentBlueIndexesSmall.add(cloud.getPoints().get(0 + (currentAdjustment % 30)).index + 30);
        }
        if (cloud.tags.contains("LARGE")) {
          currentRedIndexesLarge.add(cloud.getPoints().get(59 - (currentAdjustment % 60)).index);
          currentRedIndexesLarge.add(cloud.getPoints().get(59 - (currentAdjustment % 60)).index + 60);
        }
        if (cloud.tags.contains("SMALL")) {
            currentRedIndexesSmall.add(cloud.getPoints().get(44 - (currentAdjustment % 30)).index);
            currentRedIndexesSmall.add(cloud.getPoints().get(44 - (currentAdjustment % 30)).index + 30);
        }
      }
  }

  public void run(double deltaMs) {
    this.cumulativeTimePassedSinceIncrement += deltaMs;

    if (this.cumulativeTimePassedSinceIncrement > 25) {

    ArrayList<Integer> initialBlueSmall = new ArrayList<Integer>();
    ArrayList<Integer> renderBlueSmall = new ArrayList<Integer>();
    ArrayList<Integer> renderRedSmall = new ArrayList<Integer>();
    ArrayList<Integer> renderWhiteSmall = new ArrayList<Integer>();
    ArrayList<Integer> initialBlueLarge = new ArrayList<Integer>();
    ArrayList<Integer> renderBlueLarge = new ArrayList<Integer>();
    ArrayList<Integer> renderRedLarge = new ArrayList<Integer>();
    ArrayList<Integer> renderWhiteLarge = new ArrayList<Integer>();

    for (Integer index  : currentBlueIndexesSmall) {
      if (currentAdjustment % 30 <= 19) {
        initialBlueSmall.add(index + 10);
      } else {
        initialBlueSmall.add(index + 10 - 30);
      }
      if (currentAdjustment % 30 <= 20) {
        initialBlueSmall.add(index + 9);
      } else {
        initialBlueSmall.add(index + 9 - 30);
      }
      if (currentAdjustment % 30 <= 21) {
        initialBlueSmall.add(index + 8);
      } else {
        initialBlueSmall.add(index + 8 - 30);
      }
      if (currentAdjustment % 30 <= 22) {
        initialBlueSmall.add(index + 7);
      } else {
        initialBlueSmall.add(index + 7 - 30);
      }
      if (currentAdjustment % 30 <= 23) {
        initialBlueSmall.add(index + 6);
      } else {
        initialBlueSmall.add(index + 6 - 30);
      }
      if (currentAdjustment % 30 <= 24) {
        initialBlueSmall.add(index + 5);
      } else {
        initialBlueSmall.add(index + 5 - 30);
      }
      if (currentAdjustment % 30 <= 25) {
        initialBlueSmall.add(index + 4);
      } else {
        initialBlueSmall.add(index + 4 - 30);
      }
      if (currentAdjustment % 30 <= 26) {
        initialBlueSmall.add(index + 3);
      } else {
        initialBlueSmall.add(index + 3 - 30);
      }
      if (currentAdjustment % 30 <= 27) {
        initialBlueSmall.add(index + 2);
      } else {
        initialBlueSmall.add(index + 2 - 30);
      }
      if (currentAdjustment % 30 <= 28) {
        initialBlueSmall.add(index + 1);
      } else {
        initialBlueSmall.add(index + 1 - 30);
      }
      initialBlueSmall.add(index);

    }
    for (Integer index  : currentBlueIndexesLarge) {
      if (currentAdjustment % 60 <= 49) {
        initialBlueLarge.add(index + 10);
      } else {
        initialBlueLarge.add(index + 10 - 60);
      }
      if (currentAdjustment % 60 <= 50) {
        initialBlueLarge.add(index + 9);
      } else {
        initialBlueLarge.add(index + 9 - 60);
      }
      if (currentAdjustment % 60 <= 51) {
        initialBlueLarge.add(index + 8);
      } else {
        initialBlueLarge.add(index + 8 - 60);
      }
      if (currentAdjustment % 60 <= 52) {
        initialBlueLarge.add(index + 7);
      } else {
        initialBlueLarge.add(index + 7 - 60);
      }
      if (currentAdjustment % 60 <= 53) {
        initialBlueLarge.add(index + 6);
      } else {
        initialBlueLarge.add(index + 6 - 60);
      }
      if (currentAdjustment % 60 <= 54) {
        initialBlueLarge.add(index + 5);
      } else {
        initialBlueLarge.add(index + 5 - 60);
      }
      if (currentAdjustment % 60 <= 55) {
        initialBlueLarge.add(index + 4);
      } else {
        initialBlueLarge.add(index + 4 - 60);
      }
      if (currentAdjustment % 60 <= 56) {
        initialBlueLarge.add(index + 3);
      } else {
        initialBlueLarge.add(index + 3 - 60);
      }
      if (currentAdjustment % 60 <= 57) {
        initialBlueLarge.add(index + 2);
      } else {
        initialBlueLarge.add(index + 2 - 60);
      }
      if (currentAdjustment % 60 <= 58) {
        initialBlueLarge.add(index + 1);
      } else {
        initialBlueLarge.add(index + 1 - 60);
      }
      initialBlueLarge.add(index);

    }


    for (Integer index : currentRedIndexesSmall) {
      if (initialBlueSmall.contains(index)) {
        renderWhiteSmall.add(index);
      } else {
        renderRedSmall.add(index);
      }
      if (currentAdjustment % 30 < 29) {
        if (initialBlueSmall.contains(index - 1)) {
          renderWhiteSmall.add(index - 1);
        } else {
          renderRedSmall.add(index - 1);
        }
      } else {
        if (initialBlueSmall.contains(index - 1 + 30)) {
          renderWhiteSmall.add(index - 1 + 30);
        } else {
          renderRedSmall.add(index - 1 + 30);
        }
      }
      if (currentAdjustment % 30 < 28) {
        if (initialBlueSmall.contains(index - 2 + 30)) {
          renderWhiteSmall.add(index - 2);
        } else {
          renderRedSmall.add(index - 2);
        }
      } else {
        if (initialBlueSmall.contains(index - 2 + 30)) {
          renderWhiteSmall.add(index - 2 + 30);
        } else {
          renderRedSmall.add(index - 2 + 30);
        }
      }
      if (currentAdjustment % 30 < 27) {
        if (initialBlueSmall.contains(index - 3)) {
          renderWhiteSmall.add(index - 3);
        } else {
          renderRedSmall.add(index - 3);
        }
      } else {
        if (initialBlueSmall.contains(index - 3 + 30)) {
          renderWhiteSmall.add(index - 3 + 30);
        } else {
          renderRedSmall.add(index - 3 + 30);
        }
      }
      if (currentAdjustment % 30 < 26) {
        if (initialBlueSmall.contains(index - 4)) {
          renderWhiteSmall.add(index - 4);
        } else {
          renderRedSmall.add(index - 4);
        }
      } else {
        if (initialBlueSmall.contains(index - 4 + 30)) {
          renderWhiteSmall.add(index - 4 + 30);
        } else {
          renderRedSmall.add(index - 4 + 30);
        }
      }
      if (currentAdjustment % 30 < 25) {
        if (initialBlueSmall.contains(index - 5)) {
          renderWhiteSmall.add(index - 5);
        } else {
          renderRedSmall.add(index - 5);
        }
      } else {
        if (initialBlueSmall.contains(index - 5 + 30)) {
          renderWhiteSmall.add(index - 5 + 30);
        } else {
          renderRedSmall.add(index - 5 + 30);
        }
      }
      if (currentAdjustment % 30 < 24) {
        if (initialBlueSmall.contains(index - 6)) {
          renderWhiteSmall.add(index - 6);
        } else {
          renderRedSmall.add(index - 6);
        }
      } else {
        if (initialBlueSmall.contains(index - 6 + 30)) {
          renderWhiteSmall.add(index - 6 + 30);
        } else {
          renderRedSmall.add(index - 6 + 30);
        }
      }
      if (currentAdjustment % 30 < 23) {
        if (initialBlueSmall.contains(index - 7)) {
          renderWhiteSmall.add(index - 7);
        } else {
          renderRedSmall.add(index - 7);
        }
      } else {
        if (initialBlueSmall.contains(index - 7 + 30)) {
          renderWhiteSmall.add(index - 7 + 30);
        } else {
          renderRedSmall.add(index - 7 + 30);
        }
      }
      if (currentAdjustment % 30 < 22) {
        if (initialBlueSmall.contains(index - 8)) {
          renderWhiteSmall.add(index - 8);
        } else {
          renderRedSmall.add(index - 8);
        }
      } else {
        if (initialBlueSmall.contains(index - 8 + 30)) {
          renderWhiteSmall.add(index - 8 + 30);
        } else {
          renderRedSmall.add(index - 8 + 30);
        }
      }
      if (currentAdjustment % 30 < 21) {
        if (initialBlueSmall.contains(index - 9)) {
          renderWhiteSmall.add(index - 9);
        } else {
          renderRedSmall.add(index - 9);
        }
      } else {
        if (initialBlueSmall.contains(index - 9 + 30)) {
          renderWhiteSmall.add(index - 9 + 30);
        } else {
          renderRedSmall.add(index - 9 + 30);
        }
      }
      if (currentAdjustment % 30 < 20) {
        if (initialBlueSmall.contains(index - 10)) {
          renderWhiteSmall.add(index - 10);
        } else {
          renderRedSmall.add(index - 10);
        }
      } else {
        if (initialBlueSmall.contains(index - 10 + 30)) {
          renderWhiteSmall.add(index - 10 + 30);
        } else {
          renderRedSmall.add(index - 10 + 30);
        }
      }
    }

    for (Integer index : currentRedIndexesLarge) {
      if (initialBlueLarge.contains(index)) {
        renderWhiteLarge.add(index);
      } else {
        renderRedLarge.add(index);
      }
      if (currentAdjustment % 60 < 59) {
        if (initialBlueLarge.contains(index - 1)) {
          renderWhiteLarge.add(index - 1);
        } else {
          renderRedLarge.add(index - 1);
        }
      } else {
        if (initialBlueLarge.contains(index - 1 + 60)) {
          renderWhiteLarge.add(index - 1 + 60);
        } else {
          renderRedLarge.add(index - 1 + 60);
        }
      }
      if (currentAdjustment % 60 < 58) {
        if (initialBlueLarge.contains(index - 2 + 60)) {
          renderWhiteLarge.add(index - 2 + 60);
        } else {
          renderRedLarge.add(index - 2 + 60);
        }
      } else {
        if (initialBlueLarge.contains(index - 2 + 60)) {
          renderWhiteLarge.add(index - 2 + 60);
        } else {
          renderRedLarge.add(index - 2 + 60);
        }
      }
      if (currentAdjustment % 60 < 57) {
        if (initialBlueLarge.contains(index - 3)) {
          renderWhiteLarge.add(index - 3);
        } else {
          renderRedLarge.add(index - 3);
        }
      } else {
        if (initialBlueLarge.contains(index - 3 + 60)) {
          renderWhiteLarge.add(index - 3 + 60);
        } else {
          renderRedLarge.add(index - 3 + 60);
        }
      }
      if (currentAdjustment % 60 < 56) {
        if (initialBlueLarge.contains(index - 4)) {
          renderWhiteLarge.add(index - 4);
        } else {
          renderRedLarge.add(index - 4);
        }
      } else {
        if (initialBlueLarge.contains(index - 4 + 60)) {
          renderWhiteLarge.add(index - 4 + 60);
        } else {
          renderRedLarge.add(index - 4 + 60);
        }
      }
      if (currentAdjustment % 60 < 55) {
        if (initialBlueLarge.contains(index - 5)) {
          renderWhiteLarge.add(index - 5);
        } else {
          renderRedLarge.add(index - 5);
        }
      } else {
        if (initialBlueLarge.contains(index - 5 + 60)) {
          renderWhiteLarge.add(index - 5 + 60);
        } else {
          renderRedLarge.add(index - 5 + 60);
        }
      }
      if (currentAdjustment % 60 < 54) {
        if (initialBlueLarge.contains(index - 6)) {
          renderWhiteLarge.add(index - 6);
        } else {
          renderRedLarge.add(index - 6);
        }
      } else {
        if (initialBlueLarge.contains(index - 6 + 60)) {
          renderWhiteLarge.add(index - 6 + 60);
        } else {
          renderRedLarge.add(index - 6 + 60);
        }
      }
      if (currentAdjustment % 60 < 53) {
        if (initialBlueLarge.contains(index - 7)) {
          renderWhiteLarge.add(index - 7);
        } else {
          renderRedLarge.add(index - 7);
        }
      } else {
        if (initialBlueLarge.contains(index - 7 + 60)) {
          renderWhiteLarge.add(index - 7 + 60);
        } else {
          renderRedLarge.add(index - 7 + 60);
        }
      }
      if (currentAdjustment % 60 < 52) {
        if (initialBlueLarge.contains(index - 8)) {
          renderWhiteLarge.add(index - 8);
        } else {
          renderRedLarge.add(index - 8);
        }
      } else {
        if (initialBlueLarge.contains(index - 8 + 60)) {
          renderWhiteLarge.add(index - 8 + 60);
        } else {
          renderRedLarge.add(index - 8 + 60);
        }
      }
      if (currentAdjustment % 60 < 51) {
        if (initialBlueLarge.contains(index - 9)) {
          renderWhiteLarge.add(index - 9);
        } else {
          renderRedLarge.add(index - 9);
        }
      } else {
        if (initialBlueLarge.contains(index - 9 + 60)) {
          renderWhiteLarge.add(index - 9 + 60);
        } else {
          renderRedLarge.add(index - 9 + 60);
        }
      }
      if (currentAdjustment % 60 < 50) {
        if (initialBlueLarge.contains(index - 10)) {
          renderWhiteLarge.add(index - 10);
        } else {
          renderRedLarge.add(index - 10);
        }
      } else {
        if (initialBlueLarge.contains(index - 10 + 60)) {
          renderWhiteLarge.add(index - 10 + 60);
        } else {
          renderRedLarge.add(index - 10 + 60);
        }
      }
    }

      for (Integer index  : initialBlueSmall) {
        if (!renderRedSmall.contains(index) && !renderWhiteSmall.contains(index)) {
          renderBlueSmall.add(index);
        }
      }

      for (Integer index  : initialBlueLarge) {
        if (!renderRedLarge.contains(index) && !renderWhiteLarge.contains(index)) {
          renderBlueLarge.add(index);
        }
      }

      for (LXModel cloud : model.sub("SMALL")) {
        for (LXPoint point : cloud.getPoints()) {
          int pointIndex = point.index;
          if(renderBlueSmall.contains(pointIndex)) {
            setColor(point, Colors.CYAN);
          } else if (renderWhiteSmall.contains(pointIndex)) {
            setColor(point, Colors.WHITE);
          } else if (renderRedSmall.contains(pointIndex)) {
            setColor(point, Colors.RED);
          } else {
            setColor(point, Colors.TRANSLUCENT);
          }
        }
      }
      for (LXModel cloud : model.sub("LARGE")) {
        for (LXPoint point : cloud.getPoints()) {
          int pointIndex = point.index;
          if(renderBlueLarge.contains(pointIndex)) {
            setColor(point, Colors.CYAN);
          } else if (renderWhiteLarge.contains(pointIndex)) {
            setColor(point, Colors.WHITE);
          } else if (renderRedLarge.contains(pointIndex)) {
            setColor(point, Colors.RED);
          } else {
            setColor(point, Colors.TRANSLUCENT);
          }
        }
      }
      currentAdjustment += 1;
      this.generateIndexes();
      cumulativeTimePassedSinceIncrement = 0;
    }
  }
}