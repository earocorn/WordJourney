//package wordjourney.graphics;
//
//public class GameLoop implements Runnable{
//    private boolean running;
//    private final double updateRate= 1.00/60.00;
//
//    private long nextStatTime;
//    public static int UPDATES_PER_SECOND =1;
//    private int fps, ups;
//    @Override
//    public void run(){
//        running = true;
//        double accumulator = 0;
//        long currentTime, lastUpdate = System.currentTimeMillis();
//
//        nextStatTime = System.currentTimeMillis()+1000;
//        while (running){
//            currentTime= System.currentTimeMillis();
//            double lastRenderTimeInSeconds = (currentTime - lastUpdate)/1000d;
//            accumulator = lastRenderTimeInSeconds;
//            lastUpdate = currentTime;
//
//
//            while(accumulator > updateRate){
//                update();
//                accumulator-= updateRate;
//
//            }
//            render();
//            printState();
//        }
//
//
//    }
//    private void printState(){
//
//        if (System.currentTimeMillis() > nextStatTime){
//            System.out.println((String.format("FPS: %d, UPS: %d", fps, ups)));
//            fps=0;
//            ups=0;
//            nextStatTime= System.currentTimeMillis()+1000;
//        }
//    }
//    private void update(){
//
//        ups++;
//    }
//    private void render(){
//
//        fps++;
//    }
//}
