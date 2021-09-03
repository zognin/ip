package command;

import exception.ErrorAccessingFileException;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import message.Message;
import tasklist.Task;
import tasklist.TaskList;
import type.DukeCommandTypeEnum;

/**
 * Encapsulates a done command after it is parsed from the user input.
 */
public class DoneCommand extends Command {
    private int taskNumber;
    private Task task;

    private DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Creates a `DoneCommand`.
     *
     * @param description Description from the command excluding the command type.
     * @return `DoneCommand`.
     * @throws InvalidTaskNumberException If task number in description is not a number.
     * @throws MissingCommandDescriptionException If description is empty.
     */
    public static DoneCommand createCommand(String description)
            throws InvalidTaskNumberException, MissingCommandDescriptionException {
        // Validate before creating the command
        Command.validateDescriptionNotEmpty(DukeCommandTypeEnum.DONE, description);

        return new DoneCommand(Command.getTaskNumberFromMessage(description));
    }

    /**
     * Executes a `DoneCommand` marking a task in the list as done.
     *
     * @param list `TaskList` containing all tasks.
     * @throws NonExistentTaskNumberException If the task number does not exist in the list.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     */
    public void execute(TaskList list) throws NonExistentTaskNumberException, ErrorAccessingFileException {
        this.task = list.markTaskAsDone(taskNumber);
    }

    /**
     * Gets the output message representing the command is executed.
     *
     * @return `Message`.
     */
    public Message getOutputMessage() {
        String prefix = "Nice! I've marked this task as done:";
        String kaomoji = "≧(´▽｀)≦";

        return new Message(prefix, task.toString(), kaomoji);
    }
}
