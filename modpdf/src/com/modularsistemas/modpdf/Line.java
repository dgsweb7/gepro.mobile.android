package com.modularsistemas.modpdf;


public class Line
{

    public Line()
    {
        x1 = 0.0D;
        y1 = 0.0D;
        x2 = 0.0D;
        y2 = 0.0D;
        box_x = 0.0D;
        box_y = 0.0D;
        width = 0.29999999999999999D;
        pattern = "[] 0";
    }

    public Line(double d, double d1, double d2, double d3)
    {
        x1 = 0.0D;
        y1 = 0.0D;
        x2 = 0.0D;
        y2 = 0.0D;
        box_x = 0.0D;
        box_y = 0.0D;
        width = 0.29999999999999999D;
        pattern = "[] 0";
        x1 = d;
        y1 = d1;
        x2 = d2;
        y2 = d3;
    }

    public void setPattern(String s)
    {
        pattern = s;
    }

    public void setStartPoint(double d, double d1)
    {
        x1 = d;
        y1 = d1;
    }

    public void setEndPoint(double d, double d1)
    {
        x2 = d;
        y2 = d1;
    }

    public Point getStartPoint()
    {
        return new Point(x1, y1);
    }

    public Point getEndPoint()
    {
        return new Point(x2, y2);
    }

    public void setWidth(double d)
    {
        width = d;
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

    public void placeIn(Box box)
        throws Exception
    {
        placeIn(box, 0.0D, 0.0D);
    }

    public void placeIn(Box box, double d, double d1)
        throws Exception
    {
        box_x = box.x + d;
        box_y = box.y + d1;
    }

    public void scaleBy(double d)
        throws Exception
    {
        x1 *= d;
        x2 *= d;
        y1 *= d;
        y2 *= d;
    }

    public void drawOn(Page page)
        throws Exception
    {
        page.setPenColor(color[0], color[1], color[2]);
        page.setPenWidth(width);
        page.setLinePattern(pattern);
        page.drawLine(x1 + box_x, y1 + box_y, x2 + box_x, y2 + box_y);
    }

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double box_x;
    private double box_y;
    private double color[] = {
        0.0D, 0.0D, 0.0D
    };
    private double width;
    private String pattern;
}