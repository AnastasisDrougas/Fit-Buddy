# CoachFitness

# A Java application for analyzing fitness activities from TCX files.  
- It reads and processes activity data—including Laps, Trackpoints, Distance, Time, Speed, etc.
- Lets user add activities manually.
- Provides insights such as total distance, duration, average speed, pace, etc.
- It calculates the calories burned during your workout.
- Lets the user set a daily calorie-burning goal and shows whether the goal has been achieved.
- Computes and evaluates VO2Max values.
- Analyzes HR Zones. 
- Both CLI and GUI implementations completed.

# Class Overview
- **Main**: Application entry point. Runs the CLI when arguments are provided; otherwise, starts the GUI.

## Application Model
### XML File Reading
- **ArgumentReader**: Parses command-line arguments, separates user specifics (weight, age, sex) from XML file names, and loads all activities from the given files.
- **XMLFileReader**: Reads XML files and converts `<Activity>` nodes into `Activity` objects, supporting single or multiple file processing.
- **ArrayListConverter**: A generic utility class that converts a `NodeList` into an `ArrayList` of objects of type `E` using a provided mapping function.

- **Activities**: Holds a list of `Activity` objects. Can be created empty or from a `NodeList`, converting each node into an `Activity`.
- **Activity**: Represents a single sport activity. Stores a list of `Laps`, can be built from an XML node or manually, and calculates metrics (distance, time, heart rate, pace, speed).
- **Laps**: Represents a lap in an activity. Stores a list of `Tracks`, distance in meters, and total time in seconds, all parsed from XML.
- **Tracks**: Holds a list of `Trackpoints` for a lap, parsed from XML.
- **Trackpoints**: Stores a single trackpoint's data (time, position, altitude, distance, speed, heart rate, cadence) parsed from XML.

### Calculating Output Values
- **Calculator<T>**: Generic interface for calculating values from an `Activity`.  
  Classes that implement this interface include:  
- `TotalDistanceCalculator`: Returns the total distance, either summed from all `Laps` or taken from the last `DistanceMeters` value of the final `Trackpoint`.  
- `TotalTimeCalculator`: Returns the total time of the activity, summed from all `Laps` (in seconds).  
- `AvgHeartRateBpmCalculator`: Computes the average heart rate by summing the heart rate from each `Lap` and dividing by the number of laps.  
- `MinPaceCalculator`: Calculates the minimum pace using the formula `pace = 1000 / speed * 60` (minutes per kilometer).  
- `MaxPaceCalculator`: Calculates the maximum pace using the same formula.
 
- **VO2max**: Calculates an individual’s VO2 max using age and resting heart rate, estimates calories burned based on it, and provides a performance evaluation.

### Calorie Calculation
**CalorieCalculator**: Calculates calories burned for an activity using data from the activity itself and user-specific information.  
- Supports **two calculation methods**:  
    1. **Heart rate-based formula**: Uses user sex, age, weight, and average heart rate (`hbpm`) from the activity.  
       - Female formula: `((-20.4022 + 0.4472*hbpm + 0.1263*weight + 0.074*age) * time) / 4.184`  
       - Male formula: `((-55.0969 + 0.6309*hbpm + 0.1966*weight + 0.2017*age) * time) / 4.184`  
    2. **MET-based formula**: Uses MET values, user weight, and activity duration.  
       - Formula: `calories = MET * weight * (time / 60)`  
- Provides `calculateCaloriesForGUI(Activity a, ViewUI view)` to compute calories based on **GUI input**.
**METValuesHashMap**: Stores MET (Metabolic Equivalent of Task) values for different sports and provides a default value of 3.5 for any unlisted activities.

### Helper Classes
**DailyGoalAchievementLogic** Aggregates daily calories from activities and updates a map with the date (`yyyy-MM-dd`) as key and total calories as value.

## Application View

- **ViewUI**: Main Swing GUI for the program using a `CardLayout`.  
  - Uses a **CardLayout** to switch between panels (cards):  
    1. **WelcomeCard (Card0)**: Displays a start button to begin the workflow.  
    2. **InputCard (Card1)**: Collects user profile information (weight, age, sex, daily goal).  
    3. **FormulaCard (Card2)**: Allows the user to select calculation options or formulas.  
    4. **OutputCard (Card3)**: Displays the results of the calculations.  
  - Includes **navigation buttons** (`Next`) to move between cards.

- **Decorator GUI structure**: Cards are wrapped in panels to add logos, buttons, and layout enhancements, extending functionality without changing the card classes themselves

 ## Application Controller
- **ControllerUI**: This class manages all user interactions and connects the GUI with the app’s logic.  

    ### User interactions  
    - **Attaches listeners to all buttons and input fields** in the GUI, responding to clicks, text entries, and selections.  
    - Controls navigation between the different cards in the interface.  
    - Allows users to **manually add activities** through pop-up dialogs, entering details like sport, distance, time, heart rate, speed, pace, and date.  

    ### File handling  
    - Lets the user select `.tcx` files via a file chooser.  
    - Reads and parses the files using `XMLFileReader`.  
    - Automatically updates the list of activities when new files are added.  

    ### Calculations and stats  
    - Uses `CalorieCalculator` to compute calories for each activity based on the user’s weight, age, sex, and heart rate or MET values.  
    - Tracks daily goal achievements and generates reports showing which days the user reached their goals.  
    - Computes VO2max evaluations when the user provides their resting heart rate.  
    - Dynamically updates the output table whenever activities are loaded or added manually, showing distance, time, speed, pace, heart rate, calories, and dates.
    - Analyzes HR Zones. 

    ### Validation and error handling  
    - Ensures all numerical inputs (weight, age, goals, activity metrics) are valid.  
    - Verifies dates are entered in the correct `YYYY-MM-DD` format.  


  ## Prerequisites
### Before running the code, make sure you have installed:
- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or newer)
- [Maven](https://maven.apache.org/)
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a terminal

# How to Run
## Using Maven:
   ### For CLI Implementation.
   - mvn package
   - java -jar target/CoachFitness-1.0-SNAPSHOT.jar -w WeightValue -s Sex('m' or 'f') -a AgeValue yourTCXFile.tcx
   - **or (For advanced calorie calculation)**.
   - java -jar target/CoachFitness-1.0-SNAPSHOT.jar -w WeightValue -s Sex('m' or 'f') -a AgeValue yourTCXFile.tcx
### For GUI Implementation.
   - mvn package
   - java -jar target/CoachFitness-1.0-SNAPSHOT.jar


     


