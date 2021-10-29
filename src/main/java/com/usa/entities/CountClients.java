package com.usa.entities;

import lombok.Data;

@Data
public class CountClients {

    private Long total;
    private Client client;

    public CountClients(Long total, Client client) {
        this.total = total;
        this.client = client;
    }
    
    
}
