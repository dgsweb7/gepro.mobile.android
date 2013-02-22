
package com.modularsistemas.modpdf;

import java.util.ArrayList;
import java.util.List;


public class TextColumn
{

    public TextColumn(Font font1, int i)
        throws Exception
    {
        x = 0.0D;
        y = 0.0D;
        w = 0.0D;
        h = 0.0D;
        x1 = 0.0D;
        y1 = 0.0D;
        line_height = 0.0D;
        padding = 1.0D;
        paragraphs = null;
        font = null;
        alignment = 0;
        rotate = 0;
        font = font1;
        rotate = i;
        if(rotate != 0 && rotate != 90 && rotate != 270)
        {
            throw new Exception("Invalid rotation angle. Please use 0, 90 or 270 degrees.");
        } else
        {
            paragraphs = new ArrayList();
            return;
        }
    }

    public TextColumn(Font font1)
    {
        x = 0.0D;
        y = 0.0D;
        w = 0.0D;
        h = 0.0D;
        x1 = 0.0D;
        y1 = 0.0D;
        line_height = 0.0D;
        padding = 1.0D;
        paragraphs = null;
        font = null;
        alignment = 0;
        rotate = 0;
        font = font1;
        paragraphs = new ArrayList();
    }

    public void setPosition(double d, double d1)
    {
        x = d;
        y = d1;
        x1 = d;
        y1 = d1;
    }

    public void setSize(double d, double d1)
    {
        w = d;
        h = d1;
    }

    public void setFont(Font font1)
    {
        font = font1;
    }

    public void setAlignment(int i)
    {
        alignment = i;
    }

    public void addParagraph(Paragraph paragraph)
    {
        paragraphs.add(paragraph);
    }

    public Point drawOn(Page page)
        throws Exception
    {
        Point point = null;
        for(int i = 0; i < paragraphs.size(); i++)
        {
            Paragraph paragraph = (Paragraph)paragraphs.get(i);
            alignment = paragraph.alignment;
            point = drawParagraphOn(page, paragraph);
        }

        return point;
    }

    private Point drawParagraphOn(Page page, Paragraph paragraph)
        throws Exception
    {
        ArrayList arraylist = new ArrayList();
        double d = 0.0D;
        for(int i = 0; i < paragraph.list.size(); i++)
        {
            TextLine textline = (TextLine)paragraph.list.get(i);
            if(i == 0)
            {
                line_height = textline.font.body_height + 2D * padding;
                if(rotate == 0)
                    y1 += textline.font.ascent + padding;
                else
                if(rotate == 90)
                    x1 += textline.font.ascent + padding;
                else
                if(rotate == 270)
                    x1 -= textline.font.ascent + padding;
            }
            String as[] = textline.str.split("\\s");
            Object obj = null;
            for(int j = 0; j < as.length; j++)
            {
                String s = as[j];
                TextLine textline1 = new TextLine(textline.font, s);
                textline1.setColor(textline.color);
                textline1.setUnderline(textline.underline);
                textline1.setStrikeLine(textline.strike);
                textline1.setURIAction(textline.uri);
                d += textline.font.stringWidth(s);
                if(d >= w)
                {
                    drawLineOfText(page, arraylist);
                    moveToNextLine();
                    arraylist.clear();
                    arraylist.add(textline1);
                    d = textline.font.stringWidth((new StringBuilder()).append(s).append(" ").toString());
                } else
                {
                    arraylist.add(textline1);
                    d += textline.font.stringWidth(" ");
                }
            }

        }

        drawNonJustifiedLine(page, arraylist);
        return moveToNextLine();
    }

    private Point moveToNextLine()
    {
        if(rotate == 0)
        {
            x1 = x;
            y1 += line_height;
        } else
        if(rotate == 90)
        {
            x1 += line_height;
            y1 = y;
        } else
        if(rotate == 270)
        {
            x1 -= line_height;
            y1 = y;
        }
        return new Point(x1, y1);
    }

    private void drawLineOfText(Page page, List list)
        throws Exception
    {
        if(alignment == 3)
        {
            double d = 0.0D;
            for(int i = 0; i < list.size(); i++)
            {
                TextLine textline = (TextLine)list.get(i);
                d += textline.font.stringWidth(textline.str);
            }

            double d1 = (w - d) / (double)(list.size() - 1);
            for(int j = 0; j < list.size(); j++)
            {
                TextLine textline1 = (TextLine)list.get(j);
                textline1.setPosition(x1, y1);
                if(rotate == 0)
                {
                    textline1.setTextDirection(0);
                    textline1.drawOn(page);
                    x1 += textline1.font.stringWidth(textline1.str) + d1;
                } else
                if(rotate == 90)
                {
                    textline1.setTextDirection(90);
                    textline1.drawOn(page);
                    y1 -= textline1.font.stringWidth(textline1.str) + d1;
                } else
                if(rotate == 270)
                {
                    textline1.setTextDirection(270);
                    textline1.drawOn(page);
                    y1 += textline1.font.stringWidth(textline1.str) + d1;
                }
            }

        } else
        {
            drawNonJustifiedLine(page, list);
        }
    }

    private void drawNonJustifiedLine(Page page, List list)
        throws Exception
    {
        /*double d;
        int i;
        d = 0.0D;
        i = 0;
_L5:
        if(i >= list.size()) goto _L2; else goto _L1
_L1:
        TextLine textline = (TextLine)list.get(i);
        if(i >= list.size() - 1) goto _L4; else goto _L3
_L3:
        new StringBuilder();
        textline;
        JVM INSTR dup_x1 ;
        str;
        append();
        " ";
        append();
        toString();
        str;
_L4:
        d += textline.font.stringWidth(textline.str);
        i++;
          goto _L5
_L2:
        if(alignment == 1)
        {
            if(rotate == 0)
                x1 = x + (w - d) / 2D;
            else
            if(rotate == 90)
                y1 = y - (w - d) / 2D;
            else
            if(rotate == 270)
                y1 = y + (w - d) / 2D;
        } else
        if(alignment == 2)
            if(rotate == 0)
                x1 = x + (w - d);
            else
            if(rotate == 90)
                y1 = y - (w - d);
            else
            if(rotate == 270)
                y1 = y + (w - d);
        for(int j = 0; j < list.size(); j++)
        {
            TextLine textline1 = (TextLine)list.get(j);
            textline1.setPosition(x1, y1);
            if(rotate == 0)
            {
                textline1.setTextDirection(0);
                textline1.drawOn(page);
                x1 += textline1.font.stringWidth(textline1.str);
                continue;
            }
            if(rotate == 90)
            {
                textline1.setTextDirection(90);
                textline1.drawOn(page);
                y1 -= textline1.font.stringWidth(textline1.str);
                continue;
            }
            if(rotate == 270)
            {
                textline1.setTextDirection(270);
                textline1.drawOn(page);
                y1 += textline1.font.stringWidth(textline1.str);
            }
        }

        return;*/
    }

    double x;
    double y;
    double w;
    double h;
    double x1;
    double y1;
    double line_height;
    double padding;
    List paragraphs;
    Font font;
    int alignment;
    int rotate;
}