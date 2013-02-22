package com.modularsistemas.modpdf;

import java.util.*;


public class BarCode
{

    public BarCode(int i, String s)
    {
        x1 = 0.0D;
        y1 = 0.0D;
        m1 = 0.75D;
        type = 0;
        direction = 0;
        str = null;
        font = null;
        map = new HashMap();
        type = i;
        str = s;
        map.put(Character.valueOf('*'), "bWbwBwBwb");
        map.put(Character.valueOf('-'), "bWbwbwBwB");
        map.put(Character.valueOf('$'), "bWbWbWbwb");
        map.put(Character.valueOf('%'), "bwbWbWbWb");
        map.put(Character.valueOf(' '), "bWBwbwBwb");
        map.put(Character.valueOf('.'), "BWbwbwBwb");
        map.put(Character.valueOf('/'), "bWbWbwbWb");
        map.put(Character.valueOf('+'), "bWbwbWbWb");
        map.put(Character.valueOf('0'), "bwbWBwBwb");
        map.put(Character.valueOf('1'), "BwbWbwbwB");
        map.put(Character.valueOf('2'), "bwBWbwbwB");
        map.put(Character.valueOf('3'), "BwBWbwbwb");
        map.put(Character.valueOf('4'), "bwbWBwbwB");
        map.put(Character.valueOf('5'), "BwbWBwbwb");
        map.put(Character.valueOf('6'), "bwBWBwbwb");
        map.put(Character.valueOf('7'), "bwbWbwBwB");
        map.put(Character.valueOf('8'), "BwbWbwBwb");
        map.put(Character.valueOf('9'), "bwBWbwBwb");
        map.put(Character.valueOf('A'), "BwbwbWbwB");
        map.put(Character.valueOf('B'), "bwBwbWbwB");
        map.put(Character.valueOf('C'), "BwBwbWbwb");
        map.put(Character.valueOf('D'), "bwbwBWbwB");
        map.put(Character.valueOf('E'), "BwbwBWbwb");
        map.put(Character.valueOf('F'), "bwBwBWbwb");
        map.put(Character.valueOf('G'), "bwbwbWBwB");
        map.put(Character.valueOf('H'), "BwbwbWBwb");
        map.put(Character.valueOf('I'), "bwBwbWBwb");
        map.put(Character.valueOf('J'), "bwbwBWBwb");
        map.put(Character.valueOf('K'), "BwbwbwbWB");
        map.put(Character.valueOf('L'), "bwBwbwbWB");
        map.put(Character.valueOf('M'), "BwBwbwbWb");
        map.put(Character.valueOf('N'), "bwbwBwbWB");
        map.put(Character.valueOf('O'), "BwbwBwbWb");
        map.put(Character.valueOf('P'), "bwBwBwbWb");
        map.put(Character.valueOf('Q'), "bwbwbwBWB");
        map.put(Character.valueOf('R'), "BwbwbwBWb");
        map.put(Character.valueOf('S'), "bwBwbwBWb");
        map.put(Character.valueOf('T'), "bwbwBwBWb");
        map.put(Character.valueOf('U'), "BWbwbwbwB");
        map.put(Character.valueOf('V'), "bWBwbwbwB");
        map.put(Character.valueOf('W'), "BWBwbwbwb");
        map.put(Character.valueOf('X'), "bWbwBwbwB");
        map.put(Character.valueOf('Y'), "BWbwBwbwb");
        map.put(Character.valueOf('Z'), "bWBwBwbwb");
    }

    public void setPosition(double d, double d1)
    {
        x1 = d;
        y1 = d1;
    }

    public void setModuleLength(double d)
    {
        m1 = d;
    }

    public void setDirection(int i)
    {
        direction = i;
    }

    public void setFont(Font font1)
    {
        font = font1;
    }

    public void drawOn(Page page)
        throws Exception
    {
        if(type == 0)
            drawCodeUPC(page);
        else
        if(type == 1)
            drawCode128(page);
        else
        if(type == 2)
            drawCode39(page);
    }

