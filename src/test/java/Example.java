import steps.BaseTest;

import org.testng.annotations.Test;

public class Example extends BaseTest {
    @Test(description = "Обновить существующего питомца (вызов метода UPDATE) Позитивный тест")
    public void updateExistingPet() {
        USER_STEPS.updateExistingPet();
    }
    @Test(description = "Найти питомца по идентификатору (Вызов метода GET) Негативный тест")
    public void testFindPetByIdNegative() {
        USER_STEPS.testFindPetByIdNegative();
    }
    @Test(description = "Найти питомца по статусу (Вызов метода GET) Позитивный тест")
    public void testFindPetByStatusPositive() {
        USER_STEPS.testFindPetByStatusPositive();
    }
    @Test(description = "Добавление нового питомца в магазин (Вызов метода POST) Позитивный тест")
    public void testAddNewPet() {
        USER_STEPS.testAddNewPet();
    }
    @Test(description = "Поиск питомца по ID (Вызов метода GET) Позитивный тест")
    public void testSearchPetByIdPositive() {
        USER_STEPS.testSearchPetByIdPositive();
    }
}