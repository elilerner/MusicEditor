package cs3500.music.controller;

import org.junit.Test;

import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeanpaul on 4/5/2017.
 */
public class ConsoleControllerTest {

  @Test
  public void testConsole() {
    MusicEditorModelImpl test = new MusicEditorModelImpl();

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
    ControllerFactory factory = new ControllerFactory(test);
    IController console = factory.createController("console");
    console.run();
    assertEquals(true, console.state());
  }
}