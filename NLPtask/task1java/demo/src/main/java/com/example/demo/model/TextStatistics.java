package com.example.demo.model;

/**
 * The TextStatistics class holds statistical data about a text, such as the
 * number of sentences, words, nouns, verbs, adjectives, and named entities.
 * It also contains the annotated text with specific HTML markup.
 */
public class TextStatistics {
    // Number of sentences in the analyzed text
    public int numSentences;
    // Number of words in the analyzed text
    public int numWords;
    // Number of nouns in the analyzed text
    public int numNouns;
    // Number of verbs in the analyzed text
    public int numVerbs;
    // Number of adjectives in the analyzed text
    public int numAdjectives;
    // Number of named entities recognized in the analyzed text
    public String annotatedText;

    /**
     * Default constructor initializes a new instance of TextStatistics.
     */
    public TextStatistics() {
        // This constructor could be further utilized for initializing default values if needed.
    }

    /**
     * Constructor with parameters to set initial values for text statistics.
     * 
     * @param numSentences      The number of sentences.
     * @param numWords          The number of words.
     * @param numNouns          The number of nouns.
     * @param numVerbs          The number of verbs.
     * @param numAdjectives     The number of adjectives.
     */
    public TextStatistics(int numSentences, int numWords, int numNouns,
                          int numVerbs, int numAdjectives, int numNamedEntities) {
        this.numSentences = numSentences;
        this.numWords = numWords;
        this.numNouns = numNouns;
        this.numVerbs = numVerbs;
        this.numAdjectives = numAdjectives;
        // Note: The annotatedText field is not set here and should be assigned later.
    }

    // Getters and setters for each field
    // These provide controlled access to the fields of the class

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

    // Additional methods as needed...
}
