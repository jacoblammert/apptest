package com.example.japanese;

public enum Color {

    kanji("#ff0034"),
    onyomi("#734e55"),
    onyomi_romaji("#3d292d"),
    kunyomi("#6e2433"),
    kunyomi_romaji("#471821"),
    meaning("#368063"),
    level("#61543c"),

    activity_background("#161618");


    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
