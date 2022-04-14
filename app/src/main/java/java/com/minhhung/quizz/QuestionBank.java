package java.com.minhhung.quizz;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private static List<QuestionsList> Animal(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Animal first Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");
        final  QuestionsList question2 = new QuestionsList("Animal second Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");
        final  QuestionsList question3 = new QuestionsList("Animal third Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);

        return questionsLists;
    }
    private static List<QuestionsList> Math(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Math first Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");
        final  QuestionsList question2 = new QuestionsList("Math second Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");
        questionsLists.add(question1);
        questionsLists.add(question2);

        return questionsLists;
    }
    private static List<QuestionsList> Game(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Game first Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");
        final  QuestionsList question2 = new QuestionsList("Game second Question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");

        questionsLists.add(question1);
        questionsLists.add(question2);

        return questionsLists;
    }
    private static List<QuestionsList> Music(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Music first question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");
        final  QuestionsList question2 = new QuestionsList("Music second question","1st option","2nd option","3rd ooption","4th option" ,"1st option","");

        questionsLists.add(question1);
        questionsLists.add(question2);

        return questionsLists;
    }
    public static List<QuestionsList> getQuestions(String selectedTopicName){
        switch (selectedTopicName){
            case "animal":
                return Animal();
            case "math":
                return Math();
            case "music":
                return Music();
            default:
                return Game();
        }
    }

}
