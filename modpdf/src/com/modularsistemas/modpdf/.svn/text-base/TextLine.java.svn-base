
package com.modularsistemas.modpdf;

import java.util.List;


public class TextLine
{

    public TextLine(Font font1)
    {
        x = 0.0D;
        y = 0.0D;
        font = null;
        str = "";
        uri = null;
        underline = false;
        strike = false;
        degrees = 0;
        box_x = 0.0D;
        box_y = 0.0D;
        font = font1;
    }

    public TextLine(Font font1, String s)
    {
        x = 0.0D;
        y = 0.0D;
        font = null;
        str = "";
        uri = null;
        underline = false;
        strike = false;
        degrees = 0;
        box_x = 0.0D;
        box_y = 0.0D;
        font = font1;
        str = s;
    }

    public void setPosition(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public void setText(String s)
    {
        str = s;
    }

    public void setFont(Font font1)
    {
        font = font1;
    }

    public void setColor(double ad[])
    {
        color = ad;
    }

    public void setColor(int ai[])
    {
        color = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
    }

    public String getText()
    {
        return str;
    }

    public double[] getColor()
    {
        return color;
    }

    public void setURIAction(String s)
    {
        uri = s;
    }

    public void setUnderline(boolean flag)
    {
        underline = flag;
    }

    public void setStrikeLine(boolean flag)
    {
        strike = flag;
    }

    public void setTextDirection(int i)
    {
        degrees = i;
    }

    public void placeIn(Box box)
    {
        box_x = box.x;
        box_y = box.y;
    }

    public void drawOn(Page page)
        throws Exception
    {
        page.setTextDirection(degrees);
        x += box_x;
        y += box_y;
        if(uri != null)
            page.annots.add(new Annotation(uri, x, page.height - (y - font.ascent), x + font.stringWidth(str), page.height - (y - font.descent)));
        if(str != null)
        {
            page.setBrushColor(color[0], color[1], color[2]);
            page.drawString(font, str, x, y);
        }
        if(underline)
        {
            page.setPenWidth(font.underlineThickness);
            page.setPenColor(color[0], color[1], color[2]);
            double d = font.stringWidth(str);
            double d2 = (3.1415926535897931D * (double)degrees) / 180D;
            double d4 = font.underlinePosition * Math.sin(d2);
            double d6 = font.underlinePosition * Math.cos(d2);
            double d8 = x + d * Math.cos(d2);
            double d10 = y - d * Math.sin(d2);
            page.moveTo(x + d4, y + d6);
            page.lineTo(d8 + d4, d10 + d6);
            page.strokePath();
        }
        if(strike)
        {
            page.setPenWidth(font.underlineThickness);
            page.setPenColor(color[0], color[1], color[2]);
            double d1 = font.stringWidth(str);
            double d3 = (3.1415926535897931D * (double)degrees) / 180D;
            double d5 = (font.body_height / 4D) * Math.sin(d3);
            double d7 = (font.body_height / 4D) * Math.cos(d3);
            double d9 = x + d1 * Math.cos(d3);
            double d11 = y - d1 * Math.sin(d3);
            page.moveTo(x - d5, y - d7);
            page.lineTo(d9 - d5, d11 - d7);
            page.strokePath();
        }
        page.setTextDirection(0);
    }

    protected double x;
    protected double y;
    protected Font font;
    protected String str;
    protected String uri;
    protected boolean underline;
    protected boolean strike;
    protected int degrees;
    protected double color[] = {
        0.0D, 0.0D, 0.0D
    };
    private double box_x;
    private double box_y;
}