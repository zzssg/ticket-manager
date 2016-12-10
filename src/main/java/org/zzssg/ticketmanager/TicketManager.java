package org.zzssg.ticketmanager;

/**
 * Created by Desktop on 07.02.2016.
 */
public class TicketManager {

    public static final int FIELD_WIDTH = 9;
    public static final int FIELD_HIGHT = 3;

    private TicketCell[][] ceils = new TicketCell[FIELD_HIGHT][FIELD_WIDTH];

    public TicketManager() {
        System.out.println("Ceils hight: " + ceils.length);
        System.out.println("Ceils length: " + ceils[0].length);

    }
    public static void main(String args[]) {
        new TicketManager();
    }
}
