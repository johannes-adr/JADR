package de.jadr.eventwrapper;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class Event {

	public boolean is(EventType et) {
		if (this.getClass().getSimpleName().equalsIgnoreCase(et.toString())) {
			return true;
		}
		return false;
	}

	public static class ServerReady extends Event {
		private Guild server;
		public ServerReady(Guild server) {
			this.server = server;
		}
		public Guild getServer() {
			return server;
		}
	}
	
	public static class MessageReceived extends Event {
		TextChannel tc;
		Message m;
		Member mem;
		public MessageReceived(TextChannel tc,Message m,Member mem) {
			this.tc = tc; this.m = m; this.mem = mem;
		}
		public TextChannel getTextChannel() {
			return tc;
		}
		public Message getMessage() {
			return m;
		}
		public Member getAuthor() {
			return mem;
		}
		public Message response(String msg) {
			return tc.sendMessage(msg).complete();
		}
		public Message response(EmbedBuilder msg) {
			return tc.sendMessage(msg.build()).complete();
		}
		
	}

}
