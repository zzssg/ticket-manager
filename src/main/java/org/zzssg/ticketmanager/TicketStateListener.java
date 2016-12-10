package org.zzssg.ticketmanager;


/**
 * Created by Desktop on 11.02.2016.
 */
public interface TicketStateListener {

    public void TicketFieldStateChanged (FieldId fieldId, TicketState ticketState);
    public void TicketStateChanged (Ticket t);
}
