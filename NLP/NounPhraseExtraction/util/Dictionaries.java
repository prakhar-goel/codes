package com.sap.pi.hba.sentinel.ta.cr.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounPhraseLabel;
import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounToken;

/**
 * This class carries all the user defined dictionaries required at various
 * steps
 * 
 * @author Vini Dixit
 * 
 */
public class Dictionaries {

    /** Noun Phrase extraction */
    // NP modifiers
    public static final Set<String>          nounModifierLabels            =
                                                                               new HashSet<String>();

    // Noun tags/labels
    public static final Set<String>          nounDependencyLabels          =
                                                                               new HashSet<String>();

    public static final Set<String>          nounTags                      =
                                                                               new HashSet<String>();

    public static final Set<String>          nounModifierTags              =
                                                                               new HashSet<String>();

    public static final Set<String>          nounHeadTags                  =
                                                                               new HashSet<String>();

    public static final Set<String>          nounPhraseConnectorLabels     =
                                                                               new HashSet<String>();

    public static final Set<String>          relativeClauseConnectorTags   =
                                                                               new HashSet<String>();

    public static final Set<String>          relativeClauseConnectorLabels =
                                                                               new HashSet<String>();

    // NE related tokens/labels
    public static final Set<String>          namedEntityConnectorLabels    =
                                                                               new HashSet<String>();
    public static final Set<String>          namedEntityTags               =
                                                                               new HashSet<String>();

    // partial phrase tokens/labels
    public static final Set<String>          nounConnectorLabels           =
                                                                               new HashSet<String>();

    // Token type flags
    public static final Set<NounToken>       NOUN_FLAGS                    =
                                                                               new HashSet<NounToken>();

    // list of phrase type names used for pronouns
    public static final Set<NounPhraseLabel> pronounPhraseLabels           =
                                                                               new HashSet<NounPhraseLabel>();

    // list of named entity labels
    public static final Set<NounPhraseLabel> namedEntityLabels             =
                                                                               new HashSet<NounPhraseLabel>();

    /*** Mention extraction ***/

    // Pleonastic rules 
    public static final HashSet<String>      pleonasticSet1                =
                                                                               new HashSet<String>();

    public static final HashSet<String>      pleonasticSet2                =
                                                                               new HashSet<String>();

    public static final Set<String>          quantifiers                   =
                                                                               new HashSet<String>();

    public static final Set<String>          numbers                       =
                                                                               new HashSet<String>();

    public static final Set<String>          times                         =
                                                                               new HashSet<String>();

    // Sieve required dictionaries
    public static final Set<String>          speakerPronouns               =
                                                                               new HashSet<String>();

    public static final Set<String>          stopwords                     =
                                                                               new HashSet<String>();

    /** Pronominal Resolution requirements */

    public static final Set<String>          organizationPronouns          =
                                                                               new HashSet<String>();
    public static final Set<String>          locationPronouns              =
                                                                               new HashSet<String>();

    public static final Set<String>          pluralPronouns                =
                                                                               new HashSet<String>();
    public static final Set<String>          singularPronouns              =
                                                                               new HashSet<String>();
    public static final Set<String>          neutralPronouns               =
                                                                               new HashSet<String>();
    public static final Set<String>          possessivePronouns            =
                                                                               new HashSet<String>();

    public static final Set<String>          inanimatePronouns             =
                                                                               new HashSet<String>();
    public static final Set<String>          animatePronouns               =
                                                                               new HashSet<String>();

    public static final Set<String>          personPronouns                =
                                                                               new HashSet<String>();

    public static final Set<String>          productPronouns                =
                                                                               new HashSet<String>();
    
