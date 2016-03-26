# -*- coding: utf-8 -*-
"""
Created on Mon Oct 21 13:28:04 2013

@author: santosh
"""

Copy the source files and txtfiles/ directory to a different location
and then try running the code

1. Run gen_feat_space.py

    it will generate feat matrix in feats/ directory

2. Run cluster_words.py with following arguments

    (a) python cluster_words.py
        input: 1

        this will create a similarity matrix and stores
        top similar words in a file
        similar_top/ directory will be created.

    (b) python cluster_words.py 2
        input: 2

        this will create hard clustering on the words
        output will be written into files