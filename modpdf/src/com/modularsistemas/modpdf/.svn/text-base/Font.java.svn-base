package com.modularsistemas.modpdf;

import java.io.InputStream;
import java.util.List;


public class Font
{

    public Font(PDF pdf1, String s)
        throws Exception
    {
        name = null;
        objNumber = 0;
        fileObjNumber = -1;
        size = 12D;
        unitsPerEm = 1000;
        ascent = 0.0D;
        descent = 0.0D;
        body_height = 0.0D;
        metrics = (int[][])null;
        isStandard = true;
        kernPairs = false;
        isComposite = false;
        firstChar = 32;
        lastChar = 255;
        pdf = null;
        isCJK = false;
        codePage = 2;
        bBoxLLx = 0.0D;
        bBoxLLy = 0.0D;
        bBoxURx = 0.0D;
        bBoxURy = 0.0D;
        advanceWidths = null;
        glyphWidth = null;
        fontUnderlinePosition = 0;
        fontUnderlineThickness = 0;
        underlinePosition = 0.0D;
        underlineThickness = 0.0D;
        pdf = pdf1;
        name = s;
        pdf1.newobj();
        pdf1.append("<<\n");
        pdf1.append("/Type /Font\n");
        pdf1.append("/Subtype /Type1\n");
        pdf1.append("/BaseFont /");
        pdf1.append(s);
        pdf1.append('\n');
        if(!s.equals("Symbol") && !s.equals("ZapfDingbats"))
            pdf1.append("/Encoding /WinAnsiEncoding\n");
        pdf1.append(">>\n");
        pdf1.endobj();
        objNumber = pdf1.objNumber;
        CoreFont corefont = (CoreFont)Class.forName((new StringBuilder()).append("com.modularsistemas.modpdf.").append(name.replace('-', '_')).toString()).newInstance();
        bBoxLLx = corefont.getBBoxLLx();
        bBoxLLy = corefont.getBBoxLLy();
        bBoxURx = corefont.getBBoxURx();
        bBoxURy = corefont.getBBoxURy();
        metrics = corefont.getMetrics();
        ascent = (bBoxURy * size) / (double)unitsPerEm;
        descent = (bBoxLLy * size) / (double)unitsPerEm;
        body_height = ascent - descent;
        fontUnderlineThickness = corefont.getUnderlineThickness();
        fontUnderlinePosition = corefont.getUnderlinePosition();
        underlineThickness = ((double)fontUnderlineThickness * size) / (double)unitsPerEm;
        underlinePosition = ((double)fontUnderlinePosition * size) / (double)(-unitsPerEm) + underlineThickness / 2D;
        pdf1.fonts.add(this);
    }

