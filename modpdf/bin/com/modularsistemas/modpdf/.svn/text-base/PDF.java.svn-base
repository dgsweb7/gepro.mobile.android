
package com.modularsistemas.modpdf;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;


public class PDF
{

    public PDF(OutputStream outputstream)
        throws Exception
    {
        this(outputstream, 0);
    }

    public PDF(OutputStream outputstream, int i)
        throws Exception
    {
        compliance = 0;
        buf = null;
        objNumber = 0;
        metadataObjNumber = 0;
        outputIntentObjNumber = 0;
        fonts = new ArrayList();
        images = new ArrayList();
        pages = new ArrayList();
        objOffset = new ArrayList();
        producer = "";
        title = "";
        subject = "";
        author = "";
        byte_count = 0;
        eval = true;
        buf = outputstream;
        compliance = i;
        Date date = new Date();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        creationDate = simpledateformat.format(date);
        createDate = simpledateformat1.format(date);
        append("%PDF-1.4\n");
        append('%');
        append((byte)-14);
        append((byte)-13);
        append((byte)-12);
        append((byte)-11);
        append((byte)-10);
        append('\n');
        if(i == 1)
        {
            metadataObjNumber = addMetadataObject();
            outputIntentObjNumber = addOutputIntentObject();
        }
    }

    public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getTitle() {
		return title;
	}

	public String getSubject() {
		return subject;
	}

	public String getAuthor() {
		return author;
	}

	protected void newobj()
        throws IOException
    {
        objOffset.add(Integer.valueOf(byte_count));
        append(++objNumber);
        append(" 0 obj\n");
    }

    protected void endobj()
        throws IOException
    {
        append("endobj\n");
    }

