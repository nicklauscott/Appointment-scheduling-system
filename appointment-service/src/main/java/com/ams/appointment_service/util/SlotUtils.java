package com.ams.appointment_service.util;

import com.ams.appointment_service.model.TimeSlot;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class SlotUtils {

    public static void forEachTimeSlot(Map<UUID, List<TimeSlot>> slots, BiConsumer<UUID, TimeSlot> action) {
        for (Map.Entry<UUID, List<TimeSlot>> entry : slots.entrySet()) {
            UUID uuid = entry.getKey();
            for (TimeSlot timeSlot : entry.getValue()) {
                action.accept(uuid, timeSlot);
            }
        }
    }

    public static boolean forEachTimeSlotUntil(Map<UUID, List<TimeSlot>> slots, BiFunction<UUID, TimeSlot, Boolean> action) {
        for (Map.Entry<UUID, List<TimeSlot>> entry : slots.entrySet()) {
            UUID uuid = entry.getKey();
            for (TimeSlot timeSlot : entry.getValue()) {
                boolean shouldContinue = action.apply(uuid, timeSlot);
                if (!shouldContinue) return false;
            }
        }
        return true;
    }

    public static int startAndEndTimeToMinute(TimeSlot timeSlot) {
        LocalTime start = timeSlot.getStart();
        LocalTime end = timeSlot.getEnd();
        return  (int) ((end.toNanoOfDay() - start.toNanoOfDay()) / 60_000_000_000L);
    }

}

