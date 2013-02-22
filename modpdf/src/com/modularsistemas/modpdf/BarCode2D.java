package com.modularsistemas.modpdf;

import java.util.ArrayList;
import java.util.List;


public class BarCode2D
{

    public BarCode2D(String s)
    {
        x1 = 0.0D;
        y1 = 0.0D;
        w1 = 0.75D;
        h1 = 0.0D;
        rows = 50;
        cols = 18;
        codewords = null;
        str = null;
        str = s;
        h1 = 3D * w1;
        codewords = new int[rows * (cols + 2)];
        int ai[] = new int[rows];
        int ai1[] = new int[rows];
        int ai2[] = new int[rows * cols];
        byte byte0 = 5;
        int i = 1;
        for(int j = 0; j < rows; j++)
        {
            int l = 0;
            int k1 = 0;
            int i2 = 30 * (j / 3);
            if(i == 1)
            {
                l = i2 + (rows - 1) / 3;
                k1 = i2 + (cols - 1);
            } else
            if(i == 2)
            {
                l = i2 + 3 * byte0 + (rows - 1) % 3;
                k1 = i2 + (rows - 1) / 3;
            } else
            if(i == 3)
            {
                l = i2 + (cols - 1);
                k1 = i2 + 3 * byte0 + (rows - 1) % 3;
            }
            ai[j] = l;
            ai1[j] = k1;
            if(++i == 4)
                i = 1;
        }

        int k = rows * cols - ECC_L5.table.length;
        for(int i1 = 0; i1 < k; i1++)
            ai2[i1] = 900;

        ai2[0] = k;
        addData(ai2, k);
        addECC(ai2);
        for(int j1 = 0; j1 < rows; j1++)
        {
            int l1 = (cols + 2) * j1;
            codewords[l1] = ai[j1];
            for(int j2 = 0; j2 < cols; j2++)
                codewords[l1 + j2 + 1] = ai2[cols * j1 + j2];

            codewords[l1 + cols + 1] = ai1[j1];
        }

    }

    public void setPosition(double d, double d1)
    {
        x1 = d;
        y1 = d1;
    }

    public void drawOn(Page page)
        throws Exception
    {
        drawPdf417(page);
    }

    private List convertTheStringToListOfValues()
    {
        ArrayList arraylist = new ArrayList();
        boolean flag = false;
        int j = 8;
        boolean flag1 = false;
        boolean flag2 = false;
        for(int l = 0; l < str.length(); l++)
        {
            char c = str.charAt(l);
            if(c == ' ')
            {
                arraylist.add(Integer.valueOf(26));
                continue;
            }
            int i = TextCompact.TABLE[c][1];
            int k = TextCompact.TABLE[c][2];
            if(k == j)
            {
                arraylist.add(Integer.valueOf(i));
                continue;
            }
            if(k == 8 && j == 4)
            {
                arraylist.add(Integer.valueOf(27));
                arraylist.add(Integer.valueOf(i));
                continue;
            }
            if(k == 8 && j == 2)
            {
                arraylist.add(Integer.valueOf(28));
                arraylist.add(Integer.valueOf(i));
                j = k;
                continue;
            }
            if(k == 4 && j == 8)
            {
                arraylist.add(Integer.valueOf(27));
                arraylist.add(Integer.valueOf(i));
                j = k;
                continue;
            }
            if(k == 4 && j == 2)
            {
                arraylist.add(Integer.valueOf(27));
                arraylist.add(Integer.valueOf(i));
                j = k;
                continue;
            }
            if(k == 2 && j == 8)
            {
                arraylist.add(Integer.valueOf(28));
                arraylist.add(Integer.valueOf(i));
                j = k;
                continue;
            }
            if(k == 2 && j == 4)
            {
                arraylist.add(Integer.valueOf(28));
                arraylist.add(Integer.valueOf(i));
                j = k;
                continue;
            }
            if(k == 1 && j == 8)
            {
                arraylist.add(Integer.valueOf(29));
                arraylist.add(Integer.valueOf(i));
                continue;
            }
            if(k == 1 && j == 4)
            {
                arraylist.add(Integer.valueOf(29));
                arraylist.add(Integer.valueOf(i));
                continue;
            }
            if(k == 1 && j == 2)
            {
                arraylist.add(Integer.valueOf(29));
                arraylist.add(Integer.valueOf(i));
            }
        }

        return arraylist;
    }

