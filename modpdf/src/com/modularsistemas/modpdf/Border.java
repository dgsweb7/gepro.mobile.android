package com.modularsistemas.modpdf;


public class Border
{

    public Border()
    {
        top = true;
        bottom = true;
        left = true;
        right = true;
    }

    public Border(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        top = true;
        bottom = true;
        left = true;
        right = true;
        top = flag;
        left = flag1;
        bottom = flag2;
        right = flag3;
    }

    public boolean top;
    public boolean bottom;
    public boolean left;
    public boolean right;
}