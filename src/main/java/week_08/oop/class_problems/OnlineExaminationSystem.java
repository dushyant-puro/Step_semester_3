package week_08.oop.class_problems;
import java.util.*;
public class OnlineExaminationSystem {
 static class Question { String prompt,answer; Question(String p,String a){prompt=p;answer=a;} boolean check(String response){return answer.equalsIgnoreCase(response);} }
 static class Attempt { Map<Integer,String> answers=new HashMap<>(); boolean submitted; int score; void answer(int q,String a){if(submitted)throw new IllegalStateException("Attempt submitted");answers.put(q,a);} int submit(List<Question> qs){if(submitted)throw new IllegalStateException("Already submitted");for(int i=0;i<qs.size();i++)if(qs.get(i).check(answers.getOrDefault(i+1,"")))score++;submitted=true;return score;} }
 public static void main(String[] args){List<Question> qs=Arrays.asList(new Question("Q1","A"),new Question("Q2","B"));Attempt a=new Attempt();System.out.println("Examination 'Math Quiz' started by Student.");a.answer(1,"A");a.answer(2,"C");System.out.println("Result: "+a.submit(qs)+"/"+qs.size()+" correct.");}
}