    private void drawCodeUPC(Page page)
        throws Exception
    {
       /* double d;
        double d1;
        double d3;
        int i1;
        d = x1;
        d1 = y1;
        double d2 = 50D * m1;
        d3 = 50D * m1;
        int i = 0;
        for(int j = 0; j < 11; j += 2)
            i += str.charAt(j) - 48;

        i *= 3;
        for(int k = 1; k < 11; k += 2)
            i += str.charAt(k) - 48;

        int l = i % 10;
        i1 = (10 - l) % 10;
        new StringBuilder();
        this;
        JVM INSTR dup_x1 ;
        str;
        append();
        String.valueOf(i1);
        append();
        toString();
        str;
        d = drawEGuard(page, d, d1, m1, d3 + 8D);
        for(int j1 = 0; j1 < 6; j1++)
        {
            int l1 = str.charAt(j1) - 48;
            String s1 = String.valueOf(tableA[l1]);
            boolean flag = false;
            for(int l2 = 0; l2 < s1.length(); l2++)
            {
                int j2 = s1.charAt(l2) - 48;
                if(l2 % 2 != 0)
                    drawVertBar(page, d, d1, (double)j2 * m1, d3);
                d += (double)j2 * m1;
            }

        }

        d = drawMGuard(page, d, d1, m1, d3 + 8D);
        for(int k1 = 6; k1 < 12; k1++)
        {
            int i2 = str.charAt(k1) - 48;
            String s2 = String.valueOf(tableA[i2]);
            boolean flag1 = false;
            for(int i3 = 0; i3 < s2.length(); i3++)
            {
                int k2 = s2.charAt(i3) - 48;
                if(i3 % 2 == 0)
                    drawVertBar(page, d, d1, (double)k2 * m1, d3);
                d += (double)k2 * m1;
            }

        }

        d = drawEGuard(page, d, d1, m1, d3 + 8D);
        String s = (new StringBuilder()).append(str.charAt(0)).append("  ").append(str.charAt(1)).append(str.charAt(2)).append(str.charAt(3)).append(str.charAt(4)).append(str.charAt(5)).append("   ").append(str.charAt(6)).append(str.charAt(7)).append(str.charAt(8)).append(str.charAt(9)).append(str.charAt(10)).append("  ").append(str.charAt(11)).toString();
        double d4 = font.getSize();
        font.setSize(10D);
        page.drawString(font, s, x1 + (d - x1 - font.stringWidth(s)) / 2D, y1 + d3 + font.body_height);
        font.setSize(d4);
        return;*/
    }

    private double drawEGuard(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        drawBar(page, d + 0.5D * d2, d1, d2, d3);
        drawBar(page, d + 2.5D * d2, d1, d2, d3);
        return d + 3D * d2;
    }

    private double drawMGuard(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        drawBar(page, d + 1.5D * d2, d1, d2, d3);
        drawBar(page, d + 3.5D * d2, d1, d2, d3);
        return d + 5D * d2;
    }

    private void drawBar(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setPenWidth(d2);
        page.moveTo(d, d1);
        page.lineTo(d, d1 + d3);
        page.strokePath();
    }

    private void drawCode128(Page page)
        throws Exception
    {
        double d = x1;
        double d1 = y1;
        double d2 = m1;
        double d3 = m1;
        if(direction == 1)
            d2 += 25D;
        else
        if(direction == 0)
            d3 += 25D;
        ArrayList arraylist = new ArrayList();
        int i = 0;
        do
        {
            if(i >= str.length())
                break;
            char c = str.charAt(i);
            if(c < ' ')
            {
                arraylist.add(Integer.valueOf(98));
                arraylist.add(Integer.valueOf(c + 64));
            } else
            {
                arraylist.add(Integer.valueOf(c - 32));
            }
            if(arraylist.size() == 48)
                break;
            i++;
        } while(true);
        StringBuilder stringbuilder = new StringBuilder();
        int j = 104;
        stringbuilder.append((char)j);
        for(int k = 0; k < arraylist.size(); k++)
        {
            int i1 = ((Integer)arraylist.get(k)).intValue();
            stringbuilder.append((char)i1);
            j += i1 * (k + 1);
        }

        j %= 103;
        stringbuilder.append((char)j);
        stringbuilder.append('j');
        for(int l = 0; l < stringbuilder.length(); l++)
        {
            char c1 = stringbuilder.charAt(l);
            String s = String.valueOf(GS1_128.TABLE[c1]);
            for(int j1 = 0; j1 < s.length(); j1++)
            {
                int k1 = s.charAt(j1) - 48;
                if(j1 % 2 == 0)
                    if(direction == 0)
                        drawVertBar(page, d, d1, m1 * (double)k1, d3);
                    else
                    if(direction == 1)
                        drawHorzBar(page, d, d1, m1 * (double)k1, d2);
                if(direction == 0)
                {
                    d += (double)k1 * m1;
                    continue;
                }
                if(direction == 1)
                    d1 += (double)k1 * m1;
            }

        }

        if(font == null)
            return;
        if(direction == 0)
            page.drawString(font, str, x1 + (d - x1 - font.stringWidth(str)) / 2D, y1 + d3 + font.body_height);
        else
        if(direction == 1)
        {
            page.setTextDirection(90);
            page.drawString(font, str, d + d2 + font.body_height, d1 - (d1 - y1 - font.stringWidth(str)) / 2D);
            page.setTextDirection(0);
        }
    }

