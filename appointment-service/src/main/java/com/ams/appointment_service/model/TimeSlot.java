package com.ams.appointment_service.model;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class TimeSlot {
    private final LocalTime start;
    private final LocalTime end;

    public TimeSlot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlapsWith(LocalTime otherStart, LocalTime otherEnd) {
        return !(end.isBefore(otherStart) || start.isAfter(otherEnd));
    }

    @Override
    public String toString() {
        return "[" + start + " - " + end + "]";
    }
}


