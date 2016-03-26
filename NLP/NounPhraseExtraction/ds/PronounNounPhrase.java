package com.sap.pi.hba.sentinel.ta.cr.ds;

import com.sap.pi.hba.sentinel.ta.cr.util.Constants.NounPhraseLabel;
import com.sap.pi.hba.sentinel.ta.sa.components.Sentence;


/**
 * Handles NP with possessive pronouns
 * 
 * @author Vini Dixit
 * 
 */
public class PronounNounPhrase extends NounPhrase {

    private int pronounTokenId;
    private NounPhrase  memberPronounNP;

    /**
     * Constructor
     * 
     * @param nounPhraseString
     * @param nounPhraseType
     * @param startPhrase
     * @param endPhrase
     */
    public PronounNounPhrase(String nounPhraseString,
        NounPhraseLabel nounPhraseType, int startPhrase, int endPhrase,
        int pronounTokenId, Sentence sentence) {

        super(nounPhraseString, nounPhraseType, startPhrase, endPhrase,
            sentence);

        this.pronounTokenId = pronounTokenId;

       // setMemberPronounNP(pronounTokenId, sentence);
    }

    public void setMemberPronounPhrase(NounPhrase memberPronounPhrase)
    {
        this.memberPronounNP = memberPronounPhrase;
    }
    
    /*public void setMemberPronounNP(int pronounTokenId, Sentence sentence) {
System.out.println("calling setMemberPronounNP..." + memberPronounNP + " pronounTokenId: " + pronounTokenId);
        if (sentence != null && memberPronounNP == null) {
            
            Token pronounToken = sentence.getToken(pronounTokenId);

            if (pronounToken != null) {
                
                this.memberPronounNP = new NounPhrase(
                    pronounToken.getForm(), NounPhraseLabel.PRN,
                    pronounToken.getBegin(), pronounToken.getEnd(),
                    sentence);
                
                System.out.println("memberPronounNP set :" + this.memberPronounNP);
            }
        }
    }*/

    /**
     * Get token id of pronoun token in phrase
     * 
     * @return the pronounTokenId
     */
    public int getPronounTokenId() {

        return this.pronounTokenId;
    }

    public NounPhrase getMemberPronounNP() {

        return this.memberPronounNP;
    }
}
