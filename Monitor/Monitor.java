package Monitor;

public class Monitor implements MonitorInterface{
    @Override
    public boolean fireTransition(int transition) {
        System.out.println("Transition " + transition + " fired.");
        return true;
    }
}
