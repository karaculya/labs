# Вопросы
## Вопросы к 4 лабе:
1. Модель OSI. 
> В течение последних нескольких десятилетий размеры и количество сетей значительно выросли. В 80-х годах имелось множество типов сетей. И практически каждая из них была построена на своем типе оборудования и программного обеспечения, зачастую не совместимых между собой. Это приводило к значительным трудностям при попытке соединить несколько различных типов сетей (например, различный тип адресации делал эти попытки практически безнадежными). Эта проблема была рассмотрена Всемирной Организацией по Стандартам (International Organization for Standardization, ISO), и в результате в 1984 г. была разработана модель OSI - модель взаимодействия открытых систем (Open Systems Interconnected). Эта модель состоит из семи уровней:
> - **Физический уровень** Этот уровень описывает среду передачи данных. Стандартизируются физические устройства, отвечающие за передачу электрических сигналов (разъемы, кабеля и т.д.) и правила формирования этих сигналов. Физический уровень также отвечает за преобразование сигналов между различными средами передачи данных. Например, при необходимости соединить сегмент сети, построенной на оптоволокне и витой паре применяют т.н. конверторы (в данном случае они преобразуют световой импульс в электрический).
> - **Уровень соединения** (канальный уровень). На физическом уровне пересылаются просто набор сигналов - битов. При этом не проверяется, что несколько компьютеров могут в одну среду передачи данных одновременно передавать информацию в виде битов. Поэтому одной из задач канального уровня является проверка доступности среды передачи. Также этот уровень отвечает за доставку между источником и адресатом в пределах сети с одной топологией.
> - **Сетевой уровень** Одним из ограничений канального уровня является использование “плоской” модель адресации. Протокол, который поддерживается сетевым уровнем, использует иерархическую структуру для уникальной идентификации компьютеров. Для примера представим себе телефонную сеть. Она также имеет иерархическую адресацию. Например, в номере +7-095-101-12-34 первая цифра обозначает код страны, далее идет код области/города(095), а затем указывается сам телефон (101-12-34). Последний номер также является составным. 101 - это код станции, куда подключен телефон, а 12-34 определяет местоположение телефона. Благодаря такой иерархической структуре мы можем определить расположение требуемого абонента с наименьшими затратами. Иерархическая адресация для сети также должна позволять передавать данные между разрозненными и удаленными сетями.
> - **Транспортный уровень** Рассмотрим TCP/IP протокол транспортного уровня модели OSI. TCP/IP имеет два протокола - TCP и UDP. Transmission Control Protocol TCP – основанный на соединениях протокол, обеспечивающий надежную передачу данных между двумя компьютерами с сохранением порядка данных. Используется в HTTP, FTP. User Datagram Protocol UDP – не основанный на соединениях протокол, реализующий пересылку независимых пакетов данных, называемых дейтаграммами, от одного компьютера к другому без гарантии их доставки).
> - **Сеансовый уровень** Обеспечивает установку, контроль и окончание сессии между приложениями. Уровень сессий координирует приложения, когда они взаимодействуют между двумя хостами.
> - **Уровень представления** Уровень представлений отвечает за представление данных в форме, понятной получателю. Например, для представления данных могут быть использованы такие кодировки, как Extended Binary Coded Decimal Interchange Code (EBCDIC) или American Standard Code for Information Interchange (ASCII). Если компьютеры, между которыми установлено сетевое соединение, используют различные протоколы, то presentation layer обеспечивает их корректное взаимодействие. Уровень представлений также отвечает за шифрацию и сжатие данных.
> - **Прикладной уровень** Уровень приложений определяет, какие ресурсы существуют для связи между хостами.
> 
> Каждый уровень взаимодействует только с соседними уровнями, протокол взаимодействия стандартизован. Это позволяет использовать реализации сетевого и программного обеспечения от разных производителей в различных комбинациях. Например, протоколы высокого уровня (HTTP, FTP) не зависят от физических параметров используемой сети.
> Большинство сетевых приложений можно классифицировать как клиент-серверные приложения.
2. Модель «Клиент-сервер». Понятие порта. Абстракция сокета. 
> **Модель «Клиент-сервер»**
> Каждая из сторон виртуального соединения называется «сокет» (socket). Приложение-сервер инициализируется при запуске и далее бездействует, ожидая поступления запроса от клиента. Типы приложений-серверов - Сервер последовательной обработки запросов и Сервер параллельной обработки запросов. Процесс-клиент посылает запрос на установление соединения с сервером, требуя выполнить для него определенную функцию.

