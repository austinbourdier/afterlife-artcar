package afterlife.pattern.pixelblaze;
import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.parameter.BooleanParameter;

@LXCategory("Main Channel")
public class Judgement extends PixelblazePattern {

	public final BooleanParameter manualReset =
    	new BooleanParameter("Reset", false)
			.setMode(BooleanParameter.Mode.MOMENTARY);

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

