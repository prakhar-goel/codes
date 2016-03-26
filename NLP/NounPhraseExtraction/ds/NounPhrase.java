package com.sap.pi.hba.sentinel.ta.cr.ds;

import java.util.ArrayList;
import java.util.Collections;

import com.sap.pi.hba.sentinel.ta.cr.util.Dictionaries;
import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounPhraseLabel;
import com.sap.pi.hba.sentinel.ta.sa.components.Sentence;
import com.sap.pi.hba.sentinel.ta.sa.components.Token;

/**
 * This class encapsulates attributes of a noun phrase. An NP consist of its
 * type, string identifying it in text, and member tokens Member tokens are
 * added indirectly by mentioning token id range : startPhraseId to endPhraseId
 * 
 * @author Vini Dixit
 * 
 */
public class NounPhrase {

    // noun phrase type can be : NP, <Named entity label>, pronoun, pronoun phrase
    private NounPhraseLabel nounPhraseType;

    // words of tokens separated by space
    private String          nounPhraseString;

    // tokenId of first token in phrase
    private int             startPhraseId;

    // tokenId of last token in phrase
    private int             endPhraseId;

    // token id of head token
    private int             headTokenId;

    // Sentence class reference
    private Sentence sentence;
    
    /**
     * Constructor
     * 
     * @param nounPhraseString
     * @param nounPhraseType
     * @param startPhrase
     * @param endPhrase
     */
    public NounPhrase(String nounPhraseString, NounPhraseLabel nounPhraseType,
        int startPhrase, int endPhrase, Sentence sentence) {

        this.nounPhraseString = nounPhraseString;
        this.nounPhraseType = nounPhraseType;
        this.startPhraseId = startPhrase;
        this.endPhraseId = endPhrase;
        headTokenId = 0; // initialize to 0
        this.sentence = sentence;
    }

    /**
     * Constructor
     * 
     * @param sentence
     * @param nounPhraseType
     * @param startPhrase
     * @param endPhrase
     */
    /*public NounPhrase(Sentence sentence, NounPhraseLabel nounPhraseType,
        int startPhrase, int endPhrase) 
    {
        this.nounPhraseType = nounPhraseType;
        this.startPhraseId = startPhrase;
        this.endPhraseId = endPhrase;
        
        nounPhraseString = getPhraseString(sentence);
        headTokenId = 0; // initialize to 0
    }*/
    
    /**
     * Get string formed by combining token words.
     * 
     * @param tokens
     * @return string generated after combining tokens
     */
   /* private String getPhraseString(Sentence sentence) {

        StringBuffer phraseStrBuffer = new StringBuffer();
        
        for (int tokenId = startPhraseId; tokenId <= endPhraseId; ++tokenId) {

            Token token = sentence.getTokenById(tokenId);
            phraseStrBuffer.append(token.getForm());
            phraseStrBuffer.append(" ");
        }
        
        return phraseStrBuffer.toString().trim();
    }*/
    
    /**
     * Get label or tag of NP
     * 
     * @return type of noun phrase
     */
    public NounPhraseLabel getNounPhraseType() {

        return nounPhraseType;
    }

    /**
     * Get string identifying this NP in text
     * 
     * @return noun phrase string
     */
    public String getPhraseString() {

        return nounPhraseString;
    }

    /**
     * Get token id of first member of NP
     * 
     * @return the startPhrase
     */
    public int getStartPhraseId() {

        return startPhraseId;
    }

     /**
     * Get token id of last member of NP
     * 
     * @return the endPhrase
     */
    public int getEndPhraseId() {

        return endPhraseId;
    }

    
    /**
     * Check whether current NP a named entity. A named entity consists of one
     * of labels: PERSON, ORGANIZATION, PRODUCT, LOCATION
     * 
     * @return whether given noun phrase a named entity
     */
    public boolean isNamedEntity() {

        return Dictionaries.namedEntityLabels.contains(nounPhraseType);
    }

    /**
     * Check whether current NP a pronoun phrase. A pronoun phrase can be an
     * individual pronoun or phrase consists of possessive pronoun.
     * 
     * @return whether given noun phrase a pronoun
     */
    public boolean isPronounPhrase() {

        return Dictionaries.pronounPhraseLabels.contains(nounPhraseType);
    }

    /**
     * Get pronoun token occurring in pronoun phrase.
     * 
     * @param documentState
     * @param sentenceId
     * @return Token
     */
    public Token getPronounToken() {

        if (isPronounPhrase()) 
        {
            for (int tokenId = startPhraseId; tokenId <= endPhraseId; tokenId++)
            {
                Token token = sentence.getTokenById(tokenId);
                if (token.getPos().startsWith("PRP")) {
                    return token;
                }
            }
        }
        return null;
    }

