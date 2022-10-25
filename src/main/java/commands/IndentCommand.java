package commands;

import com.itextpdf.layout.element.Paragraph;

public class IndentCommand implements Command {
    private int indent;

    public IndentCommand(int indent) {
        this.indent = indent;
    }

    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph.setFixedLeading(indent);
    }
}
