package commands;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public interface Command {

    Paragraph getBlockElement(Paragraph paragraph, Document document);

}
