package Tut2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scheduler {
    public static void main(String[] args) {
        List<Module> modules = readModulesFromFile("modules.txt");
        List<Venue> venues = readVenuesFromFile("venues.txt");

        assignModulesToVenues(modules, venues);

        String schedulerOutput = generateSchedulerOutput(venues);
        System.out.println(schedulerOutput);
    }

    public static void assignModulesToVenues(List<Module> modules, List<Venue> venues) {
        // Sort venues by capacity.
        venues.sort(new Comparator<Venue>() {
            @Override
            public int compare(Venue v1, Venue v2) {
                return Integer.compare(v1.venueCapacity, v2.venueCapacity);
            }
        });
        
        for (Module module : modules) {
            for (Venue venue : venues) {
                if (!venue.isAssigned() && module.availableTime.equals(venue.availableTime)
                        && module.classSize <= venue.venueCapacity) {
                    venue.assignModule(module);
                    break; // Move to the next module
                }
            }
        }
    }

    public static List<Module> readModulesFromFile(String filename) {
        List<Module> modules = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String name = null;
            int classSize = 0;
            String availableTime = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Module name:")) {
                    name = line.substring(line.indexOf(":") + 2);
                } else if (line.startsWith("Class size:")) {
                    classSize = Integer.parseInt(line.substring(line.indexOf(":") + 2));
                } else if (line.startsWith("Available time:")) {
                    availableTime = line.substring(line.indexOf(":") + 2);
                    modules.add(new Module(name, classSize, availableTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modules;
    }

    public static List<Venue> readVenuesFromFile(String filename) {
        List<Venue> venues = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String venueNumber = null;
            int venueCapacity = 0;
            String availableTime = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Venue number:")) {
                    venueNumber = line.substring(line.indexOf(":") + 2);
                } else if (line.startsWith("Venue capacity:")) {
                    venueCapacity = Integer.parseInt(line.substring(line.indexOf(":") + 2));
                } else if (line.startsWith("Available time:")) {
                    availableTime = line.substring(line.indexOf(":") + 2);
                    venues.add(new Venue(venueNumber, venueCapacity, availableTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return venues;
    }

    public static String generateSchedulerOutput(List<Venue> venues) {
        StringBuilder output = new StringBuilder();
        for (Venue venue : venues) {
            if (venue.isAssigned()) {
                output.append("Venue: ").append(venue.venueNumber)
                      .append(" assigned to Module: ").append(venue.getAssignedModule().name)
                      .append("\n");
            }
        }
        return output.toString();
    }
}

class Module {
    String name;
    int classSize;
    String availableTime;

    public Module(String name, int classSize, String availableTime) {
        this.name = name;
        this.classSize = classSize;
        this.availableTime = availableTime;
    }
}

class Venue {
    String venueNumber;
    int venueCapacity;
    String availableTime;
    boolean assigned;
    private Module assignedModule;

    public Venue(String venueNumber, int venueCapacity, String availableTime) {
        this.venueNumber = venueNumber;
        this.venueCapacity = venueCapacity;
        this.availableTime = availableTime;
        this.assigned = false;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void assignModule(Module module) {
        this.assignedModule = module;
        this.assigned = true;
    }

    public Module getAssignedModule() {
        return this.assignedModule;
    }
}


