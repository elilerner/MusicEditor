package cs3500.music.model;

import org.junit.Test;

import java.awt.Button;
import java.awt.event.MouseEvent;

import cs3500.music.controller.MouseHandler;

import static junit.framework.TestCase.assertEquals;

//add more tests

/**
 * Created by jeanpaul on 4/5/2017.
 */
public class MouseHandlerTest {

  private int checkClicked = 0;

  private void testAddClick(int pressed) {
    checkClicked = pressed;
  }

  @Test
  public void testMouse() {
    MouseHandler mouseHandler = new MouseHandler();
    mouseHandler.setClicked(MouseEvent.BUTTON1, () -> testAddClick(1));
    MouseEvent mouse1 = new MouseEvent(new Button("test"), 1, 1, 1, 1, 1,
            1, false, MouseEvent.BUTTON1);

    mouseHandler.mouseClicked(mouse1);
    assertEquals(checkClicked, 1);
  }
}
