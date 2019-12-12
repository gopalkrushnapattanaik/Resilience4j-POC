package com.poc.resiliency.config;

import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.newrelic.NewRelicConfig;
import io.micrometer.newrelic.NewRelicMeterRegistry;

@Configuration
public class EmployeeManagementConfig {

	NewRelicConfig newRelicConfig = new NewRelicConfig() {
		@Override
		public String accountId() {
			return "1807777";
		}

		@Override
		public String apiKey() {
			return "wQsPsZ8e9TUHvXE15_wcTJkoo08nBco5";
		}

		@Override
		public String get(String k) {
			return null; // accept the rest of the defaults
		}
	};

	MeterRegistry registry = new NewRelicMeterRegistry(newRelicConfig, Clock.SYSTEM);

	

}