    public Font(PDF pdf1, String s, int i)
        throws Exception
    {
        name = null;
        objNumber = 0;
        fileObjNumber = -1;
        size = 12D;
        unitsPerEm = 1000;
        ascent = 0.0D;
        descent = 0.0D;
        body_height = 0.0D;
        metrics = (int[][])null;
        isStandard = true;
        kernPairs = false;
        isComposite = false;
        firstChar = 32;
        lastChar = 255;
        pdf = null;
        isCJK = false;
        codePage = 2;
        bBoxLLx = 0.0D;
        bBoxLLy = 0.0D;
        bBoxURx = 0.0D;
        bBoxURy = 0.0D;
        advanceWidths = null;
        glyphWidth = null;
        fontUnderlinePosition = 0;
        fontUnderlineThickness = 0;
        underlinePosition = 0.0D;
        underlineThickness = 0.0D;
        pdf = pdf1;
        name = s;
        codePage = i;
        isCJK = true;
        isStandard = false;
        isComposite = true;
        firstChar = 32;
        lastChar = 65518;
        pdf1.newobj();
        pdf1.append("<<\n");
        pdf1.append("/Type /FontDescriptor\n");
        pdf1.append("/FontName /");
        pdf1.append(s);
        pdf1.append('\n');
        pdf1.append("/Flags 4\n");
        pdf1.append("/FontBBox [0 0 0 0]\n");
        pdf1.append(">>\n");
        pdf1.endobj();
        pdf1.newobj();
        pdf1.append("<<\n");
        pdf1.append("/Type /Font\n");
        pdf1.append("/Subtype /CIDFontType0\n");
        pdf1.append("/BaseFont /");
        pdf1.append(s);
        pdf1.append('\n');
        pdf1.append("/FontDescriptor ");
        pdf1.append(pdf1.objNumber - 1);
        pdf1.append(" 0 R\n");
        pdf1.append("/CIDSystemInfo <<\n");
        pdf1.append("/Registry (Adobe)\n");
        if(s.startsWith("AdobeMingStd"))
        {
            pdf1.append("/Ordering (CNS1)\n");
            pdf1.append("/Supplement 4\n");
        } else
        if(s.startsWith("AdobeSongStd"))
        {
            pdf1.append("/Ordering (GB1)\n");
            pdf1.append("/Supplement 4\n");
        } else
        if(s.startsWith("KozMinPro"))
        {
            pdf1.append("/Ordering (Japan1)\n");
            pdf1.append("/Supplement 4\n");
        } else
        if(s.startsWith("AdobeMyungjoStd"))
        {
            pdf1.append("/Ordering (Korea1)\n");
            pdf1.append("/Supplement 1\n");
        } else
        {
            throw new Exception((new StringBuilder()).append("Unsupported font: ").append(s).toString());
        }
        pdf1.append(">>\n");
        pdf1.append(">>\n");
        pdf1.endobj();
        pdf1.newobj();
        pdf1.append("<<\n");
        pdf1.append("/Type /Font\n");
        pdf1.append("/Subtype /Type0\n");
        pdf1.append("/BaseFont /");
        if(s.startsWith("AdobeMingStd"))
        {
            pdf1.append((new StringBuilder()).append(s).append("-UniCNS-UTF16-H\n").toString());
            pdf1.append("/Encoding /UniCNS-UTF16-H\n");
        } else
        if(s.startsWith("AdobeSongStd"))
        {
            pdf1.append((new StringBuilder()).append(s).append("-UniGB-UTF16-H\n").toString());
            pdf1.append("/Encoding /UniGB-UTF16-H\n");
        } else
        if(s.startsWith("KozMinPro"))
        {
            pdf1.append((new StringBuilder()).append(s).append("-UniJIS-UCS2-H\n").toString());
            pdf1.append("/Encoding /UniJIS-UCS2-H\n");
        } else
        if(s.startsWith("AdobeMyungjoStd"))
        {
            pdf1.append((new StringBuilder()).append(s).append("-UniKS-UCS2-H\n").toString());
            pdf1.append("/Encoding /UniKS-UCS2-H\n");
        } else
        {
            throw new Exception((new StringBuilder()).append("Unsupported font: ").append(s).toString());
        }
        pdf1.append("/DescendantFonts [");
        pdf1.append(pdf1.objNumber - 1);
        pdf1.append(" 0 R]\n");
        pdf1.append(">>\n");
        pdf1.endobj();
        objNumber = pdf1.objNumber;
        ascent = size;
        descent = -ascent / 4D;
        body_height = ascent - descent;
        pdf1.fonts.add(this);
    }

    public Font(PDF pdf1, InputStream inputstream, int i, boolean flag)
        throws Exception
    {
        name = null;
        objNumber = 0;
        fileObjNumber = -1;
        size = 12D;
        unitsPerEm = 1000;
        ascent = 0.0D;
        descent = 0.0D;
        body_height = 0.0D;
        metrics = (int[][])null;
        isStandard = true;
        kernPairs = false;
        isComposite = false;
        firstChar = 32;
        lastChar = 255;
        pdf = null;
        isCJK = false;
        codePage = 2;
        bBoxLLx = 0.0D;
        bBoxLLy = 0.0D;
        bBoxURx = 0.0D;
        bBoxURy = 0.0D;
        advanceWidths = null;
        glyphWidth = null;
        fontUnderlinePosition = 0;
        fontUnderlineThickness = 0;
        underlinePosition = 0.0D;
        underlineThickness = 0.0D;
        pdf = pdf1;
        codePage = i;
        OpenTypeFont opentypefont = new OpenTypeFont();
        if(i == -1)
        {
            opentypefont.registerAsComposite(pdf1, this, inputstream, flag);
            isComposite = true;
            firstChar = opentypefont.otf.firstChar;
            lastChar = opentypefont.otf.lastChar;
        } else
        {
            opentypefont.registerAsSimple(pdf1, this, inputstream, i, flag);
        }
        if(!opentypefont.otf.fontName.startsWith("Droid"));
        name = opentypefont.otf.fontName;
        isStandard = false;
        unitsPerEm = opentypefont.otf.unitsPerEm;
        bBoxLLx = opentypefont.otf.bBoxLLx;
        bBoxLLy = opentypefont.otf.bBoxLLy;
        bBoxURx = opentypefont.otf.bBoxURx;
        bBoxURy = opentypefont.otf.bBoxURy;
        advanceWidths = opentypefont.otf.advanceWidths;
        glyphWidth = opentypefont.otf.glyphWidth;
        ascent = (bBoxURy * size) / (double)unitsPerEm;
        descent = (bBoxLLy * size) / (double)unitsPerEm;
        body_height = ascent - descent;
        fontUnderlineThickness = opentypefont.otf.underlineThickness;
        fontUnderlinePosition = opentypefont.otf.underlinePosition;
        underlineThickness = ((double)fontUnderlineThickness * size) / (double)unitsPerEm;
        underlinePosition = ((double)fontUnderlinePosition * size) / (double)(-unitsPerEm) + underlineThickness / 2D;
        pdf1.fonts.add(this);
        inputstream.close();
    }

