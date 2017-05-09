package cs3500.music.view;


import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.ReadOnlyMusicEditorModel;

/**
 * Draws everything in the gui.
 */
public class ConcreteGuiViewPanel extends JPanel {
  private List<Rectangle> recNotes = new ArrayList<>();
  public static final int BEAT_WIDTH = 20;
  public static final int NOTE_HEIGHT = 20;
  public static final int SIDE_WIDTH = 20;
  public static final int FIRSTX = BEAT_WIDTH + SIDE_WIDTH + 5;
  public int redLineLocation;
  private ReadOnlyMusicEditorModel model;
  public int currentScroll;


  /**
   * Constructor for the ConcreteGuiViewPanel.
   */
  public ConcreteGuiViewPanel(ReadOnlyMusicEditorModel model) {
    super();
    this.model = model;
    this.redLineLocation = FIRSTX;
  }

  /**
   * Paints everything into the screen.
   *
   * @param g given Graphics.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawNotes(g);
    drawNoteLabels(g);
    drawMeasures(g);
    drawRedLine(g);
  }

  //Draws the notes
  private void drawNotes(Graphics g) {
    int noteYPosition;
    Note currentNote;
    recNotes.clear();
    List<Note> loNote = model.getLoNote();
    for (int i = 0; i < loNote.size(); i++) {
      currentNote = loNote.get(i);
      noteYPosition = yPositition(currentNote);
      Rectangle currentRectangle;
      currentRectangle = new Rectangle((currentNote.getStart()) * BEAT_WIDTH + FIRSTX,
              noteYPosition, BEAT_WIDTH * currentNote.getDuration(), NOTE_HEIGHT);
      recNotes.add(currentRectangle);
      g.setColor(Color.GREEN);
      g.fillRect(currentRectangle.x, currentRectangle.y, currentRectangle.width,
              currentRectangle.height);
      g.setColor(Color.BLACK);
      g.fillRect((currentNote.getStart()) * BEAT_WIDTH + FIRSTX, noteYPosition,
              BEAT_WIDTH, NOTE_HEIGHT);
    }
  }

  /**
   * Drwas the Labels of the notes.
   *
   * @param g Graphics.
   */
  private void drawNoteLabels(Graphics g) {
    int current = 0;
    int lengthOfSong = model.beatLength();
    List<String> range = model.range();
    for (int i = range.size() - 1; i >= 0; i--) {
      String currentRange = range.get(i);
      int y = current * NOTE_HEIGHT + NOTE_HEIGHT * 2;
      g.drawString(currentRange, SIDE_WIDTH / 3, y - 5);
      g.drawLine(FIRSTX, y, ((lengthOfSong + (lengthOfSong % 4)) * BEAT_WIDTH) + 5, y);
      current++;
    }
  }

  /**
   * Draws the measures.
   */
  private void drawMeasures(Graphics g) {
    List<String> range = model.range();
    int lengthOfSong = model.beatLength();
    g.setColor(Color.BLACK);
    g.drawLine(FIRSTX, NOTE_HEIGHT, ((lengthOfSong + (lengthOfSong % 4)) * BEAT_WIDTH) + 5,
            NOTE_HEIGHT);
    for (int i = 0; i <= lengthOfSong + (lengthOfSong % 4); i++) {
      int xValue = (i + 1) * BEAT_WIDTH + SIDE_WIDTH + 5;
      if (i % 4 == 0) {
        g.drawLine(xValue, NOTE_HEIGHT, xValue, (range.size() + 1) * NOTE_HEIGHT);
        g.drawString(Integer.toString(i), xValue, NOTE_HEIGHT);
      }
    }
  }

  /**
   * @param n the note.
   * @return the Y position of the Note.
   */
  private int yPositition(Note n) {
    //  System.out.println("length: " + model.beatLength());
    List<String> range = model.range();
    String last = range.get(range.size() - 1);
    Pitch p = getNotePitch(last);
    int octave = getNoteOctave(last);
    int yPos = (octave - n.getOctave()) * 12 + p.ordinal() - n.getPitch().ordinal();
    yPos = yPos * NOTE_HEIGHT + NOTE_HEIGHT;
    return yPos;
  }

