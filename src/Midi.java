import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Midi {
	
	Sequencer sequencer;
	boolean isPlaying;
	long currentPosition;
	
	public Midi() {
		try {
			// Obtains the default Sequencer connected to a default device.
			sequencer = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isPlaying = false;
		currentPosition = 0;
	}
	
	public void PlayMidi(String fileName) throws IOException, InvalidMidiDataException, MidiUnavailableException {
    	// Opens the device, indicating that it should now acquire any
        // system resources it requires and become operational.
		sequencer.open();
		
		// create a stream from a file
        InputStream is = new BufferedInputStream(new FileInputStream(new File(fileName)));
        
        // Sets the current sequence on which the sequencer operates.
        // The stream must point to MIDI file data.
        sequencer.setSequence(is);

        // Starts playback of the MIDI data in the currently loaded sequence.
        sequencer.setMicrosecondPosition(currentPosition);
        sequencer.start();
        isPlaying = true;
	}
	
	public void StopMidi() {
		sequencer.stop();
		currentPosition = sequencer.getMicrosecondPosition();
		isPlaying = false;
	}
	
	public boolean getIsPlaying() { return isPlaying; }
	
}
