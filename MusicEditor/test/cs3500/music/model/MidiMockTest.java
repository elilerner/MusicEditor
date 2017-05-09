package cs3500.music.model;

/**
 * Created by jeanpaul on 3/22/2017.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MockSynthesizer;

import static junit.framework.TestCase.assertEquals;


public class MidiMockTest {

  @Test
          (expected = NullPointerException.class)
  public void testMockMidiInvalid() throws InvalidMidiDataException {
    Note n1 = new Note(2, 4, 4, Pitch.C, 1, 0);
    Note n2 = new Note(3, 5, 5, Pitch.B, 1, 2);
    Note n3 = new Note(10, 1, 3, Pitch.ASharp, 1, 3);
    ArrayList<Note> notes = new ArrayList<>(Arrays.asList(n1, n2, n3));
    MusicEditorModelImpl model = new MusicEditorModelImpl(notes, 20000);
    ReadOnlyMusicEditorModel readModel = new ReadOnlyMusicEditorModel(model);
    StringBuilder log = new StringBuilder();
    new MidiViewImpl(readModel, null);
  }

  @Test
          (expected = NullPointerException.class)
  public void testMockMidiInvalid2() throws InvalidMidiDataException {
    Note n1 = new Note(2, 4, 4, Pitch.C, 1, 0);
    Note n2 = new Note(3, 5, 5, Pitch.B, 1, 2);
    Note n3 = new Note(10, 1, 3, Pitch.ASharp, 1, 3);
    ArrayList<Note> notes = new ArrayList<>(Arrays.asList(n1, n2, n3));
    MusicEditorModelImpl song1 = new MusicEditorModelImpl(notes, 20000);
    StringBuilder log = new StringBuilder();
    MidiViewImpl midi = new MidiViewImpl(null, null);
  }

  @Test
  public void testMidiImpl() {
    StringBuilder log = new StringBuilder();
    ArrayList<Integer> loNote = new ArrayList();
    loNote.add(20);
    loNote.add(30);
    loNote.add(40);
    try {
      Synthesizer synthesizer = new MockSynthesizer(log);
      Receiver receiver = synthesizer.getReceiver();
      synthesizer.open();
      for (int i = 0; i < loNote.size(); i++) {
        int currentNote = loNote.get(i);
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, currentNote, 64);
        MidiMessage end = new ShortMessage(ShortMessage.NOTE_OFF, 0, currentNote, 64);
        receiver.send(start, -1);
        receiver.send(end, 200000);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals("start \n" +
            "The current Note is: 20 \n" +
            "The current Note is: 20 \n" +
            "The current Note is: 30 \n" +
            "The current Note is: 30 \n" +
            "The current Note is: 40 \n" +
            "The current Note is: 40 \n", log.toString());
  }
}