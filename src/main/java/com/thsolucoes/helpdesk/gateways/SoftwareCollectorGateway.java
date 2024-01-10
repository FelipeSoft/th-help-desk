/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.gateways;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import oshi.SystemInfo;
import oshi.software.os.InternetProtocolStats.IPConnection;
import oshi.software.os.OSService;

/**
 *
 * @author NTH05
 */
public class SoftwareCollectorGateway {

    private static int DELIMITER = 10;
    private static JSONObject output = new JSONObject();

    private final static SystemInfo si = new SystemInfo();

    public static JSONObject collect() {
        List<OSService> services = si.getOperatingSystem().getServices();
        List<String> backupServices = new ArrayList<>();
        List<String> antivirusServices = new ArrayList<>();

        for (OSService service : services) {
            String name = service.getName().toLowerCase();
            if (name.contains("backup") || name.contains("nuvem")) {
                backupServices.add(service.getName());
            } else if (name.contains("cyber")
                    || name.contains("antivírus")
                    || name.contains("protect")
                    || name.contains("avast")
                    || name.contains("kaspersky")) {
                antivirusServices.add(service.getName());
            }
        }

        /*System.out.println("Domínio" + si.getOperatingSystem().getNetworkParams().getDomainName());
        System.out.println("Host" + si.getOperatingSystem().getNetworkParams().getHostName());
        System.out.println("IPV4" + si.getOperatingSystem().getNetworkParams().getIpv4DefaultGateway());

        for (String dnsServer : si.getOperatingSystem().getNetworkParams().getDnsServers()) {
            System.out.println("DNS Server: " + dnsServer);
        }

        System.out.println("TCP4 Stats" + si.getOperatingSystem().getInternetProtocolStats().getTCPv4Stats());
        System.out.println("TCP4 Update Stats" + si.getOperatingSystem().getInternetProtocolStats().getUDPv4Stats());
        for (IPConnection connection : si.getOperatingSystem().getInternetProtocolStats().getConnections()) {
            if ("ESTABLISHED".equals(connection.getState().toString())) {
                System.out.println("--------- Conexão ---------");
                System.out.println("Porta Externa: " + connection.getForeignPort());
                System.out.println("Porta Local: " + connection.getLocalPort());
                System.out.println("Estado: " + connection.getState());
            }
        }*/

        output.put("backup", backupServices);
        output.put("antivirus", antivirusServices);
        return output;
    }
}
