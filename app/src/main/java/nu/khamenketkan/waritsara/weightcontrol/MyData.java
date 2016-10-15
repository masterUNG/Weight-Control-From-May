package nu.khamenketkan.waritsara.weightcontrol;

/**
 * Created by Windows 8.1 on 16/7/2559.
 */
public class MyData {

    //Explicit
    String[] foodStrings = new String[]{
            "ข้าวผัดกะเพราหมู",
            "ข้าวผัดกะเพราไก่", "ข้าวผัดกะเพรากุ้ง",
            "ข้าวผัดกะเพราเนื้อ", "ข้าวผัดกะเพราไก่ไข่ดาว",
            "ก๋วยจั๊บ", "ก๋วยเตี๋ยวคั่วไก่",
            "ก๋วยเตี๋ยวต้มยำกุ้ง", "ก๋วยเตี๋ยวผัดไทยใส่ไข่", "ก๋วยเตี๋ยวเรือน้ำตก", };
    String[] unitStrings = new String[]{
            "จาน",
            "จาน","จาน",
            "จาน","จาน",
            "ชาม", "จาน",
            "ถ้วย","จาน","ชาม", };
    String[] caloriesStrings = new String[]{
            "580",
            "554", "540",
            "622", "630",
            "240", "435",
            "320", "577", "180",};
    String[] exerciseStrings = new String[]{
            "exercise0","exercise1",
            "exercise2","exercise3","exercise4",
            "exercise5","exercise6","exercise7",
            "exercise8","exercise9",};
    String[] burnStrings = new String[]{
            "100",
            "200",
            "300",
            "400",
            "500",
            "600",
            "700",
            "800",
            "900",
            "100",};

    public String[] getFoodStrings() {
        return foodStrings;
    }

    public String[] getUnitStrings() {
        return unitStrings;
    }

    public String[] getCaloriesStrings() {
        return caloriesStrings;
    }

    public String[] getExerciseStrings() {
        return exerciseStrings;
    }

    public String[] getBurnStrings() {
        return burnStrings;
    }

} // MyData Class
