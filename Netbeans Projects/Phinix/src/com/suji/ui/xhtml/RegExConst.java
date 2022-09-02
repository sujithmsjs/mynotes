package com.suji.ui.xhtml;

public interface RegExConst {

    //([*][^\n]+[*][\n])|([^\n]+[:][\n])|([_][^\n]+[_])|([^*_:]+[\n])
    public final static String HEAD_1 = "[$][^\n]+[\n]";  //"[*][^\n]+[*][\n]";
    public final static String HEAD_3 = "[#][^\n]+[\n]";
    public final static String HEAD_4 = "[@][^\n]+[\n]";
    //public final static String PARA =   "[\n][^*#$][^\n]+[\n]";
    public final static String PARA = "[\n][^#*@$>\n]+([^\n]+[.?!\"])";

    public final static String PAGE_BREAK = "<hr[ ]+/>";

    public final static String LIST =       "([*][^\\n]+[\\n])+";
    public final static String NUM_LIST =   "([-][^\\n]+[\\n])+";
    
    
    //Paragraphs: [\n][^_*>\n]+([^\n]+[.?])
    //Titles: [*]([^\n]+)[*]
    //Italic: [_]([^\n]+)[_]
}
