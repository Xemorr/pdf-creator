package commands;

import com.itextpdf.layout.element.Paragraph;

public class LargeCommand implements Command {
    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph.setFontSize(40).setBold();
    }
}