  /**
   * @param note the note.
   * @return the octave.
   */
  private int getNoteOctave(String note) {
    String sharpeNote = note.substring(2);
    int currentOctave;
    String regularNote = note.substring(1);
    if (note.substring(1, 2).equals("#")) {
      currentOctave = Integer.valueOf(sharpeNote); //check
    } else {
      currentOctave = Integer.valueOf(regularNote); //check
    }
    return currentOctave;
  }


  /**
   * gets the pitches of the note
   *
   * @return the Pitch of the Note.
   */
  private Pitch getNotePitch(String pitch) {
    Pitch currentPitch;
    if (pitch.substring(1, 2).equals("#")) {
      currentPitch = Pitch.valueOf(pitch.substring(0, 1) + "Sharp");
    } else {
      currentPitch = Pitch.valueOf(pitch.substring(0, 1));
    }
    return currentPitch;
  }


  /**
   * draws the red Line.
   *
   * @param g Graphics.
   */
  private void drawRedLine(Graphics g) {
    List<String> range = model.range();
    g.setColor(Color.RED);
    g.drawLine(redLineLocation, NOTE_HEIGHT, redLineLocation, NOTE_HEIGHT + NOTE_HEIGHT
            * range.size());
  }

  /**
   * updates the position of the line.
   *
   * @param number a number.
   */
  public void updateRedLine(int number) {
    if (redLineLocation >= (FIRSTX + model.beatLength() * 21)) {
      //I DO NOT WANT THE RED LINE TO BE UPDATED IF THIS IS TRUE
    }
    if (number == FIRSTX) {
      redLineLocation = FIRSTX;
    } else if (number > FIRSTX) {
      redLineLocation = FIRSTX + (model.beatLength() + 1) * 20;
    } else if (redLineLocation == FIRSTX && number < 0) {
      //I DO NOT WANT THE RED LINE TO BE UPDATE IN THIS IF
    } else {
      redLineLocation += number;
    }
  }


  //Dimensions that change based on the beat length or range size.
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(FIRSTX + model.beatLength() * 21, FIRSTX + model.range().size()
            * 20 + 100);
  }

  /**
   * gets the current Red Line Location.
   *
   * @return the red line location.
   */
  public int getRedLineLocation() {
    return redLineLocation;
  }

  /**
   * Gets the CurrentNotes at the given Mouse Point.
   *
   * @param mouse the current Mouse Location.
   * @return the note at the given Point.
   */
  public Note currentNoteAtPoint(Point mouse) {
    List<String> range = model.range();
    boolean pixelRange = mouse.getY() > FIRSTX
            && mouse.getY() < 3000;
    if (pixelRange) {
      int ind = ((int) (mouse.getY() - 12) / NOTE_HEIGHT) - 4 + (currentScroll / 20);
      String sNote = range.get(range.size() - 1 - ind);
      System.out.println("sNote: " + sNote);
      int octave = getNoteOctave(sNote);
      System.out.println("o: " + octave);
      Pitch p = getNotePitch(sNote);
      System.out.println("P: " + p);
      int redLineBeat = (redLineLocation - FIRSTX) / 20;
      return new Note(1, 1, 64, p, octave, redLineBeat);
    } else {
      throw new IllegalArgumentException("Out of bounds");
    }
  }

  /**
   * Gets the Notes at the current Red Line Location.
   */
  public List<Note> getNotesAtRedLineLocation() {
    return model.getCurrentNotes((redLineLocation - 45) / 20);
  }


  /**
   * Gets the beat that the red line is currently at.
   *
   * @return the current beat.
   */
  public int getRedLineBeat() {
    return (redLineLocation - FIRSTX) / 20;
  }
}


