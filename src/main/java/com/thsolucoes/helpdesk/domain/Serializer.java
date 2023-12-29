/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.thsolucoes.helpdesk.domain;

/**
 *
 * @author NTH05
 */
public interface Serializer {
    String serialize(Object data);
    Object deserialize (String data, Object transformTo);
}
