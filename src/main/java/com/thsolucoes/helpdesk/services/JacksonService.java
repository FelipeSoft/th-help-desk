/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thsolucoes.helpdesk.domain.Serializer;

/**
 *
 * @author NTH05
 */
public class JacksonService implements Serializer {

    @Override
    public String serialize(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(data);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deserialize(String data, Object transformTo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            transformTo = mapper.readValue(data, transformTo.getClass());
            return transformTo;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
