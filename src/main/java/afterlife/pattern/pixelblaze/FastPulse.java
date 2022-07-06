package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class FastPulse extends PixelblazePattern {

	public FastPulse(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "fast_pulse";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
