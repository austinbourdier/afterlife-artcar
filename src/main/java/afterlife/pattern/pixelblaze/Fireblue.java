package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class Fireblue extends PixelblazePattern {

	public Fireblue(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "fireblue";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
