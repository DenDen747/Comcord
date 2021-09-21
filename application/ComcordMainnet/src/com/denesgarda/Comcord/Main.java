package com.denesgarda.Comcord;

import java.net.UnknownHostException;

public class Main {
    public static Server server;

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Initiating Comcord Mainnet server.");
        server = new Server();
    }
}
