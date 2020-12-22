import org.hexworks.zircon.api.*;
import org.hexworks.zircon.api.application.AppConfig;
import org.hexworks.zircon.api.builder.component.TextBoxBuilder;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.data.*;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.resource.TilesetResource;
import org.hexworks.zircon.api.screen.Screen;
import org.hexworks.zircon.api.uievent.ComponentEventType;
import org.hexworks.zircon.api.uievent.KeyCode;
import org.hexworks.zircon.api.uievent.KeyboardEventType;
import org.hexworks.zircon.api.uievent.UIEventResponse;

public class ConsoleDemo {
    private static final ColorTheme DEFAULT_THEME = ColorThemes.afterglow();
    private static final TilesetResource DEFAULT_FONT=CP437TilesetResources.yayo16x16();
    private static final Size WINDOW_SIZE=Size.create(60,33);

    public static void main(String[] args) {
        final TileGrid tileGrid = SwingApplications.startTileGrid(AppConfig.newBuilder()
                .withDefaultTileset(DEFAULT_FONT)
                .withSize(WINDOW_SIZE)
                .withDebugMode(true)
                .build());

        Screen titleScreen=Screen.create(tileGrid);
        Label titleName=Components.label()
                .withText("Game Demo")
                .withPosition(Position.create(2,2))
                .build();
        titleScreen.addComponent(titleName);

        TextArea consoleIn=Components.textArea()
          .withPosition(Position.create(2,30))
          .withSize(56, 1)
          .build();
        titleScreen.addComponent(consoleIn);

        LogArea consoleOut=Components.logArea()
          .withPosition(Position.create(2,3))
          .withSize(56,27)
          .build();
        titleScreen.addComponent(consoleOut);

        consoleIn.handleKeyboardEvents(KeyboardEventType.KEY_PRESSED, (event, phase) -> {
            if(event.getCode()== KeyCode.ENTER) {
                consoleOut.addInlineText(consoleIn.getText());
                consoleOut.addNewRows(1);
                consoleOut.commitInlineElements();
                consoleIn.resetState();
                consoleIn.requestFocus();
            }
            return UIEventResponse.processed();
        });

        titleScreen.display();
        titleScreen.setTheme(DEFAULT_THEME);
    }
}
