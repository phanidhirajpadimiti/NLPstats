package com.example.demo.service;

import com.example.demo.model.AggregatedTextStatistics;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CsvService {

    public List<AggregatedTextStatistics> readAggregatedTextStatistics(String csvFilePath) throws IOException {
        try (FileReader fileReader = new FileReader(csvFilePath)) {
            return new CsvToBeanBuilder<AggregatedTextStatistics>(fileReader)
                    .withType(AggregatedTextStatistics.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new IOException("Error reading CSV file: " + e.getMessage(), e);
        }
    }
}