    static {
        nounModifierLabels.addAll(Arrays.asList(
            "det", "amod", "quantmod", "num", "number"));

        nounDependencyLabels.addAll(Arrays.asList("nn", "poss"));
        nounTags.addAll(Arrays.asList(
            "NN", "NNP", "NNS", "NNPS", "PRP", "PRP$", "CD"));

        nounModifierTags.addAll(Arrays.asList("JJ"));

        nounHeadTags.addAll(Arrays.asList("NN"/*, "NNP"*/, "NNS"/*, "NNPS", "CD"*/));

        relativeClauseConnectorTags.addAll(Arrays.asList("WP"));
        relativeClauseConnectorLabels.addAll(Arrays.asList("rcmod"));

        nounPhraseConnectorLabels.addAll(Arrays.asList(
            "prep_of", "prep", "cc", "conj"));

        nounConnectorLabels.addAll(Arrays.asList("possessive")); //conj :NN?,CD

        namedEntityConnectorLabels.addAll(Arrays.asList("prep_of", "prep"));
        namedEntityTags.addAll(Arrays.asList("NNP", "NNPS"));

        NOUN_FLAGS.addAll(Arrays.asList(
            NounToken.CONNECTOR, NounToken.NOUN, NounToken.POSSESSIVE));

        pronounPhraseLabels.addAll(Arrays.asList(
            NounPhraseLabel.PRN, NounPhraseLabel.PRNP));

        namedEntityLabels.addAll(Arrays.asList(
            NounPhraseLabel.PERSON, NounPhraseLabel.ORGANIZATION,
            NounPhraseLabel.PRODUCT, NounPhraseLabel.LOCATION, NounPhraseLabel.GENERAL));

        pleonasticSet1.addAll(Arrays.asList(
            "possible", "seem", "appear", "mean", "follow"));

        pleonasticSet2.addAll(Arrays.asList("turn"));

        quantifiers.addAll(Arrays.asList(
            "not", "every", "any", "none", "everything", "anything", "nothing",
            "all", "enough", "both", "neither", "either", "other", "another"));

        numbers.addAll(Arrays.asList(
            "few", "half", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "first", "second", "third",
            "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth",
            "hundred", "thousand", "million", "hundredth", "thousandth",
            "millionth", "billionth", "billion", "bunch", "pinch", "amount",
            "total", "mile", "pound", "rs0", "rs", "$0", "$", "%", "percent",
            "usd0", "usd", "0", "crore", "#crd#"));

        times.addAll(Arrays.asList(
            "second", "minute", "hour", "day", "week", "month", "year",
            "decade", "century", "seconds", "minutes", "hours", "days",
            "weeks", "months", "years", "decades", "centuries", "millennium",
            "monday", "tuesday", "wednesday", "thursday", "friday", "saturday",
            "sunday", "now", "yesterday", "tomorrow", "age", "time", "era",
            "epoch", "morning", "evening", "night", "noon", "afternoon",
            "semester", "trimester", "quarter", "winter", "spring", "summer",
            "mornings", "evenings", "nights", "noons", "afternoons",
            "semesters", "trimesters", "quarters", "winters", "springs",
            "summers", "last", "autumn", "season", "seasons", "january",
            "february", "march", "april", "may", "june", "july", "august",
            "september", "october", "november", "december"));

        speakerPronouns.addAll(Arrays.asList(
            "I", "my", "me", "mine", "My", "Mine", "Me", "we", "We", "Our",
            "our", "us"));

        stopwords.addAll(Arrays.asList(
            "a", "an", "the", "of", "at", "on", "upon", "in", "to", "from",
            "out", "as", "so", "such", "or", "and", "those", "this", "these",
            "that", "for", "'s"));

        organizationPronouns.addAll(Arrays.asList(new String[] { "it", "its",
        "they", "their", "them", "which" }));

        locationPronouns.addAll(Arrays.asList(new String[] { "it", "its",
        "where", "here", "there" }));

        pluralPronouns.addAll(Arrays.asList(new String[] { "we", "us",
        "ourself", "ourselves", "ours", "our", "yourself", "yourselves",
        "they", "them", "themself", "themselves", "theirs", "their" }));

        singularPronouns.addAll(Arrays.asList(new String[] { "i", "me",
        "myself", "mine", "my", "yourself", "he", "him", "himself", "his",
        "she", "her", "herself", "hers", "her", "one",
        "oneself", "one's" }));

        neutralPronouns.addAll(Arrays.asList(new String[] { "it", "its",
        "itself", "where", "here", "there", "which" }));

        possessivePronouns.addAll(Arrays.asList(new String[] { "my", "your",
        "his", "her", "its", "our", "their", "whose" }));

        inanimatePronouns.addAll(Arrays.asList(new String[] { "it", "itself",
        "its", "where", "when" }));

        animatePronouns.addAll(Arrays.asList(new String[] { "i", "me",
        "myself", "mine", "my", "we", "us", "ourself", "ourselves", "ours",
        "our", "you", "yourself", "yours", "your", "yourselves", "he", "him",
        "himself", "his", "she", "her", "herself", "hers", "her", "one",
        "oneself", "one's", "they", "them", "themself", "themselves", "theirs",
        "their", "they", "them", "'em", "themselves", "who", "whom", "whose" }));

        personPronouns.addAll(Arrays.asList(new String[] { "i", "me", "he",
        "she", "his", "him", "her", "my", "mine" }));
        
        productPronouns.addAll(Arrays.asList(new String[] { "it", "its"}));
    }

}
