package Introduction_to_Algorithms.Part4.Greedy_Algorithm.Activity_Selection_Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * public: Can be accessed from anywhere.
 * protected: Can be accessed within the same package and by subclasses (even if they are in different packages).
 * package-private (default, no modifier): Can be accessed only within the same package.
 * private: Can be accessed only within the same class.
 */

class Activity {
    int start;
    int finish;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "(" + start + ", " + finish + ")";
    }
}

class GreedyActivitySelection {
    public static List<Activity> selectActivities(List<Activity> activities) {
        List<Activity> selectedActivities = new ArrayList<>();

        // Sort activities by their finish times
        Collections.sort(activities, Comparator.comparingInt(a -> a.finish));

        // Add the first activity to the list of selected activities
        selectedActivities.add(activities.get(0));

        // Select activities that do not overlap with the previously selected activity
        int lastFinishTime = activities.get(0).finish;
        for (int i = 1; i < activities.size(); i++) {
            if (activities.get(i).start >= lastFinishTime) {
                selectedActivities.add(activities.get(i));
                lastFinishTime = activities.get(i).finish;
            }
        }

        return selectedActivities;
    }
}

class Driver {
    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1, 4));
        activities.add(new Activity(3, 5));
        activities.add(new Activity(0, 6));
        activities.add(new Activity(5, 7));
        activities.add(new Activity(3, 8));
        activities.add(new Activity(5, 9));
        activities.add(new Activity(6, 10));
        activities.add(new Activity(8, 11));
        activities.add(new Activity(8, 12));
        activities.add(new Activity(2, 13));
        activities.add(new Activity(12, 14));

        /* it's a static method that can be called directly using the class name (GreedyActivitySelection) without creating an instance of GreedyActivitySelection with new GreedyActivitySelection() */
        List<Activity> selectedActivities = GreedyActivitySelection.selectActivities(activities);

        /**
         * Selected Activities:
         * (1, 4)
         * (5, 7)
         * (8, 11)
         * (12, 14)
         */

        System.out.println("Selected Activities:");
        for (Activity activity : selectedActivities) {
            System.out.println(activity);
        }
    }
}