package com.modularsistemas.modpdf;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class BMPImage
{

    public BMPImage(InputStream inputstream)
        throws Exception
    {
        w = 0;
        h = 0;
        palette = (byte[][])null;
        byte abyte0[] = getBytes(inputstream, 2);
        if(abyte0[0] == 66 && abyte0[1] == 77 || abyte0[0] == 66 && abyte0[1] == 65 || abyte0[0] == 67 && abyte0[1] == 73 || abyte0[0] == 67 && abyte0[1] == 80 || abyte0[0] == 73 && abyte0[1] == 67 || abyte0[0] == 80 && abyte0[1] == 84)
        {
            skipNBytes(inputstream, 8);
            int i = readSignedInt(inputstream);
            int j = readSignedInt(inputstream);
            w = readSignedInt(inputstream);
            h = readSignedInt(inputstream);
            skipNBytes(inputstream, 2);
            bpp = read2BytesLE(inputstream);
            int k = readSignedInt(inputstream);
            if(bpp > 8)
            {
                r5g6b5 = k == 3;
                skipNBytes(inputstream, 20);
                if(i > 54)
                    skipNBytes(inputstream, i - 54);
            } else
            {
                skipNBytes(inputstream, 12);
                int l = readSignedInt(inputstream);
                skipNBytes(inputstream, 4);
                parsePalette(inputstream, l);
            }
            parseData(inputstream);
        } else
        {
            throw new Exception("BMP data could not be parsed!");
        }
    }

    private void parseData(InputStream inputstream)
        throws Exception
    {
        image = new byte[w * h * 3];
        int i = 4 * (int)Math.ceil((double)(bpp * w) / 32D);
        try
        {
            for(int k = 0; k < h; k++)
            {
                byte abyte0[] = getBytes(inputstream, i);
                switch(bpp)
                {
                case 1: // '\001'
                    abyte0 = bit1to8(abyte0, w);
                    break;

                case 8: // '\b'
                case 24: // '\030'
                    break;

                case 4: // '\004'
                    abyte0 = bit4to8(abyte0, w);
                    break;

                case 16: // '\020'
                    if(r5g6b5)
                        abyte0 = bit16to24(abyte0, w);
                    else
                        abyte0 = bit16to24b(abyte0, w);
                    break;

                case 32: // ' '
                    abyte0 = bit32to24(abyte0, w);
                    break;

                default:
                    throw new Exception("Can only parse 1 bit, 4bit, 8bit, 16bit, 24bit and 32bit images");
                }
                int j = w * (h - k - 1) * 3;
                if(palette != null)
                {
                    for(int l = 0; l < w; l++)
                    {
                        image[j++] = palette[abyte0[l] >= 0 ? abyte0[l] : abyte0[l] + 256][2];
                        image[j++] = palette[abyte0[l] >= 0 ? abyte0[l] : abyte0[l] + 256][1];
                        image[j++] = palette[abyte0[l] >= 0 ? abyte0[l] : abyte0[l] + 256][0];
                    }

                } else
                {
                    for(int i1 = 0; i1 < w * 3; i1 += 3)
                    {
                        image[j++] = abyte0[i1 + 2];
                        image[j++] = abyte0[i1 + 1];
                        image[j++] = abyte0[i1 + 0];
                    }

                }
            }

        }
        catch(ArrayIndexOutOfBoundsException arrayindexoutofboundsexception)
        {
            throw new Exception("BMP parse error: imagedata not correct");
        }
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(32768);
        DeflaterOutputStream deflateroutputstream = new DeflaterOutputStream(bytearrayoutputstream, new Deflater());
        deflateroutputstream.write(image, 0, image.length);
        deflateroutputstream.finish();
        deflated = bytearrayoutputstream.toByteArray();
    }

    private static byte[] bit16to24(byte abyte0[], int i)
    {
        byte abyte1[] = new byte[i * 3];
        int j = 0;
        for(int k = 0; k < i * 2; k += 2)
        {
            abyte1[j++] = (byte)((abyte0[k] & 0x1f) << 3);
            abyte1[j++] = (byte)(((abyte0[k + 1] & 7) << 5) + ((abyte0[k] & 0xe0) >> 3));
            abyte1[j++] = (byte)(abyte0[k + 1] & 0xf8);
        }

        return abyte1;
    }

    private static byte[] bit16to24b(byte abyte0[], int i)
    {
        byte abyte1[] = new byte[i * 3];
        int j = 0;
        for(int k = 0; k < i * 2; k += 2)
        {
            abyte1[j++] = (byte)((abyte0[k] & 0x1f) << 3);
            abyte1[j++] = (byte)(((abyte0[k + 1] & 3) << 6) + ((abyte0[k] & 0xe0) >> 2));
            abyte1[j++] = (byte)((abyte0[k + 1] & 0x7c) << 1);
        }

        return abyte1;
    }

    private static byte[] bit32to24(byte abyte0[], int i)
    {
        byte abyte1[] = new byte[i * 3];
        int j = 0;
        for(int k = 0; k < i * 4; k += 4)
        {
            abyte1[j++] = abyte0[k + 1];
            abyte1[j++] = abyte0[k + 2];
            abyte1[j++] = abyte0[k + 3];
        }

        return abyte1;
    }

    private static byte[] bit4to8(byte abyte0[], int i)
    {
        byte abyte1[] = new byte[i];
        for(int j = 0; j < i; j++)
            if(j % 2 == 0)
                abyte1[j] = (byte)((abyte0[j / 2] & 0xf0) >> 4);
            else
                abyte1[j] = (byte)(abyte0[j / 2] & 0xf);

        return abyte1;
    }

    private static byte[] bit1to8(byte abyte0[], int i)
    {
        byte abyte1[] = new byte[i];
        for(int j = 0; j < i; j++)
            switch(j % 8)
            {
            case 0: // '\0'
                abyte1[j] = (byte)((abyte0[j / 8] & 0x80) >> 7);
                break;

            case 1: // '\001'
                abyte1[j] = (byte)((abyte0[j / 8] & 0x40) >> 6);
                break;

            case 2: // '\002'
                abyte1[j] = (byte)((abyte0[j / 8] & 0x20) >> 5);
                break;

            case 3: // '\003'
                abyte1[j] = (byte)((abyte0[j / 8] & 0x10) >> 4);
                break;

            case 4: // '\004'
                abyte1[j] = (byte)((abyte0[j / 8] & 8) >> 3);
                break;

            case 5: // '\005'
                abyte1[j] = (byte)((abyte0[j / 8] & 4) >> 2);
                break;

            case 6: // '\006'
                abyte1[j] = (byte)((abyte0[j / 8] & 2) >> 1);
                break;

            case 7: // '\007'
                abyte1[j] = (byte)(abyte0[j / 8] & 1);
                break;
            }

        return abyte1;
    }

    private void parsePalette(InputStream inputstream, int i)
        throws Exception
    {
        palette = new byte[i][];
        for(int j = 0; j < i; j++)
            palette[j] = getBytes(inputstream, 4);

    }

    private void skipNBytes(InputStream inputstream, int i)
    {
        try
        {
            getBytes(inputstream, i);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private byte[] getBytes(InputStream inputstream, int i)
        throws Exception
    {
        byte abyte0[] = new byte[i];
        inputstream.read(abyte0, 0, abyte0.length);
        return abyte0;
    }

    private int read2BytesLE(InputStream inputstream)
        throws Exception
    {
        byte abyte0[] = getBytes(inputstream, 2);
        int i = 0;
        i |= abyte0[1] & 0xff;
        i <<= 8;
        i |= abyte0[0] & 0xff;
        return i;
    }

    private int readSignedInt(InputStream inputstream)
        throws Exception
    {
        byte abyte0[] = getBytes(inputstream, 4);
        long l = 0L;
        l |= abyte0[3] & 0xff;
        l <<= 8;
        l |= abyte0[2] & 0xff;
        l <<= 8;
        l |= abyte0[1] & 0xff;
        l <<= 8;
        l |= abyte0[0] & 0xff;
        return (int)l;
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

    int w;
    int h;
    byte image[];
    byte deflated[];
    private int bpp;
    private byte palette[][];
    private boolean r5g6b5;
    private static final int m10000000 = 128;
    private static final int m01000000 = 64;
    private static final int m00100000 = 32;
    private static final int m00010000 = 16;
    private static final int m00001000 = 8;
    private static final int m00000100 = 4;
    private static final int m00000010 = 2;
    private static final int m00000001 = 1;
    private static final int m11110000 = 240;
    private static final int m00001111 = 15;
}