### Design patterns

1. Порождающие паттерны

    1.1. Singleton
   - в классе объявляем поле того класса который должен быть синглтон 
   - добавляем пустой приватный конструктор 
   - добавляем метод который создает объект, если поле null, в ином случае - возвращает имеющийся

 ```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Singleton {
    private static Properties INSTANCE;
    private static final String PATH = "/singleton/config.properties";
    
    private Singleton() {}

    public static synchronized Properties getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new Properties();
                INSTANCE.load(new FileInputStream(PATH));
            } catch (IOException e) {
                System.out.println("Something goes wrong");
            }
        }
        return INSTANCE;
    }
}
```
 ```java
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties p = Singleton.getInstance();
        System.out.println(p.getProperty("user"));
        System.out.println(p.getProperty("company"));
        System.out.println(p.getProperty("name"));
        System.out.println(p.getProperty("date"));
    }
}
```

1.2. Factory Method
- создаем интерфейс с методом **createInstance** возвращающий интерфейс 
```java
import main.java.labs.model.Transport;

public interface TransportFactory {
    Transport createInstance(String mark, int modelsSize);
}
```
- создаем конкретные фабрики имплементирующие этот интерфейс 
```java
import main.java.labs.model.Car;
import main.java.labs.model.Transport;

public class AutoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String mark, int modelsSize) {
        return new Car(mark, modelsSize);
    }
}
```
```java
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;

public class MotoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String mark, int modelsSize) {
        return new Motorbike(mark, modelsSize);
    }
}
```
- у них один единственный метод createInstance() который в зависимости от фабрики возвращает нужный объект
```java
public class TransportUtils {
    private static TransportFactory factory = new AutoFactory();

    public static void setTransportFactory(TransportFactory factory) {
        TransportUtils.factory = factory;
    }

    public static Transport createInstance(String name, int size) {
        return factory.createInstance(name, size);
    }
}
```
**Вызов в main:**
```java
public class Main {
    public static void main(String[] args) {
        Transport transport = TransportUtils.createInstance("auto", 3);
        System.out.println(transport.getClass().getSimpleName());

        TransportUtils.setTransportFactory(new MotoFactory());
        transport = TransportUtils.createInstance("moto", 2);
        System.out.println(transport.getClass().getSimpleName());
    }
}
```

1.3. Prototype
- Добавить в транспортные классы реализации методов Object clone(). Клонирование должно быть глубоким. Использовать super.clone(). 

**Car:**
```java
import java.io.*;
import java.util.Arrays;

public class Car implements Transport, Cloneable {
    private String mark;
    private Model[] models;

    public Car(String mark, int modelsSize) {
        this.mark = mark;
        this.models = new Model[modelsSize];
        for (int i = 0; i < modelsSize; i++) {
            this.models[i] = new Model("name" + (i + 1), i + 1);
        }
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        Car cloned = (Car) super.clone();
        cloned.models = Arrays.copyOf(this.models, getSize());
        for (int i = 0; i < getSize(); i++) {
            cloned.models[i] = (Model) this.models[i].clone();
        }
        return cloned;
    }
    
    // another methods
    
    //  внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор (класс Автомобиль хранит массив Моделей),
    private class Model implements Serializable, Cloneable {

        private String modelName;
        private double price;

        Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
```

