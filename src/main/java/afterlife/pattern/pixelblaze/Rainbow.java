package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;

public class Rainbow extends PixelblazePattern {

	public Rainbow(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "rainbow";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
