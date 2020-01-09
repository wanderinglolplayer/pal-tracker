package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/time-entries")
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;

    private final DistributionSummary distributionSummary;




    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {
        this.timeEntryRepository = timeEntryRepository;
        this.distributionSummary = meterRegistry.summary("timeEntry.summary");

    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        HttpStatus status = HttpStatus.CREATED;
        distributionSummary.record(timeEntryRepository.list().size());
        TimeEntry body = timeEntryRepository.create(timeEntryToCreate);
        ResponseEntity responseEntity = new ResponseEntity(body, status);
        return responseEntity;
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry body = timeEntryRepository.find(timeEntryId);

        if (body == null) {
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        HttpStatus status = HttpStatus.OK;
        ResponseEntity responseEntity = new ResponseEntity(body, status);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        HttpStatus status = HttpStatus.OK;
        List<TimeEntry> body = timeEntryRepository.list();
        return new ResponseEntity<>(body, status);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry body =  timeEntryRepository.update(timeEntryId, expected);
        if (body == null){
            return new ResponseEntity(body, HttpStatus.NOT_FOUND);
        }
        HttpStatus status = HttpStatus.OK;
        ResponseEntity responseEntity = new ResponseEntity(body, status);
        return responseEntity;
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        distributionSummary.record(timeEntryRepository.list().size());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
