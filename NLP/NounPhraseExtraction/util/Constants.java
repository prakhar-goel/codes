package com.sap.pi.hba.sentinel.ta.cr.util;

/**
 * This class carries all constants used throughout execution
 * 
 * @author Vini Dixit
 * 
 */
public class Constants {

    // Token noun flags/type
    public enum NounToken {NOUN, MODIFIER, POSSESSIVE, CONNECTOR, NE_CONNECTOR, NP_CONNECTOR}
  
    // noun phrase labels
    public enum NounPhraseLabel {NP, PRN, PRNP, PERSON, ORGANIZATION, LOCATION, PRODUCT, GENERAL}
    
    // pronoun resolution labels/types
   // public enum Gender { MALE, FEMALE, NEUTRAL, UNKNOWN }
    public enum Number { SINGULAR, PLURAL, BOTH, UNKNOWN }
    public enum Animacy { ANIMATE, INANIMATE, BOTH, UNKNOWN }
  //  public enum Person { I, YOU, HE, SHE, WE, THEY, IT, UNKNOWN}
}
