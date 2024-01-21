package com.example.demo.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class TextAnalysisService {

    // The CoreNLP pipeline object that will be used for text analysis
    private final StanfordCoreNLP pipeline;

    // Constructor that initializes the StanfordCoreNLP pipeline with properties
    public TextAnalysisService() {
        Properties props = new Properties();
        // Set the list of annotators to be used in the pipeline
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        // Set ner.useSUTime to false if you don't want temporal information to be resolved
        props.setProperty("ner.useSUTime", "false");
        // Initialize the pipeline with the properties
        this.pipeline = new StanfordCoreNLP(props);
    }

    // Method that takes a string of text and performs linguistic analysis
    public TextStatistics analyzeText(String text) {
        // Create an annotation object from the text
        Annotation annotation = new Annotation(text);
        // Annotate the text using the pipeline
        pipeline.annotate(annotation);
        // Retrieve the list of sentences from the annotated text
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        // Extract statistics from the list of sentences
        return extractStats(sentences);
    }

    // Helper method to extract text statistics and annotate text for display
    private TextStatistics extractStats(List<CoreMap> sentences) {
        // Builder to accumulate annotated text
        StringBuilder annotatedTextBuilder = new StringBuilder();
        // Create a new TextStatistics object to hold the results
        TextStatistics results = new TextStatistics();
        
        // Iterate over each sentence in the list
        for (CoreMap sentence : sentences) {
            // Iterate over each token (word) in the sentence
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // Retrieve the original text of the token
                String word = token.originalText();
                // Retrieve the part-of-speech tag for the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // Append the word wrapped in a span with a class based on its part-of-speech
                annotatedTextBuilder.append(wrapWordWithSpan(word, pos)).append(' ');

                // Increment the appropriate counters based on the part-of-speech
                if (pos.startsWith("NN")) {
                    results.numNouns++;
                } else if (pos.startsWith("VB")) {
                    results.numVerbs++;
                } else if (pos.startsWith("JJ")) {
                    results.numAdjectives++;
                }
            }
        }
        
        // Set the number of sentences in the results
        results.numSentences = sentences.size();
        // Calculate the number of words by splitting the annotated text
        results.numWords = annotatedTextBuilder.toString().split("\\s+").length;
        // Set the annotated text in the results
        results.annotatedText = annotatedTextBuilder.toString().trim();
        return results;
    }

    // Method to wrap words in a span with a class based on the part-of-speech
    private String wrapWordWithSpan(String word, String pos) {
        // Determine the CSS class based on the part-of-speech tag
        String cssClass;
        switch (pos) {
            case "NN":
            case "NNS":
            case "NNP":
            case "NNPS":
                cssClass = "noun";
                break;
            case "VB":
            case "VBD":
            case "VBG":
            case "VBN":
            case "VBP":
            case "VBZ":
                cssClass = "verb";
                break;
            case "JJ":
            case "JJR":
            case "JJS":
                cssClass = "adjective";
                break;
            default:
                // If the part-of-speech does not match noun, verb, or adjective, return the word unchanged
                return word;
        }
        // Return the word wrapped in a span with the determined class
        return String.format("<span class=\"%s\">%s</span>", cssClass, word);
    }

    // Inner class to hold text analysis results including statistics and annotated text
    public static class TextStatistics {
        // Number of sentences in the text
        public int numSentences;
        // Number of words in the text
        public int numWords;
        // Number of nouns in the text
        public int numNouns;
        // Number of verbs in the text
        public int numVerbs;
        // Number of adjectives in the text
        public int numAdjectives;
        // Annotated text with nouns, verbs, and adjectives wrapped in spans with CSS classes
        public String annotatedText;

        // Constructor for TextStatistics
        public TextStatistics() {
            // Initialize all counters to zero and annotatedText to an empty string
            this.numSentences = 0;
            this.numWords = 0;
            this.numNouns = 0;
            this.numVerbs = 0;
            this.numAdjectives = 0;
            this.annotatedText = "";
        }
        
        // Getters and setters for all fields (not shown here but should be implemented)
        public int getNumSentences() {
            return numSentences;
        }
    
        public void setNumSentences(int numSentences) {
            this.numSentences = numSentences;
        }
    
        public int getNumWords() {
            return numWords;
        }
    
        public void setNumWords(int numWords) {
            this.numWords = numWords;
        }
    
        public int getNumNouns() {
            return numNouns;
        }
    
        public void setNumNouns(int numNouns) {
            this.numNouns = numNouns;
        }
    
        public int getNumVerbs() {
            return numVerbs;
        }
    
        public void setNumVerbs(int numVerbs) {
            this.numVerbs = numVerbs;
        }
    
        public int getNumAdjectives() {
            return numAdjectives;
        }
    
        public void setNumAdjectives(int numAdjectives) {
            this.numAdjectives = numAdjectives;
        }
        /**
         * Gets the annotated text with HTML markup for color-coding.
         * 
         * @return A string containing the annotated text.
         */
        public String getAnnotatedText() {
            return annotatedText;
        }
    
        /**
         * Sets the annotated text with HTML markup for color-coding.
         * 
         * @param annotatedText A string containing the annotated text.
         */
        public void setAnnotatedText(String annotatedText) {
            this.annotatedText = annotatedText;
        }
    }
}
