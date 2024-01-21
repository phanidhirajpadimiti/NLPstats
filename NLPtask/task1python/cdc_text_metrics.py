import time
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import spacy
import csv

# Check if the spaCy English model is installed, and install it if not
try:
    nlp = spacy.load("en_core_web_sm")
except OSError:
    print("Downloading spaCy model...")
    from spacy.cli import download
    download("en_core_web_sm")
    nlp = spacy.load("en_core_web_sm")

# Function to scrape a single web page using Selenium
def scrape_web_page(url):
    # Configure Chrome to run in headless mode
    chrome_options = Options()
    chrome_options.add_argument("--headless")

    # Initialize the Chrome driver
    driver = webdriver.Chrome(options=chrome_options)

    try:
        # Navigate to the page
        driver.get(url)

        # Wait for the element with class 'result-description' to be present
        wait = WebDriverWait(driver, 3)
        result_description = wait.until(
            EC.presence_of_element_located((By.CLASS_NAME, 'result-description'))
        )

        # Extract the text and process it with spaCy for NLP statistics
        doc = nlp(result_description.get_attribute("textContent") if result_description else "")

        # Calculate various NLP statistics
        num_sentences = len(list(doc.sents))
        num_words = len(doc)
        num_nouns = len([token for token in doc if token.pos_ == 'NOUN'])
        num_verbs = len([token for token in doc if token.pos_ == 'VERB'])
        num_adjectives = len([token for token in doc if token.pos_ == 'ADJ'])

        # Return the statistics in a dictionary
        return {
            'url': url,
            'num_sentences': num_sentences,
            'num_words': num_words,
            'num_nouns': num_nouns,
            'num_verbs': num_verbs,
            'num_adjectives': num_adjectives,
        }
    finally:
        # Close the browser once done
        driver.quit()

# Base URL for scraping
cdc_base_url = 'https://search.cdc.gov/search/?query=heart%20disease'
results_list = []

# Scrape 100 pages, creating a URL for each page
for page_number in range(1, 101):
    print(f'Scraping page {page_number}...')
    url_to_scrape = f'{cdc_base_url}&dpage={page_number}'
    result = scrape_web_page(url_to_scrape)
    results_list.append(result)
    # Delay to prevent overloading the server
    time.sleep(3)

# Write the scraped data to a CSV file
csv_file_path = 'cdc_results.csv'
with open(csv_file_path, 'w', newline='') as csv_file:
    fieldnames = ['url', 'num_sentences', 'num_words', 'num_nouns', 'num_verbs', 'num_adjectives']
    writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
    # Write the header
    writer.writeheader()
    # Write the data
    for result in results_list:
        writer.writerow(result)

print(f'Results written to {csv_file_path}')

# Calculate the average of each statistic across all pages
average_stats = {
    'avg_num_sentences': sum(result['num_sentences'] for result in results_list) / len(results_list),
    'avg_num_words': sum(result['num_words'] for result in results_list) / len(results_list),
    'avg_num_nouns': sum(result['num_nouns'] for result in results_list) / len(results_list),
    'avg_num_verbs': sum(result['num_verbs'] for result in results_list) / len(results_list),
    'avg_num_adjectives': sum(result['num_adjectives'] for result in results_list) / len(results_list)
}

# Write the aggregated results to a separate CSV file
aggregated_csv_file_path = 'aggregated_results.csv'
with open(aggregated_csv_file_path, 'w', newline='') as csv_file:
    fieldnames = ['avg_num_sentences', 'avg_num_words', 'avg_num_nouns', 'avg_num_verbs', 'avg_num_adjectives']
    writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
    writer.writeheader()
    writer.writerow(average_stats)

print(f'Aggregated results written to {aggregated_csv_file_path}')
