package cs3500.music.model;

/**
 * Created by jeanpaul on 3/2/2017.
 */

/**
 * represents the 12 distinct pitches in the Western system of music.
 */
public enum Pitch {
  C("C"), //1
  CSharp("C#"), //2
  D("D"), //3
  DSharp("D#"), //4
  E("E"), //5
  F("F"), //6
  FSharp("F#"), //7
  G("G"), //8
  GSharp("G#"), //9
  A("A"), //10
  ASharp("A#"), //11
  B("B"); //12


  private String pitch;

  Pitch(String pitch) {
    this.pitch = pitch;
  }

  /**
   * gets the selected Pitch
   *
   * @return the Pitch in String form.
   */
  String getPitch() {
    return pitch;
  }
}

