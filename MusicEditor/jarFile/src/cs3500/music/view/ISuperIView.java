package cs3500.music.view;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.Note;

/**
 * Created by jeanpaul on 3/29/2017.
 */
public interface ISuperIView extends IView {

  /**
   * gets the current x and y position of the Mouse.
   */
  Point currentMousePosition();

  /**
   * adds the Mouse Listener.
   *
   * @param mouse the current Mouse location.
   */
  void addMouseListener(MouseListener mouse);

  /**
   * adds the Key Listener.
   *
   * @param keyboard the key pressed.
   */
  void addKeyListener(KeyListener keyboard);

  /**
   * gets the Note at the current Point.
   *
   * @param mouse The current mouse location.
   * @return the Note at the current Point.
   */
  Note currentNoteAtPoint(Point mouse);

  /**
   * updates the red line.
   *
   * @param num how much the red line is updated by.
   */
  void updateRed(int num);

  /**
   * updates the scroll.
   */
  void updateScroll();

  /**
   * updates if playing or not.
   */
  void playPause();

  /**
   * the current beat the red line is at.
   *
   * @return the current beat the red line is at.
   */
  int redLineBeat();

}
