package afterlife.midi;

import heronarts.lx.LX;
import heronarts.lx.midi.LXMidiInput;
import heronarts.lx.midi.LXMidiListener;
import heronarts.lx.midi.MidiNoteOn;
import java.net.SocketException;

public class AfterlifeMidi {

    public static int heavenCounter = 0;
    public static int hellCounter = 0;

    public AfterlifeMidi(LX lx) {
        this.loadMidiControllers(lx);
    }

    private void loadMidiControllers(LX lx) {

        lx.engine.midi.whenReady(() -> {

            for (LXMidiInput device : lx.engine.midi.inputs) {

                // Find the device that you care about here...

                if (device.getName().equals("Logidy UMI3")) {
                    device.addListener(new LXMidiListener() {
                        public void noteOnReceived(MidiNoteOn note) {
                            String channel = note.toString().split(";")[3];
                            if (channel.equals("60")) {
                                AfterlifeMidi.heavenCounter++;
                            }
                            if (channel.equals("62")) {
                                AfterlifeMidi.hellCounter++;
                            }
                            if (channel.equals("64")) {
                                AfterlifeMidi.heavenCounter = 0;
                                AfterlifeMidi.hellCounter = 0;
                            }
                        }
                    });

                    device.open();
                }
            }
        });
    }
}
