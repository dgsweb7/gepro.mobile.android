
package com.modularsistemas.modpdf;

import java.util.zip.CRC32;

public class Chunk
{

    public Chunk()
    {
    }

    public long getLength()
    {
        return chunkLength;
    }

    public void setLength(long l)
    {
        chunkLength = l;
    }

    public void setType(byte abyte0[])
    {
        type = abyte0;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte abyte0[])
    {
        data = abyte0;
    }

    public long getCrc()
    {
        return crc;
    }

    public void setCrc(long l)
    {
        crc = l;
    }

    public boolean hasGoodCRC()
    {
        CRC32 crc32 = new CRC32();
        crc32.update(type, 0, 4);
        crc32.update(data, 0, (int)chunkLength);
        return crc32.getValue() == crc;
    }

    private long chunkLength;
    protected byte type[];
    private byte data[];
    private long crc;
}