> **Понятие порта**
> Компьютер (обычно) имеет только одно физическое соединение с сетью. Соединение описывается, например, IP-адресом (32 бита на сегодняшний день). Как различать информацию для различных приложений? Сокет привязывается к порту. Порт описывается 16-битным числом. Порты 0-1023 зарезервированы

> **Абстракция сокета**
> Сетевое соединение –  это процесс передачи данных по сети между двумя компьютерами или процессами. Сокет – конечный пункт передачи данных. Для программ сокет – одно из окончаний сетевого соединения. Для установления соединения каждая из сетевых программ должна иметь свой собственный сокет. Связь между двумя сокетами может быть ориентированной или не ориентированной на соединение. Сокет связан с номером порта.

3. Пакет java.net. Класс Socket. Порядок работы с сокетом клиента. 
> **Пакет java.net**
> - Адресация.
> - Установление TCP-соединения. 
> - Передача/прием дейтаграмм через UDP. 
> - Обнаружение/идентификация сетевых ресурсов. 
> - Безопасность: авторизация / права доступа.
> 
> Класс InetAddress является интернет-адресом, или  IP. Экземпляры этого класса создаются не с помощью конструкторов, а с помощью статических методов:
> - InetAddress getLocalHost() - возвращает IP-адрес машины, на которой исполняется Java-программа. 
> - InetAddress getByName(String name) - возвращает адрес сервера, чье имя передается в качестве параметра. Это может быть как DNS-имя, так и числовой IP, записанный в виде текста, например, "67.11.12.101". 
> - InetAddress[] getAllByName(String name) - определяет все IP-адреса указанного сервера.
>
> Класс Inet4Address служит для описания адреса, состоящего из 4 байтов, с помощью класса Inet6Address описывается адрес, состоящий из 16 байтов. Начиная с JDK 1.4. существует класс InetSocketAddress(String hostname, int port), который создаёт объект адреса для указанного узла и порта.

> **Класс Socket**
> - Реализует клиентский сокет и его функции 
> - Конструкторы
>   - Socket()
>   - Socket(InetAddress address, int port)
>   - Socket(InetAddress address, int port, InetAddress localAddr, int localPort)
>   - Socket(String host, int port)
>   - Socket(String host, int port, InetAddress localAddr, int localPort)
> - Методы
>   - void close() – закрывает используемый сокет. 
>   - InetAddress getLocalAddress() 
>   - InputStream getInputStream() – возвращает поток, позволяющий читать данные, переданные по сети. 
>   - OutputStream getOutputStream() - возвращает поток, позволяющий передавать данные по сети. 
>   - static void setSocketImplFactory(SocketImplFactory fac)
>   - И прочие…

> **Порядок работы с сокетом клиента**
> - Открытие сокета. 
> - Открытие потока ввода и/или потока вывода для сокета. 
> - Чтение и запись в потоки согласно установленному протоколу общения с сервером. 
> - Закрытие потоков ввода-вывода. 
> - Закрытие сокета.
> 
> For example:
> ```
> import java.io.*;
> import java.net.*;
>
> public class EchoClient {
> public static void main(String[] args) throws IOException {
> Socket echoSocket = null;
> PrintWriter out = null;
> BufferedReader in = null;
> try {
> echoSocket = new Socket("taranis", 7);
> out = new PrintWriter(echoSocket.getOutputStream(), true);
> in = new BufferedReader(new InputStreamReader(
> echoSocket.getInputStream()));        
> } catch (UnknownHostException e) {
> System.err.println("Don't know about host: taranis.");
> System.exit(1);
> } catch (IOException e) {
> System.err.println("Couldn't get I/O for the connection to:" + "taranis.");
> System.exit(1);
> }
> BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
> String userInput;
>
>    while ((userInput = stdIn.readLine()) != null) {
>      out.println(userInput);
>      System.out.println("echo: " + in.readLine());
>    }
>
>    out.close();
>    in.close();
>    stdIn.close();
>    echoSocket.close();
>   }
> }
> ```

4. Класс ServerSocket. Сервер параллельной обработки запросов.
> **Класс ServerSocket**
> - Реализует серверный сокет и его функции 
> - Конструкторы 
>   - ServerSocket()
>   - ServerSocket(int port)
>   - ServerSocket(int port, int backlog)
> - Методы 
>   - void close() – закрывает используемый сокет 
>   - Socket accept() – приостагавливает выполнение программы и ожидает обращения клиента 
>   - void bind(SocketAddress endpoint)
>   - И прочие…
> 
> For example:
> ```
> try {
> serverSocket = new ServerSocket(4444); 
> } catch (IOException e) {
> System.out.println("Could not listen on port: 4444");
> System.exit(-1); 
> } 
> Socket clientSocket = null; 
> try {
> clientSocket = serverSocket.accept(); 
> } catch (IOException e) {
> System.out.println("Accept failed: 4444"); 
> System.exit(-1); 
> }
> ```

