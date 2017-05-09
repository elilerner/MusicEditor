package cs3500.music.model;

import org.junit.Test;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.controller.Controller;
import cs3500.music.controller.KeyboardHandler;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jeanpaul on 4/5/2017.
 */
public class ControllerTest {

  Controller controller;
  KeyboardHandler keyboardHandler;
  Map<Integer, Runnable> keyPresses = new HashMap<>();
  MusicEditorModelImpl model;
  ReadOnlyMusicEditorModel readModel;
  Note n1;
  Note n2;
  Note n3;

  /**
   * Tests the Controller.
   */
  public ControllerTest() {
    n1 = new Note(2, 4, 4, Pitch.C, 1, 0);
    n2 = new Note(3, 5, 5, Pitch.B, 1, 2);
    n3 = new Note(10, 1, 3, Pitch.ASharp, 1, 3);
    ArrayList<Note> notes = new ArrayList<>(Arrays.asList(n1, n2, n3));
    model = new MusicEditorModelImpl(notes, 20000);
    readModel = new ReadOnlyMusicEditorModel(model);
    keyboardHandler = new KeyboardHandler();
    this.controller = new Controller(model);

    keyPresses.put(KeyEvent.VK_DOWN, controller.getClassStart());
    keyPresses.put(KeyEvent.VK_UP, controller.getClassEnd());
    keyPresses.put(KeyEvent.VK_SPACE, controller.getClassPlayPause());
    keyPresses.put(KeyEvent.VK_LEFT, controller.getClassScrollLeft());
    keyPresses.put(KeyEvent.VK_RIGHT, controller.getClassScrollRight());

  }

  public void init() {
    controller = new Controller(model);
    controller.run();
  }


  @Test
  public void testControllerDown() {


    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_DOWN);
    keyPresses.get(kv.getKeyCode()).run();

    assertEquals(model.getCurrentBeat(), 0);

  }

  @Test
  public void testControllerUp() {

    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_UP);
    keyPresses.get(kv.getKeyCode()).run();

    assertEquals(model.getCurrentBeat(), 13);
  }

  @Test
  public void testControllerSpace() {

    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_SPACE);
    keyPresses.get(kv.getKeyCode()).run();

    assertEquals(controller.isPlaying(), false);
  }

  @Test
  public void testControllerSpace1() {

    assertEquals(controller.isPlaying(), true);

    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_SPACE);
    keyPresses.get(kv.getKeyCode()).run();

    assertEquals(controller.isPlaying(), false);

    keyPresses.get(kv.getKeyCode()).run();

    assertEquals(controller.isPlaying(), true);

  }

  @Test
  public void testControllerRight() {
    this.init();

    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_SPACE);
    keyPresses.get(kv.getKeyCode()).run();
    kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_RIGHT);
    keyPresses.get(kv.getKeyCode()).run();
    assertEquals(model.getCurrentBeat(), 1);

  }

  @Test
  public void testControllerRight1() {
    // this.init();
    controller.run();

    assertEquals(model.getCurrentBeat(), 0);
    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_SPACE);
    keyPresses.get(kv.getKeyCode()).run();
    kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_RIGHT);
    keyPresses.get(kv.getKeyCode()).run();
    assertEquals(model.getCurrentBeat(), 1);
    keyPresses.get(kv.getKeyCode()).run();
    assertEquals(model.getCurrentBeat(), 2);

  }


  @Test
  public void testControllerLeft() {
    // this.init();
    controller.run();

    assertEquals(model.getCurrentBeat(), 0);
    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_SPACE);
    keyPresses.get(kv.getKeyCode()).run();
    kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_RIGHT);
    keyPresses.get(kv.getKeyCode()).run();
    assertEquals(model.getCurrentBeat(), 1);
    keyPresses.get(kv.getKeyCode()).run();
    assertEquals(model.getCurrentBeat(), 2);

    kv = new KeyEvent(new Button("test"), 1, 2, 1, KeyEvent.VK_LEFT);
    keyPresses.get(kv.getKeyCode()).run();

    assertEquals(model.getCurrentBeat(), 1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testControllerExceptionNotPausedLeftOrRight() {
    this.init();

    KeyEvent kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_RIGHT);
    keyPresses.get(kv.getKeyCode()).run();

    kv = new KeyEvent(new Button("test"), 1, 1, 1, KeyEvent.VK_LEFT);
    keyPresses.get(kv.getKeyCode()).run();
  }

  @Test
  public void TestAddNotesOnMouseEvent() {
    this.init();
    controller.configureButtonHandler();
    Runnable leftClicked = new Runnable() {
      @Override
      public void run() {
        model.addNote(new Note(2, 3, 3, Pitch.ASharp, 2, 0));
      }
    };

    assertEquals(model.getLoNote().size(), 3);


    leftClicked.run();

    assertEquals(model.getLoNote().size(), 4);


  }

  @Test(expected = NullPointerException.class)
  public void testControllerExceptionNullModel() {
    controller = new Controller(null);
  }
}
