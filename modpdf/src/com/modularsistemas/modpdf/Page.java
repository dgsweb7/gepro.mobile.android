
package com.modularsistemas.modpdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Page
{

    public Page(PDF pdf1, double ad[])
        throws Exception
    {
        buf = null;
        writingMode = "1 0 0 1 ";
        renderingMode = 0;
        width = 0.0D;
        height = 0.0D;
        annots = null;
        pdf = null;
        pen_width = 0.0D;
        line_pattern = "[] 0";
        pdf = pdf1;
        annots = new ArrayList();
        width = ad[0];
        height = ad[1];
        buf = new ByteArrayOutputStream(8192);
        pdf1.pages.add(this);
    }

    protected void drawLine(double d, double d1, double d2, double d3)
        throws IOException
    {
        moveTo(d, d1);
        lineTo(d2, d3);
        strokePath();
    }

    protected void drawString(Font font, String s, double d, double d1)
        throws IOException
    {
        append("BT\n");
        append("/F");
        append(font.objNumber);
        append(' ');
        append(font.size);
        append(" Tf\n");
        if(renderingMode != 0)
        {
            append(renderingMode);
            append(" Tr\n");
        }
        append(writingMode);
        append(d);
        append(' ');
        append(height - d1);
        append(" Tm\n");
        append("[ (");
label0:
        for(int i = 0; i < s.length(); i++)
        {
            int j = s.charAt(i);
            if(font.isComposite)
            {
                if(j < font.firstChar || j > font.lastChar)
                {
                    append((byte)0);
                    append((byte)32);
                    continue;
                }
                byte byte0 = (byte)(j >> 8);
                byte byte1 = (byte)j;
                if(byte0 == 40 || byte0 == 41 || byte0 == 92)
                    append((byte)92);
                append((byte)(j >> 8));
                if(byte1 == 40 || byte1 == 41 || byte1 == 92)
                    append((byte)92);
                append((byte)j);
                continue;
            }
            if(j < font.firstChar || j > font.lastChar)
                j = 32;
            if(j == 40 || j == 41 || j == 92)
                append((byte)92);
            append((byte)j);
            if(!font.isStandard || !font.kernPairs || font.name.startsWith("C") || font.name.startsWith("S") || font.name.startsWith("Z"))
                continue;
            if(i == s.length() - 1)
                break;
            j -= 32;
            char c = s.charAt(i + 1);
            if(c < font.firstChar || c > font.lastChar)
                c = ' ';
            int k = 2;
            do
            {
                if(k >= font.metrics[j].length)
                    continue label0;
                if(font.metrics[j][k] == c)
                {
                    append(") ");
                    append(-font.metrics[j][k + 1]);
                    append(" (");
                    continue label0;
                }
                k += 2;
            } while(true);
        }

        append(") ] TJ\n");
        append("ET\n");
    }

    protected void setPenColor(double d, double d1, double d2)
        throws IOException
    {
        if(pen_color[0] == d && pen_color[1] == d1 && pen_color[2] == d2)
        {
            return;
        } else
        {
            pen_color[0] = d;
            pen_color[1] = d1;
            pen_color[2] = d2;
            append(d);
            append(' ');
            append(d1);
            append(' ');
            append(d2);
            append(" RG\n");
            return;
        }
    }

    protected void setBrushColor(double d, double d1, double d2)
        throws IOException
    {
        if(brush_color[0] == d && brush_color[1] == d1 && brush_color[2] == d2)
        {
            return;
        } else
        {
            brush_color[0] = d;
            brush_color[1] = d1;
            brush_color[2] = d2;
            append(d);
            append(' ');
            append(d1);
            append(' ');
            append(d2);
            append(" rg\n");
            return;
        }
    }

    protected void setDefaultLineWidth()
        throws IOException
    {
        append(0.0D);
        append(" w\n");
    }

    protected void setLinePattern(String s)
        throws IOException
    {
        if(s.equals(line_pattern))
            return;
        line_pattern = s;
        if(s.startsWith("["))
        {
            append(s);
        } else
        {
            int i = 0;
            int j = 0;
            for(int k = 0; k < s.length(); k++)
                if(s.charAt(k) == '-')
                    i++;
                else
                    j++;

            if(i == 0 || j == 0)
                append("[] 0");
            else
                append((new StringBuilder()).append("[").append(i / 2).append(" ").append(j / 2).append("] 0").toString());
        }
        append(" d\n");
    }

    protected void setDefaultLinePattern()
        throws IOException
    {
        append("[] 0");
        append(" d\n");
    }

    protected void setPenWidth(double d)
        throws IOException
    {
        if(pen_width == d)
        {
            return;
        } else
        {
            pen_width = d;
            append(pen_width);
            append(" w\n");
            return;
        }
    }

    protected void moveTo(double d, double d1)
        throws IOException
    {
        append(d);
        append(' ');
        append(height - d1);
        append(" m\n");
    }

    protected void lineTo(double d, double d1)
        throws IOException
    {
        append(d);
        append(' ');
        append(height - d1);
        append(" l\n");
    }

    protected void closePath()
        throws IOException
    {
        append("h\n");
    }

    protected void strokePath()
        throws IOException
    {
        append("S\n");
    }

    protected void fillPath()
        throws IOException
    {
        append("f\n");
    }

    protected void drawPath(List list, char c)
        throws Exception
    {
        if(list.size() < 2)
            throw new Exception("The Path object must contain at least 2 points");
        Point point = (Point)list.get(0);
        moveTo(point.x, point.y);
        int i = 0;
        for(int j = 1; j < list.size(); j++)
        {
            Point point1 = (Point)list.get(j);
            if(point1.isCurvePoint)
            {
                append(point1.x);
                append(' ');
                append(height - point1.y);
                if(i < 2)
                {
                    append(' ');
                    i++;
                } else
                {
                    append(" c\n");
                    i = 0;
                }
            } else
            {
                lineTo(point1.x, point1.y);
            }
        }

        if(i != 0)
        {
            throw new Exception("Invalid number of curve points in the Path object");
        } else
        {
            append(c);
            append('\n');
            return;
        }
    }

    protected void drawBezierCurve(List list, char c)
        throws IOException
    {
        Point point = (Point)list.get(0);
        moveTo(point.x, point.y);
        for(int i = 1; i < list.size(); i++)
        {
            Point point1 = (Point)list.get(i);
            append(point1.x);
            append(' ');
            append(height - point1.y);
            if(i % 3 == 0)
                append(" c\n");
            else
                append(' ');
        }

        append(c);
        append('\n');
    }

    protected void drawCircle(double d, double d1, double d2, char c)
        throws Exception
    {
        ArrayList arraylist = new ArrayList();
        Point point = new Point();
        point.x = d;
        point.y = d1 - d2;
        arraylist.add(point);
        point = new Point();
        point.x = d + 0.55000000000000004D * d2;
        point.y = d1 - d2;
        arraylist.add(point);
        point = new Point();
        point.x = d + d2;
        point.y = d1 - 0.55000000000000004D * d2;
        arraylist.add(point);
        point = new Point();
        point.x = d + d2;
        point.y = d1;
        arraylist.add(point);
        point = new Point();
        point.x = d + d2;
        point.y = d1 + 0.55000000000000004D * d2;
        arraylist.add(point);
        point = new Point();
        point.x = d + 0.55000000000000004D * d2;
        point.y = d1 + d2;
        arraylist.add(point);
        point = new Point();
        point.x = d;
        point.y = d1 + d2;
        arraylist.add(point);
        point = new Point();
        point.x = d - 0.55000000000000004D * d2;
        point.y = d1 + d2;
        arraylist.add(point);
        point = new Point();
        point.x = d - d2;
        point.y = d1 + 0.55000000000000004D * d2;
        arraylist.add(point);
        point = new Point();
        point.x = d - d2;
        point.y = d1;
        arraylist.add(point);
        point = new Point();
        point.x = d - d2;
        point.y = d1 - 0.55000000000000004D * d2;
        arraylist.add(point);
        point = new Point();
        point.x = d - 0.55000000000000004D * d2;
        point.y = d1 - d2;
        arraylist.add(point);
        point = new Point();
        point.x = d;
        point.y = d1 - d2;
        arraylist.add(point);
        drawBezierCurve(arraylist, c);
    }

    protected void drawPoint(Point point)
        throws Exception
    {
        if(point.shape == -1)
            return;
        Object obj = null;
        Object obj1 = null;
        if(point.shape == 0)
        {
            if(point.fill_shape)
                drawCircle(point.x, point.y, point.r, 'f');
            else
                drawCircle(point.x, point.y, point.r, 'S');
        } else
        if(point.shape == 1)
        {
            ArrayList arraylist = new ArrayList();
            Point point1 = new Point();
            point1.x = point.x;
            point1.y = point.y - point.r;
            arraylist.add(point1);
            point1 = new Point();
            point1.x = point.x + point.r;
            point1.y = point.y;
            arraylist.add(point1);
            point1 = new Point();
            point1.x = point.x;
            point1.y = point.y + point.r;
            arraylist.add(point1);
            point1 = new Point();
            point1.x = point.x - point.r;
            point1.y = point.y;
            arraylist.add(point1);
            if(point.fill_shape)
                drawPath(arraylist, 'f');
            else
                drawPath(arraylist, 's');
        } else
        if(point.shape == 2)
        {
            ArrayList arraylist1 = new ArrayList();
            Point point2 = new Point();
            point2.x = point.x - point.r;
            point2.y = point.y - point.r;
            arraylist1.add(point2);
            point2 = new Point();
            point2.x = point.x + point.r;
            point2.y = point.y - point.r;
            arraylist1.add(point2);
            point2 = new Point();
            point2.x = point.x + point.r;
            point2.y = point.y + point.r;
            arraylist1.add(point2);
            point2 = new Point();
            point2.x = point.x - point.r;
            point2.y = point.y + point.r;
            arraylist1.add(point2);
            if(point.fill_shape)
                drawPath(arraylist1, 'f');
            else
                drawPath(arraylist1, 's');
        } else
        if(point.shape == 3)
        {
            drawLine(point.x - point.r, point.y, point.x + point.r, point.y);
            drawLine(point.x, point.y - point.r, point.x, point.y + point.r);
        } else
        if(point.shape == 9)
        {
            ArrayList arraylist2 = new ArrayList();
            Point point3 = new Point();
            point3.x = point.x;
            point3.y = point.y - point.r;
            arraylist2.add(point3);
            point3 = new Point();
            point3.x = point.x + point.r;
            point3.y = point.y + point.r;
            arraylist2.add(point3);
            point3 = new Point();
            point3.x = point.x - point.r;
            point3.y = point.y + point.r;
            arraylist2.add(point3);
            if(point.fill_shape)
                drawPath(arraylist2, 'f');
            else
                drawPath(arraylist2, 's');
        } else
        if(point.shape == 10)
        {
            ArrayList arraylist3 = new ArrayList();
            Point point4 = new Point();
            point4.x = point.x - point.r;
            point4.y = point.y - point.r;
            arraylist3.add(point4);
            point4 = new Point();
            point4.x = point.x + point.r;
            point4.y = point.y - point.r;
            arraylist3.add(point4);
            point4 = new Point();
            point4.x = point.x;
            point4.y = point.y + point.r;
            arraylist3.add(point4);
            if(point.fill_shape)
                drawPath(arraylist3, 'f');
            else
                drawPath(arraylist3, 's');
        } else
        if(point.shape == 11)
        {
            ArrayList arraylist4 = new ArrayList();
            Point point5 = new Point();
            point5.x = point.x + point.r;
            point5.y = point.y + point.r;
            arraylist4.add(point5);
            point5 = new Point();
            point5.x = point.x - point.r;
            point5.y = point.y;
            arraylist4.add(point5);
            point5 = new Point();
            point5.x = point.x + point.r;
            point5.y = point.y - point.r;
            arraylist4.add(point5);
            if(point.fill_shape)
                drawPath(arraylist4, 'f');
            else
                drawPath(arraylist4, 's');
        } else
        if(point.shape == 12)
        {
            ArrayList arraylist5 = new ArrayList();
            Point point6 = new Point();
            point6.x = point.x - point.r;
            point6.y = point.y - point.r;
            arraylist5.add(point6);
            point6 = new Point();
            point6.x = point.x + point.r;
            point6.y = point.y;
            arraylist5.add(point6);
            point6 = new Point();
            point6.x = point.x - point.r;
            point6.y = point.y + point.r;
            arraylist5.add(point6);
            if(point.fill_shape)
                drawPath(arraylist5, 'f');
            else
                drawPath(arraylist5, 's');
        } else
        if(point.shape == 4)
            drawLine(point.x - point.r, point.y, point.x + point.r, point.y);
        else
        if(point.shape == 5)
            drawLine(point.x, point.y - point.r, point.x, point.y + point.r);
        else
        if(point.shape == 8)
        {
            drawLine(point.x - point.r, point.y - point.r, point.x + point.r, point.y + point.r);
            drawLine(point.x - point.r, point.y + point.r, point.x + point.r, point.y - point.r);
        } else
        if(point.shape == 6)
        {
            drawLine(point.x - point.r, point.y - point.r, point.x + point.r, point.y + point.r);
            drawLine(point.x - point.r, point.y + point.r, point.x + point.r, point.y - point.r);
            drawLine(point.x - point.r, point.y, point.x + point.r, point.y);
            drawLine(point.x, point.y - point.r, point.x, point.y + point.r);
        } else
        if(point.shape == 7)
        {
            double d = 0.31415926535897931D;
            double d1 = Math.sin(d);
            double d2 = Math.cos(d);
            double d3 = point.r * d2;
            double d4 = point.r * d1;
            double d5 = 2D * d3 * d1;
            double d6 = 2D * d3 * d2 - point.r;
            ArrayList arraylist6 = new ArrayList();
            Point point7 = new Point();
            point7.x = point.x;
            point7.y = point.y - point.r;
            arraylist6.add(point7);
            point7 = new Point();
            point7.x = point.x + d5;
            point7.y = point.y + d6;
            arraylist6.add(point7);
            point7 = new Point();
            point7.x = point.x - d3;
            point7.y = point.y - d4;
            arraylist6.add(point7);
            point7 = new Point();
            point7.x = point.x + d3;
            point7.y = point.y - d4;
            arraylist6.add(point7);
            point7 = new Point();
            point7.x = point.x - d5;
            point7.y = point.y + d6;
            arraylist6.add(point7);
            if(point.fill_shape)
                drawPath(arraylist6, 'f');
            else
                drawPath(arraylist6, 's');
        }
    }

    protected void setTextRenderingMode(int i)
        throws Exception
    {
        if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7)
            renderingMode = i;
        else
            throw new Exception((new StringBuilder()).append("Invalid text rendering mode: ").append(i).toString());
    }

    protected void setTextDirection(int i)
        throws Exception
    {
        if(i > 360)
            i %= 360;
        if(i == 0)
            writingMode = "1 0 0 1 ";
        else
        if(i == 90)
            writingMode = "0 1 -1 0 ";
        else
        if(i == 180)
            writingMode = "-1 0 0 -1 ";
        else
        if(i == 270)
            writingMode = "0 -1 1 0 ";
        else
        if(i == 360)
        {
            writingMode = "1 0 0 1 ";
        } else
        {
            double d = Math.sin((double)i * 0.017453292519943295D);
            double d1 = Math.cos((double)i * 0.017453292519943295D);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(d1);
            stringbuilder.append(' ');
            stringbuilder.append(d);
            stringbuilder.append(' ');
            stringbuilder.append(-d);
            stringbuilder.append(' ');
            stringbuilder.append(d1);
            stringbuilder.append(' ');
            writingMode = stringbuilder.toString().replace(',', '.');
        }
    }

    protected void append(String s)
        throws IOException
    {
        for(int i = 0; i < s.length(); i++)
            buf.write((byte)s.charAt(i));

    }

    protected void append(int i)
        throws IOException
    {
        append(String.valueOf(i));
    }

    protected void append(double d)
        throws IOException
    {
        append(String.valueOf(d).replace(',', '.'));
    }

    protected void append(char c)
        throws IOException
    {
        buf.write((byte)c);
    }

    protected void append(byte byte0)
        throws IOException
    {
        buf.write(byte0);
    }

    protected ByteArrayOutputStream buf;
    protected String writingMode;
    protected int renderingMode;
    protected double width;
    protected double height;
    protected List annots;
    protected PDF pdf;
    private double pen_color[] = {
        0.0D, 0.0D, 0.0D
    };
    private double brush_color[] = {
        0.0D, 0.0D, 0.0D
    };
    private double pen_width;
    private String line_pattern;
}