package afterlife.midi;

import heronarts.lx.LX;
import heronarts.lx.midi.LXMidiInput;
import heronarts.lx.midi.LXMidiListener;
import heronarts.lx.Tempo;
import heronarts.lx.midi.MidiNoteOn;

public class AfterlifeMidi {

    public static int heavenCounter = 0;
    public static int hellCounter = 1;

    public AfterlifeMidi(LX lx) {
        this.loadMidiControllers(lx);
    }

    private void loadMidiControllers(LX lx) {

        lx.engine.midi.whenReady(() -> {




            for (LXMidiInput device : lx.engine.midi.inputs) {
                System.err.println(device.getName());

                // Find the device that you care about here...

                if (device.getName().equals("Logidy UMI3")) {
                    device.addListener(new LXMidiListener() {
                        public void noteOnReceived(MidiNoteOn note) {
                            if (note.getPitch() == 60) {
                                AfterlifeMidi.heavenCounter++;
                            } else if (note.getPitch() == 62) {
                                AfterlifeMidi.hellCounter++;
                            } else if (note.getPitch() == 64) {
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
