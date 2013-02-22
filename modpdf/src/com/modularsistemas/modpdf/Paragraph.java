
package com.modularsistemas.modpdf;

import java.util.ArrayList;
import java.util.List;


public class Paragraph
{

    public Paragraph()
    {
        list = null;
        alignment = 0;
        list = new ArrayList();
    }

    public void add(TextLine textline)
    {
        list.add(textline);
    }

    public void setAlignment(int i)
    {
        alignment = i;
    }

    protected List list;
    protected int alignment;
}