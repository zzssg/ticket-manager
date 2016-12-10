package org.zzssg.ticketmanager;

import java.awt.*;

/**
 * Created by Desktop on 07.02.2016.
 */
public class TicketCell {
    private Integer value;
    private boolean matched;

    public TicketCell(Integer v) {
        this.value = v;
        this.matched = false;
    }

    public void setMatched() {
        this.matched = true;
    }

    public boolean isMatched() {
        return this.matched;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString(){
        return "org.zzssg.ticketmanager.TicketCell[ value: " + this.value + ", matched=" + this.matched;
    }
}
