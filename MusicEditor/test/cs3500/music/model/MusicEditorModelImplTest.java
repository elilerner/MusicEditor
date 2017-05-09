package cs3500.music.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jeanpaul on 3/2/2017.
 */
public class MusicEditorModelImplTest {
  MusicEditorModelImpl musicEditor;

  /**
   * Initializes a musicEditor.
   */
  @Before
  public void startUp() {
    musicEditor = new MusicEditorModelImpl();
  }

  /**
   * Tests that it is adding nodes correctly.
   */
  @Test
  public void testAddNote() {
    assertEquals(0, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(7, 1, 40, Pitch.D, 7, 0));
    assertEquals(1, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(6, 6, 23, Pitch.F, 2, 25));
    assertEquals(2, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(2, 7, 100, Pitch.ASharp, 6, 15));
    assertEquals(3, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(2, 7, 100, Pitch.ASharp, 6, 15));
    assertEquals(3, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(2, 7, 100, Pitch.DSharp, 6, 15));
    assertEquals(4, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(5, 7, 100, Pitch.DSharp, 6, 15));
    assertEquals(4, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(5, 7, 100, Pitch.DSharp, 6, 10));
    assertEquals(5, musicEditor.getLoNote().size());
  }

  /**
   * Test that the Model does not add a note with its AddNote method if the same note is passed in.
   */
  @Test
  public void testAddSameNote() {
    musicEditor.addNote(new Note(2, 7, 100, Pitch.ASharp, 6, 15));
    assertEquals(1, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(2, 7, 100, Pitch.ASharp, 6, 15));
    assertEquals(1, musicEditor.getLoNote().size());
    musicEditor.addNote(new Note(3, 2, 34, Pitch.ASharp, 6, 14));
    assertEquals(1, musicEditor.getLoNote().size());
  }

  /**
   * Tests the remove method in model.
   */
  @Test
  public void testRemoveNote() {
    musicEditor.addNote(new Note(7, 1, 40, Pitch.D, 7, 0));
    musicEditor.addNote(new Note(6, 6, 23, Pitch.F, 2, 25));
    musicEditor.addNote(new Note(6, 6, 23, Pitch.E, 2, 25));
    musicEditor.addNote(new Note(3, 4, 87, Pitch.ASharp, 2, 25));
    musicEditor.addNote(new Note(53, 2, 34, Pitch.DSharp, 2, 25));
    assertEquals(5, musicEditor.getLoNote().size());
    musicEditor.removeNote(new Note(6, 6, 23, Pitch.F, 2, 25));
    assertEquals(4, musicEditor.getLoNote().size());
    musicEditor.removeNote(new Note(2, 6, 30, Pitch.DSharp, 7, 0));
    assertEquals(4, musicEditor.getLoNote().size());
    musicEditor.removeNote(new Note(7, 1, 40, Pitch.D, 7, 0));
    assertEquals(3, musicEditor.getLoNote().size());
    musicEditor.removeNote(new Note(53, 2, 34, Pitch.DSharp, 2, 25));
    assertEquals(2, musicEditor.getLoNote().size());
  }

  /**
   * Tests the CombineS Method in the model.
   */
  @Test
  public void testCombineS() {
    MusicEditorModelImpl musicEditor2 = new MusicEditorModelImpl();
    Note test = new Note(4, 1, 32, Pitch.ASharp, 10, 3);
    Note test2 = new Note(7, 7, 85, Pitch.FSharp, 7, 7);
    Note test3 = new Note(9, 3, 6, Pitch.E, 4, 5);
    Note test4 = new Note(2, 5, 43, Pitch.D, 5, 12);
    musicEditor.addNote(test);
    musicEditor.addNote(test2);
    musicEditor.addNote(test4);
    musicEditor2.addNote(test);
    musicEditor2.addNote(test3);
    musicEditor.combineS(musicEditor2);
    assertEquals(new ArrayList<>(Arrays.asList(test, test2, test4, test3)),
            musicEditor.getLoNote());
    assertEquals(new ArrayList<>(Arrays.asList(test, test3)),
            musicEditor2.getLoNote());
  }

  /**
   * Tests the CombineC method in the model.
   */
  @Test
  public void testCombineC() {
    MusicEditorModelImpl musicEditor2 = new MusicEditorModelImpl();
    Note test1 = new Note(1, 5, 32, Pitch.DSharp, 4, 0);
    Note test2 = new Note(4, 7, 23, Pitch.B, 3, 5);
    Note test3 = new Note(4, 2, 76, Pitch.E, 3, 1);
    Note test4 = new Note(6, 8, 88, Pitch.ASharp, 4, 0);
    Note test5 = new Note(4, 7, 23, Pitch.B, 3, 5);
    Note test6 = new Note(6, 8, 88, Pitch.ASharp, 4, 0);
    Note test7 = new Note(1, 5, 32, Pitch.DSharp, 4, 0);
    musicEditor.addNote(test1);
    musicEditor.addNote(test7);
    musicEditor.addNote(test5);
    musicEditor.addNote(test2);
    musicEditor2.addNote(test4);
    musicEditor2.addNote(test3);
    musicEditor2.addNote(test6);
    musicEditor.combineC(musicEditor2);
    assertEquals(
            new ArrayList<Note>(Arrays.asList(test1, test2,
                    new Note(6, 8, 88, Pitch.ASharp, 4, 8),
                    new Note(4, 2, 76, Pitch.E, 3, 9))),
            musicEditor.getLoNote());
  }

