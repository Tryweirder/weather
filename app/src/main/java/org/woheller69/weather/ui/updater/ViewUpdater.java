package org.woheller69.weather.ui.updater;

import org.woheller69.weather.database.CurrentWeatherData;
import org.woheller69.weather.database.Forecast;
import org.woheller69.weather.database.WeekForecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 24.01.2017.
 */

public class ViewUpdater {
    private static List<IUpdateableCityUI> subscribers = new ArrayList<>();

    public static void addSubscriber(IUpdateableCityUI sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
        }
    }

    public static void removeSubscriber(IUpdateableCityUI sub) {
        subscribers.remove(sub);
    }

    public static void updateCurrentWeatherData(CurrentWeatherData data) {
        try {
            for (IUpdateableCityUI sub : subscribers) {
                sub.processNewWeatherData(data);
            }
        }
        catch ( Exception e) { //TODO: Sometimes app crashes during updateCurrentWeatherData, reason unknown
        e.printStackTrace();
        }
    }

    public static void updateWeekForecasts(List<WeekForecast> forecasts) {
        try {
            for (IUpdateableCityUI sub : subscribers) {
                sub.updateWeekForecasts(forecasts);
            }
        }
        catch ( Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateForecasts(List<Forecast> forecasts) {
        try {
            for (IUpdateableCityUI sub : subscribers) {
                sub.updateForecasts(forecasts);
            }
        }
        catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
