package com.jimmysalazar.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class MetricsApplication {

    public static void main(String[] args) {
        CompositeMeterRegistry compositeMeterRegistry = Metrics.globalRegistry;

        System.out.println(compositeMeterRegistry == null);

        //CompositeMeterRegistry compositeMeterRegistry = new CompositeMeterRegistry();

        // tags para segmentar métricas
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
        foo();
        System.out.printf("Número de empleados %f",counter.count());
    }

    private static void foo() {
        CompositeMeterRegistry globalRegistry = Metrics.globalRegistry;

        // La convención de nombres se puede modificar con una lambda
        /*
        globalRegistry.config().namingConvention(new NamingConvention() {
            @Override
            public String name(String s, Meter.Type type, String s1) {
                return null;
            }
        });

         */

        Counter counter = globalRegistry.counter("numero.empleados","oficina","Lima");
        counter.increment(150);
    }


}
