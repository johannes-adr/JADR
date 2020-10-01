package de.jadr.commands;

import java.util.ArrayList;

import de.jadr.commands.exceptions.CommandNotFoundException;
import de.jadr.commands.exceptions.MissingPermsException;
import de.jadr.commands.exceptions.WrongCommandArgsException;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command implements CommandExecutedRunnable{

	
	private static boolean deletemsg = true;
	public static String prefix = "!";
	
	private static ArrayList<Command> commands = new ArrayList<Command>();

	private String[] commandsprefix;
	private int[] expectedargs;
	private String commanddesc;
	private String commandargsusage;
	private Role[] neededroles;
	
	public Command(String... commandsprefix) {
		commands.add(this);
		this.commandsprefix = commandsprefix;
	}
	
	public Role[] getNeededroles() {
		return neededroles;
	}

	public void setNeededroles(Role[] neededroles) {
		this.neededroles = neededroles;
	}

	public void setCommandArgs(String commandargsusage, int... expectedargs) {
		this.commandargsusage = commandargsusage;
		this.expectedargs = expectedargs;
	}
	
	public void setCommandDesc(String commanddesc) {
		this.commanddesc = commanddesc;
	}

	public String[] getCommandPrefix() {
		return commandsprefix;
	}
	
	public int[] getExpectedargsLenght() {
		return expectedargs;
	}
	public String getCommanddesc() {
		return commanddesc;
	}
	public String getCommandargsusage() {
		return commandargsusage;
	}
	
	public static ArrayList<Command> getCommands() {
		ArrayList<Command> cmd = new ArrayList<Command>();
		cmd.addAll(commands);
		return cmd;
	}

	public static void call(GuildMessageReceivedEvent e) throws CommandNotFoundException, WrongCommandArgsException, MissingPermsException {
		String message = e.getMessage().getContentRaw();
		if (!message.startsWith(prefix)) {
			return;
		}
		if(deletemsg) {
			e.getMessage().delete().queue();
		}
		for (Command command  : commands) {
			for (String prefixes : command.getCommandPrefix()) {
				if(message.startsWith(prefix + prefixes)) {
					String arg = message.replace(prefix+prefixes + " ", "").replace(prefix+prefixes, "");
					String[] args = arg.split(" ");
					if(command.getNeededroles() != null) {						
						boolean hasrole = false;
						
						StringBuilder rolesbuilder = new StringBuilder();
						if(!e.getMember().hasPermission(Permission.ADMINISTRATOR)) {	
						for (Role r : command.getNeededroles()) {
							if(e.getMember().getRoles().contains(r)) hasrole = true;
							try {
								rolesbuilder.append("\n" +r.getName());
							}catch(Exception exe) {}
						}
						}else hasrole = true;
						if(!hasrole)throw new MissingPermsException("You need the following roles to use this command: "+ rolesbuilder.toString());
					}
					if(command.getExpectedargsLenght() != null) {
					for (int i : command.getExpectedargsLenght()) {
						if(i == args.length) {
							System.out.print("Executed command " + command.getClass().getSimpleName() + " from " + e.getAuthor().getName());
							command.run(e, args);
							return;
						}
					}
					}else {
						System.out.print("Executed command " + command.getClass().getSimpleName() + " from " + e.getAuthor().getName());
						command.run(e, args);
						return;
					}
					throw new WrongCommandArgsException(command.getCommandargsusage());
				}				
			}
		}		
		throw new CommandNotFoundException();
	}

	public static void onEventRun(GenericEvent e) {
		for (Command command : commands) {
			command.onEvent(e);
		}	
	}
}
