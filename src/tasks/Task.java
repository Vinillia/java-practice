package tasks;

import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public abstract class Task implements Comparable<Task>
{
    private final static List<Task> _tasks = new ArrayList<Task>();

    Task()
    {
        _tasks.add(this);
    }

    public static List<Task> get_tasks_list()
    {
        if (_tasks.isEmpty())
        {
            try
            {
                Class.forName("tasks.solve_equation");
                Class.forName("tasks.array_manipulation");
                Class.forName("tasks.contacts");
                Class.forName("tasks.matrix_manipulation");
                Class.forName("tasks.numbers");
                Class.forName("tasks.menu");

                _tasks.sort(Comparator.comparingInt(Task::get_task_id));
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        return new ArrayList<Task>(_tasks);
    }

    public abstract void execute(Scanner scanner);
    public abstract int get_task_id();

    @Override
    public int compareTo(Task other)
    {
        return Integer.compare(get_task_id(), other.get_task_id());
    }
}
