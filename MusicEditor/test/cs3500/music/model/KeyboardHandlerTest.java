package cs3500.music.model;

import org.junit.Test;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.controller.KeyboardHandler;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jeanpaul on 4/5/2017.
 */

public class KeyboardHandlerTest {

  private int checkPressed = 0;

  private void testAddKey(int pressed) {
    checkPressed = pressed;
  }

  @Test
  public void testKeys() {
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    Map<Integer, Runnable> keyPressedMap = new HashMap<>();
    Map<Integer, Runnable> keyReleasedMap = new HashMap<>();

    keyPressedMap.put(1, () -> testAddKey(1));

    keyboardHandler.setKeyPressedMap(keyPressedMap);

    KeyEvent key1 = new KeyEvent(new Button("test"), 1, 1, 1, 1, ' ');

    keyboardHandler.keyPressed(key1);
    assertEquals(1, checkPressed);
  }
}
