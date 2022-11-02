package commands;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public class TextAlignmentCommand implements Command {

    private TextAlignment textAlignment;

    public TextAlignmentCommand(TextAlignment alignment) {
        this.textAlignment = alignment;
    }
    @Override
    public Paragraph getBlockElement(Paragraph paragraph, Document document) {
        return paragraph.setTextAlignment(textAlignment);
    }
}
