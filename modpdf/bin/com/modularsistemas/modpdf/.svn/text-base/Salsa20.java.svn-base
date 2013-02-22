
package com.modularsistemas.modpdf;


public class Salsa20
{

    public Salsa20()
    {
        id = null;
        int ai[] = new int[16];
        StringBuilder stringbuilder = new StringBuilder(Long.toHexString(System.currentTimeMillis()));
        int i = 128 - stringbuilder.length();
        for(int j = 0; j < i; j++)
            stringbuilder.append('0');

        for(int k = 0; k < 128; k += 8)
            ai[k / 8] = (int)Long.parseLong(stringbuilder.substring(k, k + 8), 16);

        id = bin2hex(salsa20_word_specification(ai));
    }

    private int R(int i, int j)
    {
        return i << j | i >>> 32 - j;
    }

    private int[] salsa20_word_specification(int ai[])
    {
        int ai1[] = new int[16];
        int ai2[] = new int[16];
        for(int i = 0; i < 16; i++)
            ai2[i] = ai[i];

        for(int j = 20; j > 0; j -= 2)
        {
            ai2[4] ^= R(ai2[0] + ai2[12], 7);
            ai2[8] ^= R(ai2[4] + ai2[0], 9);
            ai2[12] ^= R(ai2[8] + ai2[4], 13);
            ai2[0] ^= R(ai2[12] + ai2[8], 18);
            ai2[9] ^= R(ai2[5] + ai2[1], 7);
            ai2[13] ^= R(ai2[9] + ai2[5], 9);
            ai2[1] ^= R(ai2[13] + ai2[9], 13);
            ai2[5] ^= R(ai2[1] + ai2[13], 18);
            ai2[14] ^= R(ai2[10] + ai2[6], 7);
            ai2[2] ^= R(ai2[14] + ai2[10], 9);
            ai2[6] ^= R(ai2[2] + ai2[14], 13);
            ai2[10] ^= R(ai2[6] + ai2[2], 18);
            ai2[3] ^= R(ai2[15] + ai2[11], 7);
            ai2[7] ^= R(ai2[3] + ai2[15], 9);
            ai2[11] ^= R(ai2[7] + ai2[3], 13);
            ai2[15] ^= R(ai2[11] + ai2[7], 18);
            ai2[1] ^= R(ai2[0] + ai2[3], 7);
            ai2[2] ^= R(ai2[1] + ai2[0], 9);
            ai2[3] ^= R(ai2[2] + ai2[1], 13);
            ai2[0] ^= R(ai2[3] + ai2[2], 18);
            ai2[6] ^= R(ai2[5] + ai2[4], 7);
            ai2[7] ^= R(ai2[6] + ai2[5], 9);
            ai2[4] ^= R(ai2[7] + ai2[6], 13);
            ai2[5] ^= R(ai2[4] + ai2[7], 18);
            ai2[11] ^= R(ai2[10] + ai2[9], 7);
            ai2[8] ^= R(ai2[11] + ai2[10], 9);
            ai2[9] ^= R(ai2[8] + ai2[11], 13);
            ai2[10] ^= R(ai2[9] + ai2[8], 18);
            ai2[12] ^= R(ai2[15] + ai2[14], 7);
            ai2[13] ^= R(ai2[12] + ai2[15], 9);
            ai2[14] ^= R(ai2[13] + ai2[12], 13);
            ai2[15] ^= R(ai2[14] + ai2[13], 18);
        }

        for(int k = 0; k < 16; k++)
            ai1[k] = ai2[k] + ai[k];

        return ai1;
    }

    private String bin2hex(int ai[])
    {
        String s = "0123456789abcdef";
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < ai.length; i++)
        {
            int j = ai[i];
            stringbuilder.append(s.charAt(j >> 28 & 0xf));
            stringbuilder.append(s.charAt(j >> 24 & 0xf));
            stringbuilder.append(s.charAt(j >> 20 & 0xf));
            stringbuilder.append(s.charAt(j >> 16 & 0xf));
            stringbuilder.append(s.charAt(j >> 12 & 0xf));
            stringbuilder.append(s.charAt(j >> 8 & 0xf));
            stringbuilder.append(s.charAt(j >> 4 & 0xf));
            stringbuilder.append(s.charAt(j >> 0 & 0xf));
        }

        return stringbuilder.substring(0, 32);
    }

    public String getID()
    {
        return id;
    }

    private String id;
}