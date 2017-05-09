package cs3500.music.model;

import java.util.Objects;

/**
 * To represent the note Values/ columns of the song.
 */
public class NoteValue {
  private Pitch pitch;
  private int octave;

  /**
   * @param pitch  the pitch value of the noteValue.
   * @param octave the octave of the noteValue.
   */

  public NoteValue(Pitch pitch, int octave) {
    this.pitch = pitch;
    this.octave = octave;
  }

  /**
   * Gets the Note Pitch.
   *
   * @return The Pitch for this Note.
   */
  public Pitch getNotePitch() {
    return this.pitch;
  }

  /**
   * Gets the Octave of the Note.
   *
   * @return The Octave for this Note.
   */
  public int getNoteOctave() {
    return this.octave;
  }


  /**
   * Checks if this Note is equal to that Note
   *
   * @param object a Note.
   * @return true of equal.
   */
  @Override
  public boolean equals(Object object) {
    NoteValue nv = (NoteValue) object;
    return (nv.getNotePitch() == this.getNotePitch()) &&
            (nv.getNoteOctave() == this.getNoteOctave());
  }

  /**
   * updates the hashCode.
   *
   * @return the hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(Pitch.values().length * octave);
  }

}
