/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NTH05
 */
public class Ticket {

    @JsonProperty("client")
    private Client client;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;
    
    public Ticket() {}

    public Ticket(Client client, String title, String description) {
        this.client = client;
        this.title = title;
        this.description = description;
    }
}
