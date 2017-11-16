package com.example;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


public class WeatherLayout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;
    public WeatherLayout(Weather weather, WeatherChangeListener changeListener) {
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();
        text = new TextField();

        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        text.setValueChangeMode(ValueChangeMode.BLUR);

        Binder<Weather> binder = new Binder<>(Weather.class);
        //Binds fields in this class to those in Weather based on their names
        binder.bindInstanceFields(this);
        // The following does the same more explicitly
        // binder.bind(text, Weather::getText, Weather::setText);
        // binder.bind(done, Weather::isDone, Weather::setDone);

        binder.setBean(weather);

        addComponent(done);
        addComponentsAndExpand(text);

        binder.addValueChangeListener(event -> changeListener.weatherChanged(weather));
    }
}
