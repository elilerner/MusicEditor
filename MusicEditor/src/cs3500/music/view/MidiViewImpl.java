
package cs3500.music.view;

import java.util.List;
import java.util.Objects;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicEditorModel;

/**
 * Class for the Midi.
 * Used to create music from notes.
 */
public class MidiViewImpl implements IView {
  private final Synthesizer synthesizer;
  private final Receiver receiver;
  private ReadOnlyMusicEditorModel model;

  /**
   * Constructor for the Midi
   *
   * @param song the given ReadOnly model.
   */
  public MidiViewImpl(ReadOnlyMusicEditorModel song) {
    Objects.requireNonNull(song);
    this.model = song;
    Synthesizer tempS;
    Receiver tempR;
    try {
      tempS = MidiSystem.getSynthesizer();
      tempR = tempS.getReceiver();
      tempS.open();
    } catch (MidiUnavailableException e) {
      tempS = null;
      tempR = null;
      e.printStackTrace();
    }
    this.synthesizer = tempS;
    this.receiver = tempR;
  }

  /**
   * Used for the mock.
   *
   * @param model A model.
   * @param synth A Synthesizer.
   */
  public MidiViewImpl(ReadOnlyMusicEditorModel model, Synthesizer synth) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(synth);
    this.model = model;
    Synthesizer currentSynthesizer;
    Receiver currentReceiver;
    try {
      currentSynthesizer = synth;
      currentReceiver = currentSynthesizer.getReceiver();
    } catch (MidiUnavailableException e) {
      currentSynthesizer = null;
      currentReceiver = null;
      e.printStackTrace();
    }
    this.synthesizer = currentSynthesizer;
    this.receiver = currentReceiver;
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI"> https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  public void playNote(Note note) {
    int instrument = note.getInstrument() - 1;
    int midiValue = note.midiValue();
    int volume = note.getVolume();
    int tempo = model.getTempo();
    try {
      MidiMessage start = null;
      MidiMessage stop = null;
      try {
        start = new ShortMessage(ShortMessage.NOTE_ON, instrument, midiValue,
                volume);
        stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, midiValue,
                volume);
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
      this.receiver.send(start, note.getStart() * tempo);
      this.receiver.send(stop, (note.last() + 1) * tempo);
    } catch (NullPointerException c) {
      c.printStackTrace();
    }
  }

  /**
   * plays the notes at the given beat.
   */
  @Override
  public void render() {
    List<Note> notes = model.getNotesThatStartAt(model.getCurrentBeat());
    int count = 0;
    for (int i = 0; i < notes.size(); i++) {
      playNote(notes.get(i));
    }
  }
}
