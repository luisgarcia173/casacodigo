package br.com.casacodigo.consumer;

public class UsandoLambdaParaThreads {

	public static void main(String[] args) {

		Runnable r = new Runnable() {
			public void run() {
				System.out.println("Thread jeito antigo: ");
				for (int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			}
		};
		new Thread(r).start();
		
		new Thread(() -> {
			System.out.println("\n\nThread usando lambda: ");
			for (int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		}).start();

	}

}
