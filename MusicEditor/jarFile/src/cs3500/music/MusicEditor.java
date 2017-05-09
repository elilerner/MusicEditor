package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.ControllerFactory;
import cs3500.music.controller.IController;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.util.MusicReader;

/**
 * Created by jeanpaul on 3/14/2017.
 */
public class MusicEditor {
  /**
   * Initializes the Model and view.
   */
  public static void main(String[] args) {
    try {
      MusicEditorModelImpl model =
              MusicReader.parseFile(new FileReader(args[0]), new MusicEditorModelImpl.Builder());
      ControllerFactory consoleFactory = new ControllerFactory(model);
      IController controller = consoleFactory.createController(args[1]);
      controller.run();
    } catch (FileNotFoundException fn) {
      fn.printStackTrace();
    }
  }
}

