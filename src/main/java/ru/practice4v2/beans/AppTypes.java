package ru.practice4v2.beans;

public enum AppTypes {

    WEB, MOBILE;

    public static AppTypes checkName(String name) {

        AppTypes sType = null;

        for (AppTypes type : values()) {

            if (type.name().equalsIgnoreCase(name)) {
                                                        sType = type;
                                                        break;
            }

        }

        return sType;

    }
}
