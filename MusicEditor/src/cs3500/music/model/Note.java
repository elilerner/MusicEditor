package cs3500.music.model;

/**
 * Created by jeanpaul on 3/2/2017.
 */

/**
 * represents a Note that is used in the MusicEdiorModel.
 */
public class Note {
  private int duration;
  private int instrument;
  private int volume;
  private int octave;
  private Pitch pitch;
  private int start;

  /**
   * @param duration   duration of this note.
   * @param instrument the instrument used in this note.
   * @param volume     the volume used in this note.
   * @param pitch      Pitch of this note
   * @param octave     octave of this note.
   * @param start      where the starting measure is.
   */
  public Note(int duration, int instrument, int volume,
              Pitch pitch, int octave, int start) {
    if (duration < 0) {
      throw new IllegalArgumentException("Invalid duration");
    }
    if (instrument < 0) {
      throw new IllegalArgumentException("Instrument");
    }
    if (volume < 0 || volume > 127) {
      throw new IllegalArgumentException("Invalid volume");
    }
    if (octave < 0) {
      throw new IllegalArgumentException("Invalid Octave");
    }
    if (start < 0) {
      throw new IllegalArgumentException("Invalid start");
    }
    if (pitch == null) {
      throw new IllegalArgumentException("Invalid Pitch");
    }
    this.duration = duration;
    this.instrument = instrument;
    this.volume = volume;
    this.pitch = pitch;
    this.octave = octave;
    this.start = start;
  }


  /**
   * @param o sees is this Note is equals to that Object.
   * @return true if equal, false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Note)) {
      return false;
    }
    Note n = (Note) o;
    return (this.duration == n.getDuration())
            && (this.instrument == n.getInstrument())
            && (this.volume == n.getVolume())
            && (this.pitch.equals(n.getPitch()))
            && (this.octave == n.getOctave())
            && (this.start == n.getStart());
  }

  @Override
  public int hashCode() {

    return super.hashCode();
  }

  /**
   * Gets the duration of this note.
   *
   * @return this duration.
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Gets the Instrument of this Note.
   *
   * @return this Instrument.
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Gets the Volume of this Note.
   *
   * @return this Volume.
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Gets the Pitch of this Note.
   *
   * @return this Pitch.
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * Gets the octave of this note.
   *
   * @return this octave.
   */
  public int getOctave() {
    return this.octave;
  }


  /**
   * Gets the start of this Note.
   *
   * @return this start.
   */
  public int getStart() {
    return this.start;
  }


  //@return the current Note in String form.
  @Override
  public String toString() {
    return pitch.getPitch().concat(Integer.toString(octave));
  }

  /**
   * @param n2 A Note.
   * @return An integer based on the comparison.
   */
  public int compare(Note n2) {
    if (octave == n2.getOctave()) {
      return pitch.compareTo(n2.getPitch());
    }
    return octave - n2.getOctave();
  }

  /**
   * Gets the last beat in a Note.
   *
   * @return the last beat.
   */
  public int last() {
    return start + duration - 1;
  }

  /**
   * returns the midi value of this Note.
   * @return the midi value of this Note.
   */
  public int midiValue() {
    int value = (getOctave() + 1) * 12;
    value += getPitch().ordinal();
    return value;
  }
}
