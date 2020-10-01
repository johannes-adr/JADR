package de.jadr;

import de.jadr.eventwrapper.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class EventTranslator implements EventListener {
	private DiscordBot dcbot;

	public EventTranslator(DiscordBot discordBot) {
		dcbot = discordBot;
	}

	public void onEvent(GenericEvent e) {
		if (e instanceof GuildReadyEvent)
			dcbot.callEvent(new Event.ServerReady(((GuildReadyEvent) e).getGuild()));
		else if(e instanceof GuildMessageReceivedEvent) {
			GuildMessageReceivedEvent er = (GuildMessageReceivedEvent) e;
			if(er.getAuthor().getIdLong() == dcbot.getJDA().getSelfUser().getIdLong())return;
			dcbot.callEvent(new Event.MessageReceived(er.getChannel(), er.getMessage(), er.getMember()));
		}
	}
}
