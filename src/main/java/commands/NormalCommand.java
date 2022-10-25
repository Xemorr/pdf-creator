package commands;

import com.itextpdf.layout.element.Paragraph;

public class NormalCommand implements Command {
    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph.setFontSize(12);
    }
}
