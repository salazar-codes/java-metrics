package com.jimmysalazar.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.concurrent.TimeUnit;

public class TimerMainApp {
    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        Timer timer = registry.timer("execution.time");

        // Para contar cuanto tiempo demora cierto proceso
        timer.record(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);


                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        System.out.println(timer.totalTime(TimeUnit.SECONDS));
    }
}
