package cs3500.music.view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;


import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicEditorModel;

/**
 * A skeleton Frame (i.e., a window) in Swing.
 */
public class GuiViewFrame extends JFrame implements IView {

  private final JPanel gui;
  private ConcreteGuiViewPanel concrete;
  private JScrollPane scrollPane;


  /**
   * Creates new GuiView.
   */
  public GuiViewFrame(ReadOnlyMusicEditorModel model) {
    ArrayList<Integer> fake = new ArrayList();
    fake.add(1);
    fake.add(4);
    fake.add(20);
    JPanel piano;
    this.concrete = new ConcreteGuiViewPanel(model);
    piano = new PianoGuiView(concrete);
    this.gui = concrete;
    scrollPane = new JScrollPane(gui, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.setResizable(true);
    // this.setLocationRelativeTo(null);
    this.setLayout(new GridLayout(2, 1));
    this.add(scrollPane);
    this.add(piano);
    this.setTitle("Music Editor");
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.pack();
    this.setFocusable(true);
    this.requestFocus();
    // this.addKeyListener(this);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
  }

  /**
   * Sets the visable to true.
   */
  public void initialize() {
    this.setVisible(true);
  }

  /**
   * gets the preferred size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(950, 680);
  }

  /**
   * renders the gui.
   */
  public void render() {
    initialize();
    gui.repaint();
  }

  /**
   * updates the scroll.
   *
   * @param location the current location.
   */
  public void autoScroll(int location) {
    JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
    horizontal.setValue(location);
  }

  /**
   * Gets the Note at the current Mouse location.
   *
   * @param mouse the Mouse location.
   * @return the note at the current mouse location.
   */
  public Note currentNoteAtPoint(Point mouse) {
    if (redLineBeat() % 20 == 0) {
      autoScroll(concrete.getRedLineLocation() - 45);
    }
    return concrete.currentNoteAtPoint(mouse);

  }


  /**
   * adds a mouse listener to this gui.
   */
  @Override
  public void addMouseListener(MouseListener mouse) {
    //   System.out.println("concrete mouse");
    concrete.addMouseListener(mouse);
  }

  /**
   * updates the red line.
   *
   * @param num how much to update the red line by.
   */
  public void updateRed(int num) {
    concrete.updateRedLine(num);
    //concrete.updateScroll(scrollPane.getVerticalScrollBar().getValue());
    if (redLineBeat() % 20 == 0) {
      autoScroll(concrete.getRedLineLocation() - 45);
    }
    repaint();
  }

  /**
   * Updates the scroll.
   */
  public void updateScroll() {
    if (redLineBeat() % 20 == 0) {
      autoScroll(concrete.getRedLineLocation() - 45);

    }
  }

  /**
   * gets the beat the red line is at.
   *
   * @return the beat the red line is at.
   */
  public int redLineBeat() {
    return concrete.getRedLineBeat();
  }
}
