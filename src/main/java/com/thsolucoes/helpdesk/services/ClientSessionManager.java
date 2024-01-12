/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.services;

import com.thsolucoes.helpdesk.domain.Client;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author felip
 */
public abstract class ClientSessionManager {

    private static final String ARCHIVE_PROPERTIES = "client.properties";

    public static Client getActiveClient() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(ARCHIVE_PROPERTIES)) {
            properties.load(input);

            String id = properties.getProperty("id");
            String company = properties.getProperty("company");
            String owner = properties.getProperty("owner");
            String enterpriseIdentifier = properties.getProperty("enterpriseIdentifier");
            String email = properties.getProperty("email");

            if (id != null && company != null && owner != null && enterpriseIdentifier != null && email != null) {
                return new Client(Integer.parseInt(id), company, owner, enterpriseIdentifier, email);
            }
        } catch (IOException e) {
        }
        return null;
    }

    public static void saveActiveClient(Client client) {
        Properties properties = new Properties();
        properties.setProperty("id", String.valueOf(client.id));
        properties.setProperty("company", client.company);
        properties.setProperty("owner", client.owner);
        properties.setProperty("enterpriseIdentifier", client.enterpriseIdentifier);
        properties.setProperty("email", client.email);

        try (OutputStream output = new FileOutputStream(ARCHIVE_PROPERTIES)) {
            properties.store(output, null);
        } catch (IOException e) {
        }
    }
}
