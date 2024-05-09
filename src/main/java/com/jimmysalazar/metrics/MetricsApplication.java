package com.jimmysalazar.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class MetricsApplication {

    public static void main(String[] args) {
        CompositeMeterRegistry compositeMeterRegistry = Metrics.globalRegistry;

        System.out.println(compositeMeterRegistry == null);

        //CompositeMeterRegistry compositeMeterRegistry = new CompositeMeterRegistry();

        Counter counter = compositeMeterRegistry.counter("numero.empleados","oficina","Lima");
        //Counter counter = compositeMeterRegistry.counter("numero.empleados","oficina","Lima");
        // son inútiles sin un meterRegistry
        counter.increment();
        counter.increment(200);

        MeterRegistry registry = new SimpleMeterRegistry();
        MeterRegistry registry2 = new SimpleMeterRegistry();

        compositeMeterRegistry.add(registry);
        compositeMeterRegistry.add(registry2);

        counter.increment();
        counter.increment(200);

        System.out.printf("Número de empleados %f",counter.count());
    }
}
