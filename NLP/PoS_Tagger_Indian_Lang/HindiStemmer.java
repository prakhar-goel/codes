
public class HindiStemmer {
  public int stem(char buffer[], int len) {
    // 5
    if ((len > 6) && (StemmerUtil.endsWith(buffer, len, "ाएंगी")
        || StemmerUtil.endsWith(buffer, len, "ाएंगे")
        || StemmerUtil.endsWith(buffer, len, "ाऊंगी")
        || StemmerUtil.endsWith(buffer, len, "ाऊंगा")
        || StemmerUtil.endsWith(buffer, len, "ाइयाँ")
        || StemmerUtil.endsWith(buffer, len, "ाइयों")
        || StemmerUtil.endsWith(buffer, len, "ाइयां")
      ))
      return len - 5;
    
    // 4
    if ((len > 5) && (StemmerUtil.endsWith(buffer, len, "ाएगी")
        || StemmerUtil.endsWith(buffer, len, "ाएगा")
        || StemmerUtil.endsWith(buffer, len, "ाओगी")
        || StemmerUtil.endsWith(buffer, len, "ाओगे")
        || StemmerUtil.endsWith(buffer, len, "एंगी")
        || StemmerUtil.endsWith(buffer, len, "ेंगी")
        || StemmerUtil.endsWith(buffer, len, "एंगे")
        || StemmerUtil.endsWith(buffer, len, "ेंगे")
        || StemmerUtil.endsWith(buffer, len, "ूंगी")
        || StemmerUtil.endsWith(buffer, len, "ूंगा")
        || StemmerUtil.endsWith(buffer, len, "ातीं")
        || StemmerUtil.endsWith(buffer, len, "नाओं")
        || StemmerUtil.endsWith(buffer, len, "नाएं")
        || StemmerUtil.endsWith(buffer, len, "ताओं")
        || StemmerUtil.endsWith(buffer, len, "ताएं")
        || StemmerUtil.endsWith(buffer, len, "ियाँ")
        || StemmerUtil.endsWith(buffer, len, "ियों")
        || StemmerUtil.endsWith(buffer, len, "ियां")
        ))
      return len - 4;
    
    // 3
    if ((len > 4) && (StemmerUtil.endsWith(buffer, len, "ाकर")
        || StemmerUtil.endsWith(buffer, len, "ाइए")
        || StemmerUtil.endsWith(buffer, len, "ाईं")
        || StemmerUtil.endsWith(buffer, len, "ाया")
        || StemmerUtil.endsWith(buffer, len, "ेगी")
        || StemmerUtil.endsWith(buffer, len, "ेगा")
        || StemmerUtil.endsWith(buffer, len, "ोगी")
        || StemmerUtil.endsWith(buffer, len, "ोगे")
        || StemmerUtil.endsWith(buffer, len, "ाने")
        || StemmerUtil.endsWith(buffer, len, "ाना")
        || StemmerUtil.endsWith(buffer, len, "ाते")
        || StemmerUtil.endsWith(buffer, len, "ाती")
        || StemmerUtil.endsWith(buffer, len, "ाता")
        || StemmerUtil.endsWith(buffer, len, "तीं")
        || StemmerUtil.endsWith(buffer, len, "ाओं")
        || StemmerUtil.endsWith(buffer, len, "ाएं")
        || StemmerUtil.endsWith(buffer, len, "ुओं")
        || StemmerUtil.endsWith(buffer, len, "ुएं")
        || StemmerUtil.endsWith(buffer, len, "ुआं")
        ))
      return len - 3;
    
    // 2
    if ((len > 3) && (StemmerUtil.endsWith(buffer, len, "कर")
        || StemmerUtil.endsWith(buffer, len, "ाओ")
        || StemmerUtil.endsWith(buffer, len, "िए")
        || StemmerUtil.endsWith(buffer, len, "ाई")
        || StemmerUtil.endsWith(buffer, len, "ाए")
        || StemmerUtil.endsWith(buffer, len, "ने")
        || StemmerUtil.endsWith(buffer, len, "नी")
        || StemmerUtil.endsWith(buffer, len, "ना")
        || StemmerUtil.endsWith(buffer, len, "ते")
        || StemmerUtil.endsWith(buffer, len, "ीं")
        || StemmerUtil.endsWith(buffer, len, "ती")
        || StemmerUtil.endsWith(buffer, len, "ता")
        || StemmerUtil.endsWith(buffer, len, "ाँ")
        || StemmerUtil.endsWith(buffer, len, "ां")
        || StemmerUtil.endsWith(buffer, len, "ों")
        || StemmerUtil.endsWith(buffer, len, "ें")
        ))
      return len - 2;
    
    // 1
    if ((len > 2) && (StemmerUtil.endsWith(buffer, len, "ो")
        || StemmerUtil.endsWith(buffer, len, "े")
        || StemmerUtil.endsWith(buffer, len, "ू")
        || StemmerUtil.endsWith(buffer, len, "ु")
        || StemmerUtil.endsWith(buffer, len, "ी")
        || StemmerUtil.endsWith(buffer, len, "ि")
        || StemmerUtil.endsWith(buffer, len, "ा")
       ))
      return len - 1;
    return len;
  }
}
