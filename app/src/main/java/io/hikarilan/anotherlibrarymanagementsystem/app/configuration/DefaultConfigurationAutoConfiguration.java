package io.hikarilan.anotherlibrarymanagementsystem.app.configuration;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.ConfigurationConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.ConfigurationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import static io.hikarilan.anotherlibrarymanagementsystem.app.data.ConfigurationConstants.DEFAULT_MAX_BOOK_BORROW_DAYS;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DefaultConfigurationAutoConfiguration {

    private final ConfigurationService configurationService;

    @PostConstruct
    public void createDefaultConfigurationIfNotExists() {
        if (configurationService.getMaxBookBorrowDays() < 0) {
            log.info("Creating default configuration for max book borrow days, value: {}",
                    DEFAULT_MAX_BOOK_BORROW_DAYS);
            configurationService.setMaxBookBorrowDays(DEFAULT_MAX_BOOK_BORROW_DAYS);
        }
        if (configurationService.getOverdueFinePerDay() < 0) {
            log.info("Creating default configuration for overdue fine per day, value: {}",
                    ConfigurationConstants.DEFAULT_OVERDUE_FINE_PER_DAY);
            configurationService.setOverdueFinePerDay(ConfigurationConstants.DEFAULT_OVERDUE_FINE_PER_DAY);
        }
    }
}
