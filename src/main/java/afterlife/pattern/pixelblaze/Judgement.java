package afterlife.pattern.pixelblaze;
import heronarts.lx.LX;
import heronarts.lx.LXCategory;

@LXCategory("Main Channel")
public class Judgement extends PixelblazePattern {

	public Judgement(LX lx) {
		super(lx);
	}

	@Override
	protected String getScriptName() {
		return "judgement";
	}

	@Override
	protected void run(double deltaMs) {
		runAfterlifeBasePattern(deltaMs);
	}
}

