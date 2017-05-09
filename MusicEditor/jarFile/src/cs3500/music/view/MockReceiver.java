package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by jeanpaul on 3/22/2017.
 */

/**
 * The Mock for the Receiver.
 */
public class MockReceiver implements Receiver {
  StringBuilder log;

  /**
   * Constructors the Mock Receiver.
   */
  public MockReceiver(StringBuilder log) {
    this.log = log;
  }

  /**
   * Sends the message to the log.
   *
   * @param message   the MidiMessage.
   * @param timeStamp the timestamp.
   */
  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage m = (ShortMessage) message;
    int note = m.getData1();
    log.append("The current Note is: " + note + " " + "\n");
  }

  /**
   * when closing.
   */
  @Override
  public void close() {
    log.append("Closing...");
    System.out.println(log.toString());
  }
}
