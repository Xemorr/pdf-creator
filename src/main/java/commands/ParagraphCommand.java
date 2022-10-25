package commands;

import com.itextpdf.layout.element.Paragraph;

public class ParagraphCommand implements Command {

    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return new Paragraph();
    }

}
