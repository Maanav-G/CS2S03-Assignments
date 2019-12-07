import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        // initialize inputList
        List<String> inputList = new ArrayList<>();

        // initialize input variable
        String input;

        // initialize Scanner to get input
        Scanner keyboard = new Scanner(System.in);

        // use a while loop read user input
        while(keyboard.hasNext()) {
            input = keyboard.nextLine();
            // add input, line-by-line to the input list
            inputList.add(input);
            // if input reads "FINISH" end the while loop
            if (input.equals("FINISH")) {
                break;
            }
        }

        // create an array for CarModel with 100 objects
        int numModels = 100;
        CarModel[] carModels = new CarModel[numModels];
        // initialize index of model list
        int indexModels = 0;

        // create an array for Car with 100 objects
        int numCars = 100;
        Car[] cars = new Car[numCars];
        // initialize index of car list
        int indexCars = 0;

        // initialize list for Longtrips
        List<String> longtripsList = new ArrayList<>();


        // iterate through each input from inputList
        for (int i = 0; i < inputList.size(); i++){


            // get current input
            String currentInput = inputList.get(i);
            // split input by " " and add to an array of Strings
            String[] currentInputList = currentInput.split("\\s+");
            // set String var to the 1st word in each input
            String var = currentInputList[0];


            // switch - where var is the first word
            // each case, deals with its respective command (MODEL, CAR, TRIPS, etc.)
            switch (var){

                // MODEL
                case "MODEL":
                    // split input by " " and add to an array of Strings
                    String[] modelBreakdown = inputList.get(i).split("\\s+");
                    String currentModelName = modelBreakdown[1]; // Model Name
                    double currentFuelEconomy = Double.parseDouble(modelBreakdown[2]); // Fuel Economy
                    double currentGasTankSize = Double.parseDouble(modelBreakdown[3]); // Gas Tank Size
                    // Create a new object in carModels
                    carModels[indexModels] = new CarModel(currentModelName, currentFuelEconomy, currentGasTankSize);
                    // Increase index value by 1
                    indexModels++;
                    break;

                // CAR
                case "CAR":
                    // split input by " " and add to an array of Strings
                    String[] carBreakdown = inputList.get(i).split("\\s+");
                    String currentCarName = carBreakdown[1]; // Model Name
                    int currentNumberPlate = Integer.parseInt(carBreakdown[2]); // Number Plate
                    // intialize a local carModel for case "CAR"
                    CarModel carModel = carModels[0];
                    // iterate through each CarModel from carModels
                    for(int cm = 0; cm < indexModels; cm++){
                        // get name of the current car model
                        String currentCarModel = carModels[cm].getName();
                        // if name of the car equals to the carmodel
                        if (currentCarName.equals(currentCarModel)){
                            // make variable carModel equal to current carModel
                            carModel = carModels[cm];
                        }
                    }
                    // Create a new object in cars
                    cars[indexCars] = new Car(carModel, currentNumberPlate);
                    // Increase index value by 1
                    indexCars++;
                    break;

                // TRIP
                case "TRIP":
                    // split input by " " and add to an array of Strings
                    String [] tripBreakdown = inputList.get(i).split("\\s+");
                    int numberPlateTrip = Integer.parseInt(tripBreakdown[1]); // Number Plate
                    int distanceOfTrip = Integer.parseInt(tripBreakdown[2]); // Distance of Trip

                    // new Boolean array, with 100 values
                    boolean[] tripResults = new boolean[100];
                    // iterate through each Car from cars
                    for (int f = 0; f < indexCars; f++) {
                        // get number plate of current car
                        int numberPlateCar = cars[f].getPlateNumber();
                        // if number plate of car equals to number plate from trip
                        if (numberPlateTrip == numberPlateCar){
                            // then car going on the trip is current car
                            Car carOnTrip = cars[f];
                            tripResults[f] = carOnTrip.trip(distanceOfTrip);
                            // if tripResults is true
                            if (tripResults[f]) {
                                // then print output - successful
                                System.out.println("Trip completed successfully for #" + carOnTrip.getPlateNumber());
                                // add trip to potential longtrips aka, trips completed successfully
                                longtripsList.add(inputList.get(i));
                            }
                            // if tripResults is false
                            else {
                                // then print output - unsuccessful
                                System.out.println("Not enough fuel for #" + carOnTrip.getPlateNumber());
                            }
                        }
                    }

                    break;

                // REFILL
                case "REFILL":
                    // split input by " " and add to an array of Strings
                    String [] refillBreakdown = inputList.get(i).split("\\s+");
                    int refillNumberPlate = Integer.parseInt(refillBreakdown[1]); // Number Plate of Car to Refill
                    // iterate through each Car from cars
                    for(int rc = 0; rc < indexCars; rc++){
                        int numberPlateCar = cars[rc].getPlateNumber(); // Number Plate of Current Car
                        // if Number Plate of Car to Refill equals to Number Plate of Current Car
                        if (refillNumberPlate == numberPlateCar){
                            // Refill that car
                            cars[rc].refill();
                        }
                    }
                    break;

                // LONG-TRIPS
                case "LONGTRIPS":
                    // split input by " " and add to an array of Strings
                    String [] longTripsBreakdown = inputList.get(i).split("\\s+");
                    int numberPlateLongTrip = Integer.parseInt(longTripsBreakdown[1]); // Number Plate of Car that went on Longtrip
                    int distanceLongTrip = Integer.parseInt(longTripsBreakdown[2]); // Distance of Car that went on Longtrip

                    // initialize list of Longtrips to use
                    List<String> longtripsToUse = new ArrayList<>();

                    // iterate through each trip added to longtrips list
                    for (int lt = 0; lt < longtripsList.size(); lt++){
                        // split input by " " and add to an array of Strings
                        String [] longTripsCompletedListBreakdown = longtripsList.get(lt).split("\\s+");
                        int numberPlateOfTripsCompleted = Integer.parseInt(longTripsCompletedListBreakdown[1]); // Number Plate of Car that went on a trip
                        int distanceOfTripsCompleted = Integer.parseInt(longTripsCompletedListBreakdown[2]); // Distance the Car went
                        // if Number Plate of Car that went on Longtrip equals Number Plate of Car that went on a trip AND Distance of Car that went on Longtrip smaller than Distance the Car went
                        if((numberPlateLongTrip == numberPlateOfTripsCompleted) && (distanceLongTrip < distanceOfTripsCompleted)){
                            // add current trip to list of Longtrips to use
                            longtripsToUse.add(longtripsList.get(lt));
                        }
                    }
                    // print output, with size of Longtrips to use (aka the amount of longtrips made)
                    System.out.println("#" + numberPlateLongTrip + " made " + longtripsToUse.size() + " trips longer than " + distanceLongTrip);
                    break;
            }
        }
    }
}
