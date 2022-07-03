package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;

public class PBFireworkNova extends PixelblazePattern {

	public PBFireworkNova(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "fireworknova";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
