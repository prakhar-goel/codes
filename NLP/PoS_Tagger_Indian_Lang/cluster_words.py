# -*- coding: utf-8 -*-
"""
Created on Fri Oct 18 16:09:35 2013

@author: santosh

* Run this program only after running gen_feat_space.py
* This program computes distance between word vectors and forms clusters
* Similarity matrix is generated to form clusters

"""

import os
import numpy
import codecs


LANG = ['tel', 'kan', 'mar', 'hin']


def read_words(lname):
    """
    this method reads the unique words from the file
    """

    words = []
    with codecs.open(lname, 'r', 'utf-8') as fptr:
        for line in fptr:
            line = line.replace('\n', '')
            words.append(line)

    return words


def get_similarity(vect1, vect2):
    """
    computes the cosine similarity between two vectors
    """
#    print 'shapes'
#    print numpy.shape(vect1)
#    print numpy.shape(vect2)
#    vect1 = numpy.transpose(vect1)
    vect2 = numpy.transpose(vect2)
#    print 'shapes'
#    print numpy.shape(vect1)
#    print numpy.shape(vect2)
    sim = 0.0
    numr = numpy.dot(vect1, vect2)
    den = numpy.linalg.norm(vect1) * numpy.linalg.norm(vect2)

    if(den > 0):
        sim = numr / den

#    print numpy.shape(numr)
#    print numpy.shape(sim)
    return sim


def gen_sim_matrix(matrix):
    """
    this method generates similarity matrix for a give a matrix of word vectors
    matrix: feature space, word vectors
    """

    nrow = numpy.shape(matrix)[0]  # get no. of rows
    sim_matrix = numpy.ndarray(shape=(nrow, nrow), dtype=float)

    for i in range(nrow - 1):

        sim_matrix[i][i] = 0.0
        vect1 = matrix[i]
        j = i + 1
        while(j < nrow):
            vect2 = matrix[j]
            sim = get_similarity(vect1, vect2)
            sim_matrix[i][j] = sim
            sim_matrix[j][i] = sim
            #print '(%d, %d %.3f)' % (i, j, sim)
            j += 1
        #break

    return sim_matrix


def init_clusters(words):
    """
    initializes clusters, no. of clusters = no. of words
    """
    clusters = {}
    for i in range(len(words)):
        clusters[i] = [words[i]]  # dictionary
#        clusters.append(words[i])
    return clusters


def get_max_in_row(row, rix):
    """
    returns the max value and its index(argmax)
    """

    row[rix] = 0.0
    r_max = numpy.max(row)
    r_max_ix = numpy.argmax(row)

    return r_max, r_max_ix


def cluster_vectors(sim_matrix, words):
    """
    cluster the words types based on the similarity matrix
    bottom-up or agglomerative clustering
    initially each word is a cluster
    """

    clusters = init_clusters(words)
    while(len(clusters) > 100):
        max_vals = []
        val_ixs = []

        for i in range(numpy.shape(sim_matrix)[0]):
            r_max, r_max_ix = get_max_in_row(sim_matrix[i], i)
            max_vals.append(r_max)
            val_ixs.append(r_max_ix)

        max_sim_all = numpy.argmax(max_vals)  # get the index in of max sim
        # the highest similar word pair is
        print max_sim_all,
        print val_ixs[max_sim_all]
        print words[max_sim_all],
        print words[val_ixs[max_sim_all]]
        print len(clusters)
        clusters[max_sim_all].append(words[val_ixs[max_sim_all]])
        del clusters[val_ixs[max_sim_all]]
        print len(clusters)
        sim_matrix[max_vals[max_sim_all]][val_ixs[max_sim_all]] = 0.0
        sim_matrix[val_ixs[max_sim_all]][max_vals[max_sim_all]] = 0.0


def get_avg_vector(matrix, words, w_list):
    """
    get the average vector for one cluster
    """

#    print w_list

    avg_vector = numpy.zeros(shape=(1, numpy.shape(matrix)[1]), dtype=float)
#    print numpy.shape(avg_vector)
    for wrd in w_list:
        avg_vector += matrix[words.index(wrd)]
    numpy.divide(avg_vector, len(w_list))

    return avg_vector


def get_the_closest_pair(sim_matrix):
    """
    get the closest pair of word vectors from the sim matrix
    """

    max_vals = []
    val_ixs = []

    for i in range(numpy.shape(sim_matrix)[0]):
        r_max, r_max_ix = get_max_in_row(sim_matrix[i], i)
        max_vals.append(r_max)
        val_ixs.append(r_max_ix)

    max_sim_all = numpy.argmax(max_vals)  # get the index in of max sim
    # the highest similar word pair is
    cl1 = max_sim_all
    cl2 = val_ixs[max_sim_all]

    return cl1, cl2


