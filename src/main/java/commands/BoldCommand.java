package commands;

import com.itextpdf.layout.element.Paragraph;

public class BoldCommand implements Command {
    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph.setBold();
    }
}
