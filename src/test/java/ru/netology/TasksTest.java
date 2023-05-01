package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void testSimpleTaskMatchesInTitle() {
        SimpleTask task = new SimpleTask(1, "task1");
        String query = "task";
        boolean expected = true; // строка запроса содержится в названии задачи
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSimpleTaskMatchesNotInTitle() {
        SimpleTask task = new SimpleTask(1, "task1");
        String query = "job";
        boolean expected = false; // строка запроса НЕ содержится в названии задачи
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicMatchesNotInSubtasks() {
        Epic epic = new Epic(1, new String[]{"task 1", "task 2", "task 3"});
        String query = "task 4";
        boolean expected = false; // запрос НЕ содержит такой задачи
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicMatchesInSubtasks() {
        String[] subtasks = {"task 1", "task 2", "task 3"};
        Epic epic = new Epic(1, subtasks);
        String query = "task 2";
        boolean expected = true; // запрос содержит такую задачу
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesInTopic() {
        Meeting meeting = new Meeting(1, "1 topic", "1 project", "Утром в среду");
        String query = "topic";
        boolean expected = true; // запрос содержится в теме встречи
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesInProject() {
        Meeting meeting = new Meeting(1, "1 topic", "1 project", "Утром в среду");
        String query = "project";
        boolean expected = true; // запрос содержится в проекте
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesInDate() {
        Meeting meeting = new Meeting(1, "1 topic", "1 project", "Утром в среду");
        String query = "среду";
        boolean expected = true; // запрос содержится в дате старта
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesNoWhere() {
        Meeting meeting = new Meeting(1, "1 topic", "1 project", "Утром в среду");
        String query = "job";
        boolean expected = false; // запрос ни где не содержится
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }
}