package Tut2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SchedulerTest 
{
        @Test
        public void testModuleAssignment() {

                // Create test modules and venues
                List<Module> modules = Arrays.asList(
                        new Module("Introduction to Psychology", 25, "9:00-12:00"),
                        new Module("Microbiology", 20, "8:00-11:00"),
                        new Module("Business Law", 13, "13:00-15:00"),
                        new Module("History", 30, "9:00-12:00")
                );

                List<Venue> venues = Arrays.asList(
                        new Venue("V014", 40, "9:00-12:00"),
                        new Venue("V015", 31, "8:00-11:00"),
                        new Venue("V016", 15, "13:00-15:00"),
                        new Venue("V017", 30, "9:00-12:00")
                );

                // Create the Scheduler object and perform module assignment
                Scheduler scheduler = new Scheduler();
                scheduler.assignModulesToVenues(modules, venues);

                assertEquals(true, venues.get(0).isAssigned());
                assertEquals(true, venues.get(1).isAssigned());
                assertEquals(true, venues.get(2).isAssigned());
                assertEquals(true, venues.get(3).isAssigned());
        }

        @Test
                public void testAssignedModule() {
                Venue venue = new Venue("V014", 40, "14:00-17:00");
                Module module = new Module("Microeconomics", 20, "14:00-17:00");
                
                venue.assignModule(module);
                assertEquals("Microeconomics", venue.getAssignedModule().name);
        }

        @Test
        public void testUnassignedVenues() {
                // Create test modules and venues
                List<Module> modules = Arrays.asList(
                        new Module("Introduction to Psychology", 25, "9:00-12:00"),
                        new Module("Microbiology", 20, "8:00-11:00")                       
                );

                List<Venue> venues = Arrays.asList(
                        new Venue("V018", 35, "16:00-17:00"),  // No module fits this venue
                        new Venue("V019", 25, "10:00-12:00")  // No module fits this venue
                );

                // Create the Scheduler object and perform module assignment
                Scheduler scheduler = new Scheduler();
                scheduler.assignModulesToVenues(modules, venues);

                assertEquals(false, venues.get(0).isAssigned());
                assertEquals(false, venues.get(1).isAssigned());
        }

        // @Test
        // public void testOverlappingTimeSlots() {
        //         List<Module> modules = Arrays.asList(
        //                 new Module("Computer Science", 25, "9:00-12:00"),
        //                 new Module("Philosophy", 20, "10:00-13:00"),
        //                 new Module("Graphic Design", 4, "12:00-18:00")
        //         );

        //         List<Venue> venues = Arrays.asList(
        //                 new Venue("V021", 40, "9:00-12:00"),  
        //                 new Venue("V022", 31, "10:00-13:00"),  
        //                 new Venue("V023", 15, "13:00-15:00")
        //         );

        //         Scheduler scheduler = new Scheduler();
        //         scheduler.assignModulesToVenues(modules, venues);

        //         assertEquals(true, venues.get(0).isAssigned());
        //         assertEquals(true, venues.get(1).isAssigned());
        //         assertEquals(false, venues.get(2).isAssigned());
        // }
        
        @Test
        public void testMultipleModulesPerVenue() {
                List<Module> modules = Arrays.asList(
                        new Module("Roastology", 20, "14:00-16:00"),
                        new Module("Anthology", 15, "14:00-16:00")
                );

                List<Venue> venues = Arrays.asList(
                        new Venue("V024", 40, "14:00-16:00"),
                        new Venue("V025", 20, "14:00-16:00")
                );

                Scheduler scheduler = new Scheduler();
                scheduler.assignModulesToVenues(modules, venues);

                assertEquals(true, venues.get(0).isAssigned());
                assertEquals(true, venues.get(1).isAssigned());
        }

        // @Test
        // public void testVenueCapacityExceeded() {
        //         List<Module> modules = Arrays.asList(
        //                 new Module("Cement Mixology", 20, "09:00-12:00"),
        //                 new Module("Carpentry", 25, "10:00-12:00"),
        //                 new Module("Wizards 101", 30, "11:00-13:00")
        //         );

        //         List<Venue> venues = Arrays.asList(
        //                 new Venue("V026", 25, "09:00-12:00"),
        //                 new Venue("V027", 20, "10:00-12:00"), //Capacity not enough
        //                 new Venue("V028", 40, "11:00-13:00") 

        //         );

        //         Scheduler scheduler = new Scheduler();
        //         scheduler.assignModulesToVenues(modules, venues);

        //         assertEquals(true, venues.get(0).isAssigned());
        //         assertEquals(false, venues.get(1).isAssigned());
        //         assertEquals(true, venues.get(2).isAssigned());
        // }

        @Test
        public void testNonMatchingTimeSlots() {
                List<Module> modules = Arrays.asList(
                    new Module("Module C", 25, "14:00-16:00"),
                    new Module("Module D", 20, "11:00-13:00")
                );
            
                List<Venue> venues = Arrays.asList(
                    new Venue("V028", 40, "09:00-12:00"),
                    new Venue("V029", 31, "10:00-13:00")
                );
            
                Scheduler scheduler = new Scheduler();
                scheduler.assignModulesToVenues(modules, venues);
            
                assertEquals(false, venues.get(0).isAssigned());
                assertEquals(false, venues.get(1).isAssigned());
            }
            
        // @Test
        // public void testGenerateSchedulerOutput() {
        //         List<Venue> venues = Arrays.asList(
        //                 new Venue("V014", 40, "9:00-12:00"),
        //                 new Venue("V015", 31, "8:00-11:00"),
        //                 new Venue("V016", 15, "13:00-15:00"),
        //                 new Venue("V017", 30, "9:00-12:00")
        //         );
            
        //         List<Module> modules = Arrays.asList(
        //                 new Module("Introduction to Psychology", 25, "9:00-12:00"),
        //                 new Module("Microbiology", 20, "8:00-11:00"),
        //                 new Module("Business Law", 13, "13:00-15:00"),
        //                 new Module("History", 30, "9:00-12:00")
        //         );
            
        //         // Create the Scheduler object and perform module assignment
        //         Scheduler scheduler = new Scheduler();
        //         scheduler.assignModulesToVenues(modules, venues);
            
        //         String expectedOutput = "Venue: V014 assigned to Module: Introduction to Psychology\n" +
        //                                 "Venue: V015 assigned to Module: Microbiology\n" +
        //                                 "Venue: V016 assigned to Module: Business Law\n" +
        //                                 "Venue: V017 assigned to Module: History\n";
            
        //         assertEquals(expectedOutput, scheduler.generateSchedulerOutput(venues));
        // }

}
