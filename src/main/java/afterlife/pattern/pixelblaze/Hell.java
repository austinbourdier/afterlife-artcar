package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;

public class Hell extends PixelblazePattern {

	public Hell(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "hell";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
