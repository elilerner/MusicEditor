package cs3500.music.view;

//import cs3500.music.controller.MVC;

import cs3500.music.model.ReadOnlyMusicEditorModel;

/**
 * Created by jeanpaul on 3/13/2017.
 */

/**
 * Creates instances of the View.
 */
public class FactoryView {
  //private MusicEditorModelImpl model = new MusicEditorModelImpl();
  private ReadOnlyMusicEditorModel readModel;

  public FactoryView(ReadOnlyMusicEditorModel readModel) {
    this.readModel = readModel;
  }


  /**
   * @param view A string representation of the View.
   * @return the Iview based on the view.
   */
  public IView createView(String view) {
    if (view == null) {
      throw new IllegalArgumentException("Invalid view");
    } else if (view.equals("console")) {
      return new ConsoleView(readModel);
    } else if (view.equals("visual")) {
      return new GuiViewFrame(readModel);
    } else if (view.equals("midi")) {
      return new MidiViewImpl(readModel);
    } else {
      throw new IllegalArgumentException("Invalid view");
    }
  }
}


