package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Configuration;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.ConfigurationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.hikarilan.anotherlibrarymanagementsystem.app.data.ConfigurationConstants.MAX_BOOK_BORROW_DAYS_KEY;
import static io.hikarilan.anotherlibrarymanagementsystem.app.data.ConfigurationConstants.OVERDUE_FINE_PER_DAY_KEY;

@AllArgsConstructor
@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public Optional<String> getConfiguration(String key) {
        return configurationRepository.findById(key).map(Configuration::getValue);
    }

    public void setConfiguration(String key, String value) {
        configurationRepository.save(new Configuration(key, value));
    }

    public Map<String, String> getAllConfiguration() {
        return StreamSupport.stream(configurationRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(Configuration::getKey, Configuration::getValue));
    }

    public int getMaxBookBorrowDays() {
        return getConfiguration(MAX_BOOK_BORROW_DAYS_KEY).map(Integer::parseInt).orElse(-1);
    }

    public void setMaxBookBorrowDays(int days) {
        setConfiguration(MAX_BOOK_BORROW_DAYS_KEY, String.valueOf(days));
    }

    public double getOverdueFinePerDay() {
        return getConfiguration(OVERDUE_FINE_PER_DAY_KEY).map(Double::parseDouble).orElse(-1.0);
    }

    public void setOverdueFinePerDay(double fine) {
        setConfiguration(OVERDUE_FINE_PER_DAY_KEY, String.valueOf(fine));
    }
}
