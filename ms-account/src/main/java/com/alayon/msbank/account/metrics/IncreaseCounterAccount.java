package com.alayon.msbank.account.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class IncreaseCounterAccount {

    private final Counter accountGetCounter;

    // account.get -> account_get_total
    public IncreaseCounterAccount(final MeterRegistry meterRegistry) {
        accountGetCounter = meterRegistry.counter("account.get");
    }

    public void increaseCounter() {
        accountGetCounter.increment();
    }
}

