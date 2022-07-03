package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class NeonIce extends PixelblazePattern {

	public NeonIce(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "neon_ice";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
