package cf.ystapi.events;

public interface Dateevent {
    default void OnSecondChange() {}
    default void OnMinuteChange() {}
    default void OnHourChange() {}
    default void OnDateChange() {}
    default void OnWeekChange() {}
    default void OnYearChange() {}
}
