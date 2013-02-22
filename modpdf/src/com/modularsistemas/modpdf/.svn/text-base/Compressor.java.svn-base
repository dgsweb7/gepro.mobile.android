package com.modularsistemas.modpdf;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class Compressor extends Deflater
{

    public Compressor(byte abyte0[])
    {
        bos = null;
        setInput(abyte0);
        finish();
        bos = new ByteArrayOutputStream(abyte0.length);
        byte abyte1[] = new byte[2048];
        int i;
        for(; !finished(); bos.write(abyte1, 0, i))
            i = deflate(abyte1);

    }

    public byte[] getCompressedData()
    {
        return bos.toByteArray();
    }

    public static boolean ORIGINAL_ZLIB = true;
    ByteArrayOutputStream bos;

}