import org.hexworks.zircon.api.*;
import org.hexworks.zircon.api.application.AppConfig;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.data.*;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.resource.TilesetResource;
import views.TitleScreen;

public class Main {
    private static final ColorTheme DEFAULT_THEME = ColorThemes.afterglow();
    private static final TilesetResource DEFAULT_FONT=CP437TilesetResources.yayo16x16();
    private static final Size WINDOW_SIZE=Size.create(60,33);
    private static final String MAIN_MENU_LABEL = "M A I N   M E N U";
    private static final String NEW_GAME_BUTTON_LABEL = "N E W   G A M E";
    private static final String OPTIONS_BUTTON_LABEL = "O P T I O N S";
    private static final String QUIT_BUTTON_LABEL = "Q U I T";
    private static final int MAIN_MENU_PANEL_WIDTH = 25;
    private static final int MAIN_MENU_PANEL_HEIGHT = 10;
    private static final int PANEL_SPACING = 2;

    public static void main(String[] args) {
        final TileGrid tileGrid = SwingApplications.startTileGrid(AppConfig.newBuilder()
                .withDefaultTileset(DEFAULT_FONT)
                .withSize(WINDOW_SIZE)
                .withDebugMode(true)
                .build());

        TitleScreen titleScreen=new TitleScreen(tileGrid, DEFAULT_THEME);
        titleScreen.dock();
    }
}
