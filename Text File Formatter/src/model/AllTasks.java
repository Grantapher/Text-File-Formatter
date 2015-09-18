package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AllTasks {
    public static final List<FormatTask> TASKS = new LinkedList<>();
    
    static {
        TASKS.add(new RemoveEmptyLinesTask());
        TASKS.add(new TrimWhitespaceTask());
    }

    public static List<FormatTask> getTasks() {
        return Collections.unmodifiableList(TASKS);
    }
}
