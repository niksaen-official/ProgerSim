package com.niksaen.progersim.myClass;

import android.app.Activity;

public class BookValid {

    Activity activity;
    LoadData loadData = new LoadData();
    String language, level;

    public BookValid(Activity act) {
        activity = act;
        loadData.setActivity(activity);
        language = loadData.getLanguages();
        level = loadData.getLevel();
    }

    public boolean checkForLearn(String book) {
        switch (book) {
            //Стартовые языки
            case "Книга Ruby":
            case "Книга по C":
            case "Книга Lua":
            case "Книга Node.js":
            case "Книга PHP":
            case "Книга HTML":
            case "Книга Kotlin":
            case "Книга Java":
            case "Книга C#":
            case "Книга C++":
            case "Книга Python":
            case "Книга JavaScript": {
                return true;
            }

            //Книги которым необходимо знание С#
            case "Книга uniC#":
            case "Книга C# WPF":
            case "Книга по WinForms": {
                return language.contains("Книга C#");
            }
            case "Книга C# Game LLC": {
                return language.contains("Книга C#") && !(level.contains("Junior"));
            }

            //Книги которым необходимо знание C++
            case "Книга по wxWidgets":
            case "Книга CPPame": {
                return language.contains("Книга C++");
            }
            case "Книга tauCPP Game": {
                return language.contains("Книга C++") && level.contains("Senior");
            }

            //Книги кторым необходимо знание Java
            case "Книга JavaFX":
            case "Книга Android API":
            case "Книга JavaGame": {
                return language.contains("Книга Java");
            }

            //Книги которым необходимо знание Python
            case "Книга PythoAme": {
                return language.contains("Книга Python");
            }


            //Книги которым необходимо знание JavaScript
            case "Книга caJS Engine": {
                return language.contains("Книга JavaScript");
            }
            case "Книга JS Engn": {
                return language.contains("Книга JavaScript") && !(level.contains("Junior"));
            }

            //книги которым необходимо знание доп языков
            case "Книга CSS": {
                return language.contains("Книга HTML") && !(level.equals("Junior 1"));
            }
            case "Книга MySQL": {
                return (language.contains("Книга PHP") || language.contains("Книга Node.js")) && !level.equals("Junior 2") && !(level.equals("Junior 1"));
            }
            case "Книга LuaME": {
                return language.contains("Книга Lua");
            }

            //книги которые не определились с языком :)
            case "Книга blaGame Engine": {
                if (language.contains("Книга C#") || language.contains("Книга C++") || language.contains("Книга Java")) {
                    return level.contains("Senior") && !(level.contains("1"));
                } else
                    return false;
            }
            case "Книга Qt": {
                return language.contains("Книга C++") || language.contains("Книга Python") || language.contains("Книга Ruby") || language.contains("Книга по C");
            }

            default:
                return false;
        }
    }
}