    private int addMetadataObject()
        throws Exception
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<?xpacket begin='\uFEFF' id=\"W5M0MpCehiHzreSzNTczkc9d\"?>\n");
        stringbuilder.append("<x:xmpmeta xmlns:x=\"adobe:ns:meta/\">\n");
        stringbuilder.append("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n");
        stringbuilder.append("<rdf:Description rdf:about=\"\" xmlns:pdf=\"http://ns.adobe.com/pdf/1.3/\" pdf:Producer=\"");
        stringbuilder.append(producer);
        stringbuilder.append("\"></rdf:Description>\n");
        stringbuilder.append("<rdf:Description rdf:about=\"\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n");
        stringbuilder.append("<dc:format>application/pdf</dc:format>\n");
        stringbuilder.append("<dc:title><rdf:Alt><rdf:li xml:lang=\"x-default\">");
        stringbuilder.append(title);
        stringbuilder.append("</rdf:li></rdf:Alt></dc:title>\n");
        stringbuilder.append("<dc:creator><rdf:Seq><rdf:li>");
        stringbuilder.append(producer);
        stringbuilder.append("</rdf:li></rdf:Seq></dc:creator>\n");
        stringbuilder.append("<dc:description><rdf:Alt><rdf:li xml:lang=\"en-US\">");
        stringbuilder.append("");
        stringbuilder.append("</rdf:li></rdf:Alt></dc:description>\n");
        stringbuilder.append("</rdf:Description>\n");
        stringbuilder.append("<rdf:Description rdf:about=\"\" xmlns:pdfaid=\"http://www.aiim.org/pdfa/ns/id/\">");
        stringbuilder.append("<pdfaid:part>1</pdfaid:part>");
        stringbuilder.append("<pdfaid:conformance>B</pdfaid:conformance>");
        stringbuilder.append("</rdf:Description>");
        stringbuilder.append("<rdf:Description rdf:about=\"\" xmlns:xmp=\"http://ns.adobe.com/xap/1.0/\">\n");
        stringbuilder.append("<xmp:CreateDate>");
        stringbuilder.append(createDate);
        stringbuilder.append("</xmp:CreateDate>\n");
        stringbuilder.append("</rdf:Description>\n");
        stringbuilder.append("</rdf:RDF>\n");
        stringbuilder.append("</x:xmpmeta>\n");
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 10; j++)
                stringbuilder.append("          ");

            stringbuilder.append("\n");
        }

        stringbuilder.append("<?xpacket end=\"w\"?>");
        byte abyte0[] = stringbuilder.toString().getBytes("UTF-8");
        newobj();
        append("<<\n");
        append("/Type /Metadata\n");
        append("/Subtype /XML\n");
        append("/Length ");
        append(abyte0.length);
        append("\n");
        append(">>\n");
        append("stream\n");
        for(int k = 0; k < abyte0.length; k++)
            append(abyte0[k]);

        append("\nendstream\n");
        endobj();
        return objNumber;
    }

    protected int addOutputIntentObject()
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        BufferedInputStream bufferedinputstream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("icc-profiles/sRGB_IEC61966-2-1_black_scaled.icc"));
        int i;
        while((i = bufferedinputstream.read()) != -1) 
            bytearrayoutputstream.write(i);
        bufferedinputstream.close();
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
        DeflaterOutputStream deflateroutputstream = new DeflaterOutputStream(bytearrayoutputstream1, new Deflater());
        deflateroutputstream.write(abyte0, 0, abyte0.length);
        deflateroutputstream.finish();
        newobj();
        append("<<\n");
        append("/N 3\n");
        append("/Length ");
        append(bytearrayoutputstream1.size());
        append("\n");
        append("/Filter /FlateDecode\n");
        append(">>\n");
        append("stream\n");
        append(bytearrayoutputstream1);
        append("\nendstream\n");
        endobj();
        newobj();
        append("<<\n");
        append("/Type /OutputIntent\n");
        append("/S /GTS_PDFA1\n");
        append("/OutputCondition (sRGB IEC61966-2.1)\n");
        append("/OutputConditionIdentifier (sRGB IEC61966-2.1)\n");
        append("/Info (sRGB IEC61966-2.1)\n");
        append("/DestOutputProfile ");
        append(objNumber - 1);
        append(" 0 R\n");
        append(">>\n");
        endobj();
        return objNumber;
    }

    private int addResourcesObject()
        throws Exception
    {
        newobj();
        append("<<\n");
        append("/Font\n");
        append("<<\n");
        for(int i = 0; i < fonts.size(); i++)
        {
            Font font = (Font)fonts.get(i);
            append("/F");
            append(font.objNumber);
            append(" ");
            append(font.objNumber);
            append(" 0 R\n");
        }

        append(">>\n");
        append("/XObject\n");
        append("<<\n");
        for(int j = 0; j < images.size(); j++)
        {
            Image image = (Image)images.get(j);
            append("/Im");
            append(image.objNumber);
            append(" ");
            append(image.objNumber);
            append(" 0 R\n");
        }

        append(">>\n");
        append(">>\n");
        endobj();
        return objNumber;
    }

    protected int addPagesObject()
        throws Exception
    {
        newobj();
        append("<<\n");
        append("/Type /Pages\n");
        append("/Kids [ ");
        int i = objNumber + 1;
        for(int j = 0; j < pages.size(); j++)
        {
            Page page = (Page)pages.get(j);
            append(i);
            append(" 0 R ");
            i = (i += 2) + page.annots.size();
        }

        append("]\n");
        append("/Count ");
        append(pages.size());
        append('\n');
        append(">>\n");
        endobj();
        return objNumber;
    }

    protected int addInfoObject()
        throws Exception
    {
        newobj();
        append("<<\n");
        append("/Title (");
        append(title);
        append(")\n");
        append("/Subject (");
        append(subject);
        append(")\n");
        append("/Author (");
        append(author);
        append(")\n");
        append("/Producer (");
        append(producer);
        append(")\n");
        if(compliance != 1)
        {
            append("/CreationDate (D:");
            append(creationDate);
            append(")\n");
        }
        append(">>\n");
        endobj();
        return objNumber;
    }

    protected void addAllPages(int i, int j)
        throws Exception
    {
        for(int k = 0; k < pages.size(); k++)
        {
            Page page = (Page)pages.get(k);
         
            newobj();
            append("<<\n");
            append("/Type /Page\n");
            append("/Parent ");
            append(i);
            append(" 0 R\n");
            append("/MediaBox [0.0 0.0 ");
            append(page.width);
            append(" ");
            append(page.height);
            append("]\n");
            append("/Resources ");
            append(j);
            append(" 0 R\n");
            append("/Contents ");
            append(objNumber + 1);
            append(" 0 R\n");
            if(page.annots.size() > 0)
            {
                append("/Annots [ ");
                for(int l = 0; l < page.annots.size(); l++)
                {
                    append(objNumber + 2 + l);
                    append(" 0 R ");
                }

                append("]\n");
            }
            append(">>\n");
            endobj();
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            DeflaterOutputStream deflateroutputstream = new DeflaterOutputStream(bytearrayoutputstream, new Deflater());
            deflateroutputstream.write(page.buf.toByteArray(), 0, page.buf.toByteArray().length);
            deflateroutputstream.finish();
            newobj();
            append("<<\n");
            append("/Filter /FlateDecode\n");
            append("/Length ");
            append(bytearrayoutputstream.size());
            append("\n");
            append(">>\n");
            append("stream\n");
            append(bytearrayoutputstream);
            append("\nendstream\n");
            endobj();
            addAnnotDictionaries(page);
        }

    }

    protected void addAnnotDictionaries(Page page)
        throws Exception
    {
        for(int i = 0; i < page.annots.size(); i++)
        {
            Annotation annotation = (Annotation)page.annots.get(i);
            newobj();
            append("<<\n");
            append("/Type /Annot\n");
            append("/Subtype /Link\n");
            append("/Rect [");
            append(annotation.x1);
            append(' ');
            append(annotation.y1);
            append(' ');
            append(annotation.x2);
            append(' ');
            append(annotation.y2);
            append("]\n");
            append("/Border[0 0 0]\n");
            append("/F 4\n");
            append("/A <<\n");
            append("/S /URI\n");
            append("/URI (");
            append(annotation.uri);
            append(")\n");
            append(">>\n");
            append(">>\n");
            endobj();
        }

    }

    public void flush()
        throws Exception
    {
        int i = addResourcesObject();
        int j = addInfoObject();
        int k = addPagesObject();
        addAllPages(k, i);
        newobj();
        append("<<\n");
        append("/Type /Catalog\n");
        append("/Pages ");
        append(k);
        append(" 0 R\n");
        if(compliance == 1)
        {
            append("/Metadata ");
            append(metadataObjNumber);
            append(" 0 R\n");
            append("/OutputIntents [");
            append(outputIntentObjNumber);
            append(" 0 R]\n");
        }
        append(">>\n");
        endobj();
        int l = byte_count;
        append("xref\n");
        append("0 ");
        append(objNumber + 1);
        append('\n');
        append("0000000000 65535 f \n");
        for(int i1 = 0; i1 < objOffset.size(); i1++)
        {
            int j1 = ((Integer)objOffset.get(i1)).intValue();
            String s1 = String.valueOf(j1);
            for(int k1 = 0; k1 < 10 - s1.length(); k1++)
                append('0');

            append(s1);
            append(" 00000 n \n");
        }

        append("trailer\n");
        append("<<\n");
        append("/Size ");
        append(objNumber + 1);
        append('\n');
        String s = (new Salsa20()).getID();
        append("/ID[<");
        append(s);
        append("><");
        append(s);
        append(">]\n");
        append("/Root ");
        append(objNumber);
        append(" 0 R\n");
        append("/Info ");
        append(j);
        append(" 0 R\n");
        append(">>\n");
        append("startxref\n");
        append(l);
        append('\n');
        append("%%EOF\n");
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setSubject(String s)
    {
        subject = s;
    }

    public void setAuthor(String s)
    {
        author = s;
    }

    protected void append(int i)
        throws IOException
    {
        append(String.valueOf(i));
    }

    protected void append(double d)
        throws IOException
    {
        append(String.valueOf(d).replace(',', '.'));
    }

    protected void append(String s)
        throws IOException
    {
        int i = s.length();
        for(int j = 0; j < i; j++)
            buf.write((byte)s.charAt(j));

        byte_count += i;
    }

    protected void append(char c)
        throws IOException
    {
        append((byte)c);
    }

    protected void append(byte byte0)
        throws IOException
    {
        buf.write(byte0);
        byte_count++;
    }

    protected void append(byte abyte0[], int i, int j)
        throws IOException
    {
        buf.write(abyte0, i, j);
        byte_count += j;
    }

    protected void append(ByteArrayOutputStream bytearrayoutputstream)
        throws IOException
    {
        bytearrayoutputstream.writeTo(buf);
        byte_count += bytearrayoutputstream.size();
    }

    public void setCompressor(boolean flag)
    {
        original_zlib = flag;
    }

    protected static boolean original_zlib = false;
    private int compliance;
    private OutputStream buf;
    protected int objNumber;
    protected int metadataObjNumber;
    protected int outputIntentObjNumber;
    protected List fonts;
    protected List images;
    protected List pages;
    private List objOffset;
    private String producer;
    private String creationDate;
    private String createDate;
    private String title;
    private String subject;
    private String author;
    private int byte_count;
    private boolean eval;

}