def update_sim_matrix(sim_matrix, clusters, cl1, cl2):
    """
    update the similarity matrix, so that the no. of computations
    is reduced drastically
    which means only one row and column gets updated
    and one row and column gets deleted
    """

    r_del = len(clusters) - 1
    r_up = -1

    if(len(clusters[cl1]) > len(clusters[cl2])):
        clusters[cl1] += clusters[cl2]
        clusters[cl2] = clusters[r_del]
        r_up = cl1

    else:
        clusters[cl2] += clusters[cl1]
        clusters[cl1] = clusters[r_del]
        r_up = cl2

    sim_matrix[r_up] = sim_matrix[r_del]
    sim_matrix[:, r_up] = sim_matrix[:, r_del]

    numpy.delete(sim_matrix, r_del, 0)
    numpy.delete(sim_matrix, r_del, 1)

    print 'r_up %d' % r_up
    sim_matrix[r_up][r_up] = 0.0
    for one_c in sorted(clusters.iterkeys()):
        if(one_c != r_up):
            sim = get_similarity(one_c, r_up)
            sim_matrix[one_c][r_up] = sim
            sim_matrix[r_up][one_c] = sim

    del clusters[r_del]

    #print clusters[r_up]
    return sim_matrix, clusters


def random_func(matrix, words, sim_matrix, clusters):
    """
    some random function that tries to implement
    group average agglomerative clustering
    """

    noc = 30  # no of clusters

#    sim_matrix = numpy.zeros(shape=(len(clusters), len(clusters)), dtype=float)
#
#    for one_c in sorted(clusters.keys()):
#        vect1 = get_avg_vector(matrix, words, clusters[one_c])
#        for two_c in sorted(clusters.keys()):
#            if(two_c <= one_c):
#                continue
#            else:
#                vect2 = get_avg_vector(matrix, words, clusters[two_c])
#
#            sim = get_similarity(vect1, vect2)
#            sim_matrix[one_c][two_c] = sim
#            sim_matrix[two_c][one_c] = sim

    i = 0
    while(len(clusters) >= noc):
        print 'cl len : %d' % (len(clusters))
        print 'iter : %d' % (i)
        i += 1
        cl1, cl2 = get_the_closest_pair(sim_matrix)
        print 'closest : %d %d' % (cl1, cl2)
        sim_matrix, clusters = update_sim_matrix(sim_matrix, clusters,
                                                 cl1, cl2)
        if(i == 5):
            break

    return clusters


def write_clusters(new_clusters, cname):
    """
    write the clusters into a file
    """
    with codecs.open(cname, 'w', 'utf-8') as fptr:
        for key in new_clusters.iterkeys():
            w_list = new_clusters[key]
            for word in w_list:
                fptr.write(word + ',')
            fptr.write('\n----\n')


def  save_top_similar(sname, sim_matrix, words):
    """
    save top similar words into a file
    """
    # no fo similar/closest words that are to be saved to a file
    nos = 10

    with codecs.open(sname + str(nos) + '.txt', 'w', 'utf-8') as fptr:
        i = 0
        for row in sim_matrix:
            fptr.write(words[i] + ' : ')
            row_srt = numpy.argsort(row)[::-1]  # sortin descending order
            j = 0
            while(j < nos and j < len(row_srt)):
                fptr.write(words[row_srt[j]] + ', ')
                j += 1
            fptr.write('\n-----\n')
            i += 1


def start():
    """
    the main function
    """

    ch = int(raw_input('1. Get top similar, 2. Cluster ? '))

    if(not os.path.exists('similar_top/')):
        os.system('mkdir similar_top')

    for lan in LANG:

        print 'Now processing ' + lan + ' ...'
        words = read_words('feats/uniq_' + lan + '.txt')
        matrix = numpy.load('feats/feat_space_' + lan + '.npy')

        if(ch == 1):
            sim_matrix = gen_sim_matrix(matrix)
            numpy.save('feats/sim_matrix_' + lan, sim_matrix)
            sname = 'similar_top/sim_' + lan
            save_top_similar(sname, sim_matrix, words)

        elif(ch == 2):
            sim_matrix = numpy.load('feats/sim_matrix_' + lan + '.npy')
            #cluster_vectors(sim_matrix, words)
            clusters = init_clusters(words)
            cname = 'cluster_' + lan + '.txt'
            new_clusters = random_func(matrix, words, sim_matrix, clusters)
            write_clusters(new_clusters, cname)

    print 'Done !!'

start()
