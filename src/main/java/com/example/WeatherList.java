package com.example;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
class WeatherList extends VerticalLayout implements WeatherChangeListener {
    @Autowired
    WeatherRepository repository;
    private List<Weather> weathers;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setWeathers(repository.findAll());
    }

    private void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
        removeAllComponents();
        weathers.forEach(todo -> addComponent(new WeatherLayout(todo, this)));
    }

     void addWeather(Weather weather) {
        repository.save(weather);
        update();
    }

    @Override
    public void weatherChanged(Weather weather) {
        addWeather(weather);
    }


    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
