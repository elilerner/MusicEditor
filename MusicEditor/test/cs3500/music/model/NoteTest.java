package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeanpaul on 3/4/2017.
 */
public class NoteTest {
  Note note1 = new Note(2, 2, 2, Pitch.ASharp, 2, 2);
  Note note2 = new Note(2, 2, 2, Pitch.ASharp, 2, 2);
  Note note3 = new Note(6, 3, 3, Pitch.F, 5, 8);

  @Test
  public void testequals() {
    assertEquals(true, note1.equals(note2));
    assertEquals(false, note1.equals(note3));
  }

  @Test
  public void testToString() {
    assertEquals("A#2", note1.toString());
    assertEquals("A#2", note2.toString());
    assertEquals("F5", note3.toString());
  }

  /**
   * @throws Exception if Duration is Negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidDuration() throws Exception {
    Note invalid = new Note(-1, 2, 2, Pitch.ASharp, 2, 2);
  }

  /**
   * @throws Exception if Instrument is Negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidInstrument() throws Exception {
    Note test = new Note(1, -1, 2, Pitch.ASharp, 2, 2);
  }

  /**
   * @throws Exception if Octave is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidOctave() throws Exception {
    Note invalid = new Note(1, 1, 2, Pitch.ASharp, -1, 2);
  }

  /**
   * @throws Exception if Start is Negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidStart() throws Exception {
    Note invalid = new Note(1, 1, 2, Pitch.ASharp, 1, -1);
  }

  /**
   * @throws Exception if Pitch is Negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidPitch() throws Exception {
    Note invalid = new Note(1, 1, 2, null, 1, -1);
  }
}