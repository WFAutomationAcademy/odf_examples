package com.workfusion.examples.aa_examples_bots.apiparsing.common_workflow;

import com.workfusion.examples.aa_examples_bots.common.utils.HttpRequester;
import com.workfusion.odf2.core.webharvest.TaskInput;
import com.workfusion.odf2.core.webharvest.service.vault.SecretsVaultService;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class GetResponseFromLocationIQ {

    private final TaskInput taskInput;
    private final SecretsVaultService secretsVaultService;


    public static final String API_ACCESS_TOKEN_ALIAS = "LocationIQ_API_access_token";
    public static final String INPUT_COLUMN_NAME = "company";

    private static final String LOCATION_IQ_URL = "https://us1.locationiq.com/v1/search.php";

    public GetResponseFromLocationIQ(TaskInput taskInput, SecretsVaultService secretsVaultService) {
        this.taskInput = taskInput;
        this.secretsVaultService = secretsVaultService;
    }

    public String getLocationIQResponse() {
        //Get company name from input variables and access token from Secrets Vault
        String company = taskInput.getRequiredVariable(INPUT_COLUMN_NAME);
        String accessToken = secretsVaultService.getEntry(API_ACCESS_TOKEN_ALIAS).getValue();

        //Create a list of request parameters
        List<NameValuePair> requestParams = new ArrayList<>();
        requestParams.add(new BasicNameValuePair("q", company));
        requestParams.add(new BasicNameValuePair("format", "xml"));
        requestParams.add(new BasicNameValuePair("key", accessToken));

        HttpRequester httpRequester = new HttpRequester();

        //Execute request and return result
        return httpRequester.sendGet(LOCATION_IQ_URL, requestParams);
    }
}
