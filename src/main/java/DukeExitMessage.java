/**
 * Encapsulates an exit message used when Duke exits.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for exiting.
 */
public class DukeExitMessage extends DukeOutputMessage {
    public DukeExitMessage(String message) {
        super(message);
    }

    /**
     * Gets the message with a waving face added to it
     *
     * @return the string message with a waving face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " ヾ(=´･∀･｀=)";
    }
}
