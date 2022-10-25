package commands;

import com.itextpdf.layout.element.Paragraph;

//Stub
public class NoFillCommand implements Command {
    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph;
    }
}
