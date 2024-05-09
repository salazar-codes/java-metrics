package com.jimmysalazar.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class CountersMainApp {
    public static void main(String[] args) {

        MeterRegistry registry = new SimpleMeterRegistry();

        // Utilizando un registry
        //registry.counter("salazar.students","course","Métricas con Micrometer");

        // Utilizando un builder
        Counter counter = Counter.builder("salazar.students")
                .description("Número de estudiantes")
                .tag("course","Métricas con Micrometer")
                .register(registry);

        counter.increment();
        counter.increment(200);

        System.out.println(counter.count());
    }
}
