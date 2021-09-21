package com.denesgarda.Comcord;

import java.io.IOException;

public class Main {
    public static Client client;

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Comcord server.");
        client = new Client();
    }
}