**Motorbike:**
```java
import java.io.Serializable;
import java.util.Date;

public class Motorbike implements Transport {
    private int size;
    private Model head;
    private transient long lastModified;
    private String mark;

    {
        this.head = new Model("headModel", null, null, 101);
        this.head.prev = this.head;
        this.head.next = this.head;
        this.lastModified = new Date().getTime();
    }

    public Motorbike(String mark, int size) {
        this.mark = mark;
        this.size = size;
        if (size > 1) {
            Model prev = null;
            for (int i = 0; i < size - 1; i++) {
                Model currentModel = new Model("name" + (i + 1), null, null, i + 1);
                if (i == 0) {
                    this.head.prev = currentModel;
                    this.head.next = currentModel;
                    currentModel.prev = this.head;

                } else {
                    currentModel.prev = prev;
                    this.head.prev.next = currentModel;
                    this.head.prev = currentModel;
                }
                currentModel.next = this.head;
                prev = currentModel;
            }
        }
    }

    @Override
    public Motorbike clone() throws CloneNotSupportedException {
        Motorbike cloned = (Motorbike) super.clone();
        cloned.head = new Model("headModel", null, null, 101);
        cloned.head.next = cloned.head;
        cloned.head.prev = cloned.head;
        Model original = this.head.next;
        Model clone = cloned.head.next;
        while (original != this.head) {
            Model current = (Model) original.clone();
            Model last = cloned.head.prev;
            last.next = current;
            current.prev = last;
            current.next = cloned.head;
            cloned.head.prev = current;
            clone = clone.next;
            original = original.next;
        }
        return cloned;
    }
    
    // another methods

    private class Model implements Serializable, Cloneable {
        String modelName;
        double price;
        Model prev;
        Model next;

        public Model(String modelName, Model next, Model prev, double price) {
            this.modelName = modelName;
            this.next = next;
            this.prev = prev;
            this.price = price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
```
- Проверить работу методов clone() в методе main().
```java
import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.NoSuchModelNameException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Car car = new Car("audi", 2);
            Car clonedCar = car.clone();
            printOriginalAndClone(car, clonedCar);
            clonedCar.setModelName("name1", "model1");
            printOriginalAndClone(car, clonedCar);

            System.out.println("---------------------------------------");

            Motorbike moto = new Motorbike("moto", 3);
            Motorbike clonedMoto = moto.clone();
            printOriginalAndClone(moto, clonedMoto);
            clonedMoto.setModelName("name1", "model1");
            printOriginalAndClone(moto, clonedMoto);
        } catch (DuplicateModelNameException | NoSuchModelNameException | CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printOriginalAndClone(Transport transport, Transport clonedTransport) {
        System.out.println("Original: " + transport.getMark() + " " + Arrays.toString(transport.getModels()));
        System.out.println("Clone: " + clonedTransport.getMark() + " " + Arrays.toString(clonedTransport.getModels()));
    }
}
```

**Теория:**
- Abstract Factory 
- Builder 
- Factory Method 
- Prototype 
- Singleton.

2. Структурные паттерны

    2.1 Adapter
    - Реализовать класс адаптера, метод которого принимает в качестве параметра массив строк и записывает их по очереди в выходной байтовый поток (OutputStream), который он «адаптирует». 
```java
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public static void writeStringsToOutputStream(String[] strings, OutputStream outputStream) throws IOException {
        for (String str : strings) {
            if (str != null) {
                outputStream.write(str.length());
                outputStream.write(str.getBytes());
            }
        }
        outputStream.flush();
    }

    public static String[] readStringsFromInputStream(InputStream inputStream) throws IOException {
        List<String> strings = new ArrayList<>();

        int length;
        while ((length = inputStream.read()) != -1) {
            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            strings.add(new String(buffer));
        }

        return strings.toArray(new String[0]);
    }
}
```
- Продемонстрировать работу в методе main().
```java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] originalStrings = {"Hello", "World", "Java"};

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Adapter.writeStringsToOutputStream(originalStrings, outputStream);
            System.out.println(outputStream);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            String[] readStrings = Adapter.readStringsFromInputStream(inputStream);
            System.out.println(Arrays.toString(readStrings));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

2.2 Decorator
- Добавить в класс со статическими методами реализацию метода Transport synchronizedTransport (Transport t), возвращающего ссылку на
класс-обертку указанного транспортного средства, безопасный с точки
зрения многопоточности.
```java
public class TransportUtils {
    public static Transport synchronizedTransport(Transport t) {
        return new DecoratorSynchronizedTransport(t);
    }
}
```

- Для этого потребуется описать некий новый
класс, реализующий интерфейс Транспортное средство.
```java
public class DecoratorSynchronizedTransport implements Transport {
    private Transport transport;

    public DecoratorSynchronizedTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized int getSize() {
        return transport.getSize();
    }

    @Override
    public synchronized void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        transport.addNewModel(modelName, price);
    }

    @Override
    public synchronized void removeModel(String modelName) throws NoSuchModelNameException {
        transport.removeModel(modelName);
    }

    // and other methods
}
```

2.3 Facade

Предоставляет унифицированный интерфейс вместо набора интерфейсов
некоторой подсистемы. Фасад определяет интерфейс более высокого уровня,
который упрощает использование подсистемы.

В качестве примера: работник МФЦ, у которого мы спрашиваем об услугах
и который скрывает за собой работу других сотрудников, что приводит к 
меньшему взаимодействию обращающихся с системой МФЦ

2.4 Proxy

- server
```java
package main.java.labs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input = in.readLine();
            String[] numbers = input.split(" ");
            double num1 = Double.parseDouble(numbers[0]);
            double num2 = Double.parseDouble(numbers[1]);
            double result = num1 * num2;

            out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

