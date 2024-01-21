package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.AggregatedTextStatistics;
import com.example.demo.service.CsvService;
import com.example.demo.service.TextAnalysisService;
import com.example.demo.service.TextAnalysisService.TextStatistics;

@Controller
public class HomeController {

    // Injecting the TextAnalysisService using Spring's dependency injection
    @Autowired
    private TextAnalysisService textAnalysisService;

    // Mapping for the root endpoint that serves the homepage
    @GetMapping("/")
    public String home(Model model) {
        // Logic for initial setup could be added here if necessary
        return "index"; // Return the name of the view for the home page
    }

    // Mapping for handling file uploads via POST request
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,  Model model) {
        try {
            // Retrieve the name of the uploaded file
            String fileName = file.getOriginalFilename();

            // Convert the file's bytes to a String to get the text content
            String textContent = new String(file.getBytes());

            // Analyze the text content to get statistics and annotated text
            TextStatistics textStatistics = textAnalysisService.analyzeText(textContent);

            // Check if the analysis was successful
            if (textStatistics != null) {
                // Create a string representation of the file statistics
                String parsedText = "Uploaded File Name: " + fileName +
                    "<br>Number of Sentences: " + textStatistics.numSentences +
                    "<br>Number of Words: " + textStatistics.numWords +
                    "<br>Number of Nouns: " + textStatistics.numNouns +
                    "<br>Number of Verbs: " + textStatistics.numVerbs +
                    "<br>Number of Adjectives: " + textStatistics.numAdjectives;

                // Wrap the annotated text in a div for styling
                String annotatedTextHtml = "<div class='annotated-text'>" + textStatistics.annotatedText + "</div>";

                // Add both the file statistics and annotated text to the model to be accessed by the view
                model.addAttribute("parsedText", parsedText);
                model.addAttribute("annotatedText", annotatedTextHtml);

                // Add textStatistics to the model
                model.addAttribute("textStatistics", textStatistics);


                // Return the name of the view for displaying the analysis results
                return "uploadResult";
            } else {
                // If the analysis failed, return an error message
                model.addAttribute("error", "Error analyzing text");
                return "errorPage";
            }
        } catch (IOException e) {
            // Catch and handle file I/O exceptions
            model.addAttribute("error", "Error handling file upload: " + e.getMessage());
            return "errorPage";
        }
    }

    // Mapping for handling direct text analysis via POST request
    @PostMapping("/analyzetext")
    public String analyzeText(@RequestParam("text") String text, Model model) {
        try {
            // Perform text analysis on the provided text
            TextStatistics textStatistics = textAnalysisService.analyzeText(text);

            // Check if the analysis was successful
            if (textStatistics != null) {
                // Prepare the statistics and annotated text similar to the file upload method
                String parsedText = 
                        "<br>Number of Sentences: " + textStatistics.numSentences +
                        "<br>Number of Words: " + textStatistics.numWords +
                        "<br>Number of Nouns: " + textStatistics.numNouns +
                        "<br>Number of Verbs: " + textStatistics.numVerbs +
                        "<br>Number of Adjectives: " + textStatistics.numAdjectives;

                String annotatedTextHtml = "<div class='annotated-text'>" + textStatistics.annotatedText + "</div>";

                // Add the results to the model for the view
                model.addAttribute("parsedText", parsedText);
                model.addAttribute("annotatedText", annotatedTextHtml);


                // Add textStatistics to the model
                model.addAttribute("textStatistics", textStatistics);

                // Return the name of the view to display the results
                return "uploadResult";
            } else {
                // If the analysis failed, return an error message
                model.addAttribute("error", "Error analyzing text");
                return "errorPage";
            }
        } catch (Exception e) {
            // Catch and handle general exceptions
            model.addAttribute("error", "Error analyzing text: " + e.getMessage());
            return "errorPage";
        }
    }

    @Autowired
    private CsvService csvService;

    @PostMapping("/comparison")
    public String showComparison(@ModelAttribute("textStatistics") TextStatistics textStatistics, Model model) {
        try {
            // Corrected file path
            // String csvFilePath = "C:\\Users\\ppadi\\OneDrive\\Documents\\GondyGRA\\task1python\\aggregated_results.csv";
            String csvFilePath = "..\\..\\task1python\\aggregated_results.csv";
            List<AggregatedTextStatistics> aggregatedStats = csvService.readAggregatedTextStatistics(csvFilePath);
            model.addAttribute("aggregatedStats", aggregatedStats);

            model.addAttribute("textStatistics", textStatistics);

            return "comparison"; // Name of the Thymeleaf template
        } catch (IOException e) {
            model.addAttribute("error", "Could not read aggregated statistics: " + e.getMessage());
            return "comparisonError"; // Name of the error view
        }
    }
}
