package poc.rest.config.util;

public enum Separator {

    SPACE(" "),
    COMMA(","),
    COMMA_SPACE(", "),
    COLON(":"),
    COLON_SPACE(": "),
    SEMICOLON(";"),
    SEMICOLON_SPACE("; "),
    STOP("."),
    STOP_SPACE(". "),
    EXCLAMATION("!"),
    EXCLAMATION_SPACE("! "),
    QUESTION("?"),
    QUESTION_SPACE("? "),
    EQUAL("="),
    EQUAL_SPACE("= "),
    SPACE_EQUAL_SPACE(" = "),
    CONDITION_BETWEEN(" = ? AND "),
    CONDITION_END(" = ?;"),
    QUOTE("\""),
    SPACE_DQUOTE(" \""),
    DQUOTE_COMMA("\","),
    DQUOTE_COMMA_SPACE("\", "),
    DQUOTE_SPACE("\" "),
    DQUOTE_COMMA_SPACE_DQUOTE("\", \"");



    private String separator;

    Separator(String separator){
        this.separator = separator;
    }

    public String getSeparator(){return separator;}
}
