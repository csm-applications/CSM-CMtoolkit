# Understanding the classification task

This section contains the implementations of the classifiers predicting the content of a sentence taken from a summary. Also present here are the references to folder "corpus" used for training and testing of these classifiers.

The classifiers were thought to perform the prediction of the class of sentences written in natural language. The categories to be predicted are described in the article by Felizardo, 2017.

In short, the first level contains the items of the structured abstract: context, objectives, methods, results, and conclusion. Already in a second level are the more specific items of each of the items of level 1. For example: for the results we have the possibility of a result to be quantitative or qualitative.

The classifiers present in this folder should be trained (supervised learning) and statistically predict which class is most likely for a new sentence.