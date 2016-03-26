package com.sap.pi.hba.sentinel.ta.cr.md;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.sap.pi.hba.sentinel.ta.cr.ds.CompoundNounPhrase;
import com.sap.pi.hba.sentinel.ta.cr.ds.NounPhrase;
import com.sap.pi.hba.sentinel.ta.cr.ds.PronounNounPhrase;
import com.sap.pi.hba.sentinel.ta.cr.util.Dictionaries;
import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounPhraseLabel;
import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounToken;
import com.sap.pi.hba.sentinel.ta.sa.components.Sentence;
import com.sap.pi.hba.sentinel.ta.sa.components.Token;

/**
 * This class consists of implementation of all patterns for formation of an NP.
 * It matches all NPs matching either of patterns. It then encapsulates these
 * noun phrase tokens in NounPhrase objects.
 * 
 * @author Vini Dixit
 * 
 */
public class NounPhraseExtractor {

    // possesive Pronoun TokenId if present for extracted NP
    private int                              possesivePronounTokenId;

    // phraseLabel for extracted NP
    private NounPhraseLabel                  phraseLabel;

    // phraseString for extracted NP
    private String                           phraseString;

    // startTokenId for extracted NP
    private int                              startTokenId;

    // endTokenId for extracted NP
    private int                              endTokenId;

    // startRelativeInd for extracted NP
    private int                              startRelativeInd;


    /**
     * Match all NPs occurring in a sentence. Encapsulate all those NP matches
     * in NounPhrase objects. Get list of those NounPhrase objects.
     * 
     * @param sentence
     * @return list of noun phrases exists in sentence
     */
    public List<NounPhrase> getNounPhrases(Sentence sentence) {

        List<NounPhrase> nounPhrases = null;

        if (documentState != null && sentence != null) {

            nounPhrases = allMatches(sentence);
        }

        return nounPhrases;
    }

   /**
     * Get all the NP tokens in a sentence. Call a matcher for matching all NP
     * patterns.
     * 
     * @param sentence
     * @return list of tokens forming NP in a sentence
     */
    private List<NounPhrase> allMatches(Sentence sentence) {

        List<NounPhrase> nounPhrases = new ArrayList<NounPhrase>();

        int start = 0;
        int sentenceEnd = sentence.getTokensCount();

        while (start < sentenceEnd) {

            ArrayList<Token> tokens = new ArrayList<Token>();

            // npConnectorIds consists of tokenid of NP connectors (not NE
            // connector)
            ArrayList<Integer> npConnectorIds = new ArrayList<Integer>();

            int end =
                matcher(sentence, start, sentenceEnd, tokens, npConnectorIds);

            start = end + 1;

            if (tokens.size() > 0) {

                NounPhrase nounPhrase =
                    getNounPhraseObject(sentence, tokens, npConnectorIds);
               
                if (nounPhrase != null) {
                    nounPhrases.add(nounPhrase);
                }
            }
        }

        return nounPhrases;
    }

    private NounPhrase getNounPhraseObject(
        Sentence sentence, List<Token> tokens, List<Integer> npConnectorIds)
    {
        if(tokens == null)
            return null;
        
        int tokensCount = tokens.size();
        
        if(tokensCount == 0)
        {
            return null;
        }
        
        resetNounPhraseParameters();
        NounPhrase nounPhrase = null;

        boolean isCompundNP =
            npConnectorIds != null && npConnectorIds.size() > 0;

        if (isCompundNP) // consist of encapsulated Compound NP of simple NP or possessive pronoun NP
        {
            NounPhrase compoundNounPhrase =
                getCompoundNP(tokens, npConnectorIds, sentence);

            if (compoundNounPhrase != null) {

                nounPhrase = compoundNounPhrase;
            } else {
                return null;
            }
        } 
        else 
        {
            startTokenId = tokens.get(0).getId();
            endTokenId = tokens.get(tokensCount - 1).getId();
            startRelativeInd = 0;

            setPhraseParameters(tokens, sentence);

            boolean isNPParametersAvailable = isNPParametersAvailable();

            if (isNPParametersAvailable) {

                if (phraseLabel.equals(NounPhraseLabel.PRNP))
                // NP consist of possessive pronoun
                {
                    nounPhrase = getPronounNP(sentence);
                } else // all simple NP and pronoun token phrase
                {
                    nounPhrase =
                        new NounPhrase(
                            phraseString, phraseLabel, startTokenId, endTokenId, sentence);
                }
            }
        }

        return nounPhrase;
    }

