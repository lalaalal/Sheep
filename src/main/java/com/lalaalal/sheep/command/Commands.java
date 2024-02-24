package com.lalaalal.sheep.command;

import com.lalaalal.sheep.sheet.Position;

import java.util.HashMap;

public class Commands {
    private static final HashMap<String, Command> registry = new HashMap<>();

    public static void register(String name, Command command) {
        registry.put(name, command);
    }

    public static Command get(String name) {
        return registry.get(name);
    }

    public static void initialize() {
        register("set", (sheet, sheetDrawer, args) -> {
            String position = args[0];
            String text = args[1];
            sheet.setText(Position.at(position), text);
        });
        register("get", (sheet, sheetDrawer, args) -> {
            String position = args[0];
            sheetDrawer.printDetail(System.out, sheet, position);
        });
    }
}
