// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 25/5/2010 18:17:00
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Point.java

package com.modularsistemas.modpdf;

import java.util.List;


public class Point
{

    public Point()
    {
        x = 0.0D;
        y = 0.0D;
        r = 2D;
        shape = 0;
        line_width = 0.29999999999999999D;
        line_pattern = "[] 0";
        fill_shape = false;
        isCurvePoint = false;
        text = null;
        uri = null;
        info = null;
        drawLineTo = false;
        box_x = 0.0D;
        box_y = 0.0D;
    }

    public Point(double d, double d1)
    {
        x = 0.0D;
        y = 0.0D;
        r = 2D;
        shape = 0;
        line_width = 0.29999999999999999D;
        line_pattern = "[] 0";
        fill_shape = false;
        isCurvePoint = false;
        text = null;
        uri = null;
        info = null;
        drawLineTo = false;
        box_x = 0.0D;
        box_y = 0.0D;
        x = d;
        y = d1;
    }

    public Point(double d, double d1, boolean flag)
    {
        x = 0.0D;
        y = 0.0D;
        r = 2D;
        shape = 0;
        line_width = 0.29999999999999999D;
        line_pattern = "[] 0";
        fill_shape = false;
        isCurvePoint = false;
        text = null;
        uri = null;
        info = null;
        drawLineTo = false;
        box_x = 0.0D;
        box_y = 0.0D;
        x = d;
        y = d1;
        isCurvePoint = flag;
    }

    public void setPosition(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public void setX(double d)
    {
        x = d;
    }

    public double getX()
    {
        return x;
    }

    public void setY(double d)
    {
        y = d;
    }

    public double getY()
    {
        return y;
    }

    public void setRadius(double d)
    {
        r = d;
    }

    public double getRadius()
    {
        return r;
    }

    public void setShape(int i)
    {
        shape = i;
    }

    public int getShape()
    {
        return shape;
    }

    public void setFillShape(boolean flag)
    {
        fill_shape = flag;
    }

    public boolean getFillShape()
    {
        return fill_shape;
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

    public double[] getColor()
    {
        return color;
    }

    public void setLineWidth(double d)
    {
        line_width = d;
    }

    public double getLineWidth()
    {
        return line_width;
    }

    public void setLinePattern(String s)
    {
        line_pattern = s;
    }

    public String getLinePattern()
    {
        return line_pattern;
    }

    public void setDrawLineTo(boolean flag)
    {
        drawLineTo = flag;
    }

    public boolean getDrawLineTo()
    {
        return drawLineTo;
    }

    public void setURIAction(String s)
    {
        uri = s;
    }

    public String getURIAction()
    {
        return uri;
    }

    public void setText(String s)
    {
        text = s;
    }

    public String getText()
    {
        return text;
    }

    public void setInfo(List list)
    {
        info = list;
    }

    public List getInfo()
    {
        return info;
    }

    public void placeIn(Box box, double d, double d1)
        throws Exception
    {
        box_x = box.x + d;
        box_y = box.y + d1;
    }

    public void drawOn(Page page)
        throws Exception
    {
        page.setPenWidth(line_width);
        page.setLinePattern(line_pattern);
        if(fill_shape)
            page.setBrushColor(color[0], color[1], color[2]);
        else
            page.setPenColor(color[0], color[1], color[2]);
        x += box_x;
        y += box_y;
        page.drawPoint(this);
        x -= box_x;
        y -= box_y;
    }

    public static final int INVISIBLE = -1;
    public static final int CIRCLE = 0;
    public static final int DIAMOND = 1;
    public static final int BOX = 2;
    public static final int PLUS = 3;
    public static final int H_DASH = 4;
    public static final int V_DASH = 5;
    public static final int MULTIPLY = 6;
    public static final int STAR = 7;
    public static final int X_MARK = 8;
    public static final int UP_ARROW = 9;
    public static final int DOWN_ARROW = 10;
    public static final int LEFT_ARROW = 11;
    public static final int RIGHT_ARROW = 12;
    public static final boolean IS_CURVE_POINT = true;
    protected double x;
    protected double y;
    protected double r;
    protected int shape;
    protected double color[] = {
        0.0D, 0.0D, 0.0D
    };
    protected double line_width;
    protected String line_pattern;
    protected boolean fill_shape;
    protected boolean isCurvePoint;
    protected String text;
    protected String uri;
    protected List info;
    protected boolean drawLineTo;
    private double box_x;
    private double box_y;
}