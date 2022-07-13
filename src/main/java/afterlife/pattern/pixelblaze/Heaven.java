package afterlife.pattern.pixelblaze;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class Heaven extends PixelblazePattern {

	public Heaven(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "heaven";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}
