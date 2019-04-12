** Folder: src

Contains: 

→ Approaches: Implemented approaches of CM extraction and arff generation to weka testing.
→ Exceptions: Implemented exeptions of the project
→ GUI: Implemented user interface to generate CMs
→ Model: Implemented classes used in the project
→ Modules: Contains functionalities used in the approach isolated in classes. Each module can be replaced or new modules can be easily added into folders.
→ Presets: Contains a class that returns the template proposed By Felizardo (2017).
→ Images: contains images.
→ Utils: contains implemented functionalities not related directly to the core of the project.


** Folder: Test

Contains:

→ Examples - see here working examples of approaches of NLP. 

	- Information extraction
	- Anaphora resolution

→ functionalities - This folder contains punctual tests of natural language processing algorithms, machine learning and Input or output.
	
	I/O
	- BibtexParser
	- FileLoader

	NLP
	- ClearAbstract
	- Preprocessing
	- SentenceSplitter
	- StanfordLemma
	- PosTagging

	Machine learning
	- Classifing Using j48
	- TermFrequency


→ Generators - This folder contains:
	
	- The basic generator of Concept Maps ( without User interface). 
	- The generator of arff file to training algorithm in weka.
	- The generator of pre-processed corpus.