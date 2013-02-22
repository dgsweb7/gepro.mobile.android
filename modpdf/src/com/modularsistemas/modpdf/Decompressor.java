// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 25/5/2010 18:16:58
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Decompressor.java

package com.modularsistemas.modpdf;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

public class Decompressor extends Inflater
{

    public Decompressor(byte abyte0[])
        throws Exception
    {
        bos = null;
        super.setInput(abyte0);
        bos = new ByteArrayOutputStream(abyte0.length);
        byte abyte1[] = new byte[2048];
        int i;
        for(; !super.finished(); bos.write(abyte1, 0, i))
            i = super.inflate(abyte1);

    }

    public byte[] getDecompressedData()
    {
        return bos.toByteArray();
    }

    private ByteArrayOutputStream bos;
}