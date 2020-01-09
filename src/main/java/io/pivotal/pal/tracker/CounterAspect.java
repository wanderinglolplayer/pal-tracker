package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CounterAspect {

    private Counter counter;

    public CounterAspect(MeterRegistry meterRegistry) {

        this.counter = meterRegistry.counter("timeEntry.counter");
    }

    @Before("execution(* io.pivotal.pal.tracker.TimeEntryController.*(..))")
    public void incrementCounter() {
        System.out.println("aspect called");
        counter.increment();
    }
}
