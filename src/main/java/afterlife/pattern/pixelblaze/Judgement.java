package afterlife.pattern.pixelblaze;
import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.parameter.BooleanParameter;

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
		System.err.println(lx.engine.tempo.bpm());
		runAfterlifeBasePattern(deltaMs);
	}

}

