/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author felip
 */
public class Client {

    @JsonProperty("id")
    public int id;

    @JsonProperty("company")
    public String company;

    @JsonProperty("owner")
    public String owner;

    @JsonProperty("enterpriseIdentifier")
    public String enterpriseIdentifier;
    
    @JsonProperty("email")
    public String email;

    public Client() {}

    public Client(int id, String company, String owner, String enterpriseIdentifier, String email) {
        this.id = id;
        this.company = company;
        this.owner = owner;
        this.enterpriseIdentifier = enterpriseIdentifier;
        this.email = email;
    }
}
