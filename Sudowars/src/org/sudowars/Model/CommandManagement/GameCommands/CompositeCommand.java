package org.sudowars.Model.CommandManagement.GameCommands;

import java.util.LinkedList;

import org.sudowars.Model.CommandManagement.BaseCommand;
import org.sudowars.Model.Game.Game;
import org.sudowars.Model.Game.Player;

/**
 * The class CompositeCommand. It bundles multiple game commands in one class.
 */
public class CompositeCommand extends BaseCommand implements GameCommand {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7419414649681567326L;
	/**
	 * The commands.
	 */
	private LinkedList<GameCommand> commands;

	/**
	 * Initializes a new empty composite command.
	 */
	public CompositeCommand() {
		super();
		commands = new LinkedList<GameCommand>();
	}
	
	/**
	 * Initializes a new composite command with given commands.
	 *
	 * @param commands the given commands
	 */
	public CompositeCommand(LinkedList<GameCommand> commands) {
		if (commands == null) {
			throw new IllegalArgumentException("commands are null");
		}
		this.commands = commands;
	}

	/**
	 * Adds a command.
	 *
	 * @param command the command which is added to the compositeCommand
	 */
	public void pushCommand(GameCommand command) {
		if (command == null) {
			throw new IllegalArgumentException("command is null");
		}
		commands.addLast(command);
	}

	/**
	 * Removes the last command from the compositeCommand.
	 *
	 *
	 * @return the last command
	 */
	public GameCommand popCommand() {
		GameCommand temp =  commands.getLast();
		commands.removeLast();
		return temp;
	}

	/**
	 * Gets the commands currently bundled.
	 *
	 * @return the commands
	 */
	public LinkedList<GameCommand> getCommands() {
		return commands;
	}

	/**
	 * Executes the added commands in order the where added to the composite.
	 *
	 * @param game The game on which the commands are executed.
	 * @param executingPlayer the player which executes the commands.
	 *
	 * @return <code>true</code>, if all commands executed successfull, otherwise <code>false</code>
	 *
	 * @throws IllegalArgumentException if at least one of the given params is <code>null</code>
	 */
	public boolean execute(Game game, Player executingPlayer) throws IllegalArgumentException {
		GameCommand currentCommand;
		for (int i = 0; i < commands.size(); i++) {
			currentCommand = commands.get(i);
			if (!currentCommand.execute(game, executingPlayer)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if it's a empty comp.command
	 */
	public boolean isEmpty() {
		if (commands.size() == 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public CompositeCommand clone() {
		CompositeCommand returnCommand = new CompositeCommand();
		for (int counter = 0; counter <= commands.size() - 1; counter++) {
			returnCommand.pushCommand(commands.get(counter));
		}
		return returnCommand;
	}

	@Override
	public GameCommand getInvertedCommand(Game game) {
		CompositeCommand invertedCommand = new CompositeCommand();
		GameCommand currentCommand = null;
		for (int i = this.commands.size() - 1; i > -1; --i) {
			currentCommand = this.commands.get(i);
			invertedCommand.pushCommand(currentCommand.getInvertedCommand(game));
		}
		return invertedCommand;
	}
}
