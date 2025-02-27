package command;

import exception.ErrorAccessingFileException;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import message.Message;
import tasklist.Task;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates a done command after it is parsed from the user input.
 */
public class DoneCommand extends Command {
    private int taskNumber;

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
        Command.validateDescriptionNotEmpty(CommandTypeEnum.DONE, description);

        return new DoneCommand(Command.getTaskNumberFromMessage(description));
    }

    /**
     * Executes a `DoneCommand` marking a task in the list as done.
     *
     * @param list `TaskList` containing all tasks.
     * @return Message representing the command is executed.
     * @throws NonExistentTaskNumberException If the task number does not exist in the list.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     */
    public Message execute(TaskList list) throws NonExistentTaskNumberException, ErrorAccessingFileException {
        Task task = list.markTaskAsDone(this.taskNumber);
        return this.getOutputMessage(task);
    }

    private Message getOutputMessage(Task task) {
        assert task != null : "task should not be null";

        String prefix = "Nice! I've marked this task as done:";

        return new Message(prefix, task.toString());
    }
}
