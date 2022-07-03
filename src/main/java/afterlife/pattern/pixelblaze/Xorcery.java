package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class Xorcery extends PixelblazePattern {

	public Xorcery(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "xorcery";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
