//package wordjourney.graphics;
//
//
////this will start every time the word resets and the user begins to type
//// will need a key listner like the enter button
//
//public class GameTimer extends GameTime {
//
//    private Runnable callBack;
//
//    public GameTimer(double seconds, Runnable callBack){
//        currentUpdate = getUpdatesFromSeconds(seconds);
//        this.callBack = callBack;
//    }
//
//    @Override
//    public void update(){
//        if (currentUpdate > 0){
//            currentUpdate--;
//
//            if(currentUpdate ==0){
//                callBack.run();
//            }
//        }
//    }
//}
