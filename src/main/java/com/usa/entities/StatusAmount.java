package com.usa.entities;

import lombok.Data;

@Data
public class StatusAmount {

    private Integer completed;
    private Integer cancelled;

    public StatusAmount(Integer completed, Integer cancelled) {
        this.completed = completed;
        this.cancelled = cancelled;
    }

}
