package afterlife.pattern;

import heronarts.lx.LX;
import heronarts.lx.LXLayeredComponent;
import heronarts.lx.Tempo;
import heronarts.lx.color.GradientUtils;
import heronarts.lx.color.LXColor;
import heronarts.lx.color.LinkedColorParameter;
import heronarts.lx.model.LXPoint;
import heronarts.lx.model.LXView;
import heronarts.lx.pattern.LXModelPattern;
import afterlife.model.AfterlifeWholeModel;
import afterlife.midi.AfterlifeMidi;

import java.util.*;

public abstract class AfterlifeBasePattern extends LXModelPattern<AfterlifeWholeModel> {

    public static final int TRANSPARENT = 0x00000000;

    public AfterlifeBasePattern(LX lx) {
        super(lx);
    }

    @Override
    protected void run(double deltaMs) {
        runAfterlifeBasePattern(deltaMs);
    }

    protected abstract void runAfterlifeBasePattern(double deltaMs);

    public double measure() {
      return (
              lx.engine.tempo.getCompositeBasis() %
              lx.engine.tempo.beatsPerMeasure.getValue() /
              lx.engine.tempo.beatsPerMeasure.getValue()
      );
    }

    public String judgementWinner() {
      if (AfterlifeMidi.heavenCounter > AfterlifeMidi.hellCounter) {
        return "heaven";
      } else if (AfterlifeMidi.heavenCounter < AfterlifeMidi.hellCounter) {
        return "hell";
      } else {
        return "tie";
      }
    }

    public int getPointColor(float lerp) {
      return 1;
    }

    // Compare to LXLayeredComponent's clearColors(), which is declared final.
    public void clearPixels() {
        for (LXPoint point : this.model.points) {
            colors[point.index] = TRANSPARENT; // Transparent
        }
    }

}