    private NounPhrase getPronounNP(Sentence sentence)
    {
        NounPhrase nounPhrase = null;
        
        PronounNounPhrase pronounNounPhrase = new PronounNounPhrase(
            phraseString, phraseLabel, startTokenId,
            endTokenId, possesivePronounTokenId, sentence);
       
        Token pronounToken = sentence.getTokenById(possesivePronounTokenId);
        
        NounPhrase memberPronounPhrase = new NounPhrase(
            pronounToken.getForm(), NounPhraseLabel.PRN,
            possesivePronounTokenId, possesivePronounTokenId,
            sentence);
        
        pronounNounPhrase.setMemberPronounPhrase(memberPronounPhrase);
        nounPhrase = pronounNounPhrase;
        
        return nounPhrase;
    }
    
    private NounPhrase getCompoundNP(
        List<Token> tokens, List<Integer> npConnectorIds, Sentence sentence)
    {

        NounPhrase compoundNP = null;

        if (tokens != null && tokens.size() > 0) {

            StringBuffer compoundStringBuffer = new StringBuffer();
            List<NounPhrase> nounPhraseMembers = new ArrayList<NounPhrase>();

            int npConnectorsCount = npConnectorIds.size();
            int tokensCount = tokens.size();

            // check compound connectors
            Token firstToken = tokens.get(0);
            int firstTokenId = firstToken.getId();
            int lastTokenId = tokens.get(tokensCount - 1).getId();

            int firstConnectorId = npConnectorIds.get(0);
            int lastConnectorId = npConnectorIds.get(npConnectorsCount - 1);

            // connector ids are in range of phrase
            if (!(lastConnectorId >= firstConnectorId && lastConnectorId < lastTokenId &&
                firstConnectorId > firstTokenId &&
                firstConnectorId < lastTokenId))
            {
                return null;
            }

            startTokenId = firstTokenId;
            startRelativeInd = 0;

            int connectorTokenId = -1;
            int connectorIterator = 0;

            while (endTokenId < lastTokenId) {
                NounPhrase nounPhrase = null;

                if (connectorIterator < npConnectorsCount) {

                    connectorTokenId = npConnectorIds.get(connectorIterator);

                    Token connectorToken =
                        sentence.getTokenById(connectorTokenId);

                    // check if previous token was a connector
                    if (npConnectorIds.contains(connectorTokenId - 1)) {

                        compoundStringBuffer.append(connectorToken.getForm()).append(
                            " ");

                        startTokenId = connectorTokenId + 1;
                        ++connectorIterator;
                        continue;
                    }

                    endTokenId = connectorTokenId - 1;
                    setPhraseParameters(tokens, sentence);

                    compoundStringBuffer.append(phraseString).append(" ").append(
                        connectorToken.getForm()).append(" ");

                    nounPhrase = getSimpleNounPhrase(sentence);

                    resetNounPhraseParameters();

                    startTokenId = connectorTokenId + 1;

                    startRelativeInd =
                        connectorToken.getId() - firstToken.getId() + 1;

                    ++connectorIterator;
                } else // end of connector ids
                {
                    endTokenId = lastTokenId;
                    setPhraseParameters(tokens, sentence);

                    nounPhrase = getSimpleNounPhrase(sentence);
                    compoundStringBuffer.append(phraseString).append(" ");
                }

                if (nounPhrase != null) {
                    nounPhraseMembers.add(nounPhrase);
                }
            }

            resetNounPhraseParameters();

            String compoundPhraseString =
                compoundStringBuffer.toString().trim();

            phraseString = compoundPhraseString;
            startTokenId = firstTokenId;
            endTokenId = lastTokenId;
            startRelativeInd = 0;

            boolean isNamedEntity = isNamedEntity(tokens);

            if (isNamedEntity) {

                NounPhraseLabel neLabel =
                    namedEntityMap.get(phraseString.toLowerCase());

                compoundNP =
                    new NounPhrase(
                        phraseString, neLabel, startTokenId, endTokenId,sentence);

                nounPhraseMembers.clear();

            } else {

             compoundNP =
                  new CompoundNounPhrase(
                       compoundPhraseString, NounPhraseLabel.NP, firstTokenId,
                       lastTokenId, npConnectorIds, nounPhraseMembers, sentence);
        }

        return compoundNP;
    }

    private NounPhrase getSimpleNounPhrase(Sentence sentence) {

        NounPhrase nounPhrase = null;
        boolean isNPParametersAvailable = isNPParametersAvailable();

        if (isNPParametersAvailable) {

            if (phraseLabel.equals(NounPhraseLabel.PRNP)) // NP consist of possessive pronoun
            {
                nounPhrase = getPronounNP(sentence);
                
            } else {
                nounPhrase =
                    new NounPhrase(
                        phraseString, phraseLabel, startTokenId, endTokenId, sentence);
            }
        }

        return nounPhrase;
    }

    private void resetNounPhraseParameters() {

        possesivePronounTokenId = -1;
        phraseLabel = null;
        phraseString = null;
        startTokenId = -1;
        endTokenId = -1;
        startRelativeInd = -1;
    }

    private boolean isNPParametersAvailable() {

        return phraseLabel != null && phraseString != null &&
            startTokenId != -1 && endTokenId != -1 && startRelativeInd != -1;
    }

    private void setPhraseParameters(List<Token> tokens, Sentence sentence) {

        if (startTokenId == -1 || endTokenId == -1) {
            return;
        }

        // iterate in phrase tokens
        StringBuffer phraseStrBuffer = new StringBuffer();
        for (int tokenId = startTokenId; tokenId <= endTokenId; ++tokenId) {

            Token token = sentence.getTokenById(tokenId);

            if (token != null) 
            {
                phraseStrBuffer.append(token.getForm()).append(" ");
                
                if(phraseLabel == null)
                {
                    String isNECandidate = getNELabel(token);
                    
                    if(isNECandidate != null)
                    {
                        phraseLabel = getNELabel(isNECandidate);
                    }
                    else if (possesivePronounTokenId == -1 &&
                        token.getPos().equals("PRP$"))
                    {
                        phraseLabel = NounPhraseLabel.PRNP;
                        possesivePronounTokenId = token.getId();

                    } else if (startTokenId == endTokenId &&
                        token.getPos().equals("PRP"))
                    {
                        phraseLabel = NounPhraseLabel.PRN;
                    }
                }
            }
        }

        phraseString = phraseStrBuffer.toString().trim();
        
        if (phraseLabel == null) {

            phraseLabel = NounPhraseLabel.NP;
        }
    }

    /**
     * Match all NPs in a sentence. Take phrase boundary, head of phrase and
     * dependency relations among tokens in forming patterns for an NP. Match
     * for an NP from start index to end of sentence.
     * 
     * @param sentence
     * @param start of next token
     * @param end of sentence
     * @param list of tokens forming NP
     * @return end token id of NP
     */
    private int matcher(
        Sentence sentence, int start, int end, List<Token> tokens,
        List<Integer> connectorIds)
    {
        HashSet<Integer> tokenIds = new HashSet<Integer>();

        int tokenIterator = start;
        Token headToken = null;
        boolean isNounTokenEntered = false;

        while (tokenIterator < end) {

            Token currentToken = null;
            NounToken isNPCandidate = null;

            currentToken = sentence.getTokenAtIndex(tokenIterator);
            boolean isNEMember =  getNELabel(currentToken)!=null;
            
            if(isNEMember)
            {
                int tokensCount = tokens.size();
                
                if(tokensCount > 0)
                {
                    Token lastToken = tokens.get(tokensCount-1);
                    isNounTokenEntered = Dictionaries.nounHeadTags.contains(lastToken.getPos());
                    
                    if(isNounTokenEntered)
                    {
                        return tokenIterator;
                    }
                    else
                    {
                        tokens.clear();
                    }
                }
               
                tokenIterator = getNETokens(tokens, sentence , tokenIterator, end);
                
                return tokenIterator;
            }
            
            isNPCandidate = isNPCandidate(currentToken, tokenIds, sentence);

            // token can form NP
            if (isNPCandidate != null) {
                
                if (headToken != null) {

                    // separate phrases by noun dependency : Ex. :
                    // gold->loan->financing->company Muthoot->Finance
                    boolean isUnderPhrase =
                        isUnderPhrase(
                            currentToken, headToken, tokenIds, sentence);

                    if (!isUnderPhrase) // not in under phrase headed by headToken
                    {
                        return tokenIterator > 0 ? tokenIterator - 1
                            : tokenIterator;// current token can make a start of new NP
                    }
                }

                Token headTokenCandidate = null;

                headTokenCandidate =
                    getHeadToken(
                        headToken, currentToken, isNPCandidate, tokenIds,
                        sentence);

                // add tokens
                isNounTokenEntered =
                    addToken(currentToken, tokens, tokenIds, isNounTokenEntered);

                if (isNPCandidate.equals(NounToken.NP_CONNECTOR)) {
                    connectorIds.add(currentToken.getId());
                }
                
                boolean isHeadNECandidate = getNELabel(headTokenCandidate)!=null;
               
                if (headTokenCandidate != null && !isHeadNECandidate) // next noun link exists
                {
                    headToken = headTokenCandidate;

                } else // current token cannot give new head
                {
                    if (headToken == null) // single token phrase
                    {
                        return tokenIterator;
                    }

                    if (currentToken.getId() == headToken.getId()) // phrase end
                    {
                        return tokenIterator;
                    }
                }

            } else {

                int tokensCount = tokens.size();
                
                if(tokensCount > 0)
                {
                    Token lastToken = tokens.get(tokensCount-1);
                    boolean isLastTagNoun = Dictionaries.nounHeadTags.contains(lastToken.getPos());
                    
                    if (!isNounTokenEntered ) {
                        tokens.clear();
                    }
                    else if(!isLastTagNoun) // cut till last noun
                    {
                        int lastInd = tokensCount-1;
                        
                        for(; lastInd >= 0; --lastInd)
                        {
                            lastToken = tokens.get(lastInd);
                            isLastTagNoun = Dictionaries.nounHeadTags.contains(lastToken.getPos());
                            
                            if(!isLastTagNoun)
                            {
                                tokens.remove(lastToken);
                                
                                int connectorIdsCount = connectorIds.size();
                                
                                if(connectorIdsCount > 0 && 
                                    connectorIds.get(connectorIdsCount-1)== lastToken.getId())
                                {
                                    connectorIds.remove(connectorIdsCount-1);
                                }
                            }
                            else
                                break;
                        }
                    }
                }
               
                return tokenIterator;
            }

            tokenIterator++;
        }

        return tokenIterator;
    }

    /**
     * Draw boundary among NPs in sequence. and check whether given token can be
     * a part of currently formed partial NP
     * 
     * @param currentToken
     * @param headToken
     * @param tokenIds
     * @return whether given token is part of current NP
     */
    private boolean isUnderPhrase(
        Token currentToken, Token headToken, HashSet<Integer> tokenIds,
        Sentence sentence)
    {

        if (currentToken.getId() == headToken.getId()) {
            return true;
        }

        int currentTokenHeadId = currentToken.getHeadId();

        if (currentTokenHeadId <= headToken.getId() || // same head/or modifier
            tokenIds.contains(currentTokenHeadId)) // is a connector
        {
            return true;
        }

        // relative clause connector
        if (Dictionaries.relativeClauseConnectorLabels.contains(currentToken.getDeprel()) ||
            Dictionaries.relativeClauseConnectorTags.contains(currentToken.getPos()))
        {
            return true;
        }

        return false;
    }

    /**
     * Check whether given token suitable as head noun of NP. Every NP must have
     * at least one head noun to complete.
     * 
     * @param headToken
     * @param headTokenCandidate
     * @param dependencyLabel
     * @return whether given token be a head of NP
     */
    private Token getHeadToken(
        Token headToken, Token currentToken, NounToken currentTokenType,
        HashSet<Integer> tokenIds, Sentence sentence)
    {
        
        Token curHeadToken = currentToken;
        
        if (currentTokenType != null && currentToken != null) {

            if (currentTokenType.equals(NounToken.NP_CONNECTOR) /*||
                currentTokenType.equals(NounToken.NE_CONNECTOR)*/)
            {

                HashSet<Integer> tempTokenIds = new HashSet<Integer>(tokenIds);
                tempTokenIds.add(currentToken.getId());

                // as it is identified as NP connector, it is sure that
                // following token will be a part of NP
                Token nextToken =
                    sentence.getTokenById(currentToken.getId() + 1);

                if (nextToken != null) {

                    currentTokenType =
                        isNPCandidate(nextToken, tempTokenIds, sentence);

                    Token nextHead =
                        getHeadToken(
                            headToken, nextToken, currentTokenType,
                            tempTokenIds, sentence);

                    return nextHead;
                } else {
                    return null;
                }

            }

            String dependencyLabel = currentToken.getDeprel();
            Token tempToken = currentToken;
            
            while (Dictionaries.nounDependencyLabels.contains(dependencyLabel) ||
                Dictionaries.nounModifierLabels.contains(dependencyLabel))
            {
                int currentTokenId = tempToken.getId();
                int headTokenId = tempToken.getHeadId();

                if (headTokenId > currentTokenId) {

                     curHeadToken =
                        sentence.getTokenById(headTokenId);

                    if (curHeadToken != null) { 
                        tempToken = curHeadToken; // handles sequence of modifiers
                        dependencyLabel = currentToken.getDeprel();
                    } else {
                        // logger.info("getHeadToken : no head token for: " +
                        // currentToken);
                        break;
                    }

                } else {
                    break;
                }

                // add more, when found
            }
        }

        return chooseNounHead(headToken, curHeadToken);
    }

    /**
     * @param currentHeadToken
     * @param currentToken
     * @return head Token
     */
    private Token chooseNounHead(Token currentHeadToken, Token currentToken) {

        if (currentToken != null &&
            Dictionaries.nounHeadTags.contains(currentToken.getPos()))
        {
            if (currentHeadToken != null) {

                return currentHeadToken.getId() > currentToken.getId()
                    ? currentHeadToken : currentToken;
            }

            return currentToken;
        }

        return currentHeadToken;
    }

    /**
     * Add new token in list forming an NP and check whether a noun token
     * entered in phrase till now.
     * 
     * @param token
     * @param tokens
     * @param tokenIds
     * @param isNounTokenEntered
     * @return whether noun token entered in list
     */
    private boolean addToken(
        Token token, List<Token> tokens, HashSet<Integer> tokenIds,
        boolean isNounTokenEntered)
    {

        tokens.add(token);
        tokenIds.add(token.getId());

        // isNounTokenEntered status
        return isNounTokenEntered ||
            Dictionaries.nounTags.contains(token.getPos());
    }

    /**
     * Check whether to join given token as member of NP. Get status type of
     * token, if can form an NP otherwise null
     * 
     * @param token
     * @param tokenIds
     * @param sentence
     * @return status type of token, if can form an NP otherwise null
     */
    private NounToken isNPCandidate(
        Token token, HashSet<Integer> tokenIds, Sentence sentence)
    {

        // all noun modifiers
        if (Dictionaries.nounModifierLabels.contains(token.getDeprel())) {
            
            return NounToken.MODIFIER;
        }

        // all nouns
        if (Dictionaries.nounTags.contains(token.getPos()) ||
            Dictionaries.nounDependencyLabels.contains(token.getDeprel()))
        {
            return NounToken.NOUN;
        }

        int headTokenId = token.getHeadId();
        Token headToken = sentence.getTokenById(headTokenId);

        // possessive Tokens : 's
        if (Dictionaries.nounConnectorLabels.contains(token.getDeprel()) &&
            tokenIds.contains(headTokenId))
        {

            return NounToken.POSSESSIVE;
        }

        // NP connector
        Token nextToken = sentence.getTokenById(token.getId() + 1);

        if (Dictionaries.nounPhraseConnectorLabels.contains(token.getDeprel()) &&
            (headToken != null && (Dictionaries.nounHeadTags.contains(headToken.getPos()) ||
                Dictionaries.nounModifierTags.contains(headToken.getPos()) || isRelativeClauseMember(headToken))) &&
            tokenIds.contains(headTokenId) && // joining an NP
            nextToken != null && isSimpleNounPhraseMember(nextToken))
        // joining a following simple NP
        {
            // ensure two NE are not getting connected
            if (token.getDeprel().equals("cc")) {

                Token previousToken = headToken;

                if (previousToken != null &&
                    Dictionaries.namedEntityTags.contains(previousToken.getPos()) &&
                    Dictionaries.namedEntityTags.contains(nextToken.getPos()))
                {
                    return null;
                }
            }

            return NounToken.NP_CONNECTOR;
        }

        // connecting relative clause
        int tokensCount = tokenIds.size();

        if (tokensCount > 0) {

            // when relative connector comes
            if (Dictionaries.relativeClauseConnectorTags.contains(token.getPos()) &&
                nextToken != null &&
                Dictionaries.relativeClauseConnectorLabels.contains(nextToken.getDeprel()))
            {

                return NounToken.NP_CONNECTOR;
            }

            // when relative modifier comes
            if (Dictionaries.relativeClauseConnectorLabels.contains(token.getDeprel()))
            {
                Token previousToken = sentence.getTokenById(token.getId() - 1);

                if (previousToken != null &&
                    Dictionaries.relativeClauseConnectorTags.contains(previousToken.getPos()))
                {

                    return NounToken.NP_CONNECTOR;
                }
            }
        }

        return null;
    }

    /**
     * Check whether given token a part of relative clause
     * 
     * @param token present in explored tokens of NP
     * @return boolean
     */
    private boolean isRelativeClauseMember(Token token) {

        return Dictionaries.relativeClauseConnectorLabels.contains(token.getDeprel()) ||
            Dictionaries.relativeClauseConnectorTags.contains(token.getPos());
    }

    /**
     * Check whether given token a part of simple noun phrase
     * 
     * @param token
     * @return boolean
     */
    private boolean isSimpleNounPhraseMember(Token token) {

        if(token == null)
            return false;
        
        if(Dictionaries.namedEntityTags.contains(token.getPos()))
            return false;
        
        return Dictionaries.nounHeadTags.contains(token.getPos()) ||
                (Dictionaries.nounDependencyLabels.contains(token.getDeprel())) ||
                Dictionaries.nounModifierLabels.contains(token.getDeprel()) || Dictionaries.nounModifierTags.contains(token.getPos());
    }
}
