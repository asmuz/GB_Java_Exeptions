import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос данных у пользователя
        System.out.println("Введите данные в формате:");
        System.out.println("<Фамилия> <Имя> <Отчество> <дата_рождения> <номер_телефона (только цифры!)> <пол (м/ж)>");
        System.out.println("Пример: Иванов Иван Иванович 01.01.1990 89991234567 М");
        String input = scanner.nextLine();

        // Разделение введенной строки на части по пробелам
        String[] inputData = input.split("\\s+");
        if (inputData.length != 6) {
            // Проверка на правильное количество данных
            System.err.println("Ошибка: введено неверное количество данных.");
            return;
        }

        try {
            // Извлечение данных из массива
            String surname = inputData[0];
            String name = inputData[1];
            String patronymic = inputData[2];
            String birthDate = inputData[3];
            long phoneNumber = Long.parseLong(inputData[4]);
            char gender = inputData[5].charAt(0);

            // Проверка корректности пола
            if (gender != 'М' && gender != 'м' && gender != 'Ж' && gender != 'ж') {
                throw new IllegalArgumentException("Ошибка: неверно указан пол.");
            }

            // Запись данных в файл с фамилией в качестве имени файла
            FileWriter fileWriter = new FileWriter(surname + ".txt", true);
            fileWriter.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
            fileWriter.close();

            System.out.println("Данные успешно записаны в файл " + surname + ".txt");
        } catch (NumberFormatException e) {
            // Обработка ошибки неверного формата номера телефона
            System.err.println("Ошибка: неверный формат номера телефона.");
        } catch (IllegalArgumentException e) {
            // Обработка ошибки неверно указанного пола
            System.err.println(e.getMessage());
        } catch (IOException e) {
            // Обработка ошибки записи в файл
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }
}
