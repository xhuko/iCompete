package com.icompete.utils;

import com.icompete.enums.EventState;

import java.util.Date;

/**
 * @author Sekan
 */
public class EventStateUtils {
    public static EventState getState(Date startDate, Date endDate) {
        Date currentDate = new Date();
        if (currentDate.compareTo(startDate) == -1) {
            return EventState.NOT_STARTED;
        }
        else if (currentDate.compareTo(endDate) == 1) {
            return EventState.FINISHED;
        }
        else {
            return EventState.ONGOING;
        }
    }
}
