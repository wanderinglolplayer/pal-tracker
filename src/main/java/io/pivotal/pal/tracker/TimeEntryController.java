package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/time-entries")
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        HttpStatus status = HttpStatus.CREATED;
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

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
