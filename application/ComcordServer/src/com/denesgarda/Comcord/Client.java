package com.denesgarda.Comcord;

import com.denesgarda.Prop4j.data.PropertiesFile;
import com.denesgarda.Socketeer.Connection;
import com.denesgarda.Socketeer.SocketeerClient;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Client extends SocketeerClient {
    public static PropertiesFile config;

    public Client() throws IOException {
        System.out.println("Scanning system files.");
        config = new PropertiesFile("config.properties");
        if (!config.exists()) {
            System.out.println("System files not found. Generating new ones.");
            boolean successful = new File("config.properties").createNewFile();
            if (successful) {
                config.setProperty("preferred-id", "");
                System.out.println("New system files have been created. Change them to your liking, then restart the server.");
            } else {
                System.out.println("Failed to create config files.");
            }
            System.exit(0);
        }
        System.out.println("Connecting to Comcord Mainnet.");
        Connection mainnetConnection = this.connect("localhost", 8765);
        String query = mainnetConnection.awaitResponse();
        if (query.equals("id")) {
            String id = config.getProperty("preferred-id");
            if (config.getProperty("preferred-id").equals("")) {
                while (id.length() < 4) {
                    id = String.valueOf(new Random().nextInt(9999999));
                }
            }
            System.out.println("Verifying using id " + id);
            mainnetConnection.send(id);
            String access = mainnetConnection.awaitResponse();
            if (access.equals("true")) {
                System.out.println("Connected.");
            } else {
                System.out.println("Connection failed for the following reason: " + access);
                System.exit(0);
            }
        } else {
            System.out.println("Connection failed.");
            System.exit(0);
        }
    }
}
