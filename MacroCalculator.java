import java.util.Scanner;

public class MacroCalculator {

    /*
     * REE = Resting Energy Expenditure
     * We first need to calculate the REE of the user. This value represents the resting energy expenditure of the user.
     */

    public static double REEformulaMen(double weightKG, double heightCM, int age) {
        return (10 * weightKG) + (6.25 * heightCM) - (5 * age) + 5;
    }

    public static double REEformulaWomen(double weightKG, double heightCM, int age) {
        return (10 * weightKG) + (6.25 * heightCM) - (5 * age) - 161;
    }

    /*
     * TDEE = Total Daily Energy Expenditure
     * We then need to calculate the TEE of the user. This value represents the total energy expenditure of the user. 
     * This value includes the REE and the amount of energy the user expends during physical activity.
     * This will be classified into 4 categories: Sedentary, Lightly Active, Moderately Active, and Very Active.
     * 
     * For example: If the user is a 29 year old very active male, his TDEE would be calculated as follows:
     * REE = 10 * 80 + 6.25 * 180 - 5 * 29 + 5 = 1,855 given that he is 80kg, 180cm, and 29 years old.
     * TDEE = 1,855 * 1.725 = 3,192.25
     * So for a very active male, the TDEE is 3,192.25 calories (roughly).
     * 
     * This value acts as the baseline for the user's daily calorie intake.
     */
     

    public static double TDEEFormula(String activity, double REE) {
        if (activity == "Sedentary") {
            return REE * 1.2;
        } else if (activity == "Lightly Active") {
            return REE * 1.375;
        } else if (activity == "Moderately Active") {
            return REE * 1.55;
        } else if (activity == "Very Active") {
            return REE * 1.725;
        } else {
            return 0;
        }
    }

    /*
     * Calculate calories for weight loss and weight gain via TDEE. This is for a calorie deficit of 20%.
     */

     public static double CaloriesForWeightLoss(double TDEE) {
         return TDEE - (TDEE * 0.2);
     }

     /*
      * Calculate calories for weight gain via TDEE. This is for a calorie surplus of 20%.
      */

    public static double CaloriesForWeightGain(double TDEE) {
        return TDEE + (TDEE * 0.2);
    }

/*
 * Finally we can calcluate macronutrients. Macronutrients consist of Fats, Carbohydrates, and Proteins. Let's start with proteins. 
 * For protein, we will be doing 1g of protein for every 1lb of bodyweight. Or 2.2g of protein for every 1kg of bodyweight.
 * 1g of protein = 4 calories
 * 
 * For fat, we want to aim for 20% of the total caloric intake to come from fats.
 * 1g of fat = 9 calories
 * 
 * For carbohydrates, we will have this metric be the remaining calorie intake after protein and fat have been accounted for.
 * 1g of carbohydrates = 4 calories
 */

 public static String MacroCalculator(double TDEE, double weightKG, String goal) {

        if (goal == "Weight Loss") {
            TDEE = CaloriesForWeightLoss(TDEE);
        } else if (goal == "Weight Gain") {
            TDEE = CaloriesForWeightGain(TDEE);
        } else if (goal == "Maintain Weight") {
            TDEE = TDEE;
        } else {
            System.out.println("Invalid goal");
        }

        double proteinGrams = weightKG / 2.2; // 1g of protein for 1lb of bodyweight
        double fatGrams = (TDEE * 0.2) / 9; // 20% of total calories from fat divided by 9 calories per gram of fat
        double carbohydratesGrams = (TDEE - (proteinGrams * 4) - (fatGrams * 9)) / 4; // Remaining calories after protein and fat have been accounted for divided by 4 calories per gram of carbohydrates

        String MacroStatement = "Protein: " + proteinGrams + "g" + "\n" + "Fat: " + fatGrams + "g" + "\n" + "Carbohydrates: " + carbohydratesGrams + "g" + "\n" + "Total Calories: " + TDEE + "calories" + "\n";

        return MacroStatement;
 }


    public static void main(String[] args) {

        System.out.println("Macro Calculator");

        // Scanners to get input from user


        //get age
        System.out.println("Age: ");
        Scanner ageInput = new Scanner(System.in);
        int age = ageInput.nextInt();
        //ageInput.close();

        // get weight
        System.out.println("Weight (kg): ");
        Scanner weightInput = new Scanner(System.in);
        double weightKG = weightInput.nextDouble();
        //weightInput.close();

        // get height
        System.out.println("Height (cm): ");
        Scanner heightInput = new Scanner(System.in);
        double heightCM = heightInput.nextDouble();
        //heightInput.close();

        // get activity level
        System.out.println("What is your activity level? Sedentary, Lightly Active, Moderately Active, or Very Active?");
        System.out.println("Sendetary: Little to no exercise" + "\n" + "Lightly Active: Light exercise 1-3 days per week" + "\n" + "Moderately Active: Moderate exercise 3-5 days per week" + "\n" + "Very Active: Heavy exercise 6-7 days per week");

        Scanner activityInput = new Scanner(System.in);
        String activity = activityInput.nextLine().toUpperCase();

        // get gender
        System.out.println("Gender (Male/Female: ");
        Scanner genderInput = new Scanner(System.in);
        String gender = genderInput.nextLine().toUpperCase();
        //genderInput.close();

         // we need to get the goal from the user. This goal can be to gain weight, lose weight, or maintain weight. 

         System.out.println("What is your goal? Weight Loss, Weight Gain, or Maintain Weight?");
         Scanner goalInput = new Scanner(System.in);
         String goal = goalInput.nextLine().toUpperCase();
         //goalInput.close();
        
        // calculate TDEE
        double TDEE = 0;


        if (gender == "Male") {
            double REEMen = REEformulaMen(weightKG, heightCM, age); // REE for men

            // TDEE for men
            if (activity == "Sedentary") {
                TDEE = TDEEFormula("Sedentary", REEMen);
            } else if (activity == "Lightly Active") {
                TDEE = TDEEFormula("Lightly Active", REEMen);
            } else if (activity == "Moderately Active") {
                TDEE = TDEEFormula("Moderately Active", REEMen);
            } else if (activity == "Very Active") {
                TDEE = TDEEFormula("Very Active", REEMen);
            } else {
                System.out.println("Invalid activity level.");
            }
        } else {
            double REEWomen = REEformulaWomen(weightKG, heightCM, age); // REE for women

            //TDEE for women
            if (activity == "Sedentary") {
                TDEE = TDEEFormula("Sedentary", REEWomen);
            } else if (activity == "Lightly Active") {
                TDEE = TDEEFormula("Lightly Active", REEWomen);
            } else if (activity == "Moderately Active") {
                TDEE = TDEEFormula("Moderately Active", REEWomen);
            } else if (activity == "Very Active") {
                TDEE = TDEEFormula("Very Active", REEWomen);
            } else {
                System.out.println("Invalid activity level.");
            }
        }

       

        // Time to calculate macronutrients

        System.out.println("Macronutrient Calculator");
        MacroCalculator(TDEE, weightKG, goal);
            
        // close scanners
        ageInput.close();
        weightInput.close();
        heightInput.close();
        activityInput.close();
        goalInput.close();
    }
}