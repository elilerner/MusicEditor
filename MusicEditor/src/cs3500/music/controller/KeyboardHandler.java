package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeanpaul on 3/28/2017.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Character, Runnable> keyTypedMap;
  private Map<Integer, Runnable> keyPressedMap;
  private Map<Integer, Runnable> keyReleasedMap;


  /**
   * Empty default constructor.
   */
  public KeyboardHandler() {
    keyPressedMap = new HashMap<>();
    keyTypedMap = new HashMap<>();
    keyReleasedMap = new HashMap<>();
  }

  //Given to us

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   */
  public void setKeyTypedMap(Map<Character, Runnable> map) {
    keyTypedMap = map;
  }

  //Given to us

  /**
   * Set the map for key pressed events. Key pressed events in Java Swing are integer codes
   */

  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    keyPressedMap = map;
  }

  //Given to us

  /**
   * Set the map for key released events. Key released events in Java Swing are integer codes
   */

  public void setKeyReleasedMap(Map<Integer, Runnable> map) {
    keyReleasedMap = map;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (keyReleasedMap.containsKey(e.getKeyCode())) {
      keyReleasedMap.get(e.getKeyCode()).run();
    }
  }
}
