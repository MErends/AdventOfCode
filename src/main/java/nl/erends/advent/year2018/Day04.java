package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Day04 {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day04.txt");
        long start = System.currentTimeMillis();
        List<Event> eventList = new ArrayList<>();
        input.stream().map(Day04::getEventFromString).forEach(eventList::add);
        Collections.sort(eventList);
        Map<Integer, Long> guardSleepMap = new HashMap<>();
        LocalDateTime fellAsleep = LocalDateTime.now();
        int guardId = 0;
        for (Event event : eventList) {
            switch (event.eventType) {
                case BEGIN_SHIFT:
                    guardId = event.id;
                    break;
                case FALLS_ASLEEP:
                    fellAsleep = event.dateTime;
                    break;
                case WAKES_UP:
                    long minutesAsleep = Duration.between(fellAsleep, event.dateTime).toMinutes();
                    if (guardSleepMap.containsKey(guardId)) {
                        guardSleepMap.put(guardId, guardSleepMap.get(guardId) + minutesAsleep);
                    } else {
                        guardSleepMap.put(guardId, minutesAsleep);
                    }
                    break;
            }
        }
        Optional<Map.Entry<Integer, Long>> optional = guardSleepMap.entrySet().stream()
                .max((e1, e2) -> (int) (e1.getValue() - e2.getValue()));
        int sleepyGuard = optional.isPresent() ? optional.get().getKey() : 0;
        Map<Integer, Integer> minutesAsleepMap = new HashMap<>();
        for (Event event : eventList) {
            switch (event.eventType) {
                case BEGIN_SHIFT:
                    guardId = event.id;
                    break;
                case FALLS_ASLEEP:
                    fellAsleep = event.dateTime;
                    break;
                case WAKES_UP:
                    if (guardId == sleepyGuard) {
                        for (int minute = fellAsleep.getMinute(); minute < event.dateTime.getMinute(); minute++) {
                            if (minutesAsleepMap.containsKey(minute)) {
                                minutesAsleepMap.put(minute, minutesAsleepMap.get(minute) + 1);
                            } else {
                                minutesAsleepMap.put(minute, 1);
                            }
                        }
                    }
                    break;
            }
        }
        Optional<Map.Entry<Integer, Integer>> optional1 = minutesAsleepMap.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue));
        int maxMinuteAsleep = optional1.isPresent() ? optional1.get().getKey() : 0;
        System.out.println(sleepyGuard * maxMinuteAsleep);
        long mid = System.currentTimeMillis();
        Map<Integer, Map<Integer, Integer>> guardAtMinuteAsleepMap = new HashMap<>();
        for (Event event : eventList) {
            switch (event.eventType) {
                case BEGIN_SHIFT:
                    guardId = event.id;
                    break;
                case FALLS_ASLEEP:
                    fellAsleep = event.dateTime;
                    break;
                case WAKES_UP:
                    if (!guardAtMinuteAsleepMap.containsKey(guardId)) {
                        guardAtMinuteAsleepMap.put(guardId, new HashMap<>());
                    }
                    minutesAsleepMap = guardAtMinuteAsleepMap.get(guardId);
                    for (int minute = fellAsleep.getMinute(); minute < event.dateTime.getMinute(); minute++) {
                        if (minutesAsleepMap.containsKey(minute)) {
                            minutesAsleepMap.put(minute, minutesAsleepMap.get(minute) + 1);
                        } else {
                            minutesAsleepMap.put(minute, 1);
                        }
                    }
                    break;
            }
        }
        int timesAsleep = Integer.MIN_VALUE;
        int minute = 0;
        for (Map.Entry<Integer, Map<Integer, Integer>> guardEntry : guardAtMinuteAsleepMap.entrySet()) {
            for (Map.Entry<Integer, Integer> minuteEntry : guardEntry.getValue().entrySet()) {
                if (minuteEntry.getValue() > timesAsleep) {
                    timesAsleep = minuteEntry.getValue();
                    minute = minuteEntry.getKey();
                    sleepyGuard = guardEntry.getKey();
                }
            }
        }
        System.out.println(minute * sleepyGuard);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }

    private static Event getEventFromString(String line) {
        String dateTime = line.split("\\[")[1].split("]")[0];
        EventType eventType = EventType.BEGIN_SHIFT;
        int id = 0;
        if (line.contains("falls")) {
            eventType = EventType.FALLS_ASLEEP;
        } else if (line.contains("wakes")) {
            eventType = EventType.WAKES_UP;
        } else {
            id = Integer.parseInt(line.split("#")[1].split(" ")[0]);
        }
        return new Event(LocalDateTime.parse(dateTime, formatter), id, eventType);
    }
    
    private static class Event implements Comparable<Event> {
        private LocalDateTime dateTime;
        private int id;
        private EventType eventType;

        Event(LocalDateTime dateTime, int id, EventType eventType) {
            this.dateTime = dateTime;
            this.id = id;
            this.eventType = eventType;
        }

        @Override
        public int compareTo(Event otherEvent) {
            return dateTime.compareTo(otherEvent.dateTime);
        }

        @Override
        public String toString() {
            return "Event{" +
                    "dateTime=" + dateTime +
                    ", id=" + id +
                    ", eventType=" + eventType +
                    '}';
        }
    }
    
    private enum EventType {
        BEGIN_SHIFT,
        FALLS_ASLEEP,
        WAKES_UP
    }
}
