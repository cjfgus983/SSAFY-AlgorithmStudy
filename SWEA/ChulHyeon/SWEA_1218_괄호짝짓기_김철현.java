/*
* ( < { [ 왼쪽 꺽쇠들은 스택에 넣고
* ) > } ] 오른쪽 꺽쇠들은 스택의 top과 비교해서 pop 해야함
*
* 유효하지 않아 결과가 0 으로 출력될 경우
* 1. stack 의 top 과 현재 비교한 토큰의 짝이 맞지 않을 경우
* 2. 비교를 하려고 봤더니 stack이 비어있을 경우
* 3. 문자열이 끝났는데 stack에 뭔가 남아있을 경우
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1218_괄호짝짓기_김철현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int testCase = 1; testCase <= 10; testCase++) {
            Stack<Character> stack = new Stack<>();
            br.readLine(); // 한 번 버리기
            String inputStr = br.readLine();

            int result = 1;
            for (int idx = 0; idx < inputStr.length(); idx++) {
                char ch = inputStr.charAt(idx);

                if (ch == '(' || ch == '{' || ch == '[' || ch == '<') {
                    stack.push(ch);
                }
                //1. stack 의 top 과 현재 비교한 토큰의 짝이 맞지 않을 경우
                //2. 비교를 하려고 봤더니 stack이 비어있을 경우
                else if (ch == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        result = 0;
                        break;
                    }
                }
                else if (ch == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        result = 0;
                        break;
                    }
                }
                else if (ch == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        result = 0;
                        break;
                    }
                }
                else if (ch == '>') {
                    if (stack.isEmpty() || stack.pop() != '<') {
                        result = 0;
                        break;
                    }
                }
            }
            //3. 문자열이 끝났는데 stack에 뭔가 남아있을 경우
            if(!stack.isEmpty()){
                result = 0;
            }
            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}
