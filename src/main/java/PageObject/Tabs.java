package PageObject;

public enum Tabs {
    ПЛАНЫ_ПРОВЕРОК("Планы проверок"),
    CПРАВКИ_ПРОВЕРОК("Справки проверок"),
    ВЫПОЛНЕНИЕ_РЕКОМЕНДАЦИЙ("Выполнение рекомендаций"),
    ОТЧЁТЫ("Отчёты"),
    НАСТРОЙКИ("Настройки");

    String link;

    Tabs(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}

