package main;

import java.util.ArrayList;
import java.util.Scanner;

import data.Barang;
import data.Kategori;
import userAccount.Account;

public class Main {
	//variabel global
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Account> loginData = new ArrayList<Account>();
	private ArrayList<Barang> dataBarang = new ArrayList<Barang>();
	private ArrayList<Kategori> dataKategori = new ArrayList<Kategori>();
	private Integer indexKategori = 1;
	
	public Main() {
		//data akun yang tersedia
		loginData.add(new Account("admin", "admin123"));
		loginData.add(new Account("admins", "admin123"));
		
		login();
		
		int choice = -1;
		do {
			menu();
			choice = nextInt();
			
			switch (choice) {
			case 3:
				tambahKategori();
				break;
			case 4:
				if (dataKategori.isEmpty()) {
					System.out.println("Kategori kosong");
					pressEnter();
				}else {
					tampilkanKategori();
					pressEnter();
				}
				break;
			}
		} while (choice != 0);
		
	}
	
	public void login() {
		String username;
		String password;
		boolean isAccount = true;
		
		do {
			System.out.print("Username: ");
			username = sc.nextLine();
			
			System.out.print("Password: ");
			password = sc.nextLine();
			
			int idx = 1;
			for (Account account : loginData) {
				if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
					System.out.println("Login berhasil! Selamat datang " + username);
					isAccount = false;
					pressEnter();
					break;
				}else if (account.getUsername().equals(username) && !account.getPassword().equals(password)) {
					System.out.println("Password salah!");
					pressEnter();
					break;
				}else{
					if (idx == loginData.size()) {
						System.out.println("Akun tidak terdaftar!");
						pressEnter();
					}
				}
				idx++;
				
			}
		}while (isAccount);
	}
	
	public void menu() {
		System.out.println("Tallyswift");
		generateLine(10);
		System.out.println("1. Transaksi");
		System.out.println("2. Master Data");
		System.out.println("3. Tambahkan kategori barang");
		System.out.println("4. Tampilkan kategori barang");
		System.out.println("0. Keluar");
		System.out.print(">> ");
	}
	
	public void tambahKategori() {
		String kategori;
		do {
			System.out.print("Tambah Kategori[Max 30 karakter]: ");
			kategori = sc.nextLine();			
		} while (kategori.length() > 30);
		
		Kategori kategoriBaru = new Kategori("", kategori);
		kategoriBaru.generateID(indexKategori);
		dataKategori.add(kategoriBaru);
		indexKategori++;
	}
	
	public void tampilkanKategori() {
		generateLine(42);
		System.out.printf("| %-5s | %-30s |\n", "ID", "Kategori");
		generateLine(42);
		for (Kategori kategori : dataKategori) {
			System.out.printf("| %-5s | %-30s |\n", kategori.getKategoriID(), kategori.getKategori());
		}
		generateLine(42);
	}
	
	//method untuk meminta user enter
	public void pressEnter() {
		System.out.println("Tekan enter untuk melanjutkan...");
		sc.nextLine();
	}
	
	//method untuk mencegah system error saat salah input
	int nextInt(){
        int temp = 0;
        try {
            temp = sc.nextInt();
        }catch (Exception e){
            System.out.println("Input salah!");
        }
        sc.nextLine();
        return temp;
    }

	//method untuk membuat garis
	public void generateLine(int panjang) {
		for (int i = 0; i < panjang; i++) {
			System.out.print("=");			
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
