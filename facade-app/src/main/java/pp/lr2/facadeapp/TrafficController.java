package pp.lr2.facadeapp;

public class TrafficController {
    private TrafficLight trafficLight = new TrafficLight();
    private Car car = new Car();

    public void update() {
        trafficLight.update();
        car.update(trafficLight.isRed() && car.getX() > 200);
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public Car getCar() {
        return car;
    }
}
