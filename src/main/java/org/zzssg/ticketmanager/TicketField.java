package org.zzssg.ticketmanager;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Desktop on 07.02.2016.
 */
public class TicketField implements NumberListener{
    public static final int FIELD_WIDTH = 9;
    public static final int FIELD_HIGHT = 3;

    private Ticket owner;
    private FieldId id;

    private TicketState state;

    private Map<Point, TicketCell> cells;

    public TicketField(Ticket owner, FieldId id) {
        if (owner == null) {
            System.out.println("Valid owner instance must be provided");
            //TODO Exception must be thrown instead of return
            return;
        }
        this.owner = owner;
        this.id = id;
        this.cells = new HashMap<Point, TicketCell>();
        this.state = TicketState.NO_GAIN;
    }

    public boolean fillCell(int x, int y, int value) {
        if (value < 0) return false;
        if (x > FIELD_WIDTH || y > FIELD_HIGHT) return false;
        Point p = new Point(x, y);
        if (cells.containsKey(p)) {
            System.out.println("ERROR - Attempt to fill cell [" + x + "," + y + "] which is already filled with " + cells.get(p));
            return false;
        }
        cells.put(p, new TicketCell(value));
        return true;
    }

    private void markNumberMatched(int v) {
        Iterator<Point> it = cells.keySet().iterator();
        while(it.hasNext()) {
            Point p = it.next();
            TicketCell tc = cells.get(p);
            if (tc.getValue().equals(v))
                tc.setMatched();
        }
    }

    private Vector<TicketCell> getFieldLine(int lineNumber) {
        Vector<TicketCell> result = new Vector<TicketCell>();
        Iterator<Point> it = cells.keySet().iterator();
        while(it.hasNext()) {
            Point p = it.next();
            if (p.x == lineNumber) result.add(cells.get(p));
        }
        return result;
    }

    public TicketState getState() {
        return this.state;
    }

    private boolean updateState() {
        boolean isStateChanged = false;
        boolean isAllMatched = true;
        for(int i = 1; i <= FIELD_HIGHT; i++) {
            Vector<TicketCell> currentLine = getFieldLine(i);
            boolean isLineMatched = true;
            for( TicketCell cell : currentLine) {
                if (cell.getValue() > 0 && !cell.isMatched()) isLineMatched = false;
            }
            if (isLineMatched && this.state != TicketState.FIELD_FILLED) {
                this.state = TicketState.LINE_FILLED;
                isStateChanged = true;
            }

            if (!isLineMatched)
                isAllMatched = false;
        }
        if (isAllMatched && this.state != TicketState.FIELD_FILLED) {
            this.state = TicketState.FIELD_FILLED;
            isStateChanged = true;
        }
        return isStateChanged;
    }


    @Override
    public void onNewNumber(Integer number) {
        markNumberMatched(number);
        if (updateState()) {
            owner.TicketFieldStateChanged(this.id, this.state);
        }
    }
}
