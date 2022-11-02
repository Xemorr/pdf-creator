package commands;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

public class ParagraphCommand implements Command {
    private final TextAlignment textAlignment;

    public ParagraphCommand(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
    }
    @Override
    public Paragraph getBlockElement(Paragraph paragraph, Document document) {
        Paragraph paragraph1 = new Paragraph();
        paragraph1.setTextAlignment(textAlignment);
        return paragraph1;
    }

}
