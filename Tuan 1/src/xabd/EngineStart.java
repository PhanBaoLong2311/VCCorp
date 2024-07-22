package xabd;

public class EngineStart {
    private Engine engine;

    public EngineStart(Engine engine) {
        this.engine = engine;
    }

    public void startEngine() {
        engine.start();
    }
}