    public void setSize(double d)
    {
        size = d;
        if(isCJK)
        {
            ascent = size;
            descent = -ascent / 4D;
            return;
        } else
        {
            ascent = (bBoxURy * size) / (double)unitsPerEm;
            descent = (bBoxLLy * size) / (double)unitsPerEm;
            body_height = ascent - descent;
            underlineThickness = ((double)fontUnderlineThickness * size) / (double)unitsPerEm;
            underlinePosition = ((double)fontUnderlinePosition * size) / (double)(-unitsPerEm) + underlineThickness / 2D;
            return;
        }
    }

    public double getSize()
    {
        return size;
    }

    public void setKernPairs(boolean flag)
    {
        kernPairs = flag;
    }

    public double stringWidth(String s)
    {
        int i = 0;
        if(isCJK)
            return (double)s.length() * ascent;
label0:
        for(int j = 0; j < s.length(); j++)
        {
            int k = s.charAt(j);
            if(!isStandard)
            {
                if(k < firstChar || k > lastChar)
                    i += advanceWidths[0];
                else
                    i += nonStandardFontGlyphWidth(k);
                continue;
            }
            if(k < firstChar || k > lastChar)
                k = 32;
            k -= 32;
            i += metrics[k][1];
            if(!kernPairs || name.startsWith("C") || name.startsWith("S") || name.startsWith("Z"))
                continue;
            if(j == s.length() - 1)
                break;
            char c = s.charAt(j + 1);
            if(c < firstChar || c > lastChar)
                c = ' ';
            int l = 2;
            do
            {
                if(l >= metrics[k].length)
                    continue label0;
                if(metrics[k][l] == c)
                {
                    i += metrics[k][l + 1];
                    continue label0;
                }
                l += 2;
            } while(true);
        }

        return ((double)i * size) / (double)unitsPerEm;
    }

    private int nonStandardFontGlyphWidth(int i)
    {
        int j = 0;
        if(isComposite)
            j = glyphWidth[i];
        else
        if(i < 127)
            j = glyphWidth[i];
        else
        if(codePage == 0)
            j = glyphWidth[CP1250.codes[i - 127]];
        else
        if(codePage == 1)
            j = glyphWidth[CP1251.codes[i - 127]];
        else
        if(codePage == 2)
            j = glyphWidth[CP1252.codes[i - 127]];
        else
        if(codePage == 3)
            j = glyphWidth[CP1253.codes[i - 127]];
        else
        if(codePage == 4)
            j = glyphWidth[CP1254.codes[i - 127]];
        else
        if(codePage == 7)
            j = glyphWidth[CP1257.codes[i - 127]];
        return j;
    }

    protected String name;
    protected int objNumber;
    protected int fileObjNumber;
    protected double size;
    protected int unitsPerEm;
    protected double ascent;
    protected double descent;
    protected double body_height;
    protected int metrics[][];
    protected boolean isStandard;
    protected boolean kernPairs;
    protected boolean isComposite;
    protected int firstChar;
    protected int lastChar;
    private PDF pdf;
    private boolean isCJK;
    private int codePage;
    private double bBoxLLx;
    private double bBoxLLy;
    private double bBoxURx;
    private double bBoxURy;
    private int advanceWidths[];
    private int glyphWidth[];
    private int fontUnderlinePosition;
    private int fontUnderlineThickness;
    protected double underlinePosition;
    protected double underlineThickness;
}