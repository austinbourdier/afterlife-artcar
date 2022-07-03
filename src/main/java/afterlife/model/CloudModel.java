package afterlife.model;

import java.util.*;
import java.util.stream.Collectors;

import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.transform.LXVector;

public class CloudModel extends AfterlifeModel {
        public static String type;
        public static int x;
        public static int y;
        public static int z;
        public static int direction;
  public CloudModel(List<LXPoint> points, String type, int x, int y, int z, int direction) {
    super("Cloud", points);

    this.type = type;
    this.x = x;
    this.y = y;
    this.z = z;
    this.direction = direction;
  }

}
