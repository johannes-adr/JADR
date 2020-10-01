import javax.security.auth.login.LoginException;

import de.jadr.DiscordBot;
import de.jadr.eventwrapper.EventType;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import de.jadr.eventwrapper.Event.MessageReceived;
import de.jadr.eventwrapper.Event.ServerReady;

public class TestBot {

	public static void main(String args[]) throws LoginException {
		DiscordBot dc = new DiscordBot("TOKEN");
		dc.setActivity(OnlineStatus.DO_NOT_DISTURB, Activity.listening("Wap Bap"));
		dc.onEvent((e)->{
			
			if(e.is(EventType.ServerReady)) {
				
				ServerReady sr = (ServerReady) e;
				Guild s = sr.getServer();
				System.out.println(s.getName());
				
			}else if(e.is(EventType.MessageReceived)) {
				
				MessageReceived ms = (MessageReceived) e;
				ms.response("Hi");
				
			}
		});
	}
	
}
