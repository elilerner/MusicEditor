package cs3500.music.view;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import cs3500.music.model.Note;
import cs3500.music.model.NoteValue;
import cs3500.music.model.Pitch;


/**
 * The View for the Piano.
 */
public class PianoGuiView extends JPanel {

  private ArrayList<Rectangle> pianoCords = new ArrayList<>();
  private final int PIANO_KEY_HEIGHT = 120;                      // 120
  private final int PIANO_KEY_WIDTH = PIANO_KEY_HEIGHT / 10;     // 12
  private final int BLACK_PIANO_KEY_LENGTH = (PIANO_KEY_HEIGHT / 5) * 2;    // 48
  private final int BLACK_PIANO_KEY_WIDTH = PIANO_KEY_WIDTH / 2;  // 6
  private final int KEY_COUNT = 120;
  private final int PIANO_WIDTH = PIANO_KEY_WIDTH * KEY_COUNT;
  // private List<Integer> currentNotes;
  private ConcreteGuiViewPanel concreteGuiViewPanel;

  LinkedHashMap<NoteValue, Rectangle> hashMap = new LinkedHashMap<NoteValue, Rectangle>();


  /**
   * Constructs the Piano.
   *
   * @param concrete the concrete view.
   */
  PianoGuiView(ConcreteGuiViewPanel concrete) {
    super();
    concreteGuiViewPanel = concrete;
  }

  /**
   * paints the Components.
   *
   * @param g graphics.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawPiano(g);
  }

  /**
   * Draws the piano.
   *
   * @param g the Graphics.
   */
  public void drawPiano(Graphics g) {

    hashMap = new LinkedHashMap<NoteValue, Rectangle>();

    Rectangle currentRectangle;
    int currentPitch = 0;
    int currentOctave;
    int currentPitch2 = 0;

    int temp = -1;
    for (int i = 0; i < 70; i++) {
      temp++;
      currentPitch = temp % 12;
      currentPitch2 = i % 7;
      currentOctave = temp / 12;

      currentRectangle = new Rectangle((PIANO_KEY_WIDTH * i) + 20, 0,
              PIANO_KEY_WIDTH, PIANO_KEY_HEIGHT);


      hashMap.put(new NoteValue(Pitch.values()[currentPitch], currentOctave), currentRectangle);
      g.setColor(Color.WHITE);
      g.fillRect(currentRectangle.x, currentRectangle.y, PIANO_KEY_WIDTH, PIANO_KEY_HEIGHT);

      g.setColor(Color.BLACK);
      g.drawRect(currentRectangle.x, currentRectangle.y, PIANO_KEY_WIDTH, PIANO_KEY_HEIGHT);
      // }

      if (currentPitch2 == 0 || currentPitch2 == 1 || currentPitch2 == 3
              || currentPitch2 == 4 || currentPitch2 == 5) {
        temp++;
        currentPitch = temp % 12;
        currentOctave = temp / 12;

        currentRectangle = new Rectangle((PIANO_KEY_WIDTH * i) + 27, 0, BLACK_PIANO_KEY_WIDTH,
                BLACK_PIANO_KEY_LENGTH);

        hashMap.put(new NoteValue(Pitch.values()[currentPitch], currentOctave), currentRectangle);

        g.setColor(Color.BLACK);
        g.fillRect(currentRectangle.x, currentRectangle.y, currentRectangle.width,
                currentRectangle.height);
      }
    }
    System.out.println(temp);
    System.out.println(hashMap);


    ArrayList<NoteValue> array = new ArrayList<>();
    ArrayList<Integer> indexArray = new ArrayList<>();

    for (Note n : concreteGuiViewPanel.getNotesAtRedLineLocation()) {
      for (NoteValue nv : this.getHashMap().keySet()) {
        if (n.getPitch() == nv.getNotePitch() && n.getOctave() == nv.getNoteOctave()) {
          array.add(nv);
        }

      }
    }
    for (int j = 0; j < hashMap.size(); j++) {
      for (int i = 0; i < array.size(); i++) {
        if (array.get(i).equals(hashMap.keySet().toArray()[j])) {
          indexArray.add(j);
        }
      }
    }
    for (int n : indexArray) {
      Rectangle rect = (Rectangle) hashMap.values().toArray()[n];
      System.out.println(hashMap.keySet().toArray()[n]);
      System.out.println(concreteGuiViewPanel.getNotesAtRedLineLocation());
      g.setColor(Color.ORANGE);
      g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
  }

  /**
   * gets the Hash map.
   * @return the hasMap for the given NoteValue.
   */
  public HashMap<NoteValue, Rectangle> getHashMap() {
    return hashMap;
  }


}

