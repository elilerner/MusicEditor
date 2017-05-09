package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cs3500.music.util.CompositionBuilder;

//import cs3500.music.MusicEditor;

/**
 * Created by jeanpaul on 3/2/2017.
 */

/**
 * Model for Music Editor.
 */
public class MusicEditorModelImpl implements IMusicEditorModel {

  /**
   * Builder for the CompositreBuilder that parses files.
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModelImpl> {
    private int tempo;
    private List<Note> notes = new ArrayList<>();

    /**
     * Builds the MusicEditorModel.
     */
    @Override
    public MusicEditorModelImpl build() {
      return new MusicEditorModelImpl(this.notes, this.tempo);
    }

    /**
     * Sets the Tempo.
     *
     * @param tempo The speed, in microseconds per beat.
     * @return the ComposititonBuilder.
     */
    @Override
    public CompositionBuilder<MusicEditorModelImpl> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * @param start      The start time of the note, in beats
     * @param end        The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
     *                   piano)
     * @param volume     The volume (in the range [0, 127])
     * @return CompositionBuilder.
     */
    @Override
    public CompositionBuilder<MusicEditorModelImpl> addNote(int start, int end, int instrument,
                                                            int pitch, int volume) {

      int octave = (pitch / 12) - 1;
      Pitch currentPitch = Pitch.values()[pitch - (octave + 1) * 12];

      Note note = new Note(end - start, instrument, volume, currentPitch, octave, start);
      notes.add(note);
      return this;
    }
  }


  private List<Note> loNote;
  private int currentBeat;
  private int tempo;

  /**
   * Constructor for MusicEditorModelImpl.
   */
  public MusicEditorModelImpl() {
    this.loNote = new ArrayList<>();
  }

  /**
   * @param loNote List of Notes.
   * @param tempo  the tempo for MIDI.
   */
  public MusicEditorModelImpl(List<Note> loNote, int tempo) {
    if (loNote == null) {
      throw new IllegalArgumentException("invalid notes");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("invalid tempo");
    }
    this.tempo = tempo;
    this.currentBeat = 0;
    this.loNote = loNote;
  }

  /**
   * Checks if it is a valid add. If it is it will add it. If not it will not.
   *
   * @param note a Note trying to get added.
   */
  @Override
  public void addNote(Note note) {
    nullNote(note);
    boolean validAdd = true;
    int start;
    int end;
    boolean equalNote;
    boolean sameMeasure;
    for (int i = 0; i < loNote.size(); i++) {
      sameMeasure = false;
      equalNote = loNote.get(i).getPitch().equals(note.getPitch());
      start = note.getStart();
      end = note.last();
      for (int i2 = start; i2 <= end; i2++) {
        if (i2 >= loNote.get(i).getStart() && i2 <= loNote.get(i).last()) {
          sameMeasure = true;
        }
      }
      //will not add if its the Same Note.
      if (equalNote && sameMeasure) {
        validAdd = false;
      }
    }
    //Where the actual adding of a Note is done
    if (validAdd) {
      loNote.add(note);
    }
  }

  /**
   * Removes a Note from LoNote.
   *
   * @param note a Note to be removed.
   */
  @Override
  public void removeNote(Note note) {
    nullNote(note);
    loNote.remove(note);
  }

  /**
   * Checks if a null Note is passed in.
   *
   * @param note a Note.
   */
  private void nullNote(Note note) {
    if (note == null) {
      throw new IllegalArgumentException("Invalid Note");
    }
  }

  /**
   * Gets the current state of the model.
   *
   * @return the current state.
   */
  @Override
  public String getState() {
    if (loNote.size() == 0) {
      return "";
    }
    int length = beatLength();
    StringBuilder sb = new StringBuilder(String.format("%"
            + Integer.toString(length).length() + "s", ""));
    ArrayList<String> range = range();
    for (int i = 0; i < range.size(); i++) {
      sb.append(String.format("%5s", String.format("%-2s", range.get(i))));
    }
    for (int i2 = 0; i2 <= length; i2++) {
      sb.append("\n" + addRow(i2, range));
    }
    return sb.toString();
  }

  /**
   * Gets the range of the Notes.
   *
   * @return a List of the range of the notes.
   */
  public ArrayList<String> range() {
    boolean validRange;
    boolean greaterThanFirst;
    boolean lessThanLast;
    Note firstNote = firstOrLast(true);
    int firstOctave = firstNote.getOctave();
    Note lastNote = firstOrLast(false);
    int lastOctave = lastNote.getOctave();
    ArrayList<String> range = new ArrayList<>();
    for (int i = firstOctave; i <= lastOctave; i++) {
      for (Pitch p : Pitch.values()) {
        boolean notLastOctave = i != lastOctave;
        greaterThanFirst = p.compareTo(firstNote.getPitch()) >= 0;
        lessThanLast = p.compareTo(lastNote.getPitch()) <= 0;
        if (i == firstOctave && i == lastOctave) {
          validRange = greaterThanFirst && lessThanLast;
        } else if (i == firstOctave) {
          validRange = greaterThanFirst;
        } else {
          validRange = lessThanLast || notLastOctave;
        }
        if (validRange) {
          range.add(p.getPitch() + i);
        }
      }
    }
    return range;
  }


  //@return the first or last note in this song.
  private Note firstOrLast(boolean first) {
    isEmpty();
    Note forLNote = loNote.get(0);
    for (int i = 0; i < loNote.size(); i++) {
      int current = loNote.get(i).compare(forLNote);
      if (first) {
        if (current < 0) {
          forLNote = loNote.get(i);
        }
      } else {
        if (current > 0) {
          forLNote = loNote.get(i);
        }
      }
    }
    return forLNote;
  }

  /**
   * Checks if this List of Notes are Empty.
   * Throws and Exception if it is empty.
   */
  private void isEmpty() {
    if (loNote.size() == 0) {
      throw new IllegalArgumentException("Empty");
    }
  }

  /**
   * Helper Function for getState(). Prints out the current row.
   *
   * @param beatNumber the current beat.
   * @param noteRange  the current Range.
   * @return the current row.
   */
  private String addRow(int beatNumber, ArrayList<String> noteRange) {
    boolean isHead;
    boolean isSustains;
    String currentBeat;
    String currentRow = String.format("%" + (Integer.toString(beatLength()).length() + 1) + "s",
            beatNumber);
    for (int i = 0; i < noteRange.size(); i++) {
      currentBeat = "     ";
      for (int i2 = 0; i2 < loNote.size(); i2++) {
        Note currentNote = loNote.get(i2);
        int currentStart = currentNote.getStart();
        String currentNoteString = currentNote.toString();
        String currentRange = noteRange.get(i);
        isHead = currentNoteString.equals(currentRange) && currentStart == beatNumber;
        isSustains = currentNoteString.equals(currentRange) && currentStart < beatNumber
                && currentNote.last() >= beatNumber;
        if (isHead) {
          currentBeat = "  X  ";
          break;
        } else if (isSustains) {
          currentBeat = "  |  ";
          break;
        }
      }
      currentRow = currentRow + currentBeat;
    }
    return currentRow;
  }

  /**
   * Gets the length of a List of Note's beat.
   *
   * @return the length of a List of Note's beat.
   */
  public int beatLength() {
    int currentLength = 0;
    int last;
    for (int i = 0; i < loNote.size(); i++) {
      last = loNote.get(i).last();
      if (last > currentLength) {
        currentLength = last;
      }
    }
    return currentLength;
  }

  /**
   * Combines two Model's List of Notes simultaneously.
   *
   * @param model a MusicEditorModelImpl.
   */
  @Override
  public void combineS(MusicEditorModelImpl model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid Model");
    }
    model.getLoNote().forEach(this::addNote);
  }


  /**
   * Combines two Model's List of Notes consecutively.
   *
   * @param model a MusicEditorModelImpl.
   */
  @Override
  public void combineC(MusicEditorModelImpl model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid Model");
    }
    int length = beatLength();
    for (int i = 0; i < model.getLoNote().size(); i++) {
      List<Note> modelList = model.getLoNote();
      Note currentNote = modelList.get(i);
      Note note = new Note(currentNote.getDuration(),
              currentNote.getInstrument(),
              currentNote.getVolume(),
              currentNote.getPitch(),
              currentNote.getOctave(),
              currentNote.getStart() + length);
      loNote.add(note);
    }
  }

  /**
   * Gets this LoNote
   *
   * @return a List of Notes.
   */
  @Override
  public List<Note> getLoNote() {
    return loNote;
  }

  private List<Note> copyLoNote() {
    List<Note> currentNotes = new ArrayList<Note>(2000);
    for (int i = 0; i < loNote.size(); i++) {
      currentNotes.add(loNote.get(i));
    }
    return currentNotes;
  }

  /**
   * the current notes.
   *
   * @param currentBeat the current beat.
   * @return the current Notes.
   */
  public List<Note> getCurrentNotes(int currentBeat) {
    List<Note> currentNotes = copyLoNote();
    return currentNotes.stream().filter(p ->
            currentBeat >= p.getStart()).filter(p -> currentBeat <= p.last())
            .collect(Collectors.toList());
  }

  /**
   * Gets the Notes starting at a given note.
   *
   * @param currentNote the current Note.
   * @return A list of Notes.
   */
  public List<Note> getNotesThatStartAt(int currentNote) {
    // System.out.println("Thien");
    return copyLoNote().stream().filter(p -> p.getStart() == currentNote)
            .collect(Collectors.toCollection(ArrayList::new));

  }

  /**
   * gets the current beat.
   *
   * @return the current beat.
   */
  public int getCurrentBeat() {
    return currentBeat;
  }

  /**
   * sets the current bear the the given beat.
   *
   * @param newCurrent the new current.
   */
  public void setCurrentBeat(int newCurrent) {
    currentBeat = newCurrent;
  }

  /**
   * gets the tempo.
   *
   * @return the tempo.
   */
  public int getTempo() {
    return tempo;
  }
}