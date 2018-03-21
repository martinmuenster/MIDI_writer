import java.io.File;
import java.io.IOException;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

public class Midi {
	Sequence s;
	Track t;
	
	
	public void write(String fname) {
		//****  write the MIDI sequence to a MIDI file  ****
		File f = new File(fname + ".mid");
		try {
			MidiSystem.write(s,1,f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void makeNote(int note, int start, int end) {
		try {
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0x90,note,0x60);
			MidiEvent me = new MidiEvent(mm,(long)start);
			t.add(me);

			mm = new ShortMessage();
			mm.setMessage(0x80,note,0x40);
			me = new MidiEvent(mm,(long)end);
			t.add(me);
		}
		catch(Exception e) {
			System.out.println("Exception caught " + e.toString());
		} //catch
		
	}
	
	public void setupMidi() {
	    System.out.println("midifile begin ");
		try
		{
	//****  Create a new MIDI sequence with 24 ticks per beat  ****
			s = new Sequence(javax.sound.midi.Sequence.PPQ,24);

	//****  Obtain a MIDI track from the sequence  ****
			t = s.createTrack();

	//****  General MIDI sysex -- turn on General MIDI sound set  ****
			byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
			SysexMessage sm = new SysexMessage();
			sm.setMessage(b, 6);
			MidiEvent me = new MidiEvent(sm,(long)0);
			t.add(me);

			MetaMessage mt = new MetaMessage();

	//****  set track name (meta event)  ****
			mt = new MetaMessage();
			String TrackName = new String("midifile track");
			mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
			me = new MidiEvent(mt,(long)0);
			t.add(me);

	//****  set omni on  ****
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7D,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);

	//****  set poly on  ****
			mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7F,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);

	//****  set instrument to Piano  ****
			mm = new ShortMessage();
			mm.setMessage(0xC0, 0x00, 0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);



		} //try
			catch(Exception e)
		{
			System.out.println("Exception caught " + e.toString());
		} //catch
	    System.out.println("midifile end ");
	}
}

