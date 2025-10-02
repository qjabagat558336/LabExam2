import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

public class Lab2 {

    public static void main(String[] args) {
        ERQueue er = new ERQueue();
        er.arrive("Pedro Cruz", 1, "Head injury - NOW UNCONSCIOUS ⚠️", "23:52");
        er.arrive("Carlos Mendoza", 2, "Compound fracture - leg", "23:50");
        er.arrive("Lisa Wang", 2, "Severe asthma attack", "23:56");
        er.arrive("Maria Santos", 3, "Deep laceration on arm", "23:48");
        er.arrive("Ana Lim", 4, "Sprained ankle", "23:49");

        er.displayQueue();
        er.treatNext();
        er.displayQueue();
    }
}

class Patient implements Comparable<Patient> {
    String name;
    int priority;
    String condition;
    String arrivalTime;

    public Patient(String name, int priority, String condition, String arrivalTime) {
        this.name = name;
        this.priority = priority;
        this.condition = condition;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Patient other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        } else {
            LocalTime thisTime = LocalTime.parse(this.arrivalTime, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime otherTime = LocalTime.parse(other.arrivalTime, DateTimeFormatter.ofPattern("HH:mm"));
            return thisTime.compareTo(otherTime);
        }
    }

    @Override
    public String toString() {
        return "[P" + priority + "] " + name + " - " + condition + " (" + arrivalTime + ")";
    }
}

class ERQueue {

    private PriorityQueue<Patient> queue;

    public ERQueue() {
        queue = new PriorityQueue<>();
    }

    public void arrive(String name, int priority, String condition, String time) {
        Patient p = new Patient(name, priority, condition, time);
        queue.add(p);
    }

    public void treatNext() {
        if (queue.isEmpty()) {
            System.out.println(">>> No patients to treat.");
            return;
        }
        Patient next = queue.poll();
        System.out.println("\n>>> Treating patient now...");
        System.out.println("Treated: " + next.toString());
    }

    public void displayQueue() {
        System.out.println("\n=== UPDATED QUEUE ===");
        System.out.println("Patients Waiting: " + queue.size());

        ArrayList<Patient> tempList = new ArrayList<>(queue);
        Collections.sort(tempList);

        int count = 1;
        for (Patient p : tempList) {
            System.out.println(count + ". " + p.toString());
            count++;
        }
    }
}




