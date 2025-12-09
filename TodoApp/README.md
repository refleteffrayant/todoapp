# To-Do List Android Application

Структура проекта создана. Теперь нужно заполнить файлы кодом:

## Java файлы (в папке app/src/main/java/com/example/todoapp/):
1. Task.java - модель данных задачи
2. DatabaseHelper.java - работа с базой данных SQLite
3. TaskAdapter.java - адаптер для RecyclerView
4. MainActivity.java - главный экран со списком задач
5. ViewTaskActivity.java - экран просмотра задачи
6. EditTaskActivity.java - экран создания/редактирования задачи

## XML файлы (в папке app/src/main/res/layout/):
1. activity_main.xml - разметка главного экрана
2. item_task.xml - элемент списка задач
3. activity_view_task.xml - разметка экрана просмотра
4. activity_edit_task.xml - разметка экрана редактирования

## Ресурсы (в папке app/src/main/res/values/):
1. strings.xml - строковые ресурсы
2. colors.xml - цвета приложения
3. styles.xml - стили приложения

## Конфигурационные файлы:
1. app/src/main/AndroidManifest.xml - манифест приложения
2. app/build.gradle - зависимости модуля app
3. build.gradle - корневой файл Gradle
