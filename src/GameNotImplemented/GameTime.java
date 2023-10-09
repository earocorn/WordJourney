//package wordjourney.graphics;
//
//public class GameTime {
//
//    protected int currentUpdate;
//    public GameTime(){
//        this.currentUpdate =0;
//    }
//    public void update(){
//        currentUpdate++;
//
//    }
//    public int getUpdatesFromSeconds(double seconds){
//        return (int) Math.round(seconds * 60);
//    }
//    public String getFormattedTime(){
//        StringBuilder stringBuilder = new StringBuilder();
//        int minutes = currentUpdate/GameLoop.UPDATES_PER_SECOND/60;
//        int seconds = currentUpdate/GameLoop.UPDATES_PER_SECOND % 60;
//
//        if (minutes < 10) {
//            stringBuilder.append(0);
//        }
//        stringBuilder.append(minutes);
//        stringBuilder.append(":");
//        if (seconds<10){
//            stringBuilder.append(0);
//        }
//        stringBuilder.append(seconds);
//        return stringBuilder.toString();
//    }
//
//    public boolean secondsDividableBy(double seconds){
//        return currentUpdate % getUpdatesFromSeconds(seconds) ==0;
//    }
//
//}
