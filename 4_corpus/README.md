# Important note about the corpus

The corpus is an important part of the design, since it is used for both classifier training and testing.

In this folder you will find the annotated corpus that is used in the created scripts. These corpus are public but you must follow the rules implemented by the use license present in this same repository.

Each implemented code has references to a corpus present in this folder.


## folder: [Corpus] - Bibtex_formatted

Contains 149 bibtex items with all information of the study and the abstract.

## folder: [Corpus] - Results_quantitative_qualitative

### [corpus] format_1

Contains 301 abstracts annotated with the class <results/>. The <results> tag contains multiple </result> tags, each of then contains sentences splitted and tagged using the <TypeOfResult> tag. This tag contains "quantitative" class or "qualitative". 

	Example:

	<results>
		<result>
			**sentence**
			<TypeOfResult>classification</TypeOfResult>
		</result>
	</results>

### [corpus] format_2

Contains 270 sentences extracted from Abstracts in the element "results". This sentences were annotated with "quantitative" and "qualitative" classes to identify wich type of result the sentence describes.

** folder: Single_abstract

Contains a single abstract used for tests of NLP algorithms

** folder: Pre-Processing

Contains a list of stopwords and symbols that must be removed to clear the text.

It is important to mention that pre-processing should be thought for each project. Therefore, just removing the symbols that are contained in the file can be detrimental to your project depending on your purpose.