- main:
```java
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Сервер запущен и ожидает подключения...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

- client:
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Подключено к серверу. Введите два числа, разделенных пробелом:");
            
            String userInput = scanner.nextLine();
            out.println(userInput);
            
            String response = in.readLine();
            System.out.println("Результат умножения: " + response);
        } catch (IOException e) {
            System.err.println("Ошибка в работе клиента: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

**Теория:**
- Adapter
- Bridge
- Composite
- Decorator
- Facade
- Flyweight
- Proxy

3. Образцы поведения

   3.1 Chain of Responsibility
   - Создаём интерфейс с двумя методами – установить следующего в качестве параметра этот же интерфейс и task()

```java
import main.java.labs.model.Transport;

public interface ChainOfResponsibility {
   void writeTransport(Transport transport);

   void setNextChainOfResponsibility(ChainOfResponsibility next);
}
```
   - Создаем два класса реализующих этот интерфейс
```java
import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterColumns implements ChainOfResponsibility {
    private ChainOfResponsibility next;

    @Override
    public void writeTransport(Transport transport) {
        if (transport.getSize() > 3) {
            try {
                TransportUtils.writeTransport(transport, new BufferedWriter(new FileWriter("first_labs/src/main/resources/2.txt")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (this.next != null) {
            next.writeTransport(transport);
        } else
            System.out.println(this.getClass().getSimpleName() +
                    ": Необходимо сменить исполнителя");
    }

    @Override
    public void setNextChainOfResponsibility(ChainOfResponsibility next) {
        this.next = next;
    }
}
```

```java
package main.java.labs.patterns.behavioral;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.FileWriter;
import java.io.IOException;

public class WriterRows implements ChainOfResponsibility {
    private ChainOfResponsibility next;

    @Override
    public void writeTransport(Transport transport) {
        if (transport.getSize() <= 3) {
            try (FileWriter writer = new FileWriter("first_labs/src/main/resources/1.txt")) {
                TransportUtils.writeTransportToRow(transport, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (this.next != null) {
            next.writeTransport(transport);
        } else
            System.out.println(this.getClass().getSimpleName() +
                    ": Необходимо сменить исполнителя");
    }

    @Override
    public void setNextChainOfResponsibility(ChainOfResponsibility next) {
        this.next = next;
    }
}
```
   - Они инкапсулирует в себе объект типа интерфейса и в переопределенном методе устанавливают её

```java
public class Main {
    public static void main(String[] args) {
        Car car = new Car("car model", 4);
        ChainOfResponsibility writerTransport = new WriterRows();
        writerTransport.writeTransport(new Motorbike("moto model", 2));
        writerTransport.setNextChainOfResponsibility(new WriterColumns());
        writerTransport.writeTransport(car);
    }
}
```

   3.2 Command
- Интерфейс с одним методом
```java
import main.java.labs.model.Car;

import java.io.Writer;

public interface Command {
    void writeCar(Car car, Writer writer);
}
```
- Реализации этого интерфейса
```java

import main.java.labs.model.Car;
import main.java.labs.utils.TransportUtils;

import java.io.Writer;

public class CommandColumns implements Command {
    @Override
    public void writeCar(Car car, Writer writer) {
        TransportUtils.writeTransport(car, writer);
    }
}
```

```java
import main.java.labs.model.Car;
import main.java.labs.utils.TransportUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CommandRows implements Command {
    @Override
    public void writeCar(Car car, Writer writer) {
        try {
            TransportUtils.writeTransportToRow(car, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

- Вызываемые методы в TransportUtils:
```java
public class TransportUtils {
    public static void writeTransport(Transport v, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(v.getClass().getSimpleName());
        writer.println(v.getMark());
        writer.println(v.getSize());

        if (v.getSize() > 0) {
            String[] modelNames = v.getModels();
            for (String modelName : modelNames)
                writer.println(modelName);

            double[] modelPrices = v.getPrices();
            for (double modelPrice : modelPrices)
                writer.println(modelPrice);
        }
        System.out.println("Success symbol writing object");
        writer.flush();
    }

    public static void writeTransportToRow(Transport v, Writer writer) throws IOException {
        writer.write(v.getClass().getSimpleName());
        writer.write(v.getMark());
        writer.write(v.getSize());

        if (v.getSize() > 0) {
            String[] modelNames = v.getModels();
            for (String modelName : modelNames)
                writer.write(modelName);

            double[] modelPrices = v.getPrices();
            for (double modelPrice : modelPrices)
                writer.write(String.valueOf(modelPrice));
        }
        System.out.println("Success symbol writing object");
        writer.flush();
    }
}
```
- Добавление логики в класс автомобиля:
```java
public class Car implements Transport {
    private String mark;
    private Model[] models;
    private transient Command command;

    // other methods

    public void print(Writer out) {
        command.writeCar(this, out);
    }

    public void setPrintCommand(Command command) {
        this.command = command;
    }
}
```
- Main:

```java
public class Main {
    public static void main(String[] args) {
        Car car = new Car("car model", 4);

        car.setPrintCommand(new CommandColumns());
        car.print(new FileWriter("1.txt"));

        car.setPrintCommand(new CommandRows());
        car.print(new FileWriter("2.txt"));
    }
}
```

   3.3 Iterator
   - Сделать класс Модель в классе Автомобиль доступным на уровне
     пакета и статическим. Реализовать в нём метод toString(), возвращающий
     название и цену модели.
```java
//  внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор (класс Автомобиль хранит массив Моделей),
    static class Model implements Serializable {

        private String modelName;
        private double price;

        Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Model{" +
                    "modelName='" + modelName + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
```
   - Реализовать метод java.util.Iterator iterator() в классе Автомобиль.
```java
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Car implements Transport {
    private class AutoIterator implements Iterator<Model> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < models.length;
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return models[currentIndex++];
        }
    }

    public Iterator<Model> iterator() {
        return new AutoIterator();
    }
}
```
- Main:
```java
public class Main {
    public static void main(String[] args)  {
        Car car = new Car("car model", 4);

        Iterator<Car.Model> iterator = car.iterator();

        while (iterator.hasNext()) {
            Car.Model model = iterator.next();
            System.out.println(model);
        }
    }
}
```
   3.4 Memento
   - Реализовать паттерн Memento, обеспечивающий сохранение
     текущего состояния объекта типа Автомобиль:
```java
import java.io.*;

public class Car implements Transport {
    //  поле типа String, хранящее марку автомобиля,
    private String mark;
    private Model[] models;

    // other methods

    public static class Memento {
        private byte[] state;

        private void setAuto(Car car) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(car);
            oos.close();
            this.state = baos.toByteArray();
        }

        private Car getAuto() throws IOException, ClassNotFoundException {
            ByteArrayInputStream bais = new ByteArrayInputStream(this.state);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Car) ois.readObject();
        }
    }

    public Memento createMemento() throws IOException {
        Memento memento = new Memento();
        memento.setAuto(this);
        return memento;
    }

    public void setMemento(Memento memento) throws IOException, ClassNotFoundException {
        Car savedCar = memento.getAuto();
        this.mark = savedCar.mark;
        this.models = savedCar.models;
    }
}
```
   - main:
```java
public class Main {
    public static void main(String[] args)  {
        Car car = new Car("car model", 4);

        try {
            System.out.println("Original Car: " + car);
            Car.Memento memento = car.createMemento();
            car.setMark("Honda");
            System.out.println("Modified Car: " + car);

            car.setMemento(memento);
            System.out.println("Restored Car: " + car);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
```
   3.5 Observer

   3.6 State

   3.7 Strategy
   - Реализовать паттерн Strategy, обеспечивающий подсчёт количества
   вхождений каждого элемента в одномерный массив целых чисел двумя
   разными способами.
```java
import java.util.Map;

public interface Strategy {
    Map<Integer, Integer> count(int[] array);
}
```
   - Реализации: 
```java
import java.util.HashMap;
import java.util.Map;

public class CountIteratorStrategy implements Strategy {
    @Override
    public Map<Integer, Integer> count(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            int count = 0;
            for (int i : array) {
                if (i == num) count++;
            }
            map.put(num, count);
        }
        return map;
    }
}
```

```java
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountStreamStrategy implements Strategy {
    @Override
    public Map<Integer, Integer> count(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toMap(
                        Function.identity(),
                        e -> 1,
                        Integer::sum
                ));
    }
}
```
   - p.s. Имя входного файла, в котором записан исходный массив в виде
     сериализованного объекта, передаётся как параметр командной строки
     приложения. main:

```java
import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*
        int[] array = {1, 2, 3, 4, 2, 3, 1, 2};
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("first_labs/src/main/resources/5.txt"))) {
            oos.writeObject(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        if (args.length == 0) {
            System.out.println("args don't exist file path");
        } else {
            String fileName = args[0];
            int[] array = loadArrayFromFile(fileName);

            if (array == null) {
                System.out.println("failed loading array from file");
                return;
            }

            Strategy strategy = new CountIteratorStrategy();
            long startTime = System.currentTimeMillis();
            Map<Integer, Integer> result = strategy.count(array);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Result: " + result + " for time: " + duration);

            strategy = new CountStreamStrategy();
            startTime = System.currentTimeMillis();
            result = strategy.count(array);
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println("Result: " + result + " for time: " + duration);
        }
    }

    private static int[] loadArrayFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (int[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

   3.8 Template Method

   3.9 Visitor
   - Реализовать паттерн Visitor, обеспечивающий печать полей объекта
     типа Транспортное средство в консоль в столбик или в одну строку
      - Transport:
```java
import java.io.Serializable;

public interface Transport extends Serializable {
    // other methods
    void accept(Visitor visitor);
}
```
- Car:
```java
public class Car implements Transport {
    private String mark;
    private Model[] models;

    // other methods
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
```
- Motorbike:
```java
public class Motorbike implements Transport {
    private int size;
    private Model head;
    private transient long lastModified;
    private String mark;

    // other methods
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
```
   - main:
```java
public class Main {
    public static void main(String[] args)  {
        Car car = new Car("car model", 4);

        Visitor visitor = new PrintVisitor();
        car.accept(visitor);
        new Motorbike("moto", 3).accept(visitor);
    }
}
```
4. Другие виды паттернов

   4.1 MVC
    - Model
```java
import java.util.Map;
import java.util.TreeMap;

public class Model {
    private final Map<Double, Double> points = new TreeMap<>();
    public void addPoint(double x) {
        points.put(x, Math.sin(x));
    }

    public void removePoint(double x) {
        points.remove(x);
    }

    public void updatePoint(double oldX, double newX) {
        if (points.containsKey(oldX)) {
            points.remove(oldX);
            points.put(newX, Math.sin(newX));
        }
    }

    public Map<Double, Double> getPoints() {
        return points;
    }
}
```

```java
public class Point {
    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
```
- Controller
```java
import com.example.mvcapp.model.Model;
import com.example.mvcapp.model.Point;
import com.example.mvcapp.view.CustomDoubleTableCell;
import com.example.mvcapp.view.View;
import javafx.scene.control.TableColumn;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        setupEventHandlers();
        setupTableEditing();
        updateView();
    }

    private void setupEventHandlers() {
        view.getAddButton().setOnAction(event -> {
            try {
                double x = Double.parseDouble(view.getXInput().getText());
                model.addPoint(x);
                updateView();
                view.getXInput().clear();
            } catch (NumberFormatException e) {
                view.getXInput().setText("Ошибка: введите число");
            }
        });

        view.getRemoveButton().setOnAction(event -> {
            try {
                double x = Double.parseDouble(view.getXInput().getText());
                model.removePoint(x);
                updateView();
                view.getXInput().clear();
            } catch (NumberFormatException e) {
                view.getXInput().setText("Ошибка: введите число");
            }
        });
    }
    
    private void setupTableEditing() {
        TableColumn<Point, Double> xColumn = (TableColumn<Point, Double>) view.getTable().getColumns().get(0);

        xColumn.setCellFactory(column -> new CustomDoubleTableCell<>() {
            @Override
            public void commitEdit(Double newValue) {
                if (newValue == null) return;

                Point point = getTableView().getItems().get(getIndex());
                double oldX = point.getX();

                if (newValue != oldX) {
                    model.updatePoint(oldX, newValue);
                    super.commitEdit(newValue);
                    updateView();
                }
            }
        });
    }

    private void updateView() {
        view.updateChartAndTable(model.getPoints());
    }
}
```
- View
```java
import com.example.mvcapp.model.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Map;

public class View {
    private final Stage stage;
    private final LineChart<Number, Number> chart;
    private final XYChart.Series<Number, Number> series;
    private final TextField xInput;
    private final Button addButton;
    private final Button removeButton;
    private final TableView<Point> table;
    private final ObservableList<Point> tableData;

    public View(Stage stage) {
        this.stage = stage;
        this.tableData = FXCollections.observableArrayList();

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        this.chart = new LineChart<>(xAxis, yAxis);
        this.series = new XYChart.Series<>();
        this.xInput = new TextField();
        this.addButton = new Button("Добавить точку");
        this.removeButton = new Button("Удалить точку");
        this.table = new TableView<>();

        initializeUI();
    }

    private void initializeUI() {
        chart.setTitle("y = sin(x)");
        series.setName("sin(x)");
        chart.getData().add(series);

        xInput.setPromptText("Введите x");
        table.setEditable(true);

        TableColumn<Point, Double> xColumn = new TableColumn<>("X");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        xColumn.setPrefWidth(150);

        TableColumn<Point, Double> yColumn = new TableColumn<>("Y");
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        yColumn.setPrefWidth(150);

        table.getColumns().addAll(xColumn, yColumn);
        table.setItems(tableData);

        table.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            xColumn.setPrefWidth(width / 2);
            yColumn.setPrefWidth(width / 2);
        });

        HBox topPanel = new HBox(15, xInput, addButton, removeButton);
        topPanel.setPadding(new Insets(15));

        HBox.setHgrow(xInput, Priority.ALWAYS);
        xInput.setMaxWidth(Double.MAX_VALUE);

        Region spacer = new Region();
        spacer.setPrefHeight(10);

        BorderPane root = new BorderPane();
        root.setTop(topPanel);
        root.setCenter(table);
        root.setBottom(chart);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("График синуса");
    }

    public void updateChartAndTable(Map<Double, Double> points) {
        series.getData().clear();
        tableData.clear();

        points.forEach((x, y) -> {
            series.getData().add(new XYChart.Data<>(x, y));
            tableData.add(new Point(x, y));
        });
    }

    public void show() {
        stage.show();
    }

    public TextField getXInput() {
        return xInput;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public TableView<Point> getTable() {
        return table;
    }
}
```

```java
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;

public class CustomDoubleTableCell<S> extends TableCell<S, Double> {
    private final TextField textField = new TextField();
    private final DoubleStringConverter converter = new DoubleStringConverter();

    public CustomDoubleTableCell() {
        textField.setOnAction(event -> commitEdit());
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) commitEdit();
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (isEmpty()) return;

        textField.setText(converter.toString(getItem()));
        setGraphic(textField);
        textField.selectAll();
        textField.requestFocus();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(converter.toString(getItem()));
        setGraphic(null);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                textField.setText(converter.toString(getItem()));
                setGraphic(textField);
            } else {
                setText(converter.toString(getItem()));
                setGraphic(null);
            }
        }
    }

    private void commitEdit() {
        try {
            Double value = converter.fromString(textField.getText());
            commitEdit(value);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            cancelEdit();
        }
    }
}
```
- main:
```java
import com.example.mvcapp.controller.Controller;
import com.example.mvcapp.model.Model;
import com.example.mvcapp.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class MvcApp extends Application {
    @Override
    public void start(Stage stage) {
        View view = new View(stage);
        Model model = new Model();
        Controller controller = new Controller(model, view);
        view.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
```
   4.2 DAO

- Interface:
```java
import main.java.labs.model.Transport;

import java.io.IOException;

public interface Dao {
    Transport read(String filename);
    void write(Transport transport, String filename) throws IOException;
}
```
- Implementation
```java
import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.*;

public class ByteDao implements Dao {
    @Override
    public Transport read(String filename) {
        try {
            Transport transport = TransportUtils.inputTransport(new FileInputStream(filename));
            System.out.println("Transport from bytes : " + transport);
            return transport;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to reading Transport from bytes. Error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void write(Transport transport, String filename) {
        try {
            TransportUtils.outputTransport(transport, new FileOutputStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

```java
import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.*;

public class SymbolDao implements Dao {
    @Override
    public Transport read(String filename) {
        try {
            Transport transport = TransportUtils.readTransport(new BufferedReader(new FileReader(filename)));
            System.out.println("Transport from symbols : " + transport);
            return transport;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to reading Transport from symbols. Error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void write(Transport transport, String filename) {
        try {
            TransportUtils.writeTransport(transport, new BufferedWriter(new FileWriter(filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
- methods in TransportUtils:
```java
import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;
import main.java.labs.threads.DecoratorSynchronizedTransport;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TransportUtils {
    public static Transport inputTransport(InputStream in) {
        DataInputStream dataIn = new DataInputStream(in);
        Transport transport = null;
        try {
            String className = readString(dataIn);
            if (className.isEmpty()) throw new ClassCastException("Failed casting");

            String mark = readString(dataIn);

            switch (className.toLowerCase()) {
                case ("car") -> transport = new Car(mark, 0);
                case ("motorbike") -> transport = new Motorbike(mark, 0);
            }

            int modelsSize;
            if ((modelsSize = dataIn.readInt()) > 0) {
                String[] allModelNames = new String[modelsSize];
                for (int i = 0; i < allModelNames.length; i++)
                    allModelNames[i] = readString(dataIn);

                double[] allModelPrices = new double[modelsSize];
                for (int i = 0; i < allModelPrices.length; i++)
                    allModelPrices[i] = dataIn.readDouble();

                setModels(transport, allModelNames, allModelPrices);
            }
            System.out.println("Success byte reading object");
        } catch (IOException | DuplicateModelNameException e) {
            System.out.println("Failed byte reading transport from byte stream. " + e.getMessage());
        }
        return transport;
    }

    public static void writeTransport(Transport v, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(v.getClass().getSimpleName());
        writer.println(v.getMark());
        writer.println(v.getSize());

        if (v.getSize() > 0) {
            String[] modelNames = v.getModels();
            for (String modelName : modelNames)
                writer.println(modelName);

            double[] modelPrices = v.getPrices();
            for (double modelPrice : modelPrices)
                writer.println(modelPrice);
        }
        System.out.println("Success symbol writing object");
        writer.flush();
    }

    public static Transport readTransport(Reader in) {
        Transport transport = null;
        try {
            BufferedReader reader = new BufferedReader(in);
            String className = reader.readLine();

            String mark = reader.readLine();
            switch (className.toLowerCase()) {
                case ("car") -> transport = new Car(mark, 0);
                case ("motorbike") -> transport = new Motorbike(mark, 0);
            }

            int modelsSize;
            if ((modelsSize = Integer.parseInt(reader.readLine())) > 0) {
                String[] allModelNames = new String[modelsSize];
                for (int i = 0; i < allModelNames.length; i++)
                    allModelNames[i] = reader.readLine();

                double[] allModelPrices = new double[modelsSize];
                for (int i = 0; i < allModelPrices.length; i++)
                    allModelPrices[i] = Double.parseDouble(reader.readLine());

                setModels(transport, allModelNames, allModelPrices);
            }
            System.out.println("Success symbol reading object");
        } catch (IOException | DuplicateModelNameException e) {
            System.out.println("Failed symbol reading transport from byte stream. " + e.getMessage());
        }
        return transport;
    }

    private static String readString(DataInputStream dataIn) throws IOException {
        int length = dataIn.readInt();
        byte[] bytes = dataIn.readNBytes(length);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeTransport(Transport v, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(v.getClass().getSimpleName());
        writer.println(v.getMark());
        writer.println(v.getSize());

        if (v.getSize() > 0) {
            String[] modelNames = v.getModels();
            for (String modelName : modelNames)
                writer.println(modelName);

            double[] modelPrices = v.getPrices();
            for (double modelPrice : modelPrices)
                writer.println(modelPrice);
        }
        System.out.println("Success symbol writing object");
        writer.flush();
    }
}
```
- main:

```java
public class Main {
    public static void main(String[] args) {
        Transport car = new Car("car model", 4);
        Transport motorbike = new Motorbike("moto model", 3);
        String FILENAME_1 = "path\\1.txt";
        String FILENAME_2 = "path\\2.txt";

        Dao dao = new SymbolDao();
        dao = new ByteDao();
        dao.write(car, FILENAME_1);
        dao.read(FILENAME_1);
        dao.write(motorbike, FILENAME_2);
        dao.read(FILENAME_2);
    }
}
```