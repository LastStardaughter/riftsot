package views;

import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.Functions;
import org.hexworks.zircon.api.behavior.TextOverride;
import org.hexworks.zircon.api.behavior.Themeable;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.component.ColorTheme;
import org.hexworks.zircon.api.component.ComponentAlignment;
import org.hexworks.zircon.api.component.Label;
import org.hexworks.zircon.api.data.Position;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

public class TitleScreen extends BaseView {

	Label titleName=Components.label()
		.withText("Game Demo")
		.withPosition(Position.create(2,2))
		.build();

	Button start=Components.button()
		.withText("Start")
		.withAlignmentWithin(getScreen(), ComponentAlignment.CENTER)
		.build();

	private TravelScreen travelScreen;

	public TitleScreen(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
		super(tileGrid, theme);
		travelScreen=new TravelScreen(tileGrid, theme);
	}

	@Override
	public void onDock(){
		getScreen().addComponent(titleName);
		getScreen().addComponent(start);
		start.onActivated(Functions.fromConsumer((event) -> travelScreen.dock()));
		//getScreen().setTheme(theme);
	}

}
