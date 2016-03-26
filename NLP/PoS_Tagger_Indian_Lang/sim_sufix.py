# -*- coding: utf-8 -*-
"""
Created on Thu Oct 24 22:37:21 2013

@author: santosh

get similar suffixes
"""

import os
import codecs
import operator


def get_uni(fname):
    """
    the method returns the unique unigrams and the sentences from the corpus
    fname: name of the file/dataset in the corpus
    """

    unigrams = []  # temporary unigram dict

    fptr = codecs.open(fname, 'r', 'utf-8')

    for line in fptr:
        line = line.replace('\n', '')

        if("mar" not in fname):
            line = line.split("\"")[1]
        line = line.strip()
        vals = line.split(" ")

        for val in vals:

            if len(val) > 0:
                if val not in unigrams:
                    unigrams.append(val)

    fptr.close()

    return unigrams


def start():
    """
    the main function
    """

    tdir = 'txtfiles/'

    if(not os.path.exists(tdir)):
        print 'Cannot find txtfiles/ directory.'

    files = os.listdir(tdir)

    if(not os.path.exists('./feats/')):
        print 'feats/ directory not found.'
        os.system("mkdir feats")
        print 'feats/ dir created.'

    for fname in files:

        lang = fname.split("_")[0]  # get the lang name from the file name
        print 'Now processing ' + lang,
        print 'Takes time ...'

        uni = get_uni(tdir + fname)

        sfx_1 = {}
        sfx_2 = {}
        sfx_3 = {}
        sfx_4 = {}
        sfx_5 = {}

        i = 0
        while(i < len(uni)):

            w1 = uni[i]
            j = i + 1
            last_1 = u''
            last_2 = u''
            last_3 = u''
            last_4 = u''
            last_5 = u''

            if(len(w1) >= 5):
                last_5 = w1[-5:]
            if(len(w1) >= 4):
                last_4 = w1[-4:]
            if(len(w1) >= 3):
                last_3 = w1[-3:]
            if(len(w1) >= 2):
                last_2 = w1[-2:]
            if(len(w1) >= 1):
                last_1 = w1[-1]

            while(j < len(uni) - 1):

                w2 = uni[j]
                if(last_5 in w2[-5:]):
                    if(last_5 in sfx_5):
                        sfx_5[last_5] += 1
                    else:
                        sfx_5[last_5] = 1

                if(last_4 in w2[-4:]):
                    if(last_4 in sfx_4):
                        sfx_4[last_4] += 1
                    else:
                        sfx_4[last_4] = 1

                if(last_3 in w2[-3:]):
                    if(last_3 in sfx_3):
                        sfx_3[last_3] += 1
                    else:
                        sfx_3[last_3] = 1

                if(last_2 in w2[-2:]):
                    if(last_2 in sfx_2):
                        sfx_2[last_2] += 1
                    else:
                        sfx_2[last_2] = 1

                if(last_1 in w2[-1]):
                    if(last_1 in sfx_1):
                        sfx_1[last_1] += 1
                    else:
                        sfx_1[last_1] = 1

                j += 1

            i += 1

        top_5 = sorted(sfx_5.iteritems(), key=operator.itemgetter(1),
                       reverse=True)

        top_4 = sorted(sfx_4.iteritems(), key=operator.itemgetter(1),
                       reverse=True)

        top_3 = sorted(sfx_3.iteritems(), key=operator.itemgetter(1),
                       reverse=True)

        top_2 = sorted(sfx_2.iteritems(), key=operator.itemgetter(1),
                       reverse=True)

        top_1 = sorted(sfx_1.iteritems(), key=operator.itemgetter(1),
                       reverse=True)

        p = 0.2

        sname = 'sim_sufix/' + lang + '_sfx.txt'
        with codecs.open(sname, 'w', 'utf-8') as fptr:
            for k in range(int(len(top_5) * p)):
                word = top_5[k][0]
                #print top_5[k][0],
                #print top_5[k][1]
                fptr.write(word + ', ')
            fptr.write('\n-----------\n')

            for k in range(int(len(top_4) * p)):
                word = top_4[k][0]
                fptr.write(word + ', ')
            fptr.write('\n-----------\n')

            for k in range(int(len(top_3) * p)):
                word = top_3[k][0]
                fptr.write(word + ', ')
            fptr.write('\n-----------\n')

            for k in range(int(len(top_2) * p)):
                word = top_2[k][0]
                fptr.write(word + ', ')
            fptr.write('\n-----------\n')

            for k in range(int(len(top_1) * p)):
                word = top_1[k][0]
                fptr.write(word + ', ')
            fptr.write('\n-----------\n')

start()
print 'Done !!'
