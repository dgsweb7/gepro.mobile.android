package com.modularsistemas.modpdf;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;


public class OpenTypeFont
{

    public OpenTypeFont()
    {
        otf = null;
    }

    private void embedFontFile(PDF pdf, Font font, OTF otf1, boolean flag)
        throws Exception
    {
        for(int i = 0; i < pdf.fonts.size(); i++)
        {
            Font font1 = (Font)pdf.fonts.get(i);
            if(font1.name.equals(otf1.fontName) && font1.fileObjNumber != -1)
            {
                font.fileObjNumber = font1.fileObjNumber;
                return;
            }
        }

        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DeflaterOutputStream deflateroutputstream = new DeflaterOutputStream(bytearrayoutputstream, new Deflater());
        if(otf1.cff)
            deflateroutputstream.write(otf1.buf, otf1.cff_off, otf1.cff_len);
        else
            deflateroutputstream.write(otf1.buf, 0, otf1.buf.length);
        deflateroutputstream.finish();
        pdf.newobj();
        pdf.append("<<\n");
        if(otf1.cff)
            if(flag)
                pdf.append("/Subtype /Type1C\n");
            else
                pdf.append("/Subtype /CIDFontType0C\n");
        pdf.append("/Filter /FlateDecode\n");
        pdf.append("/Length ");
        pdf.append(bytearrayoutputstream.size());
        pdf.append("\n");
        if(!otf1.cff)
        {
            pdf.append("/Length1 ");
            pdf.append(otf1.buf.length);
            pdf.append('\n');
        }
        pdf.append(">>\n");
        pdf.append("stream\n");
        pdf.append(bytearrayoutputstream);
        pdf.append("\nendstream\n");
        pdf.endobj();
        font.fileObjNumber = pdf.objNumber;
    }

    private int addFontDescriptorObject(PDF pdf, Font font, OTF otf1, boolean flag)
        throws Exception
    {
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /FontDescriptor\n");
        pdf.append("/FontName /");
        pdf.append(otf1.fontName);
        pdf.append('\n');
        if(flag)
        {
            if(otf1.cff)
                pdf.append("/FontFile3 ");
            else
                pdf.append("/FontFile2 ");
            pdf.append(font.fileObjNumber);
            pdf.append(" 0 R\n");
        }
        pdf.append("/Flags 32\n");
        pdf.append("/FontBBox [");
        pdf.append(otf1.bBoxLLx);
        pdf.append(' ');
        pdf.append(otf1.bBoxLLy);
        pdf.append(' ');
        pdf.append(otf1.bBoxURx);
        pdf.append(' ');
        pdf.append(otf1.bBoxURy);
        pdf.append("]\n");
        pdf.append("/Ascent ");
        pdf.append(otf1.ascent);
        pdf.append('\n');
        pdf.append("/Descent ");
        pdf.append(otf1.descent);
        pdf.append('\n');
        pdf.append("/ItalicAngle 0\n");
        pdf.append("/CapHeight ");
        pdf.append(otf1.capHeight);
        pdf.append('\n');
        pdf.append("/StemV 79\n");
        pdf.append(">>\n");
        pdf.endobj();
        return pdf.objNumber;
    }

