import java.util.ArrayList;
import java.util.Scanner;

public class StokAlatDapur {

    static class AlatDapur {
        int id;
        String nama;
        int stok;
        
        public AlatDapur(int id, String nama, int stok) {
            this.id = id;
            this.nama = nama;
            this.stok = stok;
        }
        
        @Override
        public String toString() {
            return "ID: " + id + " | Nama Alat: " + nama + " | Stok: " + stok;
        }
    }
    
    static ArrayList<AlatDapur> stokList = new ArrayList<>();
    static int nextId = 1;
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;
        
        while (true) {
            System.out.println("\n=== Aplikasi Stok Alat Dapur (Dapur Cerdas) ===");
            System.out.println("1. Tambah Alat");
            System.out.println("2. Lihat Stok Alat");
            System.out.println("3. Ubah Data Alat");
            System.out.println("4. Hapus Alat");
            System.out.println("5. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = input.nextInt();
            input.nextLine();
            
            switch (pilihan) {
                case 1:
                    createAlat(input);
                    break;
                case 2:
                    readAlat();
                    break;
                case 3:
                    updateAlat(input);
                    break;
                case 4:
                    deleteAlat(input);
                    break;
                case 5:
                    System.out.println("Keluar dari aplikasi.");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }
    
    public static void createAlat(Scanner input) {
        System.out.println("\n--- Tambah Alat Dapur ---");
        System.out.print("Masukkan nama alat: ");
        String nama = input.nextLine();
        System.out.print("Masukkan jumlah stok: ");
        int stok = input.nextInt();
        input.nextLine();
        
        AlatDapur alatBaru = new AlatDapur(nextId, nama, stok);
        stokList.add(alatBaru);
        nextId++;
        System.out.println("Alat berhasil ditambahkan!");
    }
    
    public static void readAlat() {
        System.out.println("\n--- Daftar Stok Alat Dapur ---");
        if (stokList.isEmpty()) {
            System.out.println("Belum ada alat yang ditambahkan.");
        } else {
            for (AlatDapur alat : stokList) {
                System.out.println(alat);
            }
        }
    }
    
    public static void updateAlat(Scanner input) {
        System.out.println("\n--- Ubah Data Alat Dapur ---");
        System.out.print("Masukkan ID alat yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine(); // Mengonsumsi newline
        
        AlatDapur alatUpdate = null;
        for (AlatDapur alat : stokList) {
            if (alat.id == id) {
                alatUpdate = alat;
                break;
            }
        }
        
        if (alatUpdate == null) {
            System.out.println("Alat dengan ID tersebut tidak ditemukan.");
            return;
        }
        
        System.out.print("Masukkan nama baru: ");
        String namaBaru = input.nextLine();
        System.out.print("Masukkan jumlah stok baru: ");
        int stokBaru = input.nextInt();
        input.nextLine();
        
        alatUpdate.nama = namaBaru;
        alatUpdate.stok = stokBaru;
        System.out.println("Data alat berhasil diperbarui!");
    }
    
    public static void deleteAlat(Scanner input) {
        System.out.println("\n--- Hapus Alat Dapur ---");
        System.out.print("Masukkan ID alat yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine();
        
        AlatDapur alatHapus = null;
        for (AlatDapur alat : stokList) {
            if (alat.id == id) {
                alatHapus = alat;
                break;
            }
        }
        
        if (alatHapus == null) {
            System.out.println("Alat dengan ID tersebut tidak ditemukan.");
            return;
        }
        
        stokList.remove(alatHapus);
        System.out.println("Alat berhasil dihapus!");
    }
}