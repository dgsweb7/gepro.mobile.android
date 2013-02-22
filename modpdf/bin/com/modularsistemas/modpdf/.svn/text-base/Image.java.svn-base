package com.modularsistemas.modpdf;

import java.io.InputStream;
import java.util.List;

public class Image
{
	public Image( InputStream inputstream )
	{
		
	}

    public Image(PDF pdf, InputStream inputstream, int i)
        throws Exception
    {
        objNumber = 0;
        x = 0.0D;
        y = 0.0D;
        w = 0.0D;
        h = 0.0D;
        box_x = 0.0D;
        box_y = 0.0D;
        data = null;
        if(i == 1)
        {
            JPEGImage jpegimage = new JPEGImage(inputstream);
            data = jpegimage.getData();
            w = jpegimage.getWidth();
            h = jpegimage.getHeight();
            if(jpegimage.getColorComponents() == 1)
                addThisImageTo(pdf, data, i, "DeviceGray", 8);
            else
            if(jpegimage.getColorComponents() == 3)
                addThisImageTo(pdf, data, i, "DeviceRGB", 8);
        } else
        if(i == 2)
        {
            PNGImage pngimage = new PNGImage(inputstream);
            data = pngimage.getData();
            w = pngimage.getWidth();
            h = pngimage.getHeight();
            if(pngimage.colorType == 0)
                addThisImageTo(pdf, data, i, "DeviceGray", pngimage.bitDepth);
            else
                addThisImageTo(pdf, data, i, "DeviceRGB", 8);
        } else
        if(i == 3)
        {
            BMPImage bmpimage = new BMPImage(inputstream);
            data = bmpimage.getData();
            w = bmpimage.getWidth();
            h = bmpimage.getHeight();
            addThisImageTo(pdf, data, i, "DeviceRGB", 8);
        }
        inputstream.close();
    }

    public void setPosition(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public void scaleBy(double d)
    {
        w *= d;
        h *= d;
    }

    public void placeIn(Box box)
        throws Exception
    {
        box_x = box.x;
        box_y = box.y;
    }

    public void drawOn(Page page)
        throws Exception
    {
        x += box_x;
        y += box_y;
        page.append("q\n");
        page.append(w);
        page.append(" 0 0 ");
        page.append(h);
        page.append(' ');
        page.append(x);
        page.append(' ');
        page.append(page.height - y - h);
        page.append(" cm\n");
        page.append("/Im");
        page.append(objNumber);
        page.append(" Do\n");
        page.append("Q\n");
    }
    


    private void addThisImageTo(PDF pdf, byte abyte0[], int i, String s, int j)
        throws Exception
    {
        pdf.newobj();
        pdf.append("<<\n");
        pdf.append("/Type /XObject\n");
        pdf.append("/Subtype /Image\n");
        if(i == 1)
            pdf.append("/Filter /DCTDecode\n");
        else
        if(i == 2 || i == 3)
            pdf.append("/Filter /FlateDecode\n");
        pdf.append("/Width ");
        pdf.append((int)w);
        pdf.append('\n');
        pdf.append("/Height ");
        pdf.append((int)h);
        pdf.append('\n');
        pdf.append("/ColorSpace /");
        pdf.append(s);
        pdf.append('\n');
        pdf.append("/BitsPerComponent ");
        pdf.append(j);
        pdf.append('\n');
        pdf.append("/Length ");
        pdf.append(abyte0.length);
        pdf.append('\n');
        pdf.append(">>\n");
        pdf.append("stream\n");
        pdf.append(abyte0, 0, abyte0.length);
        pdf.append("\nendstream\n");
        pdf.endobj();
        pdf.images.add(this);
        objNumber = pdf.objNumber;
    }
    
    public double getWidth()
    {
    	return w;
    }
    
    public double getHeigth()
    {
    	return h;
    }


    protected int objNumber;
    protected double x;
    protected double y;
    protected double w;
    protected double h;
    private double box_x;
    private double box_y;
    private byte data[];
}