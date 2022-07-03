package afterlife.util;

import java.util.*;

import afterlife.model.*;
import heronarts.lx.model.LXPoint;


public class CloudFactory {

  public static final int DISTANCE_BETWEEN_STRIPS_IN_PIXELS = 5;

  public static final List<LXPoint> build(String type, int x, int y, int z, int direction) {
    List<LXPoint> points = new ArrayList<LXPoint>();
    int numPixelsPerStrip = 75;
    int numStripsPerCloud = 2;
    int currentX = x;
    int currentY = y;
    int currentZ = z;

    if (type.equals("small")) {
      numPixelsPerStrip = 30;
    }
    while (numStripsPerCloud > 0) {
      while (numPixelsPerStrip > 0) {

        LXPoint point = new LXPoint(currentX, currentY, currentZ);

        points.add(point);

        if (direction == 1) {
          currentZ--;
        } else if (direction == 2) {
          currentX--;
        } else {
          currentX++;
        }
        numPixelsPerStrip--;
      }
      currentX = x;
      currentY = y + DISTANCE_BETWEEN_STRIPS_IN_PIXELS;
      currentZ = z;
      if (type.equals("small")) {
        numPixelsPerStrip = 30;
      } else {
        numPixelsPerStrip = 75;
      }
      numStripsPerCloud = numStripsPerCloud - 1;
    }
    return points;
  }
}
