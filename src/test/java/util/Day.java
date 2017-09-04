package util;

/**
 * Created by Anton_Chyrko on 8/22/2017.
 */
public enum Day {
    MON(1),TUE(2),WED(3),THU(4),FRI(5),SAT(6),SUN(7),WRONG_DAY(0);

    private int numOfDay;

    Day(int numOfDay) {
        this.numOfDay = numOfDay;
    }

    public int getAbbr(){
        return this.numOfDay;
    }

    public boolean isWeekend() {
        switch (this) {
            case SAT:
            case SUN:
                return true;
            default:
                return false;
        }
    }

    public Enum getDay(int day) {
        switch (day) {
            case 1:
                return MON;
            case 2:
                return TUE;
            case 3:
                return WED;
            case 4:
                return THU;
            case 5:
                return FRI;
            case 6:
                return SAT;
            case 7:
                return SUN;
            default:
                return WRONG_DAY;
        }
    }

}
