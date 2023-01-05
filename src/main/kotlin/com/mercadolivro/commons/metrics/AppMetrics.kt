package com.mercadolivro.commons.metrics

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component

@Component
class AppMetrics(
    private val meterRegistry: MeterRegistry
) {
    val incrementMetricAction = {
            response: String, action: String ->
        meterRegistry.counter(
            "${PREFIX}_count",
            "operation", action,
            "status", response
        ).increment()
    }

    companion object {
        private const val PREFIX = "mercado_livro_ops"
    }

}