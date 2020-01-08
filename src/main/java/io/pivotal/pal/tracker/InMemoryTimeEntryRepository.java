package io.pivotal.pal.tracker;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    Map<Long, TimeEntry> timeEntries = new HashMap();

    Long currentId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        Long newId;
        if (timeEntry.getId() == 0L){
            newId = currentId;
            currentId = currentId + 1L;
        } else {
            newId = timeEntry.getId();
        }
        TimeEntry newTimeEntry = new TimeEntry(
                newId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(newTimeEntry.getId(), newTimeEntry);

        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }


    public List list() {
        List listToReturn = new ArrayList();
        timeEntries.values().forEach(value -> listToReturn.add(value));
        return listToReturn;
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntries.remove(timeEntryId);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntries.get(id) == null){
            return null;
        }
        TimeEntry updatedTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(id, updatedTimeEntry);

        return updatedTimeEntry;
    }


}
