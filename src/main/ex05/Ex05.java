package ex05;

import java.util.ArrayList;

public class Ex05 {

    /*
    *
    *   Bit scale:
    *   Colum:         11   10   9   8      7  6  5  4    3 2 1 0
    *   Scaling:     2048 1024 512 256    128 64 32 16    8 4 2 1
    *
    * <program>-<course-code>:<unique-ID> / <date-of-completion>. File: <filename>.pdf;
    * Programming-PG4200:456987 / 2022-JUN-06. File: feedback-PG4200-456987.pdf
    * ArtificialIntelligence-AI1701:987456 / 2021-AUG-13. File: feedback-AI1701-987456.pdf
    *
    * Metadata                      4 bits for reading array
    *
    * Program                       0 bits
    * Course-code                   3 bits for 5 options
    * Unique-id                     20 bits
    * Complition date yyyy-mm-dd    12 + 4 + 5 bits
    * filname course + uniqueID     "3 + 20"
    *
    * */

    public byte[] compress(String feedback){

//        Removes all special signs and makes a list with only values I need.
        String[] tmp = feedback.trim().split("[.:/\\-\\n ]");
        ArrayList<String> feedbackList = new ArrayList<>();
        for (String s : tmp){
            if (!s.equals("")) feedbackList.add(s);
        }

        ArrayList<Integer> intFeedback = new ArrayList<>();
        for (int i = 0; i < feedbackList.size(); i++) {
            if (i % 11 == 0) continue; //skips program
            else if (i % 11 == 1) intFeedback.add(compressCourseCode(feedbackList.get(i))); //Course code
            else if (i % 11 == 2) intFeedback.add(Integer.parseInt(feedbackList.get(i))); //Unique ID
            else if (i % 11 == 3) intFeedback.add(Integer.parseInt(feedbackList.get(i))); //year
            else if (i % 11 == 4) intFeedback.add(compressMonthShortName(feedbackList.get(i))); //month
            else if (i % 11 == 5) intFeedback.add(Integer.parseInt(feedbackList.get(i))); //day
            else if (i % 11 >= 6) continue;// we dont need to store the remaining strings
        }

        BitWriter writer = new BitWriter();
        int maxBitIntFeedback = bitAmount(0, intFeedback.size());
        writer.write(maxBitIntFeedback, 4); //Metadata how many bit to save intFeedback
        writer.write(intFeedback.size(), maxBitIntFeedback); //Metadata saves how many loops reader need to do

        for (int i = 0; i < intFeedback.size(); i++) {
            if (i % 5 == 0) writer.write(intFeedback.get(i), 3);        //3 bits - course-code
            else if (i % 5 == 1) writer.write(intFeedback.get(i), 20);  //20 bits - unique-ID
            else if (i % 5 == 2) writer.write(intFeedback.get(i), 12);  //12 bits - year
            else if (i % 5 == 3) writer.write(intFeedback.get(i), 4);   //4 bits - month
            else if (i % 5 == 4) writer.write(intFeedback.get(i), 5);   //5 bits - day
        }

        writer.close();
        return writer.extract();
    }

    private int compressCourseCode(String code){
        return switch (code) {
            case "PG4200" -> 1;
            case "AI1701" -> 2;
            case "FP1453" -> 3;
            case "SC1007" -> 4;
            case "DS0112" -> 5;
            default -> 0; // count as error
        };
    }

    private int compressMonthShortName(String month){
        return switch (month.toUpperCase()){
            case "JAN" -> 1;
            case "FEB" -> 2;
            case "MAR" -> 3;
            case "APR" -> 4;
            case "MAY" -> 5;
            case "JUN" -> 6;
            case "JUL" -> 7;
            case "AUG" -> 8;
            case "SEP" -> 9;
            case "OCT" -> 10;
            case "NOV" -> 11;
            case "DEC" -> 12;
            default -> 0;
        };
    }



    public String decompress(byte[] feedbackArray){
        StringBuilder feedback = new StringBuilder();
        BitReader reader = new BitReader(feedbackArray);

        int maxBit = reader.readInt(4); //read how many bits needed to read intFeedback
        int intFeedback = reader.readInt(maxBit); //intfeedback is how many times the readerloop needs to go

        String fullCourseCode = null;
        String courseCode = null;
        String uniqueID = null;
        String year = null;
        String month = null;
        int day = 0;

        for (int i = 0; i < intFeedback; i++) {
            if (i % 5 == 0){
                int course = reader.readInt(3);
                fullCourseCode = decompressCourseCode(course, true);
                courseCode = decompressCourseCode(course, false);                     //3 bits - course-code
            }
            else if (i % 5 == 1) uniqueID = String.valueOf(reader.readInt(20));         //20 bits - unique-ID
            else if (i % 5 == 2) year = String.valueOf(reader.readInt(12));             //12 bits - year
            else if (i % 5 == 3) month = decompressMonthShortName(reader.readInt(4));   //4 bits - month
            else if (i % 5 == 4) {
                day = reader.readInt(5);                                                //5 bits - day

                feedback.append(fullCourseCode).append(":").append(uniqueID).append(" / ").append(year).append("-").append(month).append("-").append(String.format("%02d", day))
                .append(". File: feedback-").append(courseCode).append("-").append(uniqueID);

                if (intFeedback - i >= 5) feedback.append(".pdf;\n");   //if more line, add next line
                else feedback.append(".pdf;");                          //if last line dont add next line.
            }
        }

        return feedback.toString();
    }

    private String decompressCourseCode(int code , boolean fullname){
        if (fullname){
            return switch (code) {
                case 0 -> "WriteError";
                case 1 -> "Programming-PG4200";
                case 2 -> "ArtificialIntelligence-AI1701";
                case 3 -> "FrontendProgramming-FP1453";
                case 4 -> "Cybersecurity-SC1007";
                case 5 -> "DataScience-DS0112";
                default -> "ERROR"; // count as error
            };
        }else{
            return switch (code) {
                case 0 -> "WriteError";
                case 1 -> "PG4200";
                case 2 -> "AI1701";
                case 3 -> "FP1453";
                case 4 -> "SC1007";
                case 5 -> "DS0112";
                default -> "ERROR"; // count as error
            };
        }
    }

    private String decompressMonthShortName(int month){
        return switch (month){
            case 0 -> "WriteError";
            case 1 -> "JAN";
            case 2 -> "FEB";
            case 3 -> "MAR";
            case 4 -> "APR";
            case 5 -> "MAY";
            case 6 -> "JUN";
            case 7 -> "JUL";
            case 8 -> "AUG";
            case 9 -> "SEP";
            case 10 -> "OCT";
            case 11 -> "NOV";
            case 12 -> "DEC";
            default -> "ERROR";
        };
    }

    private static int bitAmount(double powerOfTwo, double highestNumber){
//        Need minimum 1 bit to save
        if (highestNumber == 0) return 1;

//        Checks how many bits needed to save the highest ID using 2^n > ID
        if (Math.pow(2, powerOfTwo) > highestNumber) return (int) powerOfTwo;
        powerOfTwo++;
        return bitAmount(powerOfTwo, highestNumber);
    }

}