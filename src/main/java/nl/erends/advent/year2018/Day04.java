package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Day04 extends AbstractProblem<List<String>, Integer> {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private List<Event> eventList;
    private int guardId;
    private LocalDateTime fellAsleep;
    private Map<Integer, Integer> minutesAsleepMap;
    private int sleepyGuard;
    private Map<Integer, Long> guardSleepMap;
    private Map<Integer, Map<Integer, Integer>> guardAtMinuteAsleepMap;

    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2018, 4));
    }
    
    @Override
    public Integer solve1() {
        eventList = new ArrayList<>();
        input.stream().map(Day04::getEventFromString).sorted().forEach(eventList::add);
        guardSleepMap = new HashMap<>();
        fellAsleep = LocalDateTime.now();
        guardId = 0;
        for (Event event : eventList) {
            fillGuardSleepMap(event);
        }
        Optional<Map.Entry<Integer, Long>> optional = guardSleepMap.entrySet().stream()
                .max((e1, e2) -> (int) (e1.getValue() - e2.getValue()));
        sleepyGuard = optional.isPresent() ? optional.get().getKey() : 0;
        minutesAsleepMap = new HashMap<>();
        for (Event event : eventList) {
            fillMinutesAsleepMap(event);
        }
        Optional<Map.Entry<Integer, Integer>> optional1 = minutesAsleepMap.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue));
        int maxMinuteAsleep = optional1.isPresent() ? optional1.get().getKey() : 0;
        return sleepyGuard * maxMinuteAsleep;
    }

    @Override
    public Integer solve2() {
        guardAtMinuteAsleepMap = new HashMap<>();
        for (Event event : eventList) {
            fillGuardAtMinuteAsleepMap(event);
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
        return minute * sleepyGuard;
    }

    private void fillMinutesAsleepMap(Event event) {
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

    private void fillGuardSleepMap(Event event) {
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

    private void fillGuardAtMinuteAsleepMap(Event event) {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Event event = (Event) o;
            return Objects.equals(dateTime, event.dateTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateTime);
        }
    }
    
    private enum EventType {
        BEGIN_SHIFT,
        FALLS_ASLEEP,
        WAKES_UP
    }
}
