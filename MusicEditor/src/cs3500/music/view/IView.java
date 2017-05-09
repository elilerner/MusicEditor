package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by jeanpaul on 3/13/2017.
 */

/**
 * Interface used for all the views.
 */
public interface IView {
  /**
   * Displays/Runs the View.
   */
  void render() throws InvalidMidiDataException;

}
