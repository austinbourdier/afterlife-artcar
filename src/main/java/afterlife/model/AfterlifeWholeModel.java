package afterlife.model;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import heronarts.lx.model.LXPoint;

import heronarts.lx.LX;
import heronarts.lx.model.LXModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import heronarts.lx.transform.LXVector;
import java.util.*;
import afterlife.util.CloudFactory;


public class AfterlifeWholeModel extends LXModel {
  public List<CloudModel> clouds;

  private static class Model {
    public List<CloudModel> clouds;
    public LXModel[] children;
  }

  // Number of clouds on the car
  private static final int NUM_CLOUDS = 7;
  public AfterlifeWholeModel() {
      this(loadModel());
  }

  private AfterlifeWholeModel(Model model) {
      super(model.children);

      this.clouds = model.clouds;
  }



  private static Model loadModel() {
    Model model = new Model();
    List<LXModel> children = new ArrayList<LXModel>();

    loadClouds(model);
    children.addAll(model.clouds);

    model.children = children.toArray(new LXModel[0]);

    return model;
  }

  public static final class Cloud {
        private static String type;
        private static int x;
        private static int y;
        private static int z;
        private static int direction;
    }

  private static void loadClouds(Model model) {
    model.clouds = new ArrayList<CloudModel>();
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(loadFile("Fixtures/Cloud.json"));

    JsonArray jsonClouds = gson.fromJson(reader, JsonArray.class);

    for (int cloudIndex = 0; cloudIndex < jsonClouds.size(); cloudIndex++) {
        JsonObject cloudData = jsonClouds.get(cloudIndex).getAsJsonObject();
        String type = cloudData.get("type").getAsString();
        int x = cloudData.get("x").getAsInt();
        int y = cloudData.get("y").getAsInt();
        int z = cloudData.get("z").getAsInt();
        int direction = cloudData.get("direction").getAsInt();
        List<LXPoint> points = CloudFactory.build(type, x, y, z, direction);
        CloudModel cloud = new CloudModel(points, type, x, y, z, direction);
        model.clouds.add(cloud);
    }
  }

  protected static BufferedReader loadFile(String filename) {
      try {
          File f = new File(filename);
          return new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
          throw new Error(filename + " not found below " + System.getProperty("user.dir"));
      }
  }
}
