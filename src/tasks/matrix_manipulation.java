package tasks;

import java.util.Scanner;
import java.util.Random;

public class matrix_manipulation extends Task
{
    static
    {
        new matrix_manipulation();
    }

    @Override
    public void execute(Scanner scanner)
    {
        System.out.println("Введите число: ");

        int exclude = scanner.nextInt();

        final int row_size = 4;
        final int column_size = 4;

        int[][] matrix = new int[row_size][column_size];
        Random random = new Random();

        for (int row = 0; row < row_size; row++)
        {
            for (int column = 0; column < column_size; column++)
            {
                matrix[row][column] = random.nextInt(100) - 51;
            }
        }

        for (int row = 0; row < row_size; row++)
        {
            for (int column = 0; column < column_size; column++)
            {
                if (matrix[row][column] != exclude)
                {
                    System.out.println("Найдено значение элемента матрицы, не равное заданному значению: " + matrix[row][column]);
                    return;
                }
            }
        }

        System.out.println("Не удалось найти значение элемента матрицы, не равное заданному значению");
    }

    @Override
    public int get_task_id()
    {
        return 4;
    }
}
