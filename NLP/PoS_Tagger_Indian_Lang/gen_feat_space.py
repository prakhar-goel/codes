# -*- coding: utf-8 -*-
"""
Created on Thu Oct 12 22:37:21 2013

@author: santosh

generate the feature space(matrix) based on the context
"""


from numpy import ndarray, save
import os
import codecs
import operator


TOP_IGN = 50  # ingore the top 50 words
CON_NXT = TOP_IGN + 200  # and consider the next 200 words
CONTEXT = 2  # + or - 2 words (left or right)


def write_words(uname, s_uni):
    """
    this method writes the unique words into a file
    """

    with codecs.open(uname, 'w', 'utf-8') as fptr:
        for unigram in s_uni:
            fptr.write(unigram[0] + '\n')


def get_uni(fname):
    """
    the method returns the unique unigrams and the sentences from the corpus
    fname: name of the file/dataset in the corpus
    """

    uni_temp = {}  # temporary unigram dict
    sents = []

    fptr = codecs.open(fname, 'r', 'utf-8')

    for line in fptr:
        line = line.replace('\n', '')

        if("mar" not in fname):
            line = line.split("\"")[1]
        line = line.strip()
        vals = line.split(" ")

        if(len(line) > 1):
            sents.append(line)

        for val in vals:

            if len(val) > 0:
                if val in uni_temp:
                    uni_temp[val] += 1
                else:
                    uni_temp[val] = 1

    fptr.close()

    return uni_temp, sents


def is_in_context(sents, fword, uword):
    """
    checking whether the uniq word (uword) occurs in the CONTEXT of a
    feature word (fword) in all the sentences (sents)
    sents: all the sentences
    fword: feature word
    uword: uniq word
    """

    left = 0
    right = 0

    #print 'fword : ' + fword.encode("utf-8"),
    #print 'uword : ' + uword.encode("utf-8")

    for sent in sents:

        words = sent.split(' ')
        if (fword in words and uword in words):

            j = 0
            fword_cnt = words.count(fword)
            fix = 0
            # if feature word occurs more than once in a sentence
            while(j < fword_cnt):
                cfix = words.index(fword, fix)  # current fword index
                fix = cfix
                # uniq word might occur more than once in one CONTEXT
                # rare, but possible if CONTEXT is increased
                left += words[cfix - CONTEXT:cfix].count(uword)
                right += words[cfix:cfix + CONTEXT].count(uword)
                j += 1

    return left, right


def get_row(sents, feats, uword):
    """
    get the row (feat vector) for each unique word
    sents: all the sentences in the corpus
    feats: the feature words
    uword: the uniq word for which the feat vect has to be constructed
    """

    len_fv = len(feats)
    row = ndarray(shape=(1, len_fv * 2), dtype = float)  # init a row

    # for each feature, i.e, each feature word
    for i in range(len(feats)):

        fword = feats[i]
        left, right = is_in_context(sents, fword, uword)

        row[0][i] = left
        row[0][len_fv + i - 1] = right

    #print row

    return row


def gen_matrix(sents, feats, s_uni):
    """
    generate the matrix in feature space
    each row represents a feature vector for a uniq word
    each column represents the dimension of the feat vector i.e., context
    """

    matrix = ndarray(shape=(len(s_uni), len(feats) * 2), dtype=float)

    for i in range(len(s_uni)):

        print i,

        uword = s_uni[i][0]  # uniq word

        row = get_row(sents, feats, uword)
        matrix[i] = row

    return matrix


def get_feats(s_uni):
    """
    get the feature vector, most frequently occurring words * 2
    s_uni: frequncy sorted list of unique words
    """

    feats = []  # words in feature vector

    for i in range(len(s_uni)):
        # ignore the top top_ign words and consider the next con_nxt of words
        if i >= TOP_IGN and i < CON_NXT:
            feats.append(s_uni[i][0])

#    print 'No of feats : %d' % (len(feats))
#    for feat in feats:
#        print feat[0].encode("utf-8")

    return feats


def start():
    """
    the main function
    """

    tdir = 'txtfiles/'
    files = os.listdir(tdir)

    if(not os.path.exists('./feats/')):
        print 'feats/ directory not found.'
        os.system("mkdir feats")
        print 'feats/ dir created.'

    uni = {}

    for fname in files:

        lang = fname.split("_")[0]  # get the lang name from the file name
        print 'Now processing ' + lang,
        print 'Takes time ...'

        uni, sents = get_uni(tdir + fname)
        s_uni = sorted(uni.iteritems(), key=operator.itemgetter(1),
                       reverse=True)

        feats = get_feats(s_uni)

        matrix = gen_matrix(sents, feats, s_uni)

        sfile = 'feats/feat_space_' + lang
        save(sfile, matrix)
        write_words('feats/uniq_' + lang + '.txt', s_uni)
        print '\n' + sfile + ' saved.'
        print 'Done !!'

start()
