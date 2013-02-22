package com.modularsistemas.modpdf;


public class Cell
{

    public Cell(Font font1)
    {
        width = 70D;
        height = 0.0D;
        font = null;
        text = " ";
        align = 0;
        point = null;
        border = null;
        colspan = 1;
        lineWidth = 0.0D;
        font = font1;
        border = new Border();
    }

    public Cell(Font font1, String s)
    {
        width = 70D;
        height = 0.0D;
        font = null;
        text = " ";
        align = 0;
        point = null;
        border = null;
        colspan = 1;
        lineWidth = 0.0D;
        font = font1;
        text = s;
        border = new Border();
    }

    public void setFont(Font font1)
    {
        font = font1;
    }

    public Font getFont()
    {
        return font;
    }

    public void setText(String s)
    {
        text = s;
    }

    public String getText()
    {
        return text;
    }

    public void setPoint(Point point1)
    {
        point = point1;
    }

    public Point getPoint()
    {
        return point;
    }

    public Border getBorder()
    {
        return border;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public double getColspan()
    {
        return (double)colspan;
    }

    public void setBorder(Border border1)
    {
        border = border1;
    }

    public void setNoBorders()
    {
        border.top = false;
        border.bottom = false;
        border.left = false;
        border.right = false;
    }

    public void setBgColor(double ad[])
    {
        bgColor = ad;
    }

    public void setBgColor(int ai[])
    {
        bgColor = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
    }

    public void setFgColor(double ad[])
    {
        penColor = ad;
        brushColor = ad;
    }

    public void setFgColor(int ai[])
    {
        penColor = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
        brushColor = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
    }

    public void setPenColor(double ad[])
    {
        penColor = ad;
    }

    public void setPenColor(int ai[])
    {
        penColor = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
    }

    public void setBrushColor(double ad[])
    {
        brushColor = ad;
    }

    public void setBrushColor(int ai[])
    {
        brushColor = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
    }

    public void setTextAlignment(int i)
    {
        align = i;
    }

    public void setColSpan(int i)
    {
        colspan = i;
    }

    public void setWidth(double d)
    {
        width = d;
    }

    public void setHeight(double d)
    {
        height = d;
    }

    public void setColspan(int i)
    {
        colspan = i;
    }

    public void paint(Page page, double d, double d1, double d2, 
            double d3, double d4)
        throws Exception
    {
        width = d2;
        height = d3;
        drawBackground(page, d, d1, d2, d3);
        drawBorders(page, d, d1, d2, d3);
        drawText(page, d, d1, d2, d4);
    }

    private void drawBackground(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setBrushColor(brushColor[0], brushColor[1], brushColor[2]);
        Box box = new Box(d, d1, d2, d3);
        box.setColor(bgColor);
        box.setFillShape(true);
        box.drawOn(page);
    }

    private void drawBorders(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setPenWidth(lineWidth);
        page.setPenColor(penColor[0], penColor[1], penColor[2]);
        if(border.left)
        {
            page.moveTo(d, d1);
            page.lineTo(d, d1 + d3);
            page.strokePath();
        }
        if(border.right)
        {
            page.moveTo(d + d2, d1);
            page.lineTo(d + d2, d1 + d3);
            page.strokePath();
        }
        if(border.top)
        {
            page.moveTo(d, d1);
            page.lineTo(d + d2, d1);
            page.strokePath();
        }
        if(border.bottom)
        {
            page.moveTo(d, d1 + d3);
            page.lineTo(d + d2, d1 + d3);
            page.strokePath();
        }
    }

    private void drawText(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        double d4 = d1 + font.ascent + d3;
        page.setPenColor(penColor[0], penColor[1], penColor[2]);
        page.setBrushColor(brushColor[0], brushColor[1], brushColor[2]);
        if(align == 2)
            page.drawString(font, text, (d + d2) - (font.stringWidth(text) + d3), d4);
        else
        if(align == 1)
            page.drawString(font, text, d + (d2 - font.stringWidth(text)) / 2D, d4);
        else
            page.drawString(font, text, d + d3, d4);
        if(point != null)
        {
            point.x = (d + d2) - (font.ascent / 2D + d3);
            point.y = d1 + (font.ascent / 2D + d3);
            point.r = font.ascent / 3D;
            page.drawPoint(point);
        }
    }

    protected double width;
    protected double height;
    protected Font font;
    protected String text;
    protected int align;
    protected Point point;
    public Border border;
    protected int colspan;
    protected double bgColor[] = {
        1.0D, 1.0D, 1.0D
    };
    protected double penColor[] = {
        0.0D, 0.0D, 0.0D
    };
    protected double brushColor[] = {
        0.0D, 0.0D, 0.0D
    };
    protected double lineWidth;
}