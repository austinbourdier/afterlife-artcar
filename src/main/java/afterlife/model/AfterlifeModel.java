package afterlife.model;

import java.util.*;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;

public class AfterlifeModel extends LXModel {
  private final String modelType;

  public AfterlifeModel(String modelType, List<LXPoint> points) {
    super(points, modelType);
    this.modelType = modelType;
  }

}
