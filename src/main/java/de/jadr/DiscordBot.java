package de.jadr;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.security.auth.login.LoginException;

import de.jadr.eventwrapper.Event;
import de.jadr.utils.runnables.Runnable1;
import de.jadr.utils.runnables.Runnable2;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.GenericEvent;

public class DiscordBot{
	

	
	private static Executor asyncexecutor = Executors.newCachedThreadPool();
	private JDA jda;
	private Runnable1<Event>onevent;	
	public DiscordBot(String token) throws LoginException {
		jda = JDABuilder.createDefault(token).build();
		jda.addEventListener(new EventTranslator(this));
		
		
	}
	
	public void setActivity(OnlineStatus on, Activity a) {
		jda.getPresence().setPresence(on,a, false);
	}
	public void async(Runnable r) {
		asyncexecutor.execute(r);
	}
	
	public JDA getJDA() {
		return jda;
	}

	public void onEvent(Runnable1<Event> r) {
		onevent = r;
	}
	public void callEvent(Event e) {
		onevent.run(e);
	}
}
