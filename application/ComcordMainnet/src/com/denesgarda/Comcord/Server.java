package com.denesgarda.Comcord;

import com.denesgarda.Socketeer.SocketeerServer;
import com.denesgarda.Socketeer.event.ClientConnectedEvent;
import com.denesgarda.Socketeer.event.EventHandler;
import com.denesgarda.Socketeer.event.EventListener;

import java.io.IOException;
import java.net.UnknownHostException;

public class Server extends SocketeerServer implements EventListener {
    public Server() throws UnknownHostException {
        this.setConnectionThrottle(0);
        this.setEventListener(this);
        this.listen(8765);
        System.out.println("Server started.");
    }

    @EventHandler
    public void onConnect(ClientConnectedEvent event) throws IOException {
        System.out.println("Client connected. Requesting necessary information.");
        event.getConnection().send("id");
        String response = event.getConnection().awaitResponse();
        System.out.println("Client requested to connect using id " + response);
        if (response.length() < 4) {
            System.out.println("Requested id too short. Denying access.");
            event.getConnection().send("Requested id too short.");
        } else {
            System.out.println("Request id verified. Allowing access.");
            event.getConnection().send("true");
        }
    }
}
