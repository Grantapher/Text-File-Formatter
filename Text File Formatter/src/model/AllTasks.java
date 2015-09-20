package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AllTasks {
    public static final List<AbstractFormatTask> TASKS = new LinkedList<>();
    
    static {
        TASKS.add(new RemoveEmptyLinesTask());
        TASKS.add(new TrimWhitespaceTask());
        TASKS.add(new RemoveNumberLines());
    }

    public static List<AbstractFormatTask> getSelectedTasks() {
        final List<AbstractFormatTask> tasks = TASKS.stream()
                .filter(AbstractFormatTask::isSelected)
                .collect(Collectors.toList());
                
        tasks.sort(AbstractFormatTask.sortByPriority());
        return Collections.unmodifiableList(tasks);
    }

    public static List<AbstractFormatTask> getTasks() {
        return Collections.unmodifiableList(TASKS);
    }
}
