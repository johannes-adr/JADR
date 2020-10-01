package de.jadr.commands;

import de.jadr.commands.exceptions.MissingPermsException;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public interface CommandExecutedRunnable {
	public void run(GuildMessageReceivedEvent e, String s[]) throws MissingPermsException;
	public void onEvent(GenericEvent e);
}
