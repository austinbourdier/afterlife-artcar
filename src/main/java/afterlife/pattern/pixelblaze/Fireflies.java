package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class Fireflies extends PixelblazePattern {

	public Fireflies(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "fireflies";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
