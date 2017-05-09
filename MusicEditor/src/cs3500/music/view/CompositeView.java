package cs3500.music.view;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicEditorModel;

/**
 * Created by jeanpaul on 3/28/2017.
 */

/**
 * Composite View combines the gui and the midi for simultaneous play.
 */
public class CompositeView implements ISuperIView {
  private GuiViewFrame gui;
  private MidiViewImpl midi;
  private boolean on;

  /**
   * Constructor for the Composite view
   *
   * @param model the model used for the gui and the midi.
   */
  public CompositeView(ReadOnlyMusicEditorModel model) {
    this.gui = new GuiViewFrame(model);
    this.midi = new MidiViewImpl(model);
    this.on = true;
  }

  /**
   * renders the midi if on and automatically renders the gui.
   */
  @Override
  public void render() throws InvalidMidiDataException {
    if (on) {
      midi.render();
    }
    gui.render();
  }

  /**
   * Gets the current Mouse Position in the gui.
   *
   * @return The Point of the Mouse.
   */
  @Override
  public Point currentMousePosition() {
    return gui.getMousePosition();
  }


  /**
   * Adds Mouse Listener for the gui.
   *
   * @param mouse a MouseListener.
   */
  @Override
  public void addMouseListener(MouseListener mouse) {
    gui.addMouseListener(mouse);
  }

  /**
   * Adds a Key Listener to the gui.
   *
   * @param keyboard a KeyListener.
   */
  @Override
  public void addKeyListener(KeyListener keyboard) {
    gui.addKeyListener(keyboard);
  }

  /**
   * Gets the current Notes at the given Mouse Point.
   *
   * @param mouse the current Mouse location.
   * @return the Note at the current Location.
   */
  @Override
  public Note currentNoteAtPoint(Point mouse) {

    return gui.currentNoteAtPoint(mouse);
  }

  /**
   * Updates the red Line Location in the gui.
   *
   * @param num how many pixels the red line should move.
   */
  public void updateRed(int num) {
    gui.updateRed(num);
  }

  /**
   * updates the scroll in the gui.
   */
  public void updateScroll() {
    gui.updateScroll();
  }

  /**
   * Changes the current on state.
   */
  public void playPause() {
    on = !on;
  }

  /**
   * gets the current beat the Red Line is at.
   */
  public int redLineBeat() {
    return gui.redLineBeat();
  }

}
