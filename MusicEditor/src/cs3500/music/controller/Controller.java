package cs3500.music.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicEditorModel;
import cs3500.music.view.CompositeView;

/**
 * Created by jeanpaul on 3/30/2017.
 */

/**
 * Controller for the CompositeView.
 */
public class Controller implements IController {
  private MusicEditorModelImpl model;
  private CompositeView view;
  private boolean playing;
  private boolean state = false;

  /**
   * Initializes the Controller.
   *
   * @param model the model the Controller will be working with.
   */
  public Controller(MusicEditorModelImpl model) {
    this.model = model;
    this.view = new CompositeView(new ReadOnlyMusicEditorModel(model));
    this.playing = true;
    configureKeyBoardHandler();
    configureButtonHandler();
  }

  /**
   * Creates and sets a keyboard listener for the view.
   */
  @Override
  public void configureKeyBoardHandler() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_HOME, new Start());
    keyPresses.put(KeyEvent.VK_END, new End());
    keyPresses.put(KeyEvent.VK_SPACE, new PlayPause());
    keyPresses.put(KeyEvent.VK_LEFT, new ScrollLeft());
    keyPresses.put(KeyEvent.VK_RIGHT, new ScrollRight());

    KeyboardHandler k = new KeyboardHandler();
    k.setKeyPressedMap(keyPresses);
    k.setKeyReleasedMap(keyReleases);
    k.setKeyTypedMap(keyTypes);
    view.addKeyListener(k);
  }

  /**
   * Creates and sets a Mouse Listener to the view.
   */
  @Override
  public void configureButtonHandler() {
    MouseHandler m = new MouseHandler();
    m.setClicked(MouseEvent.BUTTON1, new AddNote());

    view.addMouseListener(m);
  }

  /**
   * Starts the Controller.
   * Renders the view.
   */
  private void start() {
    try {
      view.render();
      if (playing) {
        nextBeat();
      }
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    state = true;
  }

  /**
   * What runs the Controller.
   * Timer allows for running of entire program.
   */
  @Override
  public void run() {
    try {
      SwingUtilities.invokeLater(() -> {
        Timer time = new Timer(model.getTempo() / 1000,
                (event -> start()));
        time.setInitialDelay(0);
        time.start();
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * checks if rendered correctly.
   *
   * @return true if rendered correrctly. Otherwise false.
   */
  @Override
  public boolean state() {
    return state;
  }

  /**
   * gets the class Start.
   *
   * @return a new start class.
   */
  @Override
  public Start getClassStart() {
    return new Start();
  }

  /**
   * gets the class End.
   *
   * @return a new end class.
   */
  @Override
  public End getClassEnd() {
    return new End();
  }

  /**
   * gets the class PlayPause.
   *
   * @return a new PlayPause.
   */
  @Override
  public PlayPause getClassPlayPause() {
    return new PlayPause();
  }

  /**
   * gets the class Scroll Left.
   *
   * @return a class Scroll Left.
   */
  @Override
  public ScrollLeft getClassScrollLeft() {
    return new ScrollLeft();
  }

  /**
   * gets the class Scroll Right.
   *
   * @return a class Scroll Right.
   */
  @Override
  public ScrollRight getClassScrollRight() {
    return new ScrollRight();
  }

  /**
   * tells if currently Playing.
   *
   * @return true if playing. False is Paused.
   */
  @Override
  public boolean isPlaying() {
    return playing;
  }

  /**
   * Updates the beat.
   */
  private void nextBeat() {
    model.setCurrentBeat(model.getCurrentBeat() + 1);
    view.updateRed(20);
  }

  //------------------ADD NOTES-----------------------------------------

  /**
   * Class that adds a Note to the model.
   */
  public class AddNote implements Runnable {

    /**
     * adds a Note to the model.
     */
    @Override
    public void run() {
      if (!playing) {
        Point mouseLoc;
        mouseLoc = view.currentMousePosition();
        Note temp = view.currentNoteAtPoint(mouseLoc);
        Note n = new Note(1, 1, 64, temp.getPitch(), temp.getOctave(), temp.getStart());
        model.addNote(n);
        try {
          view.render();
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
        view.updateRed(20);
      } else {
        throw new IllegalArgumentException("pause please");
      }
    }
  }

  /**
   * Class that goes to the start of the Composition.
   */
  public class Start implements Runnable {

    /**
     * Goes to the start of the Composition.
     */
    @Override
    public void run() {
      view.updateRed(45);
      model.setCurrentBeat(view.redLineBeat());

    }
  }


  /**
   * Class that goes to the end of the Composition.
   */
  public class End implements Runnable {

    /**
     * Goes to the end of the Composition.
     */
    @Override
    public void run() {
      view.updateRed(46);
      model.setCurrentBeat(view.redLineBeat());

    }
  }

  /**
   * Class that Plays and Pauses the view.
   */
  public class PlayPause implements Runnable {
    /**
     * Pchanges the current Play/Pause state.
     */
    @Override
    public void run() {
      playing = !playing;
      view.playPause();
    }
  }

  /**
   * Scrolls the Red Line to the Left.
   */
  public class ScrollLeft implements Runnable {

    /**
     * Scrolls the Red Line to the Left.
     */
    @Override
    public void run() {
      if (!playing) {
        view.updateRed(-20);
        view.updateScroll();
        model.setCurrentBeat(view.redLineBeat());
      } else {
        throw new IllegalArgumentException("Pause PLease");
      }
    }
  }

  /**
   * Scrolls the Red Line to the Right.
   */
  public class ScrollRight implements Runnable {

    /**
     * Scrolls the Red Line to the Right.
     */
    @Override
    public void run() {
      if (!playing) {
        view.updateRed(20);
        view.updateScroll();
        model.setCurrentBeat(view.redLineBeat());
      } else {
        throw new IllegalArgumentException("PausePlease");
      }
    }
  }


}