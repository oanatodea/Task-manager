import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.soap.MimeHeader;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;


public class CrearePdf {
	
	public CrearePdf(ProiecteTree proiecte, Angajat user){
		
		try {
			doIt("C:\\Users\\Oana\\Desktop\\here.pdf", proiecte,user);
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  public void doIt(final String file,ProiecteTree proiecte,Angajat user ) throws IOException, COSVisitorException
	       {

	    PDDocument doc = null;
	    try
	    {
	        doc = new PDDocument();
	        
	        PDPage page = new PDPage();
	        doc.addPage(page);
	        PDFont font = PDType1Font.HELVETICA_BOLD;

	        PDPageContentStream contentStream = new PDPageContentStream(doc,
	                page);
	        
	        float fontSize = 12;
	        float leading = 1.5f * fontSize;

	        PDRectangle mediabox = page.findMediaBox();
	        float margin = 72;
	        float width = mediabox.getWidth() - 2*margin;
	        float startX = mediabox.getLowerLeftX() + margin;
	        float startY = mediabox.getUpperRightY() - margin;
 
	        List<String> lines = new ArrayList<String>();
	        lines.add("User: "+ user.getNume()+" "+user.getPrenume());
	        lines.add("");
	        
	        for(Entry<String,Proiect> e : proiecte.getEntrySet()){
	        	if(e.getValue().getAngajati().contains(user))
	        	{
		        	lines.add(" * Proiect: "+e.getValue().getNume());
		        	lines.add(" * Descriere: "+e.getValue().getDescriere());
		        	String memo = e.getValue().getMemo();
		        	int index = 0;
		        	int nextIndex=0;
		        	lines.add(" * Memo: ");
		        	if(memo != null){	
		        		while(index<memo.length() && index>=0){
		        			nextIndex = memo.indexOf("\n", index);
		        			if(nextIndex>=0){
			        			String sub = memo.substring(index, nextIndex);
			        			lines.add(sub);
			        			index=nextIndex+1;
		        			}
		        			else{
		        				String sub = memo.substring(index, memo.length());
			        			lines.add(sub);
			        			index=-1;
		        			}
		        		}
		        	}
		        	else
		        		lines.add("Nu exista memo");
		        	lines.add("");
	        	}
	        }

	       contentStream.beginText();
	        contentStream.setFont(font, fontSize);
	        contentStream.moveTextPositionByAmount(startX, startY);            
	        for (String line: lines)
	        {
	            contentStream.drawString(line);
	            contentStream.moveTextPositionByAmount(0, -leading);
	        }
	        
	        
	        contentStream.endText();
	        contentStream.close();

	        doc.save(file);

	        System.out.println("Pdf created!");
	    }
	    finally
	    {
	        if (doc != null)
	        {
	            doc.close();
	        }
	    }
	}
}
