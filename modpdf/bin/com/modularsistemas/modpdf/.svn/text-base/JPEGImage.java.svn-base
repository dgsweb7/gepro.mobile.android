package com.modularsistemas.modpdf;

import java.io.*;

public class JPEGImage
{

    public JPEGImage(InputStream inputstream)
        throws Exception
    {
   
        width = 0;
        height = 0;
        colorComponents = 0;
        bais = null;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[2048];
        int i;
        while((i = inputstream.read(abyte0, 0, abyte0.length)) != -1) 
            bytearrayoutputstream.write(abyte0, 0, i);
        data = bytearrayoutputstream.toByteArray();
        bais = new ByteArrayInputStream(data);
        char c = (char)bais.read();
        char c1 = (char)bais.read();
        if(c == '\377' && c1 == '\330')
        {
            boolean flag = false;
            do
            {
                char c2 = nextMarker(inputstream);
                switch(c2)
                {
                case 192: 
                case 193: 
                case 194: 
                case 195: 
                case 197: 
                case 198: 
                case 199: 
                case 201: 
                case 202: 
                case 203: 
                case 205: 
                case 206: 
                case 207: 
                    bais.read();
                    bais.read();
                    bais.read();
                    height = readTwoBytes(inputstream);
                    width = readTwoBytes(inputstream);
                    colorComponents = bais.read();
                    flag = true;
                    break;

                case 196: 
                case 200: 
                case 204: 
                default:
                    skipVariable(inputstream);
                    break;
                }
            } while(!flag);
        } else
        {
            throw new Exception();
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getColorComponents()
    {
        return colorComponents;
    }

    public byte[] getData()
    {
        return data;
    }

    private int readTwoBytes(InputStream inputstream)
        throws Exception
    {
        int i = bais.read();
        i <<= 8;
        i |= bais.read();
        return i;
    }

    private char nextMarker(InputStream inputstream)
        throws Exception
    {
        int i = 0;
        char c = ' ';
        for(c = (char)bais.read(); c != '\377'; c = (char)bais.read())
            i++;

        do
            c = (char)bais.read();
        while(c == '\377');
        if(i != 0)
            throw new Exception();
        else
            return c;
    }

    private void skipVariable(InputStream inputstream)
        throws Exception
    {
        int i = readTwoBytes(inputstream);
        if(i < 2)
            throw new Exception();
        for(i -= 2; i > 0; i--)
            bais.read();

    }

    static final char M_SOF0 = 192;
    static final char M_SOF1 = 193;
    static final char M_SOF2 = 194;
    static final char M_SOF3 = 195;
    static final char M_SOF5 = 197;
    static final char M_SOF6 = 198;
    static final char M_SOF7 = 199;
    static final char M_SOF9 = 201;
    static final char M_SOF10 = 202;
    static final char M_SOF11 = 203;
    static final char M_SOF13 = 205;
    static final char M_SOF14 = 206;
    static final char M_SOF15 = 207;
    static final char M_SOS = 218;
    int width;
    int height;
    int colorComponents;
    ByteArrayInputStream bais;
    byte data[];
}