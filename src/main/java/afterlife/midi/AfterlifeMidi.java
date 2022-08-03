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
        Tempo.Listener tempoListener = new Tempo.Listener() {
            @Override
            public void onBeat(Tempo tempo, int beat) {
                if (beat == 0) {
                    // It's a measure
                    System.err.println("beat!");
                }
            }
            @Override
            public void onMeasure(Tempo tempo) {
                // Another way to tell it's a measure
                System.err.println("end of measure!");
            }
        };


        lx.engine.midi.whenReady(() -> {




            for (LXMidiInput device : lx.engine.midi.inputs) {
                System.err.println(device.getName());

                // Find the device that you care about here...

                if (device.getName().equals("Logidy UMI3")) {
                    device.addListener(new LXMidiListener() {
                        public void noteOnReceived(MidiNoteOn note) {
                            System.err.println(note.getPitch());
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