    private int addWidthsArrayObject(PDF pdf, OTF otf1, int i)
        throws Exception
    {
        pdf.newobj();
        pdf.append("[ ");
        int j = 1;
        for(int k = otf1.firstChar; k < 256; k++)
        {
            if(k < 127)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[k]));
            else
            if(i == 0)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[CP1250.codes[k - 127]]));
            else
            if(i == 1)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[CP1251.codes[k - 127]]));
            else
            if(i == 2)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[CP1252.codes[k - 127]]));
            else
            if(i == 3)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[CP1253.codes[k - 127]]));
            else
            if(i == 4)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[CP1254.codes[k - 127]]));
            else
            if(i == 7)
                pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.glyphWidth[CP1257.codes[k - 127]]));
            if(j < 10)
            {
                pdf.append(' ');
                j++;
            } else
            {
                pdf.append('\n');
                j = 1;
            }
        }

        pdf.append("]\n");
        pdf.endobj();
        return pdf.objNumber;
    }

    private int addEncodingObject(PDF pdf, OTF otf1, int i)
        throws Exception
    {
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /Encoding\n");
        pdf.append("/BaseEncoding /WinAnsiEncoding\n");
        pdf.append("/Differences [127\n");
        for(int j = 0; j < 129; j++)
        {
            if(i == 0)
                pdf.append(CP1250.names[j]);
            else
            if(i == 1)
                pdf.append(CP1251.names[j]);
            else
            if(i == 2)
                pdf.append(CP1252.names[j]);
            else
            if(i == 3)
                pdf.append(CP1253.names[j]);
            else
            if(i == 4)
                pdf.append(CP1254.names[j]);
            else
            if(i == 7)
                pdf.append(CP1257.names[j]);
            pdf.append(' ');
        }

        pdf.append("]\n");
        pdf.append(">>\n");
        pdf.endobj();
        return pdf.objNumber;
    }

    protected void registerAsSimple(PDF pdf, Font font, InputStream inputstream, int i, boolean flag)
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[65535];
        int j;
        while((j = inputstream.read(abyte0, 0, abyte0.length)) != -1) 
            bytearrayoutputstream.write(abyte0, 0, j);
        otf = new OTF(bytearrayoutputstream.toByteArray());
        if(flag)
            embedFontFile(pdf, font, otf, true);
        int k = addFontDescriptorObject(pdf, font, otf, flag);
        int l = addWidthsArrayObject(pdf, otf, i);
        int i1 = addEncodingObject(pdf, otf, i);
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /Font\n");
        if(otf.cff)
            pdf.append("/Subtype /Type1\n");
        else
            pdf.append("/Subtype /TrueType\n");
        pdf.append("/BaseFont /");
        pdf.append(otf.fontName);
        pdf.append('\n');
        pdf.append("/FirstChar ");
        pdf.append(otf.firstChar);
        pdf.append('\n');
        pdf.append("/LastChar ");
        pdf.append(255);
        pdf.append('\n');
        pdf.append("/Encoding ");
        pdf.append(i1);
        pdf.append(" 0 R\n");
        pdf.append("/Widths ");
        pdf.append(l);
        pdf.append(" 0 R\n");
        pdf.append("/FontDescriptor ");
        pdf.append(k);
        pdf.append(" 0 R\n");
        pdf.append(">>\n");
        pdf.endobj();
        font.objNumber = pdf.objNumber;
    }

    private int addCMapObject(PDF pdf, OTF otf1)
        throws Exception
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("%!PS-Adobe-3.0 Resource-CMap\n");
        stringbuilder.append("%%DocumentNeededResources: ProcSet (CIDInit)\n");
        stringbuilder.append("%%IncludedResource: ProcSet (CIDInit)\n");
        stringbuilder.append("%%BeginResource: CMap (Unknown-Unicode)\n");
        stringbuilder.append("%%EndComments\n");
        stringbuilder.append("/CIDInit /ProcSet findresource begin\n");
        stringbuilder.append("12 dict begin\n");
        stringbuilder.append("begincmap\n");
        stringbuilder.append("/CIDSystemInfo\n");
        stringbuilder.append("3 dict dup begin\n");
        stringbuilder.append("/Registry (Unknown) def\n");
        stringbuilder.append("/Ordering (Unicode) def\n");
        stringbuilder.append("/Supplement 0 def\n");
        stringbuilder.append("end def\n");
        stringbuilder.append("/CMapName /Unknown-Unicode def\n");
        stringbuilder.append("/CMapVersion 1.00 def\n");
        stringbuilder.append("/CMapType 1 def\n");
        stringbuilder.append("1 begincodespacerange\n");
        stringbuilder.append('<');
        String s = Integer.toHexString(otf1.firstChar);
        if(s.length() == 2)
            s = (new StringBuilder()).append("00").append(s).toString();
        else
        if(s.length() == 3)
            s = (new StringBuilder()).append("0").append(s).toString();
        stringbuilder.append(s);
        stringbuilder.append("> <");
        String s1 = Integer.toHexString(otf1.lastChar);
        if(s1.length() == 2)
            s1 = (new StringBuilder()).append("00").append(s1).toString();
        else
        if(s1.length() == 3)
            s1 = (new StringBuilder()).append("0").append(s1).toString();
        stringbuilder.append(s1);
        stringbuilder.append(">\n");
        stringbuilder.append("endcodespacerange\n");
        stringbuilder.append(otf1.cmap.size());
        stringbuilder.append(" begincidchar\n");
        for(int i = 0; i < otf1.cmap.size(); i++)
            stringbuilder.append((String)otf1.cmap.get(i));

        stringbuilder.append("endcidchar\n");
        stringbuilder.append("endcmap\n");
        stringbuilder.append("CMapName currentdict /CMap defineresource pop\n");
        stringbuilder.append("end\n");
        stringbuilder.append("end\n");
        stringbuilder.append("%%EndResource\n");
        stringbuilder.append("%%EOF");
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /CMap\n");
        pdf.append("/CMapName /Unknown-Unicode\n");
        pdf.append("/CIDSystemInfo <<\n");
        pdf.append("/Registry (Unknown)\n");
        pdf.append("/Ordering (Unicode)\n");
        pdf.append("/Supplement 0\n");
        pdf.append(">>\n");
        pdf.append("/Length ");
        pdf.append(stringbuilder.length());
        pdf.append("\n");
        pdf.append(">>\n");
        pdf.append("stream\n");
        pdf.append(stringbuilder.toString());
        pdf.append("\nendstream\n");
        pdf.endobj();
        return pdf.objNumber;
    }

    private int addToUnicodeCMapObject(PDF pdf, OTF otf1)
        throws Exception
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("/CIDInit /ProcSet findresource begin\n");
        stringbuilder.append("12 dict begin\n");
        stringbuilder.append("begincmap\n");
        stringbuilder.append("/CIDSystemInfo\n");
        stringbuilder.append("3 dict dup begin\n");
        stringbuilder.append("/Registry (Unknown) def\n");
        stringbuilder.append("/Ordering (ToUnicode) def\n");
        stringbuilder.append("/Supplement 0 def\n");
        stringbuilder.append("end def\n");
        stringbuilder.append("/CMapName /Unknown-ToUnicode def\n");
        stringbuilder.append("/CMapType 2 def\n");
        stringbuilder.append("1 begincodespacerange\n");
        stringbuilder.append("<0000> <FFFF>\n");
        stringbuilder.append("endcodespacerange\n");
        stringbuilder.append("1 beginbfrange\n");
        stringbuilder.append("<0000> <FFFF> <0000>\n");
        stringbuilder.append("endbfrange\n");
        stringbuilder.append("endcmap\n");
        stringbuilder.append("CMapName currentdict /CMap defineresource pop\n");
        stringbuilder.append("end\n");
        stringbuilder.append("end\n");
        stringbuilder.append("%%EndResource\n");
        stringbuilder.append("%%EOF");
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Length ");
        pdf.append(stringbuilder.length());
        pdf.append("\n");
        pdf.append(">>\n");
        pdf.append("stream\n");
        pdf.append(stringbuilder.toString());
        pdf.append("\nendstream\n");
        pdf.endobj();
        return pdf.objNumber;
    }

    private int addCIDFontDictionaryObject(PDF pdf, int i, OTF otf1)
        throws Exception
    {
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /Font\n");
        if(otf1.cff)
            pdf.append("/Subtype /CIDFontType0\n");
        else
            pdf.append("/Subtype /CIDFontType2\n");
        pdf.append("/BaseFont /");
        pdf.append(otf1.fontName);
        pdf.append('\n');
        pdf.append("/CIDSystemInfo <<\n");
        pdf.append("/Registry (Unknown)\n");
        pdf.append("/Ordering (Unicode)\n");
        pdf.append("/Supplement 0\n");
        pdf.append(">>\n");
        pdf.append("/FontDescriptor ");
        pdf.append(i);
        pdf.append(" 0 R\n");
        pdf.append("/DW ");
        pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.advanceWidths[0]));
        pdf.append('\n');
        pdf.append("/W [0[\n");
        int j = 1;
        for(int k = 0; k < otf1.advanceWidths.length; k++)
        {
            pdf.append((int)((1000D / (double)otf1.unitsPerEm) * (double)otf1.advanceWidths[k]));
            if(j < 10)
            {
                pdf.append(' ');
                j++;
            } else
            {
                pdf.append('\n');
                j = 1;
            }
        }

        pdf.append("]]\n");
        pdf.append("/CIDToGIDMap /Identity\n");
        pdf.append(">>\n");
        pdf.endobj();
        return pdf.objNumber;
    }

    protected void registerAsComposite(PDF pdf, Font font, InputStream inputstream, boolean flag)
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[65535];
        int i;
        while((i = inputstream.read(abyte0, 0, abyte0.length)) != -1) 
            bytearrayoutputstream.write(abyte0, 0, i);
        otf = new OTF(bytearrayoutputstream.toByteArray());
        if(flag)
            embedFontFile(pdf, font, otf, false);
        int j = addFontDescriptorObject(pdf, font, otf, flag);
        int k = addCMapObject(pdf, otf);
        int l = addCIDFontDictionaryObject(pdf, j, otf);
        int i1 = addToUnicodeCMapObject(pdf, otf);
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /Font\n");
        pdf.append("/Subtype /Type0\n");
        pdf.append("/BaseFont /");
        pdf.append(otf.fontName);
        pdf.append('\n');
        pdf.append("/Encoding ");
        pdf.append(k);
        pdf.append(" 0 R\n");
        pdf.append("/DescendantFonts [");
        pdf.append(l);
        pdf.append(" 0 R]\n");
        pdf.append("/ToUnicode ");
        pdf.append(i1);
        pdf.append(" 0 R\n");
        pdf.append(">>\n");
        pdf.endobj();
        font.objNumber = pdf.objNumber;
    }

    protected OTF otf;
}