    private void drawCode39(Page page)
        throws Exception
    {
        str = (new StringBuilder()).append("*").append(str).append("*").toString();
        double d = x1;
        double d1 = y1;
        double d3 = 50D * m1;
        double d4 = 50D * m1;
        if(direction == 0)
        {
            for(int i = 0; i < str.length(); i++)
            {
                String s = (String)map.get(Character.valueOf(str.charAt(i)));
                if(s == null)
                    throw new Exception((new StringBuilder()).append("The input string '").append(str).append("' contains characters that are invalid in a Code39 barcode.").toString());
                for(int k = 0; k < 9; k++)
                {
                    char c = s.charAt(k);
                    if(c == 'w')
                    {
                        d += m1;
                        continue;
                    }
                    if(c == 'W')
                    {
                        d += m1 * 3D;
                        continue;
                    }
                    if(c == 'b')
                    {
                        drawVertBar(page, d, d1, m1, d4);
                        d += m1;
                        continue;
                    }
                    if(c == 'B')
                    {
                        drawVertBar(page, d, d1, m1 * 3D, d4);
                        d += m1 * 3D;
                    }
                }

                d += m1;
            }

            if(font != null)
                page.drawString(font, str, x1 + (d - x1 - font.stringWidth(str)) / 2D, y1 + d4 + font.body_height);
        } else
        if(direction == 1)
        {
            for(int j = 0; j < str.length(); j++)
            {
                String s1 = (String)map.get(Character.valueOf(str.charAt(j)));
                if(s1 == null)
                    throw new Exception((new StringBuilder()).append("The input string '").append(str).append("' contains characters that are invalid in a Code39 barcode.").toString());
                for(int l = 0; l < 9; l++)
                {
                    char c1 = s1.charAt(l);
                    if(c1 == 'w')
                    {
                        d1 += m1;
                        continue;
                    }
                    if(c1 == 'W')
                    {
                        d1 += 3D * m1;
                        continue;
                    }
                    if(c1 == 'b')
                    {
                        drawHorzBar(page, d, d1, m1, d4);
                        d1 += m1;
                        continue;
                    }
                    if(c1 == 'B')
                    {
                        drawHorzBar(page, d, d1, 3D * m1, d4);
                        d1 += 3D * m1;
                    }
                }

                d1 += m1;
            }

            if(font != null)
            {
                page.setTextDirection(270);
                page.drawString(font, str, d - font.body_height, y1 + (d1 - y1 - font.stringWidth(str)) / 2D);
                page.setTextDirection(0);
            }
        } else
        if(direction == 2)
        {
            double d5 = 0.0D;
            for(int i1 = 0; i1 < str.length(); i1++)
            {
                String s2 = (String)map.get(Character.valueOf(str.charAt(i1)));
                if(s2 == null)
                    throw new Exception((new StringBuilder()).append("The input string '").append(str).append("' contains characters that are invalid in a Code39 barcode.").toString());
                for(int k1 = 0; k1 < 9; k1++)
                {
                    char c2 = s2.charAt(k1);
                    if(c2 == 'w' || c2 == 'b')
                    {
                        d5 += m1;
                        continue;
                    }
                    if(c2 == 'W' || c2 == 'B')
                        d5 += 3D * m1;
                }

                d5 += m1;
            }

            d1 += d5 - m1;
            for(int j1 = 0; j1 < str.length(); j1++)
            {
                String s3 = (String)map.get(Character.valueOf(str.charAt(j1)));
                for(int l1 = 0; l1 < 9; l1++)
                {
                    char c3 = s3.charAt(l1);
                    if(c3 == 'w')
                    {
                        d1 -= m1;
                        continue;
                    }
                    if(c3 == 'W')
                    {
                        d1 -= 3D * m1;
                        continue;
                    }
                    if(c3 == 'b')
                    {
                        drawHorzBar2(page, d, d1, m1, d4);
                        d1 -= m1;
                        continue;
                    }
                    if(c3 == 'B')
                    {
                        drawHorzBar2(page, d, d1, 3D * m1, d4);
                        d1 -= 3D * m1;
                    }
                }

                d1 -= m1;
            }

            if(font != null)
            {
                double d2 = y1 + (d5 - m1);
                page.setTextDirection(90);
                page.drawString(font, str, d + d3 + font.body_height, d2 - (d2 - y1 - font.stringWidth(str)) / 2D);
                page.setTextDirection(0);
            }
        }
    }

    private void drawVertBar(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setPenWidth(d2);
        page.moveTo(d + d2 / 2D, d1);
        page.lineTo(d + d2 / 2D, d1 + d3);
        page.strokePath();
    }

    private void drawHorzBar(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setPenWidth(d2);
        page.moveTo(d, d1 + d2 / 2D);
        page.lineTo(d + d3, d1 + d2 / 2D);
        page.strokePath();
    }

    private void drawHorzBar2(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setPenWidth(d2);
        page.moveTo(d, d1 - d2 / 2D);
        page.lineTo(d + d3, d1 - d2 / 2D);
        page.strokePath();
    }

    public static final int UPC = 0;
    public static final int CODE128 = 1;
    public static final int CODE39 = 2;
    public static final int LEFT_TO_RIGHT = 0;
    public static final int TOP_TO_BOTTOM = 1;
    public static final int BOTTOM_TO_TOP = 2;
    private int tableA[] = {
        3211, 2221, 2122, 1411, 1132, 1231, 1114, 1312, 1213, 3112
    };
    private int tableB[] = {
        1123, 1222, 2212, 1141, 2311, 1321, 4111, 2131, 3121, 2113
    };
    private double x1;
    private double y1;
    private double m1;
    private int type;
    private int direction;
    private String str;
    private Font font;
    private Map map;
}