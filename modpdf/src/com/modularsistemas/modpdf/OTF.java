package com.modularsistemas.modpdf;

import java.util.ArrayList;
import java.util.List;


public class OTF
{

    public OTF(byte abyte0[])
        throws Exception
    {
        buf = null;
        cff = false;
        cff_off = 0;
        cff_len = 0;
        cmap = null;
        bBoxLLx = 0;
        bBoxLLy = 0;
        bBoxURx = 0;
        bBoxURy = 0;
        unitsPerEm = 1000;
        ascent = 0;
        descent = 0;
        firstChar = 0;
        lastChar = 0;
        cidForUnicode = null;
        capHeight = 0;
        advanceWidths = null;
        glyphWidth = null;
        numberOfHMetrics = 0;
        index = 0;
        postVersion = 0L;
        italicAngle = 0L;
        underlinePosition = 0;
        underlineThickness = 0;
        fontName = null;
        buf = abyte0;
        cmap = new ArrayList();
        ArrayList arraylist = new ArrayList();
        long l = (long)readInt32(abyte0) & 0xffffffffL;
        if(l != 0x10000L && l != 0x74727565L && l != 0x4f54544fL)
            throw new Exception((new StringBuilder()).append("OTF version == ").append(l).append(" is not supported.").toString());
        int i = readInt16(abyte0);
        int j = readInt16(abyte0);
        int k = readInt16(abyte0);
        int i1 = readInt16(abyte0);
        Object obj = null;
        Object obj1 = null;
        for(int j1 = 0; j1 < i; j1++)
        {
            FontTable fonttable = new FontTable();
            StringBuilder stringbuilder = new StringBuilder();
            for(int l1 = 0; l1 < 4; l1++)
                stringbuilder.append((char)abyte0[index++]);

            fonttable.tag = stringbuilder.toString();
            fonttable.checkSum = (long)readInt32(abyte0) & 0xffffffffL;
            fonttable.offset = readInt32(abyte0);
            fonttable.len = readInt32(abyte0);
            arraylist.add(fonttable);
        }

        int k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable1 = (FontTable)arraylist.get(k1);
            if(fonttable1.tag.equals("head"))
            {
                index = fonttable1.offset + 16;
                int i2 = readInt16(abyte0);
                unitsPerEm = readInt16(abyte0);
                index += 16;
                bBoxLLx = (short)readInt16(abyte0);
                bBoxLLy = (short)readInt16(abyte0);
                bBoxURx = (short)readInt16(abyte0);
                bBoxURy = (short)readInt16(abyte0);
                break;
            }
            k1++;
        } while(true);
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable2 = (FontTable)arraylist.get(k1);
            if(fonttable2.tag.equals("hhea"))
            {
                index = fonttable2.offset + 4;
                ascent = (short)readInt16(abyte0);
                descent = (short)readInt16(abyte0);
                index += 26;
                numberOfHMetrics = readInt16(abyte0);
                break;
            }
            k1++;
        } while(true);
        advanceWidths = new int[numberOfHMetrics];
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable3 = (FontTable)arraylist.get(k1);
            if(fonttable3.tag.equals("hmtx"))
            {
                index = fonttable3.offset;
                for(int j2 = 0; j2 < numberOfHMetrics; j2++)
                {
                    advanceWidths[j2] = readInt16(abyte0);
                    index += 2;
                }

                break;
            }
            k1++;
        } while(true);
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable4 = (FontTable)arraylist.get(k1);
            if(fonttable4.tag.equals("name"))
            {
                index = fonttable4.offset;
                int k2 = readInt16(abyte0);
                int i3 = readInt16(abyte0);
                int l3 = readInt16(abyte0);
                for(int k4 = 0; k4 < i3; k4++)
                {
                    int i5 = readInt16(abyte0);
                    int j5 = readInt16(abyte0);
                    int l5 = readInt16(abyte0);
                    int j6 = readInt16(abyte0);
                    int l6 = readInt16(abyte0);
                    int j7 = readInt16(abyte0);
                    if(i5 == 1 && j5 == 0 && l5 == 0 && j6 == 6)
                    {
                        int i8 = fonttable4.offset + l3 + j7;
                        fontName = new String(abyte0, i8, l6);
                    }
                }

                break;
            }
            k1++;
        } while(true);
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable5 = (FontTable)arraylist.get(k1);
            if(fonttable5.tag.equals("OS/2"))
            {
                index = fonttable5.offset + 64;
                firstChar = readInt16(abyte0);
                lastChar = readInt16(abyte0);
                index += 20;
                capHeight = readInt16(abyte0);
                break;
            }
            k1++;
        } while(true);
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable6 = (FontTable)arraylist.get(k1);
            if(fonttable6.tag.equals("post"))
            {
                index = fonttable6.offset;
                postVersion = (long)readInt32(abyte0) & 0xffffffffL;
                italicAngle = (long)readInt32(abyte0) & 0xffffffffL;
                underlinePosition = (short)readInt16(abyte0);
                underlineThickness = (short)readInt16(abyte0);
                break;
            }
            k1++;
        } while(true);
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable7 = (FontTable)arraylist.get(k1);
            if(fonttable7.tag.equals("CFF "))
            {
                cff = true;
                cff_off = fonttable7.offset;
                cff_len = fonttable7.len;
                break;
            }
            k1++;
        } while(true);
        k1 = 0;
        do
        {
            if(k1 >= arraylist.size())
                break;
            FontTable fonttable8 = (FontTable)arraylist.get(k1);
            if(fonttable8.tag.equals("cmap"))
            {
                index = fonttable8.offset + 2;
                break;
            }
            k1++;
        } while(true);
        k1 = readInt16(abyte0);
        int l2 = 1;
        do
        {
            if(l2 > k1)
                break;
            int j3 = readInt16(abyte0);
            int i4 = readInt16(abyte0);
            if(j3 == 3 && i4 == 1)
            {
                index += readInt32(abyte0) - 8 * l2;
                break;
            }
            index += 4;
            l2++;
        } while(true);
        l2 = readInt16(abyte0);
        int k3 = readInt16(abyte0);
        int j4 = readInt16(abyte0);
        int l4 = readInt16(abyte0) / 2;
        index += 6;
        int ai[] = new int[l4];
        for(int k5 = 0; k5 < l4; k5++)
            ai[k5] = readInt16(abyte0);

        index += 2;
        int ai1[] = new int[l4];
        for(int i6 = 0; i6 < l4; i6++)
            ai1[i6] = readInt16(abyte0);

        short aword0[] = new short[l4];
        for(int k6 = 0; k6 < l4; k6++)
            aword0[k6] = (short)readInt16(abyte0);

        int ai2[] = new int[l4];
        for(int i7 = 0; i7 < l4; i7++)
            ai2[i7] = readInt16(abyte0);

        int ai3[] = new int[(k3 - (16 + 8 * l4)) / 2];
        for(int k7 = 0; k7 < ai3.length; k7++)
            ai3[k7] = readInt16(abyte0);

        cidForUnicode = new int[lastChar + 1];
        glyphWidth = new int[lastChar + 1];
        for(int l7 = firstChar; l7 <= lastChar; l7++)
        {
            int j8 = getSegmentFor(l7, ai1, ai, l4);
            if(j8 == -1)
            {
                cidForUnicode[l7] = 0;
                glyphWidth[l7] = advanceWidths[0];
                continue;
            }
            int k8 = 0;
            int l8 = ai2[j8];
            if(l8 == 0)
            {
                k8 = (aword0[j8] + l7) % 0x10000;
            } else
            {
                l8 /= 2;
                l8 -= l4 - j8;
                k8 = ai3[l8 + (l7 - ai1[j8])];
                if(k8 != 0)
                    k8 += aword0[j8] % 0x10000;
                else
                    k8 = 0;
            }
            cidForUnicode[l7] = k8;
            if(k8 < advanceWidths.length)
                glyphWidth[l7] = advanceWidths[k8];
            else
                glyphWidth[l7] = advanceWidths[0];
            appendToCMap(l7, k8);
        }

    }

    private void appendToCMap(int i, int j)
    {
        String s = Integer.toHexString(i);
        if(s.length() == 2)
            s = (new StringBuilder()).append("00").append(s).toString();
        else
        if(s.length() == 3)
            s = (new StringBuilder()).append("0").append(s).toString();
        cmap.add((new StringBuilder()).append("<").append(s).append("> ").append(j).append("\n").toString());
    }

    private int getSegmentFor(int i, int ai[], int ai1[], int j)
    {
        int k = -1;
        int l = 0;
        do
        {
            if(l >= j)
                break;
            if(i <= ai1[l] && i >= ai[l])
            {
                k = l;
                break;
            }
            l++;
        } while(true);
        return k;
    }

    private int readInt16(byte abyte0[])
    {
        int i = 0;
        i |= abyte0[index++] << 8 & 0xff00;
        i |= abyte0[index++] & 0xff;
        return i;
    }

    private int readInt32(byte abyte0[])
    {
        int i = 0;
        i |= abyte0[index++] << 24 & 0xff000000;
        i |= abyte0[index++] << 16 & 0xff0000;
        i |= abyte0[index++] << 8 & 0xff00;
        i |= abyte0[index++] & 0xff;
        return i;
    }

    protected byte buf[];
    protected boolean cff;
    protected int cff_off;
    protected int cff_len;
    protected List cmap;
    protected short bBoxLLx;
    protected short bBoxLLy;
    protected short bBoxURx;
    protected short bBoxURy;
    protected int unitsPerEm;
    protected short ascent;
    protected short descent;
    protected int firstChar;
    protected int lastChar;
    protected int cidForUnicode[];
    protected int capHeight;
    protected int advanceWidths[];
    protected int glyphWidth[];
    private int numberOfHMetrics;
    private int index;
    protected long postVersion;
    protected long italicAngle;
    protected short underlinePosition;
    protected short underlineThickness;
    protected String fontName;
}