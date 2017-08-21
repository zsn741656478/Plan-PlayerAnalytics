package main.java.com.djrapitops.plan.database.sql;

import main.java.com.djrapitops.plan.Log;

public class Select extends WhereParser {

    public Select(String start) {
        super(start);
    }

    public static Select from(String table, String... columns) {
        Select parser = new Select("SELECT ");
        int size = columns.length;
        for (int i = 0; i < size; i++) {
            if (size > 1 && i > 0) {
                parser.append(", ");
            }
            parser.append(columns[i]);
        }

        parser.append(" FROM ").append(table);
        Log.debug(parser.toString());
        return parser;
    }
}