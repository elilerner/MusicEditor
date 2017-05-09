package cs3500.music.controller;

import java.awt.event.MouseEvent;

/**
 * Created by jeanpaul on 3/28/2017.
 */

/**
 * Class for MouseHandler.
 * Handles all needed Mouse Events.
 */
public class MouseHandler implements java.awt.event.MouseListener {
  private Runnable leftClicked;
  private Runnable middleClicked;
  private Runnable rightClicked;

  /**
   * sets the left/middle/right clicks.
   *
   * @param button   the button being clicked.
   * @param runnable he runnable.
   */
  public void setClicked(int button, Runnable runnable) {
    if (button == MouseEvent.BUTTON1) {
      this.leftClicked = runnable;
    } else if (button == MouseEvent.BUTTON2) {
      this.middleClicked = runnable;
    } else if (button == MouseEvent.BUTTON3) {
      this.rightClicked = runnable;
    }
  }

  /**
   * Invoked when the mouse button has been clicked pressed and released) on a component.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      this.leftClicked.run();
    } else if (e.getButton() == MouseEvent.BUTTON2) {
      this.middleClicked.run();
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      this.rightClicked.run();
    }
  }


  //Invooked when a mouse button has been pressed on a component
  @Override
  public void mousePressed(MouseEvent e) {
  //DO NOT USE
  }

  //Invoked when a mouse button has been released on a component
  @Override
  public void mouseReleased(MouseEvent e) {
    //DO NOT USE

  }

  //Invoked when the mouse enters a component
  @Override
  public void mouseEntered(MouseEvent e) {
  //DO NOT USE
  }

  //Invoked when the mouse exits a component
  @Override
  public void mouseExited(MouseEvent e) {
  //DO NOT USE
  }
}
