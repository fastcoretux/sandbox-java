package net.fastcoretux.sandbox.quartz.actuate;

import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fastcoretux
 * @since 17.04.2018
 */
@Configuration
@ConditionalOnClass(Scheduler.class)
@AutoConfigureAfter(QuartzAutoConfiguration.class)
public class QuartzJobEndpointAutoConfig {

    private final Scheduler scheduler;

    public QuartzJobEndpointAutoConfig(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Bean
    @ConditionalOnMissingBean
    QuartzJobEndpoint quartzJobEndpoint() {
        return new QuartzJobEndpointImpl(scheduler);
    }

}
