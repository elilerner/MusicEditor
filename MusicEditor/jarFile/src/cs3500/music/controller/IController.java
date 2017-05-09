package cs3500.music.controller;

/**
 * Created by jeanpaul on 4/5/2017.
 */

/**
 * Interface for all the Controllers used.
 * These include GuiMidiController, Controller, and ConsoleController.
 */
public interface IController {

  /**
   * Starts the controller with a Timer.
   */
  void run();


  boolean state();

  /**
   * gets the class Start.
   *
   * @return a new start class.
   */
  Object getClassStart();

  /**
   * gets the class End.
   *
   * @return a new end class.
   */
  Object getClassEnd();

  /**
   * gets the class PlayPause.
   *
   * @return a new PlayPause.
   */
  Object getClassPlayPause();

  /**
   * gets the class Scroll Left.
   *
   * @return a class Scroll Left.
   */
  Object getClassScrollLeft();

  /**
   * gets the class Scroll Right.
   *
   * @return a class Scroll Right.
   */
  Object getClassScrollRight();

  /**
   * tells if currently Playing.
   *
   * @return true if playing. False is Paused.
   */
  boolean isPlaying();

  /**
   * Creates and sets a Mouse Listener to the view.
   */
  void configureButtonHandler();

  /**
   * Creates and sets a keyboard listener for the view.
   */
  void configureKeyBoardHandler();

}
