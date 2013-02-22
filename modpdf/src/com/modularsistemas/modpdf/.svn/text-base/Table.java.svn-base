
package com.modularsistemas.modpdf;

import java.util.ArrayList;
import java.util.List;


public class Table
{

    public Table(Font font, Font font1)
    {
        rendered = 0;
        tableData = null;
        numOfHeaderRows = 0;
        x1 = 0.0D;
        y1 = 0.0D;
        w1 = 50D;
        h1 = 30D;
        lineWidth = 0.20000000000000001D;
        lineColor = RGB.BLACK;
        margin = 1.0D;
        bottom_margin = 30D;
        f1 = null;
        f2 = null;
        e = null;
        f1 = font;
        f2 = font1;
        tableData = new ArrayList();
    }

    public void setPosition(double d, double d1)
    {
        x1 = d;
        y1 = d1;
    }

    public void setSize(double d, double d1)
    {
        w1 = d;
        h1 = d1;
    }

    public void setLineWidth(double d)
    {
        lineWidth = d;
    }

    public void setLineColor(double ad[])
    {
        lineColor = ad;
    }

    public void setLineColor(int ai[])
    {
        lineColor = (new double[] {
            (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
        });
    }

    public void setCellPadding(double d)
    {
        margin = d;
    }

    public void setCellMargin(double d)
    {
        margin = d;
    }

    public void setBottomMargin(double d)
    {
        bottom_margin = d;
    }

    public void setData(List list)
        throws Exception
    {
        tableData = list;
    }

    public void setData(List list, int i)
        throws Exception
    {
        tableData = list;
        numOfHeaderRows = i;
        rendered = i;
    }

    public void autoAdjustColumnWidths()
    {
        double ad[] = new double[((List)tableData.get(0)).size()];
        for(int i = 0; i < tableData.size(); i++)
        {
            List list = (List)tableData.get(i);
            for(int k = 0; k < list.size(); k++)
            {
                Cell cell = (Cell)list.get(k);
                if(cell.colspan > 1)
                    continue;
                cell.width = cell.font.stringWidth(cell.text);
                if(ad[k] == 0.0D || cell.width > ad[k])
                    ad[k] = cell.width;
            }

        }

        for(int j = 0; j < tableData.size(); j++)
        {
            List list1 = (List)tableData.get(j);
            for(int l = 0; l < list1.size(); l++)
            {
                Cell cell1 = (Cell)list1.get(l);
                if(ad[l] != 0.0D)
                    cell1.width = ad[l] + 3D * margin;
                else
                    cell1.width = cell1.font.body_height;
            }

        }

    }

    public void rightAlignNumbers()
    {
        for(int i = numOfHeaderRows; i < tableData.size(); i++)
        {
            List list = (List)tableData.get(i);
            for(int j = 0; j < list.size(); j++)
            {
                Cell cell = (Cell)list.get(j);
                try
                {
                    Double.valueOf(cell.text.replace(",", ""));
                    cell.align = 2;
                }
                catch(Exception exception)
                {
                    e = exception;
                }
            }

        }

    }

    public void removeLineBetweenRows(int i, int j)
    {
        List list = (List)tableData.get(i);
        Object obj = null;
        for(int k = 0; k < list.size(); k++)
        {
            Cell cell = (Cell)list.get(k);
            cell.border.bottom = false;
        }

        list = (List)tableData.get(j);
        for(int l = 0; l < list.size(); l++)
        {
            Cell cell1 = (Cell)list.get(l);
            cell1.border.top = false;
        }

    }

    public void setTextAlignInColumn(int i, int j)
        throws Exception
    {
        for(int k = 0; k < tableData.size(); k++)
        {
            List list = (List)tableData.get(k);
            if(i < list.size())
                ((Cell)list.get(i)).align = j;
        }

    }

    public void setTextColorInColumn(int i, double ad[])
        throws Exception
    {
        for(int j = 0; j < tableData.size(); j++)
        {
            List list = (List)tableData.get(j);
            if(i < list.size())
                ((Cell)list.get(i)).brushColor = ad;
        }

    }

    public void setTextColorInColumn(int i, int ai[])
        throws Exception
    {
        for(int j = 0; j < tableData.size(); j++)
        {
            List list = (List)tableData.get(j);
            if(i < list.size())
                ((Cell)list.get(i)).brushColor = (new double[] {
                    (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
                });
        }

    }

    public void setTextFontInColumn(int i, Font font, double d)
        throws Exception
    {
        for(int j = 0; j < tableData.size(); j++)
        {
            List list = (List)tableData.get(j);
            if(i < list.size())
            {
                ((Cell)list.get(i)).font = font;
                ((Cell)list.get(i)).font.setSize(d);
            }
        }

    }

    public void setTextColorInRow(int i, double ad[])
        throws Exception
    {
        List list = (List)tableData.get(i);
        for(int j = 0; j < list.size(); j++)
            ((Cell)list.get(j)).brushColor = ad;

    }

    public void setTextColorInRow(int i, int ai[])
        throws Exception
    {
        List list = (List)tableData.get(i);
        for(int j = 0; j < list.size(); j++)
            ((Cell)list.get(j)).brushColor = (new double[] {
                (double)ai[0] / 255D, (double)ai[1] / 255D, (double)ai[2] / 255D
            });

    }

    public void setTextFontInRow(int i, Font font, double d)
        throws Exception
    {
        List list = (List)tableData.get(i);
        for(int j = 0; j < list.size(); j++)
        {
            ((Cell)list.get(j)).font = font;
            ((Cell)list.get(j)).font.setSize(d);
        }

    }

    /**
     * @deprecated Method setWidthForColumn is deprecated
     */

    public void setWidthForColumn(int i, double d)
        throws Exception
    {
        setColumnWidth(i, d);
    }

    public void setColumnWidth(int i, double d)
        throws Exception
    {
        for(int j = 0; j < tableData.size(); j++)
        {
            List list = (List)tableData.get(j);
            if(i < list.size())
                ((Cell)list.get(i)).width = d;
        }

    }

    public Cell getCellAtRowColumn(int i, int j)
        throws Exception
    {
        if(i >= 0)
            return (Cell)((List)tableData.get(i)).get(j);
        else
            return (Cell)((List)tableData.get(tableData.size() + i)).get(j);
    }

    public List getRow(int i)
        throws Exception
    {
        return (List)tableData.get(i);
    }

    public List getColumn(int i)
        throws Exception
    {
        ArrayList arraylist = new ArrayList();
        for(int j = 0; j < tableData.size(); j++)
        {
            List list = (List)tableData.get(j);
            if(i < list.size())
                arraylist.add(list.get(i));
        }

        return arraylist;
    }

    public int getNumberOfPages(Page page)
        throws Exception
    {
        int i = 1;
        int j = numOfHeaderRows;
        double d = 0.0D;
label0:
        do
        {
            if(j != tableData.size())
            {
                double d3 = y1;
                for(int k = 0; k < numOfHeaderRows; k++)
                {
                    Cell cell1 = (Cell)((List)tableData.get(k)).get(0);
                    double d1 = cell1.font.body_height + 2D * margin;
                    d3 += d1;
                }

                do
                {
                    if(j >= tableData.size())
                        continue label0;
                    Cell cell = (Cell)((List)tableData.get(j)).get(0);
                    double d2 = cell.font.body_height + 2D * margin;
                    d3 += d2;
                    if(d3 + d2 > page.height - bottom_margin)
                    {
                        i++;
                        continue label0;
                    }
                    j++;
                } while(true);
            }
            return i;
        } while(true);
    }

    public void drawOn(Page page)
        throws Exception
    {
        double d = x1;
        double d1 = y1;
        double d2 = 0.0D;
        double d5 = 0.0D;
        page.setPenWidth(lineWidth);
        page.setPenColor(lineColor[0], lineColor[1], lineColor[2]);
        for(int i = 0; i < numOfHeaderRows; i++)
        {
            List list = (List)tableData.get(i);
            for(int k = 0; k < list.size(); k++)
            {
                Cell cell = (Cell)list.get(k);
                d5 = cell.font.body_height + 2D * margin;
                double d3 = cell.width;
                for(int i1 = 1; i1 < cell.colspan; i1++)
                    d3 += ((Cell)list.get(++k)).width;

                page.setBrushColor(cell.brushColor[0], cell.brushColor[1], cell.brushColor[2]);
                cell.paint(page, d, d1, d3, d5, margin);
                d += d3;
            }

            d = x1;
            d1 += d5;
        }

        for(int j = rendered; j < tableData.size(); j++)
        {
            List list1 = (List)tableData.get(j);
            for(int l = 0; l < list1.size(); l++)
            {
                Cell cell1 = (Cell)list1.get(l);
                d5 = cell1.font.body_height + 2D * margin;
                double d4 = cell1.width;
                for(int j1 = 1; j1 < cell1.colspan; j1++)
                    d4 += ((Cell)list1.get(++l)).width;

                page.setBrushColor(cell1.brushColor[0], cell1.brushColor[1], cell1.brushColor[2]);
                cell1.paint(page, d, d1, d4, d5, margin);
                d += d4;
            }

            d = x1;
            d1 += d5;
            if(d1 + d5 > page.height - bottom_margin)
            {
                if(j == tableData.size() - 1)
                    rendered = -1;
                else
                    rendered = j + 1;
                return;
            }
        }

        rendered = -1;
    }

    public boolean hasMoreData()
    {
        return rendered != -1;
    }

    public double getWidth()
    {
        double d = 0.0D;
        List list = (List)tableData.get(0);
        for(int i = 0; i < list.size(); i++)
            d += ((Cell)list.get(i)).width;

        return d;
    }

    public double getHeight()
    {
        double d = 0.0D;
        for(int i = 0; i < tableData.size(); i++)
        {
            List list = (List)tableData.get(i);
            d += ((Cell)list.get(0)).height;
        }

        return d;
    }

    public static int DATA_HAS_0_HEADER_ROWS = 0;
    public static int DATA_HAS_1_HEADER_ROWS = 1;
    public static int DATA_HAS_2_HEADER_ROWS = 2;
    public static int DATA_HAS_3_HEADER_ROWS = 3;
    public static int DATA_HAS_4_HEADER_ROWS = 4;
    public static int DATA_HAS_5_HEADER_ROWS = 5;
    public static int DATA_HAS_6_HEADER_ROWS = 6;
    public static int DATA_HAS_7_HEADER_ROWS = 7;
    public static int DATA_HAS_8_HEADER_ROWS = 8;
    public static int DATA_HAS_9_HEADER_ROWS = 9;
    private int rendered;
    private List tableData;
    private int numOfHeaderRows;
    private double x1;
    private double y1;
    private double w1;
    private double h1;
    private double lineWidth;
    private double lineColor[];
    private double margin;
    private double bottom_margin;
    private Font f1;
    private Font f2;
    private Exception e;

}