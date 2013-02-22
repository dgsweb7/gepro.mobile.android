
package com.modularsistemas.modpdf;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

public class Chart
{

    public Chart(Font font, Font font1)
    {
        w = 300D;
        h = 200D;
        x1 = 0.0D;
        y1 = 0.0D;
        x2 = 0.0D;
        y2 = 0.0D;
        x3 = 0.0D;
        y3 = 0.0D;
        x4 = 0.0D;
        y4 = 0.0D;
        x5 = 0.0D;
        y5 = 0.0D;
        x6 = 0.0D;
        y6 = 0.0D;
        x7 = 0.0D;
        y7 = 0.0D;
        x8 = 0.0D;
        y8 = 0.0D;
        x_max = 0.0D;
        x_min = 0.0D;
        y_max = 0.0D;
        y_min = 0.0D;
        x_axis_grid_lines = 0;
        y_axis_grid_lines = 0;
        title = "";
        x_axis_title = "";
        y_axis_title = "";
        h_grid_line_width = 0.0D;
        v_grid_line_width = 0.0D;
        h_grid_line_pattern = "[1 1] 0";
        v_grid_line_pattern = "[1 1] 0";
        chart_border_width = 0.29999999999999999D;
        inner_border_width = 0.29999999999999999D;
        nf = null;
        minFractionDigits = 2;
        maxFractionDigits = 2;
        f1 = null;
        f2 = null;
        chartData = null;
        f1 = font;
        f2 = font1;
        nf = NumberFormat.getInstance();
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setXAxisTitle(String s)
    {
        x_axis_title = s;
    }

    public void setYAxisTitle(String s)
    {
        y_axis_title = s;
    }

    public void setData(List list)
    {
        chartData = list;
    }

    public List getData()
    {
        return chartData;
    }

    public void setPosition(double d, double d1)
    {
        x1 = d;
        y1 = d1;
    }

    public void setSize(double d, double d1)
    {
        w = d;
        h = d1;
    }

    public void setMinimumFractionDigits(int i)
    {
        minFractionDigits = i;
    }

    public void setMaximumFractionDigits(int i)
    {
        maxFractionDigits = i;
    }

    public void drawOn(Page page)
        throws Exception
    {
        nf.setMinimumFractionDigits(minFractionDigits);
        nf.setMaximumFractionDigits(maxFractionDigits);
        x2 = x1 + w;
        y2 = y1;
        x3 = x2;
        y3 = y1 + h;
        x4 = x1;
        y4 = y3;
        if(x_min == 0.0D && x_max == 0.0D && y_min == 0.0D && y_max == 0.0D)
        {
            setMinAndMaxChartValues();
            roundXAxisMinAndMaxValues();
            roundYAxisMinAndMaxValues();
        }
        page.drawString(f1, title, x1 + (w - f1.stringWidth(title)) / 2D, y1 + 1.5D * f1.body_height);
        double d = 2.5D * f1.body_height;
        double d1 = getLongestAxisYLabelWidth() + 2D * f2.body_height;
        double d2 = 2D * f2.body_height;
        double d3 = 2.5D * f2.body_height;
        x5 = x1 + d1;
        y5 = y1 + d;
        x6 = x2 - d2;
        y6 = y5;
        x7 = x6;
        y7 = y3 - d3;
        x8 = x5;
        y8 = y7;
        drawChartBorder(page);
        drawInnerBorder(page);
        drawHorizontalGridLines(page);
        drawXAxisLabels(page);
        drawYAxisLabels(page);
        drawVerticalGridLines(page);
        for(int i = 0; i < chartData.size(); i++)
        {
            List list = (List)chartData.get(i);
            for(int j = 0; j < list.size(); j++)
            {
                Point point = (Point)list.get(j);
                point.x = x5 + ((point.x - x_min) * (x6 - x5)) / (x_max - x_min);
                point.y = y8 - ((point.y - y_min) * (y8 - y5)) / (y_max - y_min);
                if(point.uri != null)
                    page.annots.add(new Annotation(point.uri, point.x - point.r, page.height - (point.y - point.r), point.x + point.r, page.height - (point.y + point.r)));
            }

        }

        drawPoints(page, chartData);
        drawLines(page, chartData);
        page.setBrushColor(0.0D, 0.0D, 0.0D);
        page.setTextDirection(90);
        page.drawString(f2, y_axis_title, x1 + f2.body_height, y8 - (y8 - y5 - f2.stringWidth(y_axis_title)) / 2D);
        page.setTextDirection(0);
        page.drawString(f2, x_axis_title, x5 + (x6 - x5 - f2.stringWidth(x_axis_title)) / 2D, y4 - f2.body_height / 2D);
        page.setDefaultLineWidth();
        page.setDefaultLinePattern();
    }

    private double getLongestAxisYLabelWidth()
    {
        double d = f2.stringWidth((new StringBuilder()).append(nf.format(y_min)).append("0").toString());
        double d1 = f2.stringWidth((new StringBuilder()).append(nf.format(y_max)).append("0").toString());
        if(d1 > d)
            return d1;
        else
            return d;
    }

    private void setMinAndMaxChartValues()
    {
        for(int i = 0; i < chartData.size(); i++)
        {
            List list = (List)chartData.get(i);
            for(int j = 0; j < list.size(); j++)
            {
                Point point = (Point)list.get(j);
                if(point.x < x_min)
                    x_min = point.x;
                if(point.x > x_max)
                    x_max = point.x;
                if(point.y < y_min)
                    y_min = point.y;
                if(point.y > y_max)
                    y_max = point.y;
            }

        }

    }

    private void roundXAxisMinAndMaxValues()
    {
        double d = Math.abs(x_min);
        double d1 = Math.abs(x_max);
        if(x_max > 0.0D && x_min >= 0.0D)
        {
            Round round = roundUp(x_max);
            x_axis_grid_lines = round.num_of_grid_lines;
            x_max = round.value * Math.pow(10D, round.exponent);
            x_min = 0.0D;
        } else
        if(x_max <= 0.0D && x_min < 0.0D)
        {
            Round round1 = roundUp(d);
            x_axis_grid_lines = round1.num_of_grid_lines;
            x_max = 0.0D;
            x_min = -(round1.value * Math.pow(10D, round1.exponent));
        } else
        if(x_min < 0.0D && x_max > 0.0D)
            if(d1 >= d)
            {
                Round round2 = roundUp(d1);
                x_axis_grid_lines = round2.num_of_grid_lines;
                x_max = round2.value * Math.pow(10D, round2.exponent);
                double d2 = x_max / (double)x_axis_grid_lines;
                double d4 = 0.0D;
                do
                {
                    x_axis_grid_lines++;
                    d4 -= d2;
                } while(d4 > x_min);
                x_min = d4;
            } else
            if(d1 < d)
            {
                Round round3 = roundUp(d);
                x_axis_grid_lines = round3.num_of_grid_lines;
                x_min = -(round3.value * Math.pow(10D, round3.exponent));
                double d3 = x_min / (double)x_axis_grid_lines;
                double d5 = 0.0D;
                do
                {
                    x_axis_grid_lines++;
                    d5 -= d3;
                } while(d5 < x_max);
                x_max = d5;
            }
    }

    private void roundYAxisMinAndMaxValues()
    {
        double d = Math.abs(y_min);
        double d1 = Math.abs(y_max);
        if(y_max > 0.0D && y_min >= 0.0D)
        {
            Round round = roundUp(y_max);
            y_axis_grid_lines = round.num_of_grid_lines;
            y_max = round.value * Math.pow(10D, round.exponent);
            y_min = 0.0D;
        } else
        if(y_max <= 0.0D && y_min < 0.0D)
        {
            Round round1 = roundUp(d);
            y_axis_grid_lines = round1.num_of_grid_lines;
            y_max = 0.0D;
            y_min = -(round1.value * Math.pow(10D, round1.exponent));
        } else
        if(y_min < 0.0D && y_max > 0.0D)
            if(d1 >= d)
            {
                Round round2 = roundUp(d1);
                y_axis_grid_lines = round2.num_of_grid_lines;
                y_max = round2.value * Math.pow(10D, round2.exponent);
                double d2 = y_max / (double)y_axis_grid_lines;
                double d4 = 0.0D;
                do
                {
                    y_axis_grid_lines++;
                    d4 -= d2;
                } while(d4 > y_min);
                y_min = d4;
            } else
            if(d1 < d)
            {
                Round round3 = roundUp(d);
                y_axis_grid_lines = round3.num_of_grid_lines;
                y_min = -(round3.value * Math.pow(10D, round3.exponent));
                double d3 = y_min / (double)y_axis_grid_lines;
                double d5 = 0.0D;
                do
                {
                    y_axis_grid_lines++;
                    d5 -= d3;
                } while(d5 < y_max);
                y_max = d5;
            }
    }

    private void drawChartBorder(Page page)
        throws Exception
    {
        page.setPenWidth(chart_border_width);
        page.setPenColor(0.0D, 0.0D, 0.0D);
        page.setDefaultLinePattern();
        page.moveTo(x1, y1);
        page.lineTo(x2, y2);
        page.lineTo(x3, y3);
        page.lineTo(x4, y4);
        page.closePath();
        page.strokePath();
    }

    private void drawInnerBorder(Page page)
        throws Exception
    {
        page.setPenWidth(inner_border_width);
        page.setPenColor(0.0D, 0.0D, 0.0D);
        page.setDefaultLinePattern();
        page.moveTo(x5, y5);
        page.lineTo(x6, y6);
        page.lineTo(x7, y7);
        page.lineTo(x8, y8);
        page.closePath();
        page.strokePath();
    }

    private void drawHorizontalGridLines(Page page)
        throws Exception
    {
        page.setPenWidth(h_grid_line_width);
        page.setPenColor(0.0D, 0.0D, 0.0D);
        page.setLinePattern(h_grid_line_pattern);
        double d = x8;
        double d1 = y8;
        double d2 = (y8 - y5) / (double)y_axis_grid_lines;
        for(int i = 0; i < y_axis_grid_lines; i++)
        {
            page.drawLine(d, d1, x6, d1);
            d1 -= d2;
        }

        page.setDefaultLinePattern();
    }

    private void drawVerticalGridLines(Page page)
        throws Exception
    {
        page.setPenWidth(v_grid_line_width);
        page.setPenColor(0.0D, 0.0D, 0.0D);
        page.setLinePattern(v_grid_line_pattern);
        double d = x5;
        double d1 = y5;
        double d2 = (x6 - x5) / (double)x_axis_grid_lines;
        for(int i = 0; i < x_axis_grid_lines; i++)
        {
            page.drawLine(d, d1, d, y8);
            d += d2;
        }

        page.setDefaultLinePattern();
    }

    private void drawXAxisLabels(Page page)
        throws Exception
    {
        double d = x5;
        double d1 = y8 + f2.body_height;
        double d2 = (x6 - x5) / (double)x_axis_grid_lines;
        for(int i = 0; i < x_axis_grid_lines + 1; i++)
        {
            String s = nf.format(x_min + ((x_max - x_min) / (double)x_axis_grid_lines) * (double)i);
            page.drawString(f2, s, d - f2.stringWidth(s) / 2D, d1);
            d += d2;
        }

    }

    private void drawYAxisLabels(Page page)
        throws Exception
    {
        double d = x5 - getLongestAxisYLabelWidth();
        double d1 = y8 + f2.ascent / 3D;
        double d2 = (y8 - y5) / (double)y_axis_grid_lines;
        for(int i = 0; i < y_axis_grid_lines + 1; i++)
        {
            String s = nf.format(y_min + ((y_max - y_min) / (double)y_axis_grid_lines) * (double)i);
            page.drawString(f2, s, d, d1);
            d1 -= d2;
        }

    }

    private void drawPoints(Page page, List list)
        throws Exception
    {
        for(int i = 0; i < list.size(); i++)
        {
            List list1 = (List)list.get(i);
            for(int j = 0; j < list1.size(); j++)
            {
                Point point = (Point)list1.get(j);
                page.setPenWidth(point.line_width);
                page.setLinePattern(point.line_pattern);
                page.setPenColor(point.color[0], point.color[1], point.color[2]);
                page.setBrushColor(point.color[0], point.color[1], point.color[2]);
                page.drawPoint(point);
            }

        }

        page.setDefaultLinePattern();
    }

    private void drawLines(Page page, List list)
        throws Exception
    {
        for(int i = 0; i < list.size(); i++)
        {
            int j = 0;
            List list1 = (List)list.get(i);
            for(int k = 0; k < list1.size(); k++)
            {
                Point point = (Point)list1.get(k);
                if(!point.drawLineTo)
                    continue;
                if(j == 0)
                {
                    page.moveTo(point.x, point.y);
                    page.setPenWidth(point.line_width);
                    page.setLinePattern(point.line_pattern);
                    page.setPenColor(point.color[0], point.color[1], point.color[2]);
                } else
                {
                    page.lineTo(point.x, point.y);
                }
                j++;
            }

            if(j > 1)
                page.strokePath();
        }

        page.setPenWidth(0.0D);
        page.setDefaultLinePattern();
        page.setPenColor(0.0D, 0.0D, 0.0D);
    }

    private void drawBars(Page page, List list)
        throws IOException
    {
        for(int i = 0; i < list.size(); i++)
        {
            List list1 = (List)list.get(i);
            for(int j = 0; j < list1.size(); j++)
            {
                Point point = (Point)list1.get(j);
                if(point.text != null)
                    page.drawString(f2, point.text, x5, point.y - 5D);
                page.setPenWidth(2D * point.r);
                page.setPenColor(0.0D, 0.0D, 0.0D);
                page.drawLine(x5, point.y, point.x, point.y);
                page.setPenWidth(2D * point.r - 1.0D);
                page.setPenColor(point.color[0], point.color[1], point.color[2]);
                page.drawLine(x5 + inner_border_width, point.y, point.x - 0.5D <= x5 ? x5 : point.x - 0.5D, point.y);
                page.setPenColor(0.0D, 0.0D, 0.0D);
            }

        }

    }

    private Round roundUp(double d)
    {
        int i = (int)Math.floor(Math.log(d) / Math.log(10D));
        d *= Math.pow(10D, -i);
        Round round = new Round(1.0D, i, 10);
        if(d > 9D)
            round = new Round(10D, i, 10);
        else
        if(d > 8D)
            round = new Round(9D, i, 9);
        else
        if(d > 7D)
            round = new Round(8D, i, 8);
        else
        if(d > 6D)
            round = new Round(7D, i, 7);
        else
        if(d > 5D)
            round = new Round(6D, i, 6);
        else
        if(d > 4D)
            round = new Round(5D, i, 5);
        else
        if(d > 3.5D)
            round = new Round(4D, i, 8);
        else
        if(d > 3D)
            round = new Round(3.5D, i, 7);
        else
        if(d > 2.5D)
            round = new Round(3D, i, 6);
        else
        if(d > 2D)
            round = new Round(2.5D, i, 5);
        else
        if(d > 1.75D)
            round = new Round(2D, i, 8);
        else
        if(d > 1.5D)
            round = new Round(1.75D, i, 7);
        else
        if(d > 1.25D)
            round = new Round(1.5D, i, 6);
        else
        if(d > 1.0D)
            round = new Round(1.25D, i, 5);
        return round;
    }

    private double[] mean(List list)
    {
        double ad[] = new double[2];
        for(int i = 0; i < list.size(); i++)
        {
            Point point = (Point)list.get(i);
            ad[0] += point.x;
            ad[1] += point.y;
        }

        ad[0] /= list.size() - 1;
        ad[1] /= list.size() - 1;
        return ad;
    }

    private double covar(List list)
    {
        double d = 0.0D;
        double ad[] = mean(list);
        for(int i = 0; i < list.size(); i++)
        {
            Point point = (Point)list.get(i);
            d += (point.x - ad[0]) * (point.y - ad[1]);
        }

        return d / (double)(list.size() - 1);
    }

    private double devsq(List list)
    {
        double d = 0.0D;
        double ad[] = mean(list);
        for(int i = 0; i < list.size(); i++)
        {
            Point point = (Point)list.get(i);
            d += Math.pow(point.x - ad[0], 2D);
        }

        return d;
    }

    public double slope(List list)
    {
        return (covar(list) / devsq(list)) * (double)(list.size() - 1);
    }

    public double intercept(List list, double d)
    {
        double ad[] = mean(list);
        return ad[1] - d * ad[0];
    }

    private double w;
    private double h;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double x4;
    private double y4;
    private double x5;
    private double y5;
    private double x6;
    private double y6;
    private double x7;
    private double y7;
    private double x8;
    private double y8;
    private double x_max;
    private double x_min;
    private double y_max;
    private double y_min;
    private int x_axis_grid_lines;
    private int y_axis_grid_lines;
    private String title;
    private String x_axis_title;
    private String y_axis_title;
    private double h_grid_line_width;
    private double v_grid_line_width;
    private String h_grid_line_pattern;
    private String v_grid_line_pattern;
    private double chart_border_width;
    private double inner_border_width;
    private NumberFormat nf;
    private int minFractionDigits;
    private int maxFractionDigits;
    private Font f1;
    private Font f2;
    private List chartData;
}