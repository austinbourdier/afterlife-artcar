package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class RainbowMelt extends PixelblazePattern {

	public RainbowMelt(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "rainbow_melt";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
