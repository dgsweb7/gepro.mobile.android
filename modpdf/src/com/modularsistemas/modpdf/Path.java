
package com.modularsistemas.modpdf;

import java.util.ArrayList;
import java.util.List;


public class Path
{

    public Path()
    {
        width = 0.29999999999999999D;
        pattern = "[] 0";
        fill_shape = false;
        close_path = false;
        points = null;
        box_x = 0.0D;
        box_y = 0.0D;
        points = new ArrayList();
    }

    public void add(Point point)
    {
        points.add(point);
    }

    public void setPattern(String s)
    {
        pattern = s;
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
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)(ai[2] / 255)
        });
    }

    public void setClosePath(boolean flag)
    {
        close_path = flag;
    }

    public void setFillShape(boolean flag)
    {
        fill_shape = flag;
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
        for(int i = 0; i < points.size(); i++)
        {
            Point point = (Point)points.get(i);
            point.x *= d;
            point.y *= d;
        }

    }

    public void drawOn(Page page)
        throws Exception
    {
        if(fill_shape)
            page.setBrushColor(color[0], color[1], color[2]);
        else
            page.setPenColor(color[0], color[1], color[2]);
        page.setPenWidth(width);
        page.setLinePattern(pattern);
        for(int i = 0; i < points.size(); i++)
        {
            Point point = (Point)points.get(i);
            point.x += box_x;
            point.y += box_y;
        }

        if(fill_shape)
            page.drawPath(points, 'f');
        else
        if(close_path)
            page.drawPath(points, 's');
        else
            page.drawPath(points, 'S');
        for(int j = 0; j < points.size(); j++)
        {
            Point point1 = (Point)points.get(j);
            point1.x -= box_x;
            point1.y -= box_y;
        }

    }

    private double color[] = {
        0.0D, 0.0D, 0.0D
    };
    private double width;
    private String pattern;
    private boolean fill_shape;
    private boolean close_path;
    private List points;
    private double box_x;
    private double box_y;
}