package util.collection;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> hs = new HashSet<>();
        hs.add("Beta");
        hs.add("Alfa");
        hs.add("Eta");
        hs.add("Gamma");
        hs.add("Epsilion");
        hs.add("Omega");
        System.out.println(hs);
        Car car = new Car("Honda", new Date());
        Set<Car> cars = new HashSet<>();
        cars.add(car);
        car.setCreation(new Date());
        System.out.println(cars.contains(car));
    }

    private static class Car {
        private String mark;
        private Date creation;

        public Car(String mark, Date creation) {
            this.mark = mark;
            this.creation = creation;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public Date getCreation() {
            return creation;
        }

        public void setCreation(Date creation) {
            this.creation = creation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Car car = (Car) o;
            return Objects.equals(mark, car.mark) &&
                    Objects.equals(creation, car.creation);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mark, creation);
        }
    }
}
