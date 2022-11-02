package commands;

import com.itextpdf.layout.properties.TextAlignment;

import java.util.Optional;

public class CommandParser {

    private boolean bold = false;
    private boolean italic = false;
    private boolean large = false;
    private TextAlignment textAlignment = TextAlignment.LEFT;

    private int indent = 0;

    public Optional<Command> createCommand(String line) {
        if (line.startsWith(".")) {
            String[] command = line.substring(1).split(" ");
            CommandType type = CommandType.valueOf(command[0].toUpperCase());
            switch (type) {
                case PARAGRAPH -> {
                    return Optional.of(new ParagraphCommand(textAlignment));
                }
                case BOLD -> bold = true;
                case ITALICS -> italic = true;
                case REGULAR -> {
                    bold = false;
                    italic = false;
                }
                case FILL -> {
                    textAlignment = TextAlignment.JUSTIFIED;
                    return Optional.of(new TextAlignmentCommand(textAlignment));
                }
                case NOFILL -> {
                    textAlignment = TextAlignment.LEFT;
                    return Optional.of(new TextAlignmentCommand(textAlignment));
                }
                case LARGE -> large = true;
                case NORMAL -> large = false;
                case INDENT -> {
                    int delta = Integer.parseInt(command[1]);
                    indent += delta;
                    if (delta != 0) return Optional.of(new ParagraphCommand(textAlignment));
                }
            }
            return Optional.empty();
        } else {
            return Optional.of(new TextCommand(line, indent, bold, italic, large, textAlignment));
        }
    }

}
