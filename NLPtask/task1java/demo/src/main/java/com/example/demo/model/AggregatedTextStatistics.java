package com.example.demo.model;

public class AggregatedTextStatistics {
    private double avg_num_sentences;
    private double avg_num_words;
    private double avg_num_nouns;
    private double avg_num_verbs;
    private double avg_num_adjectives;
    // Additional fields as per your CSV structure

    // Default constructor
    public AggregatedTextStatistics() {
    }

    // Getters and setters for average number of sentences
    public double getAvg_num_sentences() {
        return avg_num_sentences;
    }

    public void setAvg_num_sentences(double avg_num_sentences) {
        this.avg_num_sentences = avg_num_sentences;
    }

    // Getters and setters for average number of words
    public double getAvg_num_words() {
        return avg_num_words;
    }

    public void setAvg_num_words(double avg_num_words) {
        this.avg_num_words = avg_num_words;
    }

    // Getters and setters for average number of nouns
    public double getAvg_num_nouns() {
        return avg_num_nouns;
    }

    public void setAvg_num_nouns(double avg_num_nouns) {
        this.avg_num_nouns = avg_num_nouns;
    }

    // Getters and setters for average number of verbs
    public double getAvg_num_verbs() {
        return avg_num_verbs;
    }

    public void setAvg_num_verbs(double avg_num_verbs) {
        this.avg_num_verbs = avg_num_verbs;
    }

    // Getters and setters for average number of adjectives
    public double getAvg_num_adjectives() {
        return avg_num_adjectives;
    }

    public void setAvg_num_adjectives(double avg_num_adjectives) {
        this.avg_num_adjectives = avg_num_adjectives;
    }

    // Additional getters and setters for any other fields

    @Override
    public String toString() {
        return "AggregatedTextStatistics{" +
                "avgNumSentences=" + avg_num_sentences +
                ", avgNumWords=" + avg_num_words +
                ", avgNumNouns=" + avg_num_nouns +
                ", avgNumVerbs=" + avg_num_verbs +
                ", avgNumAdjectives=" + avg_num_adjectives +
                '}';
    }
}
