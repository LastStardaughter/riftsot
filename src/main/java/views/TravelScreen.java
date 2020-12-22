package views;

import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.ColorTheme;
import org.hexworks.zircon.api.component.ComponentAlignment;
import org.hexworks.zircon.api.component.Label;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;
import util.Rand;

import static java.lang.Thread.sleep;

public class TravelScreen extends BaseView {
	public TravelScreen(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
		super(tileGrid, theme);
		eventLabel = Components.label()
			.withAlignmentWithin(tileGrid, ComponentAlignment.CENTER)
			.withSize(21,1)
			.build();
		dateLabel = Components.label()
			.withPosition(2,30)
			.withSize(21,1)
			.build();
		distLabel = Components.label()
			.withPosition(2,31)
			.withSize(21,1)
			.build();

		getScreen().addComponent(eventLabel);
		getScreen().addComponent(dateLabel);
		getScreen().addComponent(distLabel);
	}

	float partialDay=0.0f;
	int day=1, dist=0;
	String eventString="Day 1";
	Label eventLabel;
	Label distLabel;
	Label dateLabel;
	volatile boolean isActive=false;
	volatile long lastUpdated;
	static final float DAYS_PER_SECOND=1.0f;

	@Override
	public void onDock(){
		isActive=true;
		lastUpdated=System.currentTimeMillis();
		new Thread(() -> {
			try {
				while (isActive) {
					render(System.currentTimeMillis());
					sleep(50);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public void onUndock(){
		isActive=false;
	}
	

	public void render(long timeMillis) {
		float delta=(timeMillis-lastUpdated)/1000.0f;
		//System.out.println(lastUpdated);
		partialDay+=(delta/DAYS_PER_SECOND);
		if(partialDay>=1.0f) {
			day++;
			partialDay-=1.0f;
			dist+=18;
			if(Rand.nextInt(13)>8) {
				eventString="Random event at " + Rand.nextInt(24) + ":00";
			} else { eventString="Nothing happens";}
			eventLabel.setText(eventString);
		}
		
		StringBuilder text=new StringBuilder(100);
		text.append("Day ");
		text.append(day);
		text.append("\n");
		text.append((int) Math.floor(partialDay*24.0));
		text.append(":00");
		dateLabel.setText(text.toString());
		distLabel.setText(dist + " mi");
		lastUpdated=timeMillis;

		/*
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new PauseScreen(game, this));
			dispose();
		}
		*/

	}

}
