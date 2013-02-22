package com.modularsistemas.modpdf;


public abstract class CoreFont
{

    public CoreFont()
    {
    }

    protected abstract int getBBoxLLx();

    protected abstract int getBBoxLLy();

    protected abstract int getBBoxURx();

    protected abstract int getBBoxURy();

    protected abstract int getUnderlineThickness();

    protected abstract int getUnderlinePosition();

    protected abstract int[][] getMetrics();

    public static String COURIER = "Courier";
    public static String COURIER_BOLD = "Courier-Bold";
    public static String COURIER_OBLIQUE = "Courier-Oblique";
    public static String COURIER_BOLD_OBLIQUE = "Courier-BoldOblique";
    public static String HELVETICA = "Helvetica";
    public static String HELVETICA_BOLD = "Helvetica-Bold";
    public static String HELVETICA_OBLIQUE = "Helvetica-Oblique";
    public static String HELVETICA_BOLD_OBLIQUE = "Helvetica-BoldOblique";
    public static String TIMES_ROMAN = "Times-Roman";
    public static String TIMES_BOLD = "Times-Bold";
    public static String TIMES_ITALIC = "Times-Italic";
    public static String TIMES_BOLD_ITALIC = "Times-BoldItalic";
    public static String SYMBOL = "Symbol";
    public static String ZAPF_DINGBATS = "ZapfDingbats";

}