  /**
   * Tests the GetState Method from MusicEditorModelImpl.
   * For testing purposes the format style for this test is not at the recommended 100 line length.
   */
  @Test
  public void testGetState() {
    MusicEditorModelImpl test = new MusicEditorModelImpl();

    // melody
    test.addNote(new Note(7, 4, 84, Pitch.DSharp, 1, 0));
    test.addNote(new Note(5, 7, 96, Pitch.D, 1, 3));
    test.addNote(new Note(7, 2, 43, Pitch.B, 1, 5));
    test.addNote(new Note(4, 8, 45, Pitch.E, 1, 9));
    test.addNote(new Note(8, 2, 98, Pitch.ASharp, 1, 3));
    test.addNote(new Note(3, 5, 44, Pitch.C, 1, 6));
    test.addNote(new Note(5, 3, 32, Pitch.CSharp, 1, 12));
    test.addNote(new Note(6, 5, 65, Pitch.F, 1, 24));
    test.addNote(new Note(9, 7, 34, Pitch.FSharp, 1, 23));
    test.addNote(new Note(5, 6, 87, Pitch.G, 1, 0));
    test.addNote(new Note(9, 2, 23, Pitch.GSharp, 1, 34));
    test.addNote(new Note(2, 8, 54, Pitch.ASharp, 1, 15));
    test.addNote(new Note(6, 9, 24, Pitch.A, 1, 18));
    test.addNote(new Note(7, 1, 65, Pitch.B, 1, 26));
    test.addNote(new Note(4, 4, 6, Pitch.GSharp, 1, 20));
    test.addNote(new Note(8, 2, 4, Pitch.CSharp, 1, 18));
    test.addNote(new Note(3, 6, 76, Pitch.F, 1, 10));
    test.addNote(new Note(2, 3, 34, Pitch.CSharp, 1, 26));
    test.addNote(new Note(7, 7, 54, Pitch.GSharp, 1, 30));
    test.addNote(new Note(3, 3, 64, Pitch.D, 1, 2));
    test.addNote(new Note(10, 3, 64, Pitch.C, 2, 1));


    assertEquals("     C1  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1\n" +
                    "  0                 X                   X                      \n" +
                    "  1                 |                   |                      \n" +
                    "  2                 |                   |                      \n" +
                    "  3            X    |                   |              X       \n" +
                    "  4            |    |                   |              |       \n" +
                    "  5            |    |                                  |    X  \n" +
                    "  6  X         |    |                                  |    |  \n" +
                    "  7  |         |                                       |    |  \n" +
                    "  8  |                                                 |    |  \n" +
                    "  9                      X                             |    |  \n" +
                    " 10                      |    X                        |    |  \n" +
                    " 11                      |    |                             |  \n" +
                    " 12       X              |    |                                \n" +
                    " 13       |                                                    \n" +
                    " 14       |                                                    \n" +
                    " 15       |                                            X       \n" +
                    " 16       |                                            |       \n" +
                    " 17                                                            \n" +
                    " 18       X                                       X            \n" +
                    " 19       |                                       |            \n" +
                    " 20       |                                  X    |            \n" +
                    " 21       |                                  |    |            \n" +
                    " 22       |                                  |    |            \n" +
                    " 23       |                        X         |    |            \n" +
                    " 24       |                   X    |                           \n" +
                    " 25       |                   |    |                           \n" +
                    " 26       X                   |    |                        X  \n" +
                    " 27       |                   |    |                        |  \n" +
                    " 28                           |    |                        |  \n" +
                    " 29                           |    |                        |  \n" +
                    " 30                                |                        |  \n" +
                    " 31                                |                        |  \n" +
                    " 32                                                         |  \n" +
                    " 33                                                            \n" +
                    " 34                                          X                 \n" +
                    " 35                                          |                 \n" +
                    " 36                                          |                 \n" +
                    " 37                                          |                 \n" +
                    " 38                                          |                 \n" +
                    " 39                                          |                 \n" +
                    " 40                                          |                 \n" +
                    " 41                                          |                 \n" +
                    " 42                                          |                 ",
            test.getState());
  }

  @Test
  public void testEmptyState() {
    MusicEditorModelImpl test = new MusicEditorModelImpl();
    assertEquals("",
            test.getState());
  }

  @Test
          (expected = IllegalArgumentException.class)
  public void testCombineCInvalid() {
    musicEditor.combineC(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineSInvalid() {
    musicEditor.combineS(null);
  }

  /**
   * tests that method addNote Throws an Exception if a null Note is provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNoteInvalid() {
    musicEditor.addNote(null);
  }

  /**
   * tests that method addNote Throws an Exception if a null Note is provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteInvalid() {
    musicEditor.removeNote(null);
  }
}