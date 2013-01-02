package net.freehal.ui.xmpp;

import java.util.Collections;

import net.freehal.core.util.ExitListener;
import net.freehal.core.util.LogUtils;
import net.freehal.core.util.SystemUtils;
import net.freehal.ui.common.DataInitializer;
import net.freehal.ui.common.MainLoopListener;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

public class XmppServer implements ExitListener, MainLoopListener {

	private ConnectionConfiguration connConfig;
	private String username;
	private String password;

	public XmppServer(String host, int port, String username, String password) {
		SystemUtils.destructOnExit(this);

		// initialize data
		DataInitializer.initializeLanguageSpecificData(Collections.<String> emptySet());

		// print status info
		LogUtils.i("XMPP Extension started:");
		LogUtils.i("  Host: " + host);
		LogUtils.i("  Port: " + port);
		LogUtils.i("  User: " + username);
		LogUtils.i("  Password: " + password);

		this.username = username;
		this.password = password;

		if (host.contains("gmail") || host.contains("google"))
			connConfig = new ConnectionConfiguration("talk.google.com", port, "gmail.com");
		else
			connConfig = new ConnectionConfiguration(host, port, host);
	}

	@Override
	public void onExit(int status) {}

	@Override
	public void loop() {
		XMPPConnection connection = new XMPPConnection(connConfig);
		if (connect(connection) && login(connection)) {
			connection.getChatManager().addChatListener(new ChatManagerListener() {
				@Override
				public void chatCreated(Chat chat, boolean createdLocally) {
					if (!createdLocally) {
						chat.addMessageListener(new IncomingMessageListener());
					}
				}
			});
		}
	}

	private boolean login(XMPPConnection connection) {
		try {
			connection.login(username, password);
			LogUtils.i("Logged in as " + connection.getUser() + " (" + username + ")");

			Presence presence = new Presence(Presence.Type.available);
			connection.sendPacket(presence);
			return true;

		} catch (XMPPException ex) {
			LogUtils.e("Failed to log in as " + connection.getUser() + " (" + username + ")");
			LogUtils.e(ex);
			return false;
		}
	}

	private boolean connect(XMPPConnection connection) {
		try {
			SASLAuthentication.supportSASLMechanism("PLAIN", 0);
			connection.connect();
			LogUtils.i("Connected to " + connection.getHost());
			return true;

		} catch (XMPPException ex) {
			LogUtils.e("Failed to connect to " + connection.getHost());
			LogUtils.e(ex);
			return false;
		}
	}
}