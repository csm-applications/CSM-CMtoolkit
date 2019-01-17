# -*- coding: utf-8 -*-
import numpy as np
import re
import pickle
import nltk
from nltk.corpus import stopwords
from sklearn.datasets import load_files

#importing the dataset

abstracts = load_files('C:\\Users\\vini_\\Documents\\GitHub\\CSM-CMtoolkit\\test-files\\flcorpus')
X,y = abstracts.data,abstracts.target


# storing as pickle files
with open('X.pickle', 'wb') as f:
    pickle.dump(X,f)
with open('y.pickle', 'wb') as f:
    pickle.dump(y,f)
    

# creating the corpus

corpus = []


for i in range (0,len(X)):
    #abstracts = re.sub(r'\W', ' ', str(X[i]))
    abstracts = re.sub(r'\s+[a-z]\s+', ' ', str(X[i]))
    abstracts = abstracts.lower()
    #abstracts = re.sub(r'\s+[a-z]\s+', ' ', abstracts)
    abstracts = re.sub(r'\d', '#n', abstracts)
    abstracts = re.sub(r'^[a-z]\s+', ' ', abstracts)
    abstracts = re.sub(r'\\[a-z]', ' ', abstracts)
    abstracts = re.sub(r'\\\\[a-z]', ' ', abstracts)
    abstracts = re.sub(r'b\'', '', abstracts)
    abstracts = re.sub(r'\s+',' ', abstracts)
    abstracts = re.sub(r'^\s','', abstracts)
    abstracts = re.sub(r'\s&','', abstracts)
    corpus.append(abstracts)


from sklearn.feature_extraction.text import CountVectorizer

vectorizer = CountVectorizer(max_features=100, min_df = 0, max_df = 0.8, stop_words = stopwords.words('english'))
X = vectorizer.fit_transform(corpus).toarray()


from sklearn.feature_extraction.text import TfidfTransformer
transformer = TfidfTransformer()
X = transformer.fit_transform(X).toarray()

# separando em 2 set (teste e treinamento)
from sklearn.model_selection import train_test_split
text_train, text_test, sent_train, sent_test = train_test_split(X,y,test_size=0.2,random_state=0)

# criando um classificador de regressão logistica
from sklearn.linear_model import LogisticRegression

classifier = LogisticRegression()
classifier.fit(text_train,sent_train)

# predicting um novo exemplar
sent_pred = classifier.predict(text_test)

from sklearn.metrics import confusion_matrix
cm = confusion_matrix(sent_test,sent_pred)

# salvando o modelo

with open('classifier.pickle', 'wb') as f:
    pickle.dump(classifier,f)
    
with open ('tfidfmodel.pickle', 'wb') as f:
    pickle.dump(vectorizer,f)

# unpickling the classifier and vectorizer

with open('classifier.pickle', 'rb') as f:
    clf = pickle.load(f)
    
with open('tfidfmodel.pickle', 'rb') as f:
    tfidf = pickle.load(f)
    
sample = ['Seventy-seven percent of journal/conference papers focus on 20% of subject indexes in software engineering, including Testing and Debugging (D.2.5), Software/Program Verification (D.2.4), and Management (D.2.9).']
sample = tfidf.transform(sample).toarray()
    
print (clf.predict(sample))

print(sample)