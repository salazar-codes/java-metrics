package com.jimmysalazar.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GaugeMainApp {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        List<String> list = new ArrayList<>(4);

        /**
         * Un Gauge muestra el valor actual de una medida, son utilizados para
         * monitorear estadísticas de cache, colecciones, etc
         */
        Gauge gauge = Gauge.builder("list.size", list, List::size).register(registry);

        System.out.println(gauge.value());

        list.addAll(Arrays.asList("salazar","codes","helloworld"));

        System.out.println(gauge.value());
    }
}
