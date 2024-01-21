# NLPstats

# Python Web Scraping

**Overview**

This Python project is designed to scrape web pages from a health-related website and analyze the content using Natural Language Processing (NLP) techniques. The script extracts content from each web page, calculates various NLP statistics, and then writes these results to a CSV file. Additionally, it computes and records the average of these statistics across all pages in a separate CSV file.

**Features**

Web Scraping: Extracts text content from multiple web pages on a specified health-related website.
NLP Analysis: Calculates the number of sentences, words, nouns, verbs, and adjectives in the scraped text.
Data Aggregation: Computes the average values of the NLP statistics across all pages.
Result Exportation: Stores individual and aggregated statistics in CSV format for easy analysis and reporting.

**Technologies Used**

Python: The core programming language used for the project.
Selenium: A tool for automating web browsers, used here for scraping web content.
spaCy: An open-source software library for advanced NLP in Python.
CSV Module: For writing the extracted data and NLP statistics into CSV files.

**Requirements**
To run this script, you will need:

Python 3.x
Selenium (selenium package)
spaCy (spacy package) and its English model (en_core_web_sm)
Chrome WebDriver (for Selenium)
A running instance of the Chrome browser

**Screenshots**

![image](https://github.com/phanidhirajpadimiti/NLPstats/assets/142957336/2122612c-c350-45d3-8568-c405f2713735)

![image](https://github.com/phanidhirajpadimiti/NLPstats/assets/142957336/4f457894-6103-487f-9d77-d31cdff460bf)

# Spring Boot Text Analysis Application

**Overview**

This Spring Boot application provides functionality for analyzing text data. Users can upload text files, and the application will process these files to extract various statistics such as the number of sentences, words, nouns, verbs, and adjectives. The application also compares these statistics with aggregated data from a pre-existing CSV file.

**Features**

Text Analysis: Analyze uploaded text files to gather statistics.
Data Comparison: Compare text statistics with aggregated data from a CSV file.
Web Interface: Easy-to-use web interface for uploading text files and viewing results.

**Prerequisites**
Java JDK 21 or later
Maven 3.6.0 or later

**Usage**

Navigate to http://localhost:8080 in your web browser.
Upload a text file to get its statistical analysis.
View the comparison of current text statistics against aggregated data.

**Architecture**

This project is built using the Spring Boot framework, with the following key components:

Controller: Handles HTTP requests and interactions with the service layer.
Service: Business logic for text analysis and CSV data processing.
Model: Data models representing text statistics and aggregated statistics.
View: Thymeleaf templates for rendering the UI.

**Screenshots**

![image](https://github.com/phanidhirajpadimiti/NLPstats/assets/142957336/e94f0640-42c3-492c-93b6-880d0ffbee84)

![image](https://github.com/phanidhirajpadimiti/NLPstats/assets/142957336/f1c0cf58-716e-45dc-90a1-e8fcf9c212f6)

![image](https://github.com/phanidhirajpadimiti/NLPstats/assets/142957336/7f5b3a59-1b18-4f41-b65c-3686161052f2)






**Contact**

Phanidhiraj Padimiti - phanidhirajp@arizona.edu


