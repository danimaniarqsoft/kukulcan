package mx.infotec.dads.kukulkan.util;

import java.util.Random;

public class NameGenerator {

    private static String[] beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru", "Ray", "Bre", "Zed", "Drak", "Mor", "Jag",
            "Mer", "Jar", "Mjol", "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro", "Mar", "Luk" };
    private static String[] middle = { "air", "ir", "mi", "sor", "mee", "clo", "red", "cra", "ark", "arc", "miri",
            "lori", "cres", "mur", "zer", "marac", "zoir", "slamar", "salmar", "urak" };
    private static String[] end = { "d", "ed", "ark", "arc", "es", "er", "der", "tron", "med", "ure", "zur", "cred",
            "mur" };

    private static Random rand = new Random();

    private NameGenerator() {
    }

    public static String generateName() {
        return beginning[rand.nextInt(beginning.length)] + middle[rand.nextInt(middle.length)]
                + end[rand.nextInt(end.length)];

    }
}