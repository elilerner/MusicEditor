package cs3500.music.model;

import java.util.List;

/**
 * Created by jeanpaul on 3/27/2017.
 */

/**
 * Used for the View. Only outputs non-mutable data.
 */
public class ReadOnlyMusicEditorModel implements IMusicEditorModel {
  //int count = 0;
  IMusicEditorModel model;

  /**
   * Constructor the for the ReadOnlyMusicEditor.
   */
  public ReadOnlyMusicEditorModel(IMusicEditorModel model) {
    this.model = model;
  }

  /**
   * Error, mutable type.
   *
   * @param n a Note.
   */
  @Override
  public void addNote(Note n) {
    throw new RuntimeException("Not in my town");
  }

  /**
   * Error, mutable. type.
   *
   * @param note a Note to be removed.
   */
  @Override
  public void removeNote(Note note) {
    throw new RuntimeException("Amit taught me better than that!");
  }

  /**
   * Gets the current state.
   *
   * @return String version of the state.
   */
  @Override
  public String getState() {
    return model.getState();
  }

  /**
   * Error.
   *
   * @param model a MusicEditorModelImpl.
   */
  @Override
  public void combineS(MusicEditorModelImpl model) {
    throw new RuntimeException("You kidding me?");
  }

  /**
   * Error.
   *
   * @param model a MusicEditorModelImpl.
   */
  @Override
  public void combineC(MusicEditorModelImpl model) {
    throw new RuntimeException("Not today!");
  }

  /**
   * Gets the List of Notes.
   *
   * @return List of Notes.
   */
  @Override
  public List<Note> getLoNote() {
    return model.getLoNote();
  }

  /**
   * Gets the Length of the song in beats.
   *
   * @return the length of the Song.
   */
  @Override
  public int beatLength() {
    return model.beatLength();
  }

  /**
   * gets the range of notes.
   *
   * @return List of the range.
   */
  @Override
  public List<String> range() {
    return model.range();
  }


  /**
   * Gets the current Tempo.
   *
   * @return tempo of song.
   */
  @Override
  public int getTempo() {
    return model.getTempo();
  }

  /**
   * gets Notes that start at a given beat.
   *
   * @return List of Notes at a given beat.
   */
  @Override
  public List<Note> getNotesThatStartAt(int currentNote) {
    return model.getNotesThatStartAt(currentNote);
  }

  /**
   * gets the Current Beat.
   *
   * @return the current beat.
   */
  public int getCurrentBeat() {
    return model.getCurrentBeat();
  }

  /**
   * Gets the current Notes at the given beat.
   *
   * @return List of Notes at the current Beat.
   */
  @Override
  public List<Note> getCurrentNotes(int currentBeat) {
    return model.getCurrentNotes(currentBeat);
  }

}
