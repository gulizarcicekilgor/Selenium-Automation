package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

    public class CRUD {

        List<String> users;

        @BeforeMethod
        public void setup() {
            // Her testten önce boş liste oluşturulur
            users = new ArrayList<>();
        }

        @Test(priority = 1)
        public void testCreate() {
            users.add("Ali");
            Assert.assertTrue(users.contains("Ali"), "Kullanıcı eklenemedi!");
        }

        @Test(priority = 2)
        public void testRead() {
            users.add("Veli");
            String user = users.get(0);
            Assert.assertEquals(user, "Veli", "Okunan kullanıcı yanlış!");
        }

        @Test(priority = 3)
        public void testUpdate() {
            users.add("Ayşe");
            users.set(0, "Fatma");  // update
            Assert.assertEquals(users.get(0), "Fatma", "Güncelleme başarısız!");
        }

        @Test(priority = 4)
        public void testDelete() {
            users.add("Mehmet");
            users.remove("Mehmet");  // delete
            Assert.assertFalse(users.contains("Mehmet"), "Silme işlemi başarısız!");
        }

}
