package cs3500.music.controller;

import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.ReadOnlyMusicEditorModel;
import cs3500.music.view.ConsoleView;

/**
 * Created by jeanpaul on 4/5/2017.
 */

/**
 * The class for our Console Controller.
 * Renders the Console View.
 */
public class ConsoleController implements IController {

  private ConsoleView view;
  private boolean state = false;

  /**
   * Initializes the ConsoleController.
   */
  public ConsoleController(MusicEditorModelImpl model) {
    this.view = new ConsoleView(new ReadOnlyMusicEditorModel(model));
  }

  /**
   * Runs the ConsoleController.
   * Renders it to the Console.
   */
  @Override
  public void run() {
    view.render();
    state = true;
  }

  /**
   * Tells if it was able to render correctly.
   *
   * @return true if ran. False if not ran.
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

}
