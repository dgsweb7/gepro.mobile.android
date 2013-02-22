
package com.modularsistemas.modpdf;



public class Box
{

    public Box()
    {
        x = 0.0D;
        y = 0.0D;
        w = 0.0D;
        h = 0.0D;
        width = 0.29999999999999999D;
        pattern = "[] 0";
        fill_shape = false;
    }

    public Box(double d, double d1, double d2, double d3)
    {
        x = 0.0D;
        y = 0.0D;
        w = 0.0D;
        h = 0.0D;
        width = 0.29999999999999999D;
        pattern = "[] 0";
        fill_shape = false;
        x = d;
        y = d1;
        w = d2;
        h = d3;
    }

    public void setPosition(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public void setSize(double d, double d1)
    {
        w = d;
        h = d1;
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

    public void setLineWidth(double d)
    {
        width = d;
    }

    public void setPattern(String s)
    {
        pattern = s;
    }

    public void setFillShape(boolean flag)
    {
        fill_shape = flag;
    }

    public void placeIn(Box box, double d, double d1)
        throws Exception
    {
        x = box.x + d;
        y = box.y + d1;
    }

    public void scaleBy(double d)
        throws Exception
    {
        x *= d;
        y *= d;
    }

    public void drawOn(Page page)
        throws Exception
    {
        page.setPenWidth(width);
        page.setLinePattern(pattern);
        page.moveTo(x, y);
        page.lineTo(x + w, y);
        page.lineTo(x + w, y + h);
        page.lineTo(x, y + h);
        page.closePath();
        if(fill_shape)
        {
            page.setBrushColor(color[0], color[1], color[2]);
            page.fillPath();
        } else
        {
            page.setPenColor(color[0], color[1], color[2]);
            page.strokePath();
        }
    }

    protected double x;
    protected double y;
    private double w;
    private double h;
    private double color[] = {
        0.0D, 0.0D, 0.0D
    };
    private double width;
    private String pattern;
    private boolean fill_shape;
}