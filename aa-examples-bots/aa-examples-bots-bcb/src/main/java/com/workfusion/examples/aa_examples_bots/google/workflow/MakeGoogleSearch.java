package com.workfusion.examples.aa_examples_bots.google.workflow;

import com.workfusion.examples.aa_examples_bots.google.automation.client.GoogleClient;
import com.workfusion.examples.aa_examples_bots.google.automation.entities.FoundItemDto;
import com.workfusion.examples.aa_examples_bots.google.automation.pages.MainPage;
import com.workfusion.examples.aa_examples_bots.google.automation.pages.ResultsPage;
import org.slf4j.Logger;

import java.util.List;

public class MakeGoogleSearch {

    private static final String GOOGLE_URL = "https://google.com";

    public static List<FoundItemDto> makeSearch(Logger logger, String textToSearch) {
        //Create client to be an entry point
        GoogleClient googleClient = new GoogleClient(logger);
        //Go to Main page
        MainPage mainPage = googleClient.getMainPage(GOOGLE_URL);
        //Search provided text
        ResultsPage resultsPage = mainPage.makeSearch(textToSearch);
        //Return search results
        return resultsPage.getFoundItemsDto();
    }
}
