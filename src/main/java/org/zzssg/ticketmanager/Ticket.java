package org.zzssg.ticketmanager;

/**
 * Created by Desktop on 07.02.2016.
 */
public class Ticket implements NumberListener, TicketStateListener{

    private TicketField upField, downField;
    private TicketState state;

    public Ticket() {
        this.upField = new TicketField(this, FieldId.UP);
        this.downField = new TicketField(this, FieldId.DOWN);
        this.state = TicketState.NO_GAIN;
    }

    public void fillTicket(FieldId fieldId, int x, int y, int value) {
        getFileldById(fieldId).fillCell(x, y, value);
    }

    private TicketField getFileldById(FieldId fieldId) {
        switch (fieldId) {
            case UP:
                return upField;
            case DOWN:
                return downField;
            default:
                return null;
        }
    }

    @Override
    public void onNewNumber(Integer number) {
        upField.onNewNumber(number);
        downField.onNewNumber(number);
    }


    @Override
    public void TicketFieldStateChanged(FieldId fieldId, TicketState ticketState) {


    }

    @Override
    public void TicketStateChanged(Ticket t) {

    }
}
