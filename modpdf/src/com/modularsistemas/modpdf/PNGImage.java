
package com.modularsistemas.modpdf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class PNGImage
{

    public PNGImage(InputStream inputstream)
        throws Exception
    {
        w = 0;
        h = 0;
        bitDepth = 8;
        colorType = 0;
        validatePNG(inputstream);
        ArrayList arraylist = new ArrayList();
        processPNG(arraylist, inputstream);
        for(int i = 0; i < arraylist.size(); i++)
        {
            Chunk chunk = (Chunk)arraylist.get(i);
            if(chunk.type[0] == 73 && chunk.type[1] == 72 && chunk.type[2] == 68 && chunk.type[3] == 82)
            {
                w = toIntValue(chunk.getData(), 0);
                h = toIntValue(chunk.getData(), 4);
                bitDepth = chunk.getData()[8];
                colorType = chunk.getData()[9];
                continue;
            }
            if(chunk.type[0] == 73 && chunk.type[1] == 68 && chunk.type[2] == 65 && chunk.type[3] == 84)
            {
                data = chunk.getData();
                continue;
            }
            if(chunk.type[0] != 80 || chunk.type[1] != 76 || chunk.type[2] != 84 || chunk.type[3] != 69)
                continue;
            rgb = chunk.getData();
            if(rgb.length % 3 != 0)
                throw new Exception("Incorrect palette length.");
        }

        inflated = getDecompressedData();
        if(colorType == 0)
        {
            if(bitDepth == 8)
                image = getImageColorType0BitDepth8();
            else
            if(bitDepth == 4)
                image = getImageColorType0BitDepth4();
            else
            if(bitDepth == 2)
                image = getImageColorType0BitDepth2();
            else
            if(bitDepth == 1)
                image = getImageColorType0BitDepth1();
            else
                throw new Exception((new StringBuilder()).append("Image with unsupported bit depth == ").append(bitDepth).toString());
        } else
        if(colorType == 6)
            image = getImageColorType6BitDepth8();
        else
        if(rgb == null)
            image = getImageColorType2BitDepth8();
        else
        if(bitDepth == 8)
            image = getImageColorType3BitDepth8();
        else
        if(bitDepth == 4)
            image = getImageColorType3BitDepth4();
        else
        if(bitDepth == 2)
            image = getImageColorType3BitDepth2();
        else
        if(bitDepth == 1)
            image = getImageColorType3BitDepth1();
        else
            throw new Exception((new StringBuilder()).append("Image with unsupported bit depth == ").append(bitDepth).toString());
        deflated = deflateReconstructedData();
    }

    public int getWidth()
    {
        return w;
    }

    public int getHeight()
    {
        return h;
    }

    public byte[] getData()
    {
        return deflated;
    }

    private void processPNG(List list, InputStream inputstream)
        throws Exception
    {
        Chunk chunk;
        do
        {
            chunk = getChunk(inputstream);
            list.add(chunk);
        } while(chunk.type[0] != 73 || chunk.type[1] != 69 || chunk.type[2] != 78 || chunk.type[3] != 68);
    }

    private void validatePNG(InputStream inputstream)
        throws Exception
    {
        byte abyte0[] = new byte[8];
        if(inputstream.read(abyte0, 0, abyte0.length) == -1)
            throw new Exception("File is too short!");
        if((abyte0[0] & 0xff) != 137 || abyte0[1] != 80 || abyte0[2] != 78 || abyte0[3] != 71 || abyte0[4] != 13 || abyte0[5] != 10 || abyte0[6] != 26 || abyte0[7] != 10)
            throw new Exception("Wrong PNG signature.");
        else
            return;
    }

    private Chunk getChunk(InputStream inputstream)
        throws Exception
    {
        Chunk chunk = new Chunk();
        chunk.setLength(getLong(inputstream));
        chunk.setType(getBytes(inputstream, 4L));
        chunk.setData(getBytes(inputstream, chunk.getLength()));
        chunk.setCrc(getLong(inputstream));
        if(!chunk.hasGoodCRC())
            throw new Exception("Chunk has bad CRC.");
        else
            return chunk;
    }

    private long getLong(InputStream inputstream)
        throws Exception
    {
        byte abyte0[] = getBytes(inputstream, 4L);
        return (long)toIntValue(abyte0, 0) & 0xffffffffL;
    }

    private byte[] getBytes(InputStream inputstream, long l)
        throws Exception
    {
        byte abyte0[] = new byte[(int)l];
        inputstream.read(abyte0, 0, abyte0.length);
        return abyte0;
    }

    private int toIntValue(byte abyte0[], int i)
    {
        long l = 0L;
        l |= (long)abyte0[0 + i] & 255L;
        l <<= 8;
        l |= (long)abyte0[1 + i] & 255L;
        l <<= 8;
        l |= (long)abyte0[2 + i] & 255L;
        l <<= 8;
        l |= (long)abyte0[3 + i] & 255L;
        return (int)l;
    }

    private byte[] getImageColorType2BitDepth8()
    {
        int i = 0;
        byte abyte0[] = new byte[inflated.length - h];
        byte byte0 = 0;
        int j = 3 * w;
        for(int k = 0; k < inflated.length; k++)
        {
            if(k % (j + 1) == 0)
            {
                byte0 = inflated[k];
                continue;
            }
            abyte0[i] = inflated[k];
            int l = 0;
            int i1 = 0;
            int j1 = 0;
            if(i % j >= 3)
                l = abyte0[i - 3] & 0xff;
            if(i >= j)
                i1 = abyte0[i - j] & 0xff;
            if(i % j >= 3 && i >= j)
                j1 = abyte0[i - (j + 3)] & 0xff;
            applyFilters(byte0, abyte0, i, l, i1, j1);
            i++;
        }

        return abyte0;
    }

    private byte[] getImageColorType6BitDepth8()
    {
        int i = 0;
        byte abyte0[] = new byte[4 * w * h];
        byte byte0 = 0;
        int j = 4 * w;
        for(int k = 0; k < inflated.length; k++)
        {
            if(k % (j + 1) == 0)
            {
                byte0 = inflated[k];
                continue;
            }
            abyte0[i] = inflated[k];
            int l = 0;
            int j1 = 0;
            int l1 = 0;
            if(i % j >= 4)
                l = abyte0[i - 4] & 0xff;
            if(i >= j)
                j1 = abyte0[i - j] & 0xff;
            if(i % j >= 4 && i >= j)
                l1 = abyte0[i - (j + 4)] & 0xff;
            applyFilters(byte0, abyte0, i, l, j1, l1);
            i++;
        }

        byte abyte1[] = new byte[3 * w * h];
        int i1 = 0;
        for(int k1 = 0; k1 < abyte0.length; k1++)
            if(k1 % 4 != 0)
                abyte1[i1++] = abyte0[k1];

        return abyte1;
    }

    private byte[] getImageColorType3BitDepth8()
    {
        int i = 0;
        boolean flag = false;
        byte abyte0[] = new byte[3 * (inflated.length - h)];
        int k = w + 1;
        for(int l = 0; l < inflated.length; l++)
            if(l % k != 0)
            {
                int j = 3 * (inflated[l] & 0xff);
                abyte0[i++] = rgb[j];
                abyte0[i++] = rgb[j + 1];
                abyte0[i++] = rgb[j + 2];
            }

        return abyte0;
    }

    private byte[] getImageColorType3BitDepth4()
    {
        int i = 0;
        boolean flag = false;
        byte abyte0[] = new byte[6 * (inflated.length - h)];
        int l = w / 2 + 1;
        if(w % 2 > 0)
            l++;
        for(int i1 = 0; i1 < inflated.length; i1++)
        {
            if(i1 % l == 0)
                continue;
            byte byte0 = inflated[i1];
            int j = 3 * (byte0 >> 4 & 0xf);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) != 0)
            {
                int k = 3 * (byte0 >> 0 & 0xf);
                abyte0[i++] = rgb[k];
                abyte0[i++] = rgb[k + 1];
                abyte0[i++] = rgb[k + 2];
            }
        }

        return abyte0;
    }

    private byte[] getImageColorType3BitDepth2()
    {
        int i = 0;
        boolean flag = false;
        byte abyte0[] = new byte[12 * (inflated.length - h)];
        int l = w / 4 + 1;
        if(w % 4 > 0)
            l++;
        for(int i1 = 0; i1 < inflated.length; i1++)
        {
            if(i1 % l == 0)
                continue;
            byte byte0 = inflated[i1];
            int j = 3 * (byte0 >> 6 & 3);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 4 & 3);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 2 & 3);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) != 0)
            {
                int k = 3 * (byte0 >> 0 & 3);
                abyte0[i++] = rgb[k];
                abyte0[i++] = rgb[k + 1];
                abyte0[i++] = rgb[k + 2];
            }
        }

        return abyte0;
    }

    private byte[] getImageColorType3BitDepth1()
    {
        int i = 0;
        boolean flag = false;
        byte abyte0[] = new byte[24 * (inflated.length - h)];
        int l = w / 8 + 1;
        if(w % 8 > 0)
            l++;
        for(int i1 = 0; i1 < inflated.length; i1++)
        {
            if(i1 % l == 0)
                continue;
            byte byte0 = inflated[i1];
            int j = 3 * (byte0 >> 7 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 6 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 5 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 4 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 3 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 2 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) == 0)
                continue;
            j = 3 * (byte0 >> 1 & 1);
            abyte0[i++] = rgb[j];
            abyte0[i++] = rgb[j + 1];
            abyte0[i++] = rgb[j + 2];
            if(i % (3 * w) != 0)
            {
                int k = 3 * (byte0 >> 0 & 1);
                abyte0[i++] = rgb[k];
                abyte0[i++] = rgb[k + 1];
                abyte0[i++] = rgb[k + 2];
            }
        }

        return abyte0;
    }

    private byte[] getImageColorType0BitDepth8()
    {
        int i = 0;
        byte abyte0[] = new byte[inflated.length - h];
        byte byte0 = 0;
        int j = w;
        for(int k = 0; k < inflated.length; k++)
        {
            if(k % (j + 1) == 0)
            {
                byte0 = inflated[k];
                continue;
            }
            abyte0[i] = inflated[k];
            int l = 0;
            int i1 = 0;
            int j1 = 0;
            if(i % j >= 1)
                l = abyte0[i - 1] & 0xff;
            if(i >= j)
                i1 = abyte0[i - j] & 0xff;
            if(i % j >= 1 && i >= j)
                j1 = abyte0[i - (j + 1)] & 0xff;
            applyFilters(byte0, abyte0, i, l, i1, j1);
            i++;
        }

        return abyte0;
    }

    private byte[] getImageColorType0BitDepth4()
    {
        int i = 0;
        byte abyte0[] = new byte[inflated.length - h];
        int j = w / 2 + 1;
        if(w % 2 > 0)
            j++;
        for(int k = 0; k < inflated.length; k++)
            if(k % j != 0)
                abyte0[i++] = inflated[k];

        return abyte0;
    }

    private byte[] getImageColorType0BitDepth2()
    {
        int i = 0;
        byte abyte0[] = new byte[inflated.length - h];
        int j = w / 4 + 1;
        if(w % 4 > 0)
            j++;
        for(int k = 0; k < inflated.length; k++)
            if(k % j != 0)
                abyte0[i++] = inflated[k];

        return abyte0;
    }

    private byte[] getImageColorType0BitDepth1()
    {
        int i = 0;
        byte abyte0[] = new byte[inflated.length - h];
        int j = w / 8 + 1;
        if(w % 8 > 0)
            j++;
        for(int k = 0; k < inflated.length; k++)
            if(k % j != 0)
                abyte0[i++] = inflated[k];

        return abyte0;
    }

    private void applyFilters(byte byte0, byte abyte0[], int i, int j, int k, int l)
    {
        if(byte0 != 0)
            if(byte0 == 1)
                abyte0[i] += (byte)j;
            else
            if(byte0 == 2)
                abyte0[i] += (byte)k;
            else
            if(byte0 == 3)
                abyte0[i] += (byte)(int)Math.floor((double)(j + k) / 2D);
            else
            if(byte0 == 4)
            {
                int i1 = 0;
                int j1 = (j + k) - l;
                int k1 = Math.abs(j1 - j);
                int l1 = Math.abs(j1 - k);
                int i2 = Math.abs(j1 - l);
                if(k1 <= l1 && k1 <= i2)
                    i1 = j;
                else
                if(l1 <= i2)
                    i1 = k;
                else
                    i1 = l;
                abyte0[i] += (byte)(i1 & 0xff);
            }
    }

    private byte[] getDecompressedData()
        throws Exception
    {
        Decompressor decompressor = new Decompressor(data);
        return decompressor.getDecompressedData();
    }

    private byte[] deflateReconstructedData()
        throws Exception
    {
        Compressor compressor = new Compressor(image);
        return compressor.getCompressedData();
    }

    int w;
    int h;
    byte data[];
    byte inflated[];
    byte image[];
    byte deflated[];
    byte rgb[];
    public byte bitDepth;
    public int colorType;
}