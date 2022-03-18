# Description

Samples for “Code-based RPA” course on [AutomationAcademy.com](https://automationacademy.com/learn/my/).

# Installation

You should have account on [WorkFusion Documentation](https://doc.workfusion.com) to access these links.
* [IA Cloud Developer installation](https://doc.workfusion.com/enterprise/docs/iac/core/studio/install-iac-developer/)
* [Importing project into WF Studio](https://doc.workfusion.com/enterprise/docs/iac/core/studio/set-up-workfusion-samples-project-from-github/). 
Note that in order this guide can be applicable to odf_examples repository you should change the link from rpa_samples (https://github.com/WFAutomationAcademy/rpa_samples.git) to odf_examples (https://github.com/WFAutomationAcademy/odf_examples.git)  

# Project structure

Project consists of 2 different types of examples: Code examples and full ODF 2 based examples

1. Code examples
Can be found directly in the following directory: aa-examples/src/main/java/com/workfusion/example

Has 4 examples: Exception handling, Screenshots making, Send email, Write to Excel

Directory | Description | 
| :-----: | :--------: | 
|aa-examples/src/main/java/com/workfusion/example | Directory for code scripts| 

2. Full ODF 2 based examples
Has multiple examples inside the folder aa-examples/aa-examples-bots:

Directory | Description | 
| :-----: | :--------: | 
|aa-examples-bots-bcb/src/main/../aa_examples_bots | Directory for code scripts
|aa-examples-bots-bcb/src/tests/../aa_examples_bots | Directory for JUnit tests
|aa-examples-bots-bcb/src/main/resources/input | csv files, which are used as input for BP


# Samples

## 1. Code examples

* ExampleExeption - separate piece of code, that can be used in each project for throwing exceptions via messages (https://github.com/WFAutomationAcademy/odf_examples/blob/main/src/main/java/com/workfusion/example/ExampleException.java).    
* MakeScreenshot - separate piece of code with multiple examples of how making the screenshots can be implemented inside the code (https://github.com/WFAutomationAcademy/odf_examples/blob/main/src/main/java/com/workfusion/example/MakeScreenshot.java).
* SendEmail - separate piece of code, that can be used for sending mails with text body (https://github.com/WFAutomationAcademy/odf_examples/blob/main/src/main/java/com/workfusion/example/SendEmail.java).
* WriteToExcel - separate piece of code, that can be used for creating Excel file and adding some data inside it (https://github.com/WFAutomationAcademy/odf_examples/blob/main/src/main/java/com/workfusion/example/WriteToExcel.java).

##  2. Full ODF 2 based examples

* Apiparsing - the basics of working with API services (in particular with https://us1.locationiq.com/)  (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/apiparsing).
* Common - combined things that are used in multiple examples for http requests and outputs. It is not a standalone example to solve the particular automation task. (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/common).
* Translatingwithapi - translating the text, using MS Azure by API requests (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/translatingwithapi).
* Scrapwebtable - scraping the data from the web site table and parsing the result (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/scrapwebtable).
* Notepad - work with Notepad application (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/notepad).
* Webapplication - working with https://train-invoiceplane.workfusion.com web application, (login, upload the data, create and save invoices) (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/webapplication).
* Google - scraping the search results on the Google web site (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/google).
* Steam - find the game with maximum discount on Steam web site and scraps its name and release date (https://github.com/WFAutomationAcademy/odf_examples/tree/main/aa-examples-bots/aa-examples-bots-bcb/src/main/java/com/workfusion/examples/aa_examples_bots/steam).
