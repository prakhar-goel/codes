package com.sap.pi.hba.sentinel.ta.cr.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounPhraseLabel;
import com.sap.pi.hba.sentinel.ta.sa.components.Sentence;
import com.sap.pi.hba.sentinel.ta.sa.components.Token;

/**
 * This class encapsulates simple NPs connected by suitable connectors and
 * forming more complete and meaningful NP.
 * 
 * @author Vini Dixit
 * 
 */

public class CompoundNounPhrase extends NounPhrase {

    // connectors of simple noun phrases, present between start and end of phrase
    private List<Integer>    connectorIds;

    // simple noun phrases connected by connector
    private List<NounPhrase> memberNounPhrases;

    /**
     * Constructor
     * 
     * @param sentence
     * @param nounPhraseType
     * @param startPhrase
     * @param endPhrase
     */
    public CompoundNounPhrase(String nounPhraseString,
        NounPhraseLabel nounPhraseType, int startPhrase, int endPhrase,
        List<Integer> npConnectorIds, List<NounPhrase> simpleNPs, Sentence sentence) {

        super(nounPhraseString, nounPhraseType, startPhrase, endPhrase, sentence);

        connectorIds = new ArrayList<Integer>(npConnectorIds);
        memberNounPhrases = new ArrayList<NounPhrase>(simpleNPs);
    }

    /**
     * @return the connectorIds
     */
    public List<Integer> getConnectorIds() {

        return connectorIds;
    }

    /**
     * @return the simpleNounPhrases
     */
    public List<NounPhrase> getMemberNounPhrases() {

        return memberNounPhrases;
    }

    /**
     * Get set of head tokens head token in compound NP is set of heads of NPs
     * connected by "and"
     * 
     * @return Set<Token>
     */
    public Set<Token> getHeadTokens(Sentence sentence) {

        Set<Token> headTokens = new HashSet<Token>();

        for (NounPhrase nounPhrase : memberNounPhrases) {
            Token headToken = null;

            if (headTokens.size() == 0) {
                headToken = nounPhrase.getHeadToken(sentence);
            } else {
                headToken = getHeadConnected(nounPhrase, sentence);
            }

            if (headToken != null) {
                headTokens.add(headToken);
            }
        }

        return headTokens;
    }

    /**
     * Get set of head tokens forms head token in compound NP is set of heads of
     * NPs connected by "and"
     * 
     * @return Set<String>
     */
    public Set<String> getHeadTokenForms(Sentence sentence) {

        Set<String> headTokens = new HashSet<String>();

        for (NounPhrase nounPhrase : memberNounPhrases) {
            Token headToken = null;

            if (headTokens.size() == 0) {
                headToken = nounPhrase.getHeadToken(sentence);
            } else {
                headToken = getHeadConnected(nounPhrase, sentence);
            }

            if (headToken != null) {
                headTokens.add(headToken.getForm());
            } else if (headTokens.size() > 0) // end of head tokens
            {
                break;
            }
        }

        return headTokens;
    }

    /**
     * Check if current NP is connected to an NP and Get head from it
     * 
     * @param nounPhrase
     * @param sentence
     * @return next head token
     */
    private Token getHeadConnected(NounPhrase nounPhrase, Sentence sentence) {

        int previousTokenId = nounPhrase.getStartPhraseId() - 1;

        if (connectorIds.contains(previousTokenId)) {
            Token connectorToken = sentence.getTokenById(previousTokenId);

            if (connectorToken != null &&
                connectorToken.getPos().equals("CC"))
            {
                return nounPhrase.getHeadToken(sentence);
            }
        }

        return null;
    }
}
