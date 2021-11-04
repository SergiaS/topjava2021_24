package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;

public class LogRule implements TestRule {

    private final Logger log = LoggerFactory.getLogger(LogRule.class);
    private final Clock clock = Clock.systemDefaultZone();
    private long currentTime = 0L;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                currentTime = clock.millis();
                base.evaluate();
                log.info("{} time work: {} sec", description.getMethodName(), (clock.millis() - currentTime) / 1000.0);
            }
        };
    }
}