    /**
     * Check whether a given dependency label exists for NP. This dependency
     * label is part of information of Token objects inside NP.
     * 
     * @param dependecyLabel
     * @param sentenceNumber
     * @return head token id with given dependency label
     */
    public int hasDependencyLabel(
        String dependecyLabel, Sentence sentence/*int sentenceNumber, DocumentState globalInstance*/)
    {

     //   Sentence sentence =
     //       globalInstance.getCRDocument().getSentence(sentenceNumber);
        
        for (int tokenId = endPhraseId; tokenId >= startPhraseId; tokenId--) {

            Token token = sentence.getTokenById(tokenId);

            if (token.getDeprel().equals(dependecyLabel))
            {
                return token.getHeadId();
            }
        }

        return -1;
    }

    /**
     * Get head token of noun phrase
     * 
     * @param sentence
     * @return Token
     */
    public Token getHeadToken(Sentence sentence) {

        if (headTokenId >= startPhraseId && headTokenId <= endPhraseId)
        {
            return sentence.getTokenById(headTokenId);
        }

        Token headToken = null;
        Token headCandidate = null;
        
        int prevTokenId = 0;
        int tokenId = startPhraseId;
        boolean isInrange = isInRange(tokenId);
           
        for (;isInrange && tokenId>prevTokenId;)
        {
             headCandidate = sentence.getTokenById(tokenId);

             if (Dictionaries.nounTags.contains(headCandidate.getPos()) &&
                 !headCandidate.getPos().equals("CD")) 
             {
                 headToken = headCandidate;
                 headTokenId = tokenId;

                 // set pointer to head of current head to check whether its noun head exists
                 prevTokenId = tokenId;
                 tokenId = headToken.getHeadId();

             } else {

                 prevTokenId = tokenId;
                 tokenId++;
             }
             
            isInrange = isInRange(tokenId);
          }
            
        return headToken;
    }

    /**
     * Check whether a token in range of current NP
     * 
     * @param tokenId
     * @return boolean
     */
    private boolean isInRange(int tokenId)
    {
        return tokenId>=startPhraseId && tokenId<=endPhraseId;
    }
    
    /**
     * connectorIds contains token id all connectors in NP
     * 
     * @param connectorTokenId
     */
   /* public void addConnectorTokenId(int connectorTokenId) {

        connectorIds.add(connectorTokenId);
    }*/

    public Sentence getSentence()
    {
    	return sentence;
    }
    
    public Token getStartToken()
    {
    	return sentence.getTokenById(startPhraseId);
    }
    
    public Token getEndToken()
    {
    	return sentence.getTokenById(endPhraseId);
    }
    
    public Token getToken(int tokenId)
    {
    	return sentence.getTokenById(tokenId);
    }
    
    public ArrayList<Token> getTokenList()
    {
    	ArrayList<Token> tokenList = new ArrayList<Token>();
    	ArrayList<Token> sentTokenList = sentence.getTokenList(); 
    	Collections.sort(sentTokenList);
    	if(sentTokenList!=null && sentTokenList.size()>0)
    	{
    		tokenList.addAll(sentTokenList.subList(startPhraseId-1, endPhraseId));
    	}
    	return tokenList;
    } 
    
    @Override
    public String toString() {

        String str = "{";
        for(int tokenId = startPhraseId; tokenId<=endPhraseId; tokenId++)
        {
            Token token = getToken(tokenId);
            str = str + "[" + token+ "] ";
        }
        return str+"}";
        
        /*StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(startPhraseId).append("->").append(endPhraseId).append(
            " ");
        stringBuffer.append("[").append(nounPhraseType).append("]");
        //stringBuffer.append(nounPhraseString);
        for(int tokenId = startPhraseId; tokenId<=endPhraseId; tokenId++)
        {
        	Token token = getToken(tokenId);
        	stringBuffer.append(token.getForm() + "\t"+ token.getEntityTag()).append("\n");
        }

        return stringBuffer.toString();*/
    }
    
    /*@Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof NounPhrase))
        {
            return false;
        }
        
        NounPhrase nounPhrase = (NounPhrase) obj;
        
        return nounPhrase.nounPhraseString.equals(this.nounPhraseString) &&
            nounPhrase.nounPhraseType.equals(this.nounPhraseType) &&
            nounPhrase.getStartPhraseId() == this.startPhraseId &&
            nounPhrase.getEndPhraseId() == this.endPhraseId;
    }*/
}
