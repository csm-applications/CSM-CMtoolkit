# Welcome to the CMToolkit project.

This project was created by the research group of the Federal Technological University of Paraná - Cornélio Procópio Campus.


Before you start, you need to understand a bit of the context which this tool is placed. Our main goal is to support secondary studies, such as Systematic Literature Reviews (SLR) and Systematic Mappings (SM). This type of study have been increasingly used in Software Engineering (SE) since they allow
the identification of available evidence related to a research topic. One of the main activities of the process of conducting a secondary study is the primary studies selection, which involves, at first, the reading of the abstracts of the candidate studies. However, with the growing number of scientific publications, coupled with the poor quality of their abstracts, it makes this activity increasingly difficult for researchers. Some solutions have been proposed to mitigate the problem, among them, the use of structured abstracts and graphic summaries. Previous studies have proposed guidelines for the construction of graphic summaries. However, these summaries continue to be created manually. 

This tool was created to validate the possibility of proposing an efficient approach for the automatic construction of graphic abstracts based on CMs using NLP techniques. For this, we studied related areas like the main practices for the construction of CMs from NLP. 

This tool is a beta version, however, preliminary results show that the proposed initiative can generate valid propositions and represent graphic summaries through CMs. It means that it can possibily be used to summarize a complex structure of textual information, contributing to the identification of the most important information of an article and supporting the selection of primary studies in a SLR or SM. 


# This project contains:

- 1_ Official documentation presented in a master's thesis document - 04/02/2018;
- 2_ Java implementation of concept map generator;
- 3_ Python implementation (beta version) to be used in future projects;
- 4_ Annotated corpus of abstracts used for training and testing the proposed approach.

# Running the project

## Step 01 - Configuring your environment

To run this project you will need Java (1.8 version) installed and correctly configured. In my lab tests, I used netbeans 11 for running this project, feel free to use any IDE that you want.

- Learn more how to install an configure java and netbeans? access: 
https://www3.ntu.edu.sg/home/ehchua/programming/howto/netbeans_howto.html

Next, you need to solve all dependencies of this project. For this you have to download:

- Stanford core NLP for Java: https://stanfordnlp.github.io/CoreNLP/
- Weka 3.8 Jar: https://waikato.github.io/weka-wiki/downloading_weka/
- Jbibtex: https://mvnrepository.com/artifact/org.jbibtex/jbibtex
 
You also may need to download bounce, for using swing interface of this software:

- Bounce: https://jar-download.com/artifacts/nz.ac.waikato.cms.weka.thirdparty/bounce/0.18/source-code

After downloading all Jar files, setup a new project on Netbeans or any other IDE and add to classpath the dependencies that you downloaded. After that, all errors of missing classes must be solved.

## Step 02: setup

Consider that this software is still on beta version, therefore, some improvements and issues must be done in this step. However, until now must configure some parameters to correctly run the software. 

Please access source > util > Paths.java

In this file you must setup:
```Java

public class Paths {
    public static String STOPWORDS = "<absolute path>\\test\\resources\\Stopwords.txt";
    public static String SYMBOLS = "<absolute path>\\test\\resources\\Symbols.txt";
    public static String ABSTRACTS = "<absolute path>\\test\\resources\\abstracts";
    public static String CORPUS_SWT = "<absolute path>\\test\\resources\\Corpus\\SoftwareTest";
    public static String PREP_CORPUS_SWT = "<absolute path>\\test\\resources\\PreProcessedCorpus\\SoftwareTest";
}

```

These paths reference the files that contains stopwords, symbols to remove. In addition, they contain the Path for corpus and pre-processed corpus used for this project. 

## Step 03: Run

I recommend to use the graphical interface to build Conceptual Maps. You can easily find it in source package > GUI > main.java. In this interface you will find an windows to place your abstract divided in five different classifications (context, objective, methods, results, conclusion). After placing your abstract, click on "Generate" and wait for the program output your concept maps.

The graphic interface looks like this after concept map generation:

![alt text](https://github.com/csm-applications/CSM-CMtoolkit/blob/master/1_Documentation/CMGeneratorScreenshot.JPG)


In your netbeans console you can observe generation progress and some details about classifier training and Open IE extractions. In the current version the classifier achieved this performance:

```plaintext
------------------Classifier statistics------------------


Correctly Classified Instances           103219               83.4073 %
Incorrectly Classified Instances          20534               16.5927 %
Kappa statistic                          0.6016
Mean absolute error                      0.1672
Root mean squared error                  0.4074
Relative absolute error                 40.0142 %
Root relative squared error             89.1071 %
Total Number of Instances                123753     

=== Detailed Accuracy By Class ===

                 TP Rate  FP Rate  Precision  Recall   F-Measure  MCC      ROC Area  PRC Area  Class
                 0,716    0,116    0,723      0,716    0,719      0,602    0,766     0,560     Quantitative
                 0,884    0,284    0,880      0,884    0,882      0,602    0,766     0,842     Qualitative
Weighted Avg.    0,834    0,234    0,834      0,834    0,834      0,602    0,766     0,758     
----------------------------End--------------------------
```


After concept map generation, the window will display the following informations:
```plaintext

<root>Analyzing the Use of Concept Maps in Computer Science: A Systematic Mapping Study</root>
	([CP2] Context)
	([CP3] Objective)
	([CP4] Method)
	([CP5] Result)
		are divided into → ([CP7] Qualitative)
		are divided into → ([CP8] Quantitative)
	([CP6] Conclusion)
	([CP7] Qualitative)
		like → ([CP9] CMs initiative)
		like → ([CP11] information)
		like → ([CP13] we)
		like → ([CP15] relevant information)
		like → ([CP16] it)
		like → ([CP18] mapping)
		like → ([CP20] increase interest)
	([CP8] Quantitative)
	([CP9] CMs initiative)
		be in → ([CP10] different subarea of Computer Science)
	([CP10] different subarea of Computer Science)
	([CP11] information)
		answer → ([CP12] set of research question)
	([CP12] set of research question)
	([CP13] we)
		identify → ([CP14] 108 study)
	([CP14] 108 study)
	([CP15] relevant information)
		answer → ([CP12] set of research question)
	([CP16] it)
		have → ([CP17] have extensively investigate)
	([CP17] have extensively investigate)
	([CP18] mapping)
		show → ([CP19] increase interest in topic in recent year)
	([CP19] increase interest in topic in recent year)
	([CP20] increase interest)
		be in → ([CP21] recent year)
	([CP21] recent year)
```

which can be summarized visually in:

![alt text](https://github.com/csm-applications/CSM-CMtoolkit/blob/master/1_Documentation/GeneratedCMExample.jpg)


# License

This project is under GPL V3 license. Basically it means that you can:
- Commercial Use
- Modify
- Distribute
- Place Warranty
- Use Patent Claims

But you can't:
- Sublicense
- Hold Liable

and, you must:
- Include original
- State changes
- Disclose Source
- Include License
- Include Copyright
- Include Install Instructions

Learn more about this license:
- https://www.gnu.org/licenses/gpl-3.0.en.html