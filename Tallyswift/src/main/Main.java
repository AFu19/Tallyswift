package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import data.Barang;
import data.Kategori;
import userAccount.Account;
import Transaksi.Transaksi;

public class Main {

	// variabel global
	private Scanner sc = new Scanner(System.in);
	private Random rand = new Random();
	private ArrayList<Account> loginData = new ArrayList<Account>();
	private ArrayList<Barang> dataBarang = new ArrayList<Barang>();
	private ArrayList<Kategori> dataKategori = new ArrayList<Kategori>();
	private ArrayList<Transaksi> dataTransaksi = new ArrayList<Transaksi>();
	private Integer indexKategori = 1;

	public Main() {

		// data akun yang tersedia
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
				} else {
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
				} else if (account.getUsername().equals(username) && !account.getPassword().equals(password)) {
					System.out.println("Password salah!");
					pressEnter();
					break;
				} else {
					if (idx == loginData.size()) {
						System.out.println("Akun tidak terdaftar!");
						pressEnter();
					}
				}
				idx++;

			}
		} while (isAccount);
	}

	public void menu() {
	

		while (true) {
			

			int option;
			System.out.println("Tallyswift");
			generateLine(10);
			System.out.println("1. Transaksi");
			System.out.println("2. Master Data");
			System.out.println("0. Keluar");
			System.out.print(">> ");

			option = sc.nextInt();
			sc.nextLine();

			if (option == 1) {
				transaksi();

			} else if (option == 2) {

				int opsi;

				while (true) {
					
					view();
					System.out.println("");
					
					System.out.println("1. tambah barang");
					System.out.println("2. ubah barang");
					System.out.println("3. hapus barang");
					System.out.println("0. back");
					opsi = sc.nextInt();
					sc.nextLine();

					if (opsi == 1) {

						tambahBarang();

					} else if (opsi == 2) {
						
						update();
						

					} else if (opsi == 3) {
						
						delete();

					} else if (opsi == 4) {
						menu();

					}

				}

			} else if (option == 0) {
				break;
			}

		}

	}

	public void transaksi() {
		String namaBarang, checkoutchoice, metodePembayaran, pilihBank, pilihmb;
		Integer qty;
		
		dataBarang();
		System.out.println();
		
		boolean noCheckout = true;
		
		while (noCheckout) {
			boolean adaBarang = false;

	        while (!adaBarang) {
	            System.out.print("Nama barang: ");
	            namaBarang = sc.nextLine();

	            for (Barang barang : dataBarang) {
	                if (barang.getNamaBarang().equalsIgnoreCase(namaBarang)) {
	                    adaBarang = true;
	                    
	                    do {
	                    	System.out.print("Jumlah barang [>0]: ");
		                    qty = nextInt();
						} while (qty < 0);
	                    
	                    if (qty > 0 && qty <= barang.getStok()) {
	                        Integer harga = barang.getHargaSatuan() * qty;
	                        dataTransaksi.add(new Transaksi(namaBarang, qty, harga));
	                        barang.setStok(barang.getStok() - qty);
	                    } else {
	                        System.out.println("Jumlah barang melebihi stok");
	                    }
	                    break;
	                }
	            }

	            if (!adaBarang) {
	                System.out.println("Barang tidak ditemukan.");
	            }
	        }
			
			Integer subTotal = 0;
			System.out.println("Keranjang Belanja");
			generateLine(52);
			System.out.printf("| %-30s | %-3s | %-9s |\n", "Nama Barang", "Qty", "Harga");
			generateLine(52);
			for (Transaksi transaksi : dataTransaksi) {
				System.out.printf("| %-30s | %-3d | %-9d |\n", transaksi.getNamaBarang(), transaksi.getQty(), transaksi.getHarga());
				subTotal += transaksi.getHarga();
			}
			generateLine(52);
			
			Integer ppn = (int) (subTotal * 0.10);
			Integer total = subTotal + ppn;
			System.out.println("Subtotal: " + subTotal);
	        System.out.println("PPN (10%): " + ppn);
	        System.out.println("Total: " + total);
			System.out.println();
			
			do {
				System.out.print("Checkout? [Y/N]: ");
		        checkoutchoice = sc.nextLine();
			} while (!(checkoutchoice.equalsIgnoreCase("Y") || checkoutchoice.equalsIgnoreCase("N")));
			noCheckout = checkoutchoice.equalsIgnoreCase("N");
		}
		metodePembayaran();
	}
	
	public void metodePembayaran() {
		String metodePembayaran, pilihBank, pilihmb;
		Integer nominalBayar = 0;
		do {
			System.out.print("Pilih metode pembayaran [cash, debit card, mobile banking]: ");
			metodePembayaran = sc.nextLine();
		} while (!(metodePembayaran.equalsIgnoreCase("cash") || metodePembayaran.equalsIgnoreCase("debit card") || metodePembayaran.equalsIgnoreCase("mobile banking")));

		if (metodePembayaran.equalsIgnoreCase("cash")) {
			System.out.print("Masukkan nominal yang dibayarkan: ");
			nominalBayar = nextInt();
		} else if (metodePembayaran.equalsIgnoreCase("debit card")) {
			do {
				System.out.print("Pilih bank [CIMB Niaga, Mandiri, BCA, Permata Bank, BRI]: ");
				pilihBank = sc.nextLine();
			} while (!(pilihBank.equalsIgnoreCase("CIMB Niaga") || pilihBank.equalsIgnoreCase("Mandiri") || pilihBank.equalsIgnoreCase("BCA") ||
					pilihBank.equalsIgnoreCase("Permata Bank") || pilihBank.equalsIgnoreCase("BRI")));
		} else if (metodePembayaran.equalsIgnoreCase("mobile banking")) {
			do {
				System.out.print("Pilih mobile banking [Gopay, Dana, OVO]: ");
				pilihmb = sc.nextLine();
			} while (!(pilihmb.equalsIgnoreCase("Gopay") || pilihmb.equalsIgnoreCase("Dana") || pilihmb.equalsIgnoreCase("OVO")));
		}

		Integer subTotal = 0;
		System.out.println("Transaksi Berhasil!");
		generateLine(50);
		System.out.printf("| %-30s | %-3s | %-9s |\n", "Nama Barang", "Qty", "Harga");
		generateLine(50);
		for (Transaksi transaksi : dataTransaksi) {
			System.out.printf("| %-30s | %-3d | %-9d |\n", transaksi.getNamaBarang(), transaksi.getQty(), transaksi.getHarga());
			subTotal += transaksi.getHarga();
		}
		generateLine(50);
		
		Integer ppn = (int) (subTotal * 0.10);
		Integer total = subTotal + ppn;
	    if (metodePembayaran.equalsIgnoreCase("debit card") || metodePembayaran.equalsIgnoreCase("mobile banking")) {
			nominalBayar = total;
		}
	Integer kembalian = nominalBayar - total;
	System.out.println("Subtotal: " + subTotal);
        System.out.println("PPN (10%): " + ppn);
        System.out.println("Total: " + total);
        System.out.println("Pembayaran: " + metodePembayaran + ": " + nominalBayar);
        System.out.println("Kembalian: " + kembalian);
        System.out.println("");
        
        pressEnter();
	}

	public void dataBarang() {
		System.out.println("Data Barang");
		generateLine(49);
		System.out.printf("| %-30s | %-12s |\n", "Nama Barang", "Harga Satuan");
		generateLine(49);
		for (Barang barang : dataBarang) {
			System.out.printf("| %-30s | %-12d |\n", barang.getNamaBarang(), barang.getHargaSatuan());
		}
		generateLine(49);
	}	

	public void tambahBarang() {
		
		String kodeBarang; // BR[0-9][0-9][0-9]
		String kategoriID; // KG[0-9][0-9][0-9]
		String namaBarang;
		Integer hargaSatuan;
		Integer stok;
		
		System.out.print("masukkan nama barang : ");
		namaBarang = sc.nextLine();
		
		System.out.print("masukkan harga satuan : ");
		hargaSatuan = sc.nextInt();sc.nextLine();
		
		System.out.print("masukkan stok : ");
		stok = sc.nextInt();sc.nextLine();
		
		kodeBarang = "BR" + rand.nextInt(10)+ rand.nextInt(10)+ rand.nextInt(10);
		kategoriID = "KG" + rand.nextInt(10)+ rand.nextInt(10)+ rand.nextInt(10);
		
		dataBarang.add(new Barang(kodeBarang, kategoriID, namaBarang, hargaSatuan, stok));
		
		System.out.println("Barang dengan kode barang : " + kodeBarang + " dan kategoti ID : " + kategoriID + " berhasil dimasukkan");
		System.out.println("Tekan enter untuk lanjut...");
		sc.nextLine();
		
	}
	
	public void view() {
		if(dataBarang.isEmpty()) {
			System.out.println("belum ada barang...");
		}else {
			for (int i = 0; i < dataBarang.size(); i++) {
				System.out.println("Kode Barang : " + dataBarang.get(i).getKodeBarang());
				System.out.println("Kategori ID : " + dataBarang.get(i).getKategoriID());
				System.out.println("Nama : " + dataBarang.get(i).getNamaBarang());
				System.out.println("Harga Satuan : " + dataBarang.get(i).getHargaSatuan());
				System.out.println("Stok : " + dataBarang.get(i).getStok());
				System.out.println(" ");
				
				
			}
		}
	}
	
	public void update() {
		
		String upKodeBarang;
		String namaBarang;
		Integer hargaSatuan;
		Integer stok;
	

		view();
		System.out.println(" ");

		do {

			System.out.println("masukkan ID yang akan di update : ");
			upKodeBarang = sc.nextLine();

		} while (!checkID(upKodeBarang));
		
		System.out.print("masukkan nama barang : ");
		namaBarang = sc.nextLine();
		
		System.out.print("masukkan harga satuan : ");
		hargaSatuan = sc.nextInt();sc.nextLine();
		
		System.out.print("masukkan stok : ");
		stok = sc.nextInt();sc.nextLine();
		
		for (int i = 0; i < dataBarang.size(); i++) {
			dataBarang.get(i).setNamaBarang(namaBarang);
			dataBarang.get(i).setHargaSatuan(hargaSatuan);
			dataBarang.get(i).setStok(stok);

		}

		System.out.println("barang dengan kode barang : " + upKodeBarang + " berhasil di update");
		System.out.println("Tekan enter untuk lanjut...");
		sc.nextLine();

	}
	
	public void delete() {
		
		int no = 1;
		
		for (int i = 0; i < dataBarang.size(); i++) {
			System.out.println(no);
			System.out.println("Kode Barang : " + dataBarang.get(i).getKodeBarang());
			System.out.println("Kategori ID : " + dataBarang.get(i).getKategoriID());
			System.out.println("Nama : " + dataBarang.get(i).getNamaBarang());
			System.out.println("Harga Satuan : " + dataBarang.get(i).getHargaSatuan());
			System.out.println("Stok : " + dataBarang.get(i).getStok());
			System.out.println(" ");
			no++;
			
			
		}

		int del;

		do {
			
			System.out.print("pilih barang yang akan dihapus [1 - " + dataBarang.size() + "] / 0 untuk kembali: ");
			del = sc.nextInt();

			if (del == 0) {
				break;
			} else {

				del -= 1;
				dataBarang.remove(del);
				
				System.out.println("Barang bersahil dihapus");
				System.out.println("Tekan enter untuk lanjut...");
				sc.nextLine();
	
			}

		} while (del < 1 || del > dataBarang.size());

	

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

	// method untuk meminta user enter
	public void pressEnter() {
		System.out.println("Tekan enter untuk melanjutkan...");
		sc.nextLine();
	}

	// method untuk mencegah system error saat salah input
	int nextInt() {
		int temp = 0;
		try {
			temp = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Input salah!");
		}
		sc.nextLine();
		return temp;
	}

	// method untuk membuat garis
	public void generateLine(int panjang) {
		for (int i = 0; i < panjang; i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	// method untuk cek kodeBarang
	public boolean checkID(String ID) {
		for (Barang ids : dataBarang) {
			if (ids instanceof Barang) {
				if (ids.getKodeBarang().equals(ID)) {
					return true;
				}
			}
		}
		return false;

	}

	public static void main(String[] args) {
		new Main();
	}

}
