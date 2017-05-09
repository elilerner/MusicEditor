package cs3500.music.controller;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.ReadOnlyMusicEditorModel;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IView;
import cs3500.music.view.MidiViewImpl;

/**
 * Created by jeanpaul on 4/5/2017.
 */

/**
 * Controller for the Gui and Midi views.
 */
public class GuiMidiController implements IController {
  private MusicEditorModelImpl model;
  private IView view;
  private boolean state = false;

  /**
   * Constructor for the GuiMidiController.
   *
   * @param model    the model the views will be suing.
   * @param viewType either "visual" or "midi".
   */
  public GuiMidiController(MusicEditorModelImpl model, String viewType) {
    this.model = model;
    if (viewType.equals("visual")) {
      this.view = new GuiViewFrame(new ReadOnlyMusicEditorModel(model));
    } else if (viewType.equals("midi")) {
      this.view = new MidiViewImpl(new ReadOnlyMusicEditorModel(model));
    }
  }

  /**
   * Runs the program.
   */
  @Override
  public void run() {
    try {
      SwingUtilities.invokeLater(() -> {
        Timer time = new Timer(model.getTempo() / 1000,
                (event -> nextBeat()));
        time.setInitialDelay(0);
        time.start();
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    state = true;
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public boolean state() {
    return state;
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public Object getClassStart() {
    throw new IllegalArgumentException("Nope");
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public Object getClassEnd() {
    throw new IllegalArgumentException("Nope");
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public Object getClassPlayPause() {
    throw new IllegalArgumentException("Nope");
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public Object getClassScrollLeft() {
    throw new IllegalArgumentException("Nope");
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public Object getClassScrollRight() {
    throw new IllegalArgumentException("Nope");
  }

  /**
   * Not needed for this Controller.
   *
   * @return an exception.
   */
  @Override
  public boolean isPlaying() {
    throw new IllegalArgumentException("Nope");
  }

  //Not needed for this Controller.
  @Override
  public void configureButtonHandler() {
    throw new IllegalArgumentException("Nope");
  }

  //Not needed for this Controller.
  @Override
  public void configureKeyBoardHandler() {
    throw new IllegalArgumentException("Nope");
  }

  /**
   * Goes to the next Beat.
   */
  private void nextBeat() {
    model.setCurrentBeat(model.getCurrentBeat() + 1);
    try {
      view.render();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }
}