    private void addData(int ai[], int i)
    {
        List list = convertTheStringToListOfValues();
        int j = 1;
        boolean flag = false;
        boolean flag1 = false;
        int i1 = 0;
        do
        {
            if(i1 >= list.size())
                break;
            int k = ((Integer)list.get(i1)).intValue();
            int l;
            if(i1 + 1 == list.size())
                l = 29;
            else
                l = ((Integer)list.get(i1 + 1)).intValue();
            if(++j == i)
                break;
            ai[j] = 30 * k + l;
            i1 += 2;
        } while(true);
    }

    private void addECC(int ai[])
    {
        int ai1[] = new int[ECC_L5.table.length];
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        int j1 = ai.length - ai1.length;
        for(int k1 = 0; k1 < j1; k1++)
        {
            int i = (ai[k1] + ai1[ai1.length - 1]) % 929;
            for(int i2 = ai1.length - 1; i2 > 0; i2--)
            {
                int j = (i * ECC_L5.table[i2]) % 929;
                int l = 929 - j;
                ai1[i2] = (ai1[i2 - 1] + l) % 929;
            }

            int k = (i * ECC_L5.table[0]) % 929;
            int i1 = 929 - k;
            ai1[0] = i1 % 929;
        }

        for(int l1 = 0; l1 < ai1.length; l1++)
            if(ai1[l1] != 0)
                ai[ai.length - 1 - l1] = 929 - ai1[l1];

    }

    private void drawPdf417(Page page)
        throws Exception
    {
        double d = x1;
        double d1 = y1;
        int ai[] = {
            8, 1, 1, 1, 1, 1, 1, 3
        };
        for(int i = 0; i < ai.length; i++)
        {
            int k = ai[i];
            if(i % 2 == 0)
                drawBar(page, d, d1, (double)k * w1, (double)rows * h1);
            d += (double)k * w1;
        }

        x1 = d;
        int j = 1;
        for(int l = 0; l < codewords.length; l++)
        {
            int i1 = codewords[l];
            String s = String.valueOf(PDF417.TABLE[i1][j]);
            for(int l1 = 0; l1 < 8; l1++)
            {
                int i2 = s.charAt(l1) - 48;
                if(l1 % 2 == 0)
                    drawBar(page, d, d1, (double)i2 * w1, h1);
                d += (double)i2 * w1;
            }

            if(l == codewords.length - 1)
                break;
            if((l + 1) % (cols + 2) != 0)
                continue;
            d = x1;
            d1 += h1;
            if(++j == 4)
                j = 1;
        }

        d1 = y1;
        int ai1[] = {
            7, 1, 1, 3, 1, 1, 1, 2, 1
        };
        for(int j1 = 0; j1 < ai1.length; j1++)
        {
            int k1 = ai1[j1];
            if(j1 % 2 == 0)
                drawBar(page, d, d1, (double)k1 * w1, (double)rows * h1);
            d += (double)k1 * w1;
        }

    }

    private void drawBar(Page page, double d, double d1, double d2, 
            double d3)
        throws Exception
    {
        page.setPenWidth(d2);
        page.moveTo(d + d2 / 2D, d1);
        page.lineTo(d + d2 / 2D, d1 + d3);
        page.strokePath();
    }

    private static final int ALPHA = 8;
    private static final int LOWER = 4;
    private static final int MIXED = 2;
    private static final int PUNCT = 1;
    private static final int LATCH_TO_LOWER = 27;
    private static final int SHIFT_TO_ALPHA = 27;
    private static final int LATCH_TO_MIXED = 28;
    private static final int LATCH_TO_ALPHA = 28;
    private static final int SHIFT_TO_PUNCT = 29;
    private double x1;
    private double y1;
    private double w1;
    private double h1;
    private int rows;
    private int cols;
    private int codewords[];
    private String str;
}