package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Уборка"}));
        Task[] expected = {};
        Task[] actual = todos.search("Гимнастика");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOneMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Уборка"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко")};
        Task[] actual = todos.search("молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayAllQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Уборка"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Уборка"})};
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Доп. тесты для 100% покрытия
    @Test
    public void testEquals() {
        // Создание двух экземпляров класса Task с одинаковыми id
        Task task1 = new Task(1);
        Task task2 = new Task(1);

        // Равенство с самим собой
        Task task = new Task(1);
        assertTrue(task.equals(task));

        // Проверка, что метод equals возвращает false, если id у двух объектов не равны
        Task task3 = new Task(2);
        assertFalse(task1.equals(task3));
        assertFalse(task3.equals(task1));

        // Проверка, что метод equals возвращает false, если сравниваем объект типа Task с объектом другого класса
        assertFalse(task1.equals(new Object()));

        // Неравенство объекта Task и null
        Task task0 = new Task(1);
        assertFalse(task.equals(null));
    }
}