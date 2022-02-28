package com.workfusion.examples.aa_examples_bots.translatingwithapi.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.workfusion.bot.service.SecureEntryDTO;
import com.workfusion.examples.aa_examples_bots.common.task.GenericTaskMultipleResults;
import com.workfusion.examples.aa_examples_bots.common.utils.HttpRequester;
import com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.request.RequestItem;
import com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.response.detect.DetectResponseItem;
import com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.response.translate.TranslateResponseItem;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.webharvest.TaskInput;
import com.workfusion.odf2.core.webharvest.service.vault.SecretsVaultService;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@BotTask
public class TranslateWithMSAzureTranslatorTask implements GenericTaskMultipleResults {

    //This Secrets Vault record should contain Azure Translator Service region as key
    //and api access key as value
    //Register on https://azure.microsoft.com/en-us/services/cognitive-services/translator/
    //Create your instance of translator service (Can be used free subscription). Obtain your access token and region.
    public static final String AZURE_TRANSLATOR_CREDENTIALS_ALIAS = "azure_translator_api_credentials";

    public static final String TEXT_INPUT_COLUMN_NAME = "text_to_translate";
    public static final String TRANSLATE_TO_INPUT_COLUMN_NAME = "translate_to";

    private static final String AZURE_HOST = "https://api.cognitive.microsofttranslator.com";
    private static final String DETECT_LANGUAGE_ENDPOINT = "/detect";
    private static final String TRANSLATE_ENDPOINT = "/translate";

    private static final NameValuePair COMMON_REQUIRED_URL_PARAMS
            = new BasicNameValuePair("api-version", "3.0");

    private final TaskInput taskInput;
    private final SecretsVaultService secretsVaultService;

    @Inject
    public TranslateWithMSAzureTranslatorTask(Injector injector) {
        this.taskInput = injector.instance(TaskInput.class);
        this.secretsVaultService = injector.instance(SecretsVaultService.class);
    }

    @Override
    public List<Map<String, String>> run() {
        Gson gson = new Gson();
        List<NameValuePair> headers = getHeaders();
        String textToTranslate = taskInput.getRequiredVariable(TEXT_INPUT_COLUMN_NAME);

        //Create body json for requests
        List<RequestItem> requestItems = Collections.singletonList(new RequestItem().setText(textToTranslate));
        String requestsBodyAsJsonString = gson.toJson(requestItems);

        HttpRequester httpRequester = new HttpRequester();

        //Execute "detect" request to get language of provided text and get response as string
        String detectLanguageResponseAsString = httpRequester.sendPost(AZURE_HOST + DETECT_LANGUAGE_ENDPOINT,
                Collections.singletonList(COMMON_REQUIRED_URL_PARAMS), headers, requestsBodyAsJsonString);

        //Deserialize response to get it as java object
        List<DetectResponseItem> detectResponseItems = gson.fromJson(detectLanguageResponseAsString,
                new TypeToken<List<DetectResponseItem>>() {
                }.getType());

        //Get detected language from response
        String detectedLanguage = detectResponseItems.get(0).getLanguage();

        //Get language to translate text to
        String translateTo = taskInput.getRequiredVariable(TRANSLATE_TO_INPUT_COLUMN_NAME);

        //Define url parameters for "translate" request
        List<NameValuePair> translateRequestUrlParams = new ArrayList<>();
        translateRequestUrlParams.add(COMMON_REQUIRED_URL_PARAMS);
        translateRequestUrlParams.add(new BasicNameValuePair("from", detectedLanguage));
        translateRequestUrlParams.add(new BasicNameValuePair("to", translateTo));

        //Execute "translate" request to get translated text and get response as string
        String translateResponseAsString = httpRequester.sendPost(AZURE_HOST + TRANSLATE_ENDPOINT,
                translateRequestUrlParams, headers, requestsBodyAsJsonString);

        //Deserialize response to get it as java object
        List<TranslateResponseItem> translateResponseItems = gson.fromJson(translateResponseAsString,
                new TypeToken<List<TranslateResponseItem>>() {
                }.getType());

        //Fill output data to be exported
        Map<String, String> result = new LinkedHashMap<>();
        result.put("detected_language", detectedLanguage);
        result.put("translated_text", translateResponseItems.get(0)
                .getTranslations().get(0).getText());

        return Collections.singletonList(result);
    }

    //Returns common headers for both requests
    private List<NameValuePair> getHeaders() {
        SecureEntryDTO secureEntry = this.secretsVaultService.getEntry(AZURE_TRANSLATOR_CREDENTIALS_ALIAS);
        List<NameValuePair> headers = new ArrayList<>();
        headers.add(new BasicNameValuePair("Ocp-Apim-Subscription-Region", secureEntry.getKey()));
        headers.add(new BasicNameValuePair("Ocp-Apim-Subscription-Key", secureEntry.getValue()));
        return headers;
    }
}
