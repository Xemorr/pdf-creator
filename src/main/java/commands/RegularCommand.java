package commands;

import com.itextpdf.layout.element.Paragraph;

public class RegularCommand implements Command {
    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph;
    }
}
