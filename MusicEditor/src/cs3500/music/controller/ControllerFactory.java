package cs3500.music.controller;

import cs3500.music.model.MusicEditorModelImpl;

/**
 * Created by jeanpaul on 4/5/2017.
 */

/**
 * Factory for the Controllers.
 */
public class ControllerFactory {
  private MusicEditorModelImpl model;

  /**
   * Constructor for the Controller Factory.
   */
  public ControllerFactory(MusicEditorModelImpl model) {
    this.model = model;
  }


  /**
   * @param view A string representation of the View.
   * @return the Iview based on the view.
   */
  public IController createController(String view) {
    if (view == null) {
      throw new IllegalArgumentException("Invalid view");
    } else if (view.equals("console")) {
      return new ConsoleController(model);
    } else if (view.equals("visual")) {
      return new GuiMidiController(model, view);
    } else if (view.equals("midi")) {
      return new GuiMidiController(model, view);
    } else if (view.equals("composite")) {
      return new Controller(model);
    } else {
      throw new IllegalArgumentException("Invalid Controller");
    }
  }
}