> **Сервер параллельной обработки запросов**
> 
> ![image](first_labs/src/main/resources/parallel-processing.png)
5. Дейтаграммы. 
> Дейтаграмма – независимое, самодостаточное сообщение, посылаемое по сети, чья доставка, время (порядок) доставки и содержимое не гарантируются. Могут использоваться как для адресной, так и для широковещательной рассылки.
> **DatagramPacket**
> - Экземпляры являются прототипами дейтаграмм-сообщений 
> - Конструкторы 
> - DatagramPacket(byte[] buf, int offset, int length, InetAddress address, int port)
> - И прочие… 
> - Методы 
>   - byte[] getData()
>   - int getLength()
>   - int getOffset()
>   - SocketAddress getSocketAddress()
>   - void setSocketAddress(SocketAddress address)
>   - void setData(byte[] buf, int offset, int length)
>   - И прочие… 
> 
> **DatagramSocket**
> - Экземпляры являются не ориентированными на соединение сокетами 
> - Конструкторы 
>   - DatagramSocket()
>   - DatagramSocket(int port, InetAddress laddr)
>   - И другие… 
> - Методы 
>   - void bind(SocketAddress addr)
>   - void close()
>   - void connect(InetAddress address, int port)
>   - void send(DatagramPacket p)
>   - void receive(DatagramPacket p)
>   - И другие…

6. Uniform Resource Locator.
> - URL – адрес ресурса в Интернет 
> - Имя протокола 
> - Протокол, используемый для связи 
> - Имя хоста 
> - Имя компьютера, на котором расположен ресурс 
> - Имя файла 
> - Путь к файлу на компьютере 
> - Номер порта
> - Номер порта для соединения (необязателен, если порт не указан, то используется значение по умолчанию для указанного протокола)
> - Ссылка
> - Ссылка на именованный якорь (необязательна)
> - Может быть абсолютным и относительным
> 
> URL gamelan = new URL("http", "www.gamelan.com", 80, "pages/Gamelan.network.html"); 
> Для простого извлечения содержимого заданного ресурса достаточно использовать метод openStream() класса URL. Этот метод возвращает объект InputStream. Прямое чтение из URL:
> ```
> import java.net.*;
> import java.io.*;
> 
> public class URLReader {
> public static void main(String[] args) throws Exception {
> URL yahoo = new URL("http://www.yahoo.com/");
> BufferedReader in = new BufferedReader(
> new InputStreamReader(
> yahoo.openStream()));
> String inputLine;
> while ((inputLine = in.readLine()) != null)
> System.out.println(inputLine);
> in.close();
>   }
> }
> ```
> Для получения дополнительной информации о ресурсе потребуется использовать класс URLConnection, который предоставляет гораздо больше средств управления доступом к Web-ресурсам. Для получения объекта URLConnection нужно вызвать метод openConnection() класса URL.  Чтение из URL-соединения:
> ```
> import java.net.*;
> import java.io.*;
> public class URLConnectionReader {
> public static void main(String[] args) throws Exception {
> URL yahoo = new URL("http://www.yahoo.com/");
> URLConnection yc = yahoo.openConnection();
> BufferedReader in = new BufferedReader(
> new InputStreamReader(
> yc.getInputStream()));
> String inputLine;
> while ((inputLine = in.readLine()) != null)
> System.out.println(inputLine);
> in.close();
>   }
> }
>```
> Существует несколько методов класса URLConnection, предназаначеннных для указания свойств соединения ещё до подключения к серверу. По умолчанию соединение получает входной поток для чтения, но не получает выходной поток для записи. Для получения выходного потока нужно вызвать метод setDoOutput(true). Запись в URL-соединение:
> ```
> import java.io.*;
> import java.net.*;
> public class Reverse {
> public static void main(String[] args) throws Exception {
> if (args.length != 1) {
> System.err.println("Usage:  java Reverse" + "string_to_reverse");
> System.exit(1);
> }
> String stringToReverse = URLEncoder.encode(args[0], “US-ASCII”);
> URL url = new URL("http://java.sun.com/cgi-bin/backwards");
> URLConnection connection = url.openConnection();
> connection.setDoOutput(true);
> PrintWriter out = new PrintWriter(
> connection.getOutputStream());
> out.println("string=" + stringToReverse);
> out.close();
> BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
> String inputLine;
> while ((inputLine = in.readLine()) != null)
> System.out.println(inputLine);	
> in.close();
>   }
> }
> ```