package cs3500.music.model;

import java.util.List;

/**
 * Created by jeanpaul on 3/1/2017.
 */

/**
 * Interface for the Music Model used in the Model.
 */
public interface IMusicEditorModel {

  /**
   * adds a new note to this model.
   */
  void addNote(Note n);

  /**
   * Removes a note from this model.
   *
   * @param note a Note to be removed.
   */
  void removeNote(Note note);

  /**
   * Gets the current State of the model.
   *
   * @return a String version of the state of the model.
   */
  String getState();

  /**
   * Combines two Model's List of Notes simultaneously.
   *
   * @param model a MusicEditorModelImpl.
   */
  void combineS(MusicEditorModelImpl model);


  /**
   * Combines two Model's List of Notes consecutively.
   *
   * @param model a MusicEditorModelImpl.
   */
  void combineC(MusicEditorModelImpl model);

  /**
   * Gets this LoNote.
   *
   * @return a List of Notes.
   */
  List<Note> getLoNote();

  /**
   * Returns length of beat.
   *
   * @return the Length of the beat.
   */
  int beatLength();

  /**
   * @return A list of String that signify the range in the Notes.
   */
  List<String> range();

  /**
   * The tempo.
   *
   * @return the tempo of the model.
   */
  int getTempo();

  List<Note> getNotesThatStartAt(int currentNote);

  int getCurrentBeat();

  List<Note> getCurrentNotes(int currentBeat);

}

