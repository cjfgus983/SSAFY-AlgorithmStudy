package BOJ6588;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import java.util.Scanner;

public class Main {
	static final int MAX_SIZE = 1000000;
	static boolean[] primes = new boolean[MAX_SIZE+1];
	
	public static void main(String[] args) throws IOException  {
		
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		FindPrimes(); //소수를미리 0 찾아놓는다.
		
		while(true) {
			int num1=0,num2=0;
			int n = Integer.parseInt(br.readLine());
//			int n = sc.nextInt();
			if (n==0) { //0이면 종료
				return;
			}
			if (n<6 || n%2==1 || n>=MAX_SIZE) {
				System.out.println("6이상 짝수만 입력하세요요");
				continue;
			}

			for(int index =3; index<=n/2; index+=2) { //index가 n부터 n/2 까지보면서
				if (primes[index] && primes[n-index]) {
					num2 = n-index;
					num1 = index;
					break;
					
				}
			}
			if(num1==0) { //그럴일은 없겠지만 골드바흐가 틀렸다면
				System.out.println("Goldbach's conjecture is wrong");
			}

			System.out.printf("%d = %d + %d\n",n,num1,num2);
		}
	}
	
	/*
	 * 최대 범위보다 작은 소수를 찾는 함수
	 */
	static void FindPrimes() {
		primes[2]= true;
		for(int index =3; index < MAX_SIZE;index+=2) {
			boolean isPrime = true;
			
			for(int sub =3; sub*sub<=index;sub+=2) { //에라토스테네스의체-> √index 까지만 확인
				if(index%sub==0) { //sub는 index의 약수인가요?
					isPrime=false; //찾았다..
					break; 
				}
			} 
			if(isPrime) { //약수가 없으면 그게 소수다.
				primes[index] = true;
//				System.out.println(index);
			}
		}
